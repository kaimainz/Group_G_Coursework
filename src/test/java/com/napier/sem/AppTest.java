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


    //Unit tests for displayCityContLargetoSmall method

    @Test
    void displayCityContLargetoSmallNull() {

        app.displayCityContLargeToSmall(null);

    }

    @Test
    void displayCityContLargetoSmallEmpty(){

        ArrayList<City> cityListCont = new ArrayList<City>();

        for (int i = 0; i < cityListCont.size(); i++){

            app.displayCityContLargeToSmall(cityListCont.get(i));
        }
    }

    @Test
    void displayCityContLargetoSmallContainsNull(){
        ArrayList<City> cityListCont = new ArrayList<City>();
        cityListCont.add(null);

        for (int i = 0; i < cityListCont.size();i++){

            app.displayCityContLargeToSmall(cityListCont.get(i));

        }
    }

    @Test
    void displayCityContLargetoSmall(){
        ArrayList<City> cityListCont = new ArrayList<City>();
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
        cityListCont.add(c);

        for (int i = 0; i < cityListCont.size(); i++) {

            app.displayCityContLargeToSmall(cityListCont.get(i));


        }

    }

    //Unit tests for displayCityRegLargeToSmall method

    @Test
    void displayCityRegLargeToSmallNull() {
        app.displayCityRegLargeToSmall(null);

    }

    @Test
    void displayCityRegLargeToSmallEmpty(){

        ArrayList<City> cityListReg = new ArrayList<City>();

        for (int i = 0; i < cityListReg.size(); i++){

            app.displayCityRegLargeToSmall(cityListReg.get(i));
        }
    }

    @Test
    void displayCityRegLargeToSmallContainsNull(){

        ArrayList<City> cityListReg = new ArrayList<City>();
        cityListReg.add(null);

        for (int i = 0; i < cityListReg.size();i++){

            app.displayCityRegLargeToSmall(cityListReg.get(i));

        }
    }

    @Test
    void displayCityRegLargeToSmall(){
        ArrayList<City> cityListReg = new ArrayList<City>();
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
        cityListReg.add(c);

        for (int i = 0; i < cityListReg.size(); i++) {

            app.displayCityRegLargeToSmall(cityListReg.get(i));


        }


    }

    //unit tests for displayCityContinentLargetoSmall

    @Test
    void displayCityCountryLargeToSmallNull(){

        app.displayCityCountryLargeToSmall(null);
    }

    @Test
    void displayCityCountryLargeToSmallEmpty(){

        ArrayList<City> cityListCountry = new ArrayList<City>();

        for (int i = 0; i < cityListCountry.size(); i++){

            app.displayCityCountryLargeToSmall(cityListCountry.get(i));
        }
    }

    @Test
    void displayCityCountryLargeToSmallContainsNull(){
        ArrayList<City> cityListCountry = new ArrayList<City>();
        cityListCountry.add(null);

        for (int i = 0; i < cityListCountry.size();i++){

            app.displayCityCountryLargeToSmall(cityListCountry.get(i));

        }

    }

    @Test
    void displayCityCountryLargeToSmall(){
        ArrayList<City> cityListCountry = new ArrayList<City>();
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
        cityListCountry.add(c);

        for (int i = 0; i < cityListCountry.size(); i++) {

            app.displayCityCountryLargeToSmall(cityListCountry.get(i));


        }

    }

    //Unit tests for displayCityDistrictLargeToSmall

    @Test
    void displayCityDistrictLargeToSmallNull(){

        app.displayCityDistrictLargeToSmall(null);

    }

    @Test void displayCityDistrictLargeToSmallEmpty(){

        ArrayList<City> cityListDistrict = new ArrayList<City>();

        for (int i = 0; i < cityListDistrict.size(); i++){

            app.displayCityDistrictLargeToSmall(cityListDistrict.get(i));
        }
    }

    @Test
    void displayCityDistrictLargeToSmallContainsEmpty(){

        ArrayList<City> cityListDistrict = new ArrayList<City>();
        cityListDistrict.add(null);

        for (int i = 0; i < cityListDistrict.size();i++){

            app.displayCityDistrictLargeToSmall(cityListDistrict.get(i));

        }

    }

    @Test
    void displayCityDistrictLargeToSmall(){
        ArrayList<City> cityListDistrict = new ArrayList<City>();
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
        cityListDistrict.add(c);

        for (int i = 0; i < cityListDistrict.size(); i++) {

            app.displayCityCountryLargeToSmall(cityListDistrict.get(i));


        }



    }

    }


