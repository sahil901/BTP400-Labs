package btp400.lab4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the DatabaseHandler class
 */
class DatabaseHandlerTest {
    static DatabaseHandler dbHandler;

    @BeforeEach
    void setUp() {
        dbHandler = new DatabaseHandler();
    }

    @Test
    void db() {
        //not expected to return a null value
        try {
			assertNotEquals(null, dbHandler.db());
		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
		}

    }
    
    
    /*
     * TEST NUMBER 2: 
     * Since the db() method only returns a connection, there's no way of testing whether the tables are 
     * actually created and the data inserted or not. If I seperated the connection and the tables then I 
     * would be able to test the creation of the table(s)
     */
    
 
}