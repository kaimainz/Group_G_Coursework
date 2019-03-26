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
        app.connect("localhost:33060");
    }



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

    @Test
    void testGetCity()

    {
        City c = app.getCity("1");
        assertEquals(c.ID, 1);
        assertEquals(c.Name,"Kabul");



    }

    @Test
    void testCityPopLargeToSmall() {

        ArrayList<City> results = app.CityPopLargeToSmall();
        System.out.println("Number of results: " +  results.size());
    }

    @Test
    void testCityContLargeToSmall(){

        ArrayList<City> results = app.CityContLargetoSmall();
        System.out.println("Number of results: " +  results.size());

    }

    @Test
    void testCityRegionLargetoSmall(){

        ArrayList<City> results = app.CityRegionLargetoSmall();
        System.out.println("Number of results: " +  results.size());

    }

    @Test
    void testCityCountryLargetoSmall(){

        ArrayList<City> results = app.CityCountryLargetoSmall();
        System.out.println("Number of results: " +  results.size());

    }

    @Test
    void testCityDistrictLargetoSmall(){

        ArrayList<City> results = app.CityDistrictLargetoSmall();
        System.out.println("Number of results: " +  results.size());

    }

}