/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseTables;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ali
 */
public class buildingManagerIT {
    
    public buildingManagerIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of displayAllBuildings method, of class buildingManager.
     */
    @Test
    public void testDisplayAllBuildings() throws Exception {
        System.out.println("displayAllBuildings");
        building expResult = null;
        building result = buildingManager.displayAllBuildings();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of display method, of class buildingManager.
     */
    @Test
    public void testDisplay() throws Exception {
        System.out.println("display");
        String dCampus = "";
        building expResult = null;
        building result = buildingManager.display(dCampus);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
}
