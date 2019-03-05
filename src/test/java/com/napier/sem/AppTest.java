package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void testCityPoplowest()
    {

        ArrayList<City> results = app.CityPopLargeToSmall();

        if (results == null)
        {
            fail("no results");
            return;
        }

        int tempLowest = Integer.MAX_VALUE;

        // write test to see if results are in order
        for (int i = 0; i <= results.size(); i++)
        {
            if (results.get(i).Population < tempLowest)
            {
                tempLowest = results.get(i).Population;
                continue;
            }
            else
            {
               // TEST FAILEd
                fail("Wrong order");
            }
        }
    }

    @Test
    void testCityPopnull()
    {
        app.CityPopLargeToSmall();
    }
}