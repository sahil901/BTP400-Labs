package btp400.lab3.q2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
/**
 *
 * @author SAHIL 
 */
public class ProducerTester {
    
    private static final String workingFolder = System.getProperty("user.dir"); //the path to the current working folder
    
    public static void main(String[] args) throws InterruptedException {
            executeNonSynchronisedProducerAndConsumer();
            executeSynchronisedProducerAndConsumer();
        
    }
    
    /**
     * Executes the synchronised producer and consumer program
     */
    public static void executeSynchronisedProducerAndConsumer() {
        
        SynchronisedProducerAndConsumer spc = new SynchronisedProducerAndConsumer();
        
        Runnable producer = () -> {
            spc.produce();
        }; 
        
        Runnable consumer = () -> {
            try {
                File file = new File(workingFolder + "\\" + Thread.currentThread().getName() + ".txt");
                    spc.consume(file);
                    return;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
        
        ExecutorService producerPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); //create thread pool
       
        for(int i = 0; i < 5; i++)
            producerPool.submit(producer); //add 5 producers to thread pool
        
         for(int i = 0; i < 3; i++)
            producerPool.submit(consumer); //add 3 consumers to thread pool
        
        
    }

    public static void executeNonSynchronisedProducerAndConsumer() { 
       
        NonSynchronisedProducerAndConsumer nspc = new NonSynchronisedProducerAndConsumer();
        
        Runnable producer = () -> {
            nspc.produce();
        }; 
        
        Runnable consumer = () -> {
            try {
                while(true)
                    nspc.consume();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
        
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); //create thread pool
       
        for(int i = 0; i < 5; i++)
            threadPool.submit(producer); //add 5 producers to thread pool
        
         for(int i = 0; i < 3; i++)
            threadPool.submit(consumer); //add 3 consumers to thread pool
    }

}

class NonSynchronisedProducerAndConsumer {
    
    //locks and conditions to synchronise the threads
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    
    private static final String workingFolder = System.getProperty("user.dir"); //the path to the current working folder
    
    private static final int BUFFER_CAPACITY = 100;
    private static final Queue<Reading> buffer = new ArrayDeque<Reading>(BUFFER_CAPACITY);
    
    
    /**
     * produces readeING and adds them to the buffer
     */
    public void produce() {
            lock.lock();
            try {
                int productionsLeft = 2000; //the maximum number of products each producer can produce
                while(productionsLeft > 0) {
                    
                    while(buffer.size() == BUFFER_CAPACITY) //while the buffer is full, keep waiting for the consumers to consume a reading
                        notFull.await();
                    
                    buffer.add(Reading.createObject());
                    productionsLeft = productionsLeft - 1; //decrement the number of productions left
                    notEmpty.signal();  //notify the consumers that the buffer is no longer empty
                    
                }
                lock.unlock();
            }catch(InterruptedException ex) {
               ex.printStackTrace(); 
            }  
    }
 
    
    /**
     * Consumes readings from the buffer
     */
    public void consume() throws IOException {
                
        try {
                    
            File file = new File(workingFolder + "\\" + Thread.currentThread().getName() + ".txt");
            FileWriter writer;
            if(file.exists());
            else
            file.createNewFile();
            
            StringBuilder readings = new StringBuilder(); //string builder for appending the readings before they get written to the file
                    
            while(true) {
                lock.lock();
                
                while(buffer.isEmpty())//while the buffer is empty keep waiting until it is loaded, wait for 1 minute max
                    notEmpty.await();
                
                writer = new FileWriter(file);
                
                Reading reading = buffer.poll();
                notFull.signal();//notify the producers after consuming a reading from the buffer
                        
                if(reading != null) {//if a reading object is not null, append it to the readers StringBuilder and append the builder to the file
                    readings.append(reading.toString() + "\n");
                    writer.append(readings.toString());
                } 
                
                else {//if the buffer is empty, start a timer and wait at most 1 minute for it to be fed a reading. If not then terminate the thread
                
                    long start = System.currentTimeMillis();
                    long end = start + 60 * 1000;

                    while (System.currentTimeMillis() < end && buffer.peek() == null);
                
                    if(System.currentTimeMillis() >= end) {
                        writer.append(readings.toString()); //and append the builder to the file
                        writer.close();
                        return;
                    }
                    
                }
                
                lock.unlock();//release the lock after each iteration to give chance to other consumers
                writer.close();
            }
                    
        }catch(InterruptedException ex) {
            ex.printStackTrace();
        }
             
                                
    }
    

}

class SynchronisedProducerAndConsumer {
   
    private static final int BUFFER_CAPACITY = 100;
    private static final Queue<Reading> buffer = new ArrayBlockingQueue<Reading>(BUFFER_CAPACITY);
    ReentrantLock lock = new ReentrantLock();
    
    /**
     * produces readings and adds them to the buffer
     */
    public void produce() {
        int productionsLeft = 2000; //the maximum number of products each producer can produce
        while(productionsLeft > 0) {
            if(buffer.offer(Reading.createObject()))
                productionsLeft = productionsLeft - 1; //decrement the number of productions left once the queue is offered a reading
        
        }
    }
    
    /**
     * Consumes readings from the buffer
     */
    public void consume(File file) throws IOException, InterruptedException {
        
        FileWriter writer;
        if(file.exists());
        
        else
            file.createNewFile();
                    
                    
        StringBuilder readings = new StringBuilder();
                    
        while(true) {
            lock.lock();
            
            writer = new FileWriter(file);
       if(buffer.isEmpty())
              Thread.currentThread().wait(60000);//if the buffer is empty wait for at most 1 minute
            Reading reading = buffer.poll();
            if(reading != null) {//if a reading object is not null, append it to the readers StringBuilder 
                readings.append(reading.toString() + "\n");
            } 
            
            else {//if the buffer is empty, start a timer and wait at most 1 minute for it to be fed a reading. If not then terminate the thread
                
                long start = System.currentTimeMillis();
                long end = start + 60 * 1000;

                while (System.currentTimeMillis() < end && buffer.peek() == null);
                
                if(System.currentTimeMillis() >= end) {
                    writer.append(readings.toString()); //and append the builder to the file
                    System.out.println(buffer.size());
                    System.out.println("Time elapsed");
                    writer.close();
                    return;
                }
                    
            }
            
            writer.append(readings.toString()); //and append the builder to the file
            writer.close();
            lock.unlock();//release the lock after each iteration to give chance to other consumers
                        
        } 
                                
    }
}



                
