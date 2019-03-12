package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("db");
    }

   // @Test
   // void testGetCity()
    //{

        //Got to section - Adding integration tests

       // ArrayList<City> results = app.CityPopLargeToSmall();

       // int tempLowest = Integer.MAX_VALUE;

        // write test to see if results are in order
       // for (int i = 0; i <= results.size(); i++){

          //  if (results.get(i).Population < tempLowest){
             //   tempLowest = results.get(i).Population;
              //  continue;
          //  }else{
             //  System.out.println("Integration test failed!");
           // }
       // }
   // }

    @Test
    void testAddCity()
    {

        City c = new City();
        c.ID = 99999;
        c.Name = "Test_Name";
        c.District = "Test_District";
        c.Population = 1000000;
        c.CountryCode = "ABC";
        assertEquals(c.ID, 99999);
        assertEquals(c.Name, "Test_Name");
        assertEquals(c.District, "Test_District");


    }


}