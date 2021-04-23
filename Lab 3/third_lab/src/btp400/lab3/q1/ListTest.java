/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btp400.lab3.q1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.beans.Observable;
import btp400.lab3.q2.ProducerTester;

/**
 *
 * @author SAHIL PATEL
 */
public class ListTest extends Application {
    
    private VBox topLeftVBox;
    private Label lbListImplementation;
    private ChoiceBox<String> choiceBoxListImplementation;
    private Label lbListSize;
    private TextField tfListSize;
    private VBox topRightVBox;
    private Label lbActions;
    private Button btRunTest;
    private Label lbTimeElapsed;
    private ToggleGroup tgActions;
    private RadioButton radioBtLoadList;
    private RadioButton radioBtSortList;
    private RadioButton radioBtSequentialAccess;
    private RadioButton radioBtRandomAccess;
    private Label lbAccessCount;
    private TextField tfRandomAccessCount;
    private Label lbSecondsElapsed;
    private List<Long> arrayList = new ArrayList<Long>();
    private List<Long> linkedList = new LinkedList<Long>();
    private List<Long> vectorList = new Vector<Long>(0);
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        // in order to run the actual ProducerTester class when doing the command line demo
        ProducerTester cl = new ProducerTester();
  
        topLeftVBox = new VBox();
        topLeftVBox.setSpacing(10.0);
        topLeftVBox.setLayoutX(31.0);
        topLeftVBox.setLayoutY(25.0);
        
        lbListImplementation = new Label("List Implementation");
        lbListImplementation.setFont(new Font("System Bold", 14.0));
        
        choiceBoxListImplementation = new ChoiceBox<String>();
        choiceBoxListImplementation.setPrefWidth(150.0);
        choiceBoxListImplementation.getItems().addAll("Array List", "Linked List", "Vector");
        choiceBoxListImplementation.getSelectionModel().select(0);//select the array list implementation by default
        
        lbListSize = new Label("List Size");
        lbListSize.setFont(new Font("System Bold", 14.0));
        
        tfListSize = new TextField();
        tfListSize.setText("0");
        
        topLeftVBox.getChildren().addAll(lbListImplementation, choiceBoxListImplementation, lbListSize, tfListSize);
        
        topRightVBox = new VBox();
        topRightVBox.setLayoutX(307.0);
        topRightVBox.setLayoutY(14.0);
        topRightVBox.setSpacing(10.0);
        
        lbActions = new Label("ACTIONS");
        lbActions.setFont(new Font("System Bold", 31.0));
        
        tgActions = new ToggleGroup();
        
        radioBtLoadList = new RadioButton("Load List");
        radioBtLoadList.setMnemonicParsing(false);
        radioBtLoadList.setToggleGroup(tgActions);
        
        radioBtSortList = new RadioButton("Sort List");
        radioBtSortList.setMnemonicParsing(false);
        radioBtSortList.setToggleGroup(tgActions);
        
        radioBtSequentialAccess = new RadioButton("Sequential Access");
        radioBtSequentialAccess.setMnemonicParsing(false);
        radioBtSequentialAccess.setToggleGroup(tgActions);
        
        radioBtRandomAccess = new RadioButton("Random Access");
        radioBtRandomAccess.setMnemonicParsing(false);
        radioBtRandomAccess.setToggleGroup(tgActions);
        
        //select the sort list option by default
        tgActions.selectToggle(radioBtLoadList);
        
        lbAccessCount = new Label(" Random Access Count");
        lbAccessCount.setFont(new Font("System Bold", 14.0));
        
        tfRandomAccessCount = new TextField();
        tfRandomAccessCount.setPrefHeight(25.0);
        tfRandomAccessCount.setPrefWidth(80.0);
        tfRandomAccessCount.setText("0");
        
        topRightVBox.getChildren().addAll(lbActions, radioBtLoadList, radioBtSortList,
                radioBtSequentialAccess, radioBtRandomAccess, lbAccessCount, tfRandomAccessCount);
        
        btRunTest = new Button("Run Test");
        btRunTest.setLayoutX(338.0);
        btRunTest.setLayoutY(245.0);
        btRunTest.setPrefHeight(46.0);
        btRunTest.setPrefWidth(73.0);
        btRunTest.setMnemonicParsing(false);
        btRunTest.setFont(new Font("System Bold", 12.0));
        btRunTest.setOnAction((event)->runTest());
        
        lbTimeElapsed = new Label("TIME TAKEN FOR TEST RUN");
        lbTimeElapsed.setLayoutX(23.0);
        lbTimeElapsed.setLayoutY(213.0);
        lbTimeElapsed.setFont(new Font("System Bold", 20.0));
        
        lbSecondsElapsed = new Label();
        lbSecondsElapsed.setLayoutX(31.0);
        lbSecondsElapsed.setLayoutY(243.0);
        lbSecondsElapsed.setFont(new Font("System Bold", 25));
        lbSecondsElapsed.setTextFill(Color.LIME);
        
        AnchorPane parent = new AnchorPane();
        parent.setPrefHeight(317);
        parent.setPrefWidth(486);
        parent.getChildren().addAll(topLeftVBox, topRightVBox, btRunTest, lbTimeElapsed, lbSecondsElapsed);
        
        //Validate user input
        disableOrEnableRunTestButton();
        restrictUserInput();
        
        
        Scene scene = new Scene(parent);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Disables or enables the Run Test button if the user hasn't entered a
     * value for the random access count
     */
    private void disableOrEnableRunTestButton() {
        tfRandomAccessCount.textProperty().addListener((Observable ov)->{
            if(tfRandomAccessCount.getText().equals("") && tgActions.getSelectedToggle().equals(radioBtRandomAccess))
                btRunTest.setDisable(true);
            else btRunTest.setDisable(false);
        });
        
        radioBtRandomAccess.selectedProperty().addListener((Observable ov)->{
            if(tfRandomAccessCount.getText().equals("") && tgActions.getSelectedToggle().equals(radioBtRandomAccess))
                btRunTest.setDisable(true);
            else btRunTest.setDisable(false);
        });
        
        radioBtLoadList.selectedProperty().addListener((Observable ov)->{
            if(tgActions.getSelectedToggle().equals(radioBtLoadList))
                btRunTest.setDisable(false);
        });
        
        radioBtSequentialAccess.selectedProperty().addListener((Observable ov)->{
            if(tgActions.getSelectedToggle().equals(radioBtSequentialAccess))
                btRunTest.setDisable(false);
        });
        
        radioBtSortList.selectedProperty().addListener((Observable ov)->{
            if(tgActions.getSelectedToggle().equals(radioBtSortList))
                btRunTest.setDisable(false);
        });
       
    }
    
    /**
     * Restrict the user's input in the text boxes to numbers
     */
    private void restrictUserInput() {
        tfListSize.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d{0,11}([\\.]\\d{0,2})?")) {
                tfListSize.setText(newValue);
            } else {
                tfListSize.setText(oldValue);
            }
        });
        
        tfRandomAccessCount.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d{0,11}([\\.]\\d{0,2})?")) {
                tfRandomAccessCount.setText(newValue);
            } else {
                tfRandomAccessCount.setText(oldValue);
            }
        });
    }
    
    
    
    public void runTest() {
        
        Toggle selectedToggle = tgActions.getSelectedToggle();
        String selectedAction;
        
        if(selectedToggle.equals(radioBtSortList)) {
            selectedAction = "Sort List";
        }
        else if(selectedToggle.equals(radioBtSequentialAccess)) {
            selectedAction = "Sequential Access";
        }
        else if(selectedToggle.equals(radioBtLoadList)) {
            selectedAction = "Load List";
        } 
        else
            selectedAction = "Random Access";
        
        String selectedImplementation = choiceBoxListImplementation.getValue().toString();
        int listSize = Integer.parseInt(tfListSize.getText());
        int randomAccessCount = Integer.parseInt(tfListSize.getText());
        
        switch(selectedImplementation) {
            case "Array List": switch(selectedAction) {
                case "Load List": loadList();
                break;
                
                case "Sort List": sortList(arrayList, listSize);
                break;
                
                case "Sequential Access": sequentialAccess(arrayList, listSize);
                break;
                
                case "Random Access": randomAccess(arrayList, listSize, randomAccessCount);
                break;
                
            }
            break;
            
            case "Linked List": switch(selectedAction) {
                case "Load List": loadList();
                break;
                
                case "Sort List": sortList(linkedList, listSize);
                break;
                
                case "Sequential Access": sequentialAccess(linkedList, listSize);
                break;
                
                case "Random Access": randomAccess(linkedList, listSize, randomAccessCount);
                break;
                
            }
            break;
            
            case "Vector": switch(selectedAction) {
                case "Load List": loadList();
                break;
                
                case "Sort List": sortList(vectorList, listSize);
                break;
                
                case "Sequential Access": sequentialAccess(vectorList, listSize);
                break;
                
                case "Random Access": randomAccess(vectorList, listSize, randomAccessCount);
                break;
                
            }
            break;
                        
        }
        
        
        
    }
    
    /**
     * Loads a list
     */
    private void loadList() {
        String selectedImplementation = choiceBoxListImplementation.getValue().toString();
        int listSize = Integer.parseInt(tfListSize.getText());
        Stopwatch sw = null;
        
        switch(selectedImplementation) {
            case "Array List": {
                Random rand = new Random();
                sw = new Stopwatch();
                for(int i = 0; i < listSize; i++) {
                    arrayList.add(rand.nextLong());
                }
            }
            break;
            
            case "Linked List": {
                Random rand = new Random();
                sw = new Stopwatch();
                for(int i = 0; i < listSize; i++) {
                    linkedList.add(rand.nextLong());
                }
            }
            break;
            
            case "Vector": {
                Random rand = new Random();
                sw = new Stopwatch();
                for(int i = 0; i < listSize; i++) {
                    vectorList.add(rand.nextLong());
                }
            }
            break;
        }
        
        lbSecondsElapsed.setText(String.format("%5.3f", sw.elapsedTime()) + " Seconds");
    }
        
    /**
     * Sorts a list
     * @param list
     * @param size 
     */
    public void sortList(List<Long> list, int size) {
        System.out.println("The List is Sorting.");
        //start the stop watch
        Stopwatch sw = new Stopwatch();
        
        Collections.sort(list, (Long num1, Long num2) -> num1.compareTo(num2));
        
        //display elapsed time
        lbSecondsElapsed.setText(String.format("%5.3f", sw.elapsedTime()) + " Seconds");
    }
    
    /**
     * Access a list randomly randomAccessCount times
     * @param list
     * @param size
     * @param accessCount 
     */
    public void randomAccess(List<Long> list, int size, int randomAccessCount) {
        
        //start the stop watch
        Stopwatch sw = new Stopwatch();
        
        for(int i = 0; i < randomAccessCount; i++) {
            Random rand = new Random();
            list.get(rand.nextInt(size));
        }
        
        //display elapsed time
        lbSecondsElapsed.setText(String.format("%5.5f", sw.elapsedTime()) + " Seconds");
        
    }
    
    /**
     * Access a list of sequentially
     * @param list
     * @param size 
     */
    public void sequentialAccess(List<Long> list, int size) {
        
        //start the stop watch
        Stopwatch sw = new Stopwatch();
        
        for(int i = 0; i < size; i++) {
            list.get(i);
        }
        
        //display elapsed time
        lbSecondsElapsed.setText(String.format("%5.5f", sw.elapsedTime()) + " Seconds");
    }
        
    
}

/**
 * Models a stopwath
 *
 */
class Stopwatch { 

    private final long start;

    /**
     * Initializes a new stopwatch.
     */
    public Stopwatch() {
        start = System.currentTimeMillis();
    } 


    /**
     * Returns the elapsed CPU time (in seconds) since the stopwatch was created.
     *
     * @return elapsed CPU time (in seconds) since the stopwatch was created
     */
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
    
}

