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
    void displayCityNull() {
        app.displaycity(null);
    }

    @Test
    void displayCityEmpty() {

        ArrayList<City> cityList = new ArrayList<City>();

        for (int i = 0; i < cityList.size(); i++) {

            app.displaycity(cityList.get(i));
        }

    }

    @Test
    void displayCityContainsNull() {
        ArrayList<City> cityList = new ArrayList<City>();
        cityList.add(null);

        for (int i = 0; i < cityList.size(); i++) {

            app.displaycity(cityList.get(i));
        }

    }

    @Test
    void displayCity() {
        ArrayList<City> cityList = new ArrayList<City>();
        City c = new City();
        Country con = new Country();
        c.ID = 1;
        c.CountryCode = "ABC";
        c.Name = "Name";
        c.District = "District";
        c.Population = 999;
        con.code = "ABCD";
        con.countryName = "Country";
        con.Continent = "Africa";
        con.Region = "West";
        con.SurfaceArea = 3.6f;

        c.country = con;
        cityList.add(c);

        for (int i = 0; i < cityList.size(); i++) {

            app.displaycity(cityList.get(i));


        }
    }
}
