package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        // Get city ordered by pop large to small
        ArrayList<City> cityList = a.CityPopLargeToSmall();
        // Display results
        for (int i = 0; i < cityList.size(); i++) {
            a.displaycity(cityList.get(i));
       }

        //Find Cities within a continent ordered by large to small
        ArrayList<City> cityListContinent = a.CityContLargetoSmall();

        //Display Results
        for (int i = 0; i < cityListContinent.size(); i++) {
            a.displayCityContLargeToSmall(cityListContinent.get(i));
        }

        //Find Cities within a region organised large to small
        ArrayList<City> cityListRegion = a.CityRegionLargetoSmall();

        //Display Results
        for (int i = 0; i < cityListRegion.size(); i++) {
            a.displayCityRegLargeToSmall(cityListRegion.get(i));
        }

        //Find Cities within a country organised large to small
        ArrayList<City> cityListCountry = a.CityCountryLargetoSmall();

        //Display Results
        for (int i = 0; i < cityListCountry.size(); i++){
            a.displayCityCountryLargeToSmall(cityListCountry.get(i));
        }

        //Find Cities within a district organised large to small
        ArrayList<City> cityListDistrict = a.CityDistrictLargetoSmall();

        //Display Results
        for (int i = 0; i < cityListDistrict.size();i++){
            a.displayCityDistrictLargeToSmall(cityListDistrict.get(i));
        }

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public ArrayList<City> CityPopLargeToSmall() {

        ArrayList<City> ResultList = new ArrayList<City>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.name, country.name, city.District, city.Population, country.code, city.CountryCode "
                            + "FROM country, city "
                            + "WHERE country.code = city.CountryCode "
                            + "ORDER BY city.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
            // Check one is returned
            while (rset.next()) {

                //Define database attributes in Java
                City city = new City();

                city.ID = rset.getInt("ID");
                city.Name = rset.getString("Name");
                city.Population = rset.getInt("Population");
                city.CountryCode = rset.getString("CountryCode");
                city.District = rset.getString("District");


                Country myCountry = new Country();


                myCountry.code = rset.getString("Code");

                myCountry.countryName = rset.getString("Name");


                city.country = myCountry;
                ResultList.add(city);
            }

            return ResultList;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    public ArrayList<City> CityContLargetoSmall() {

        ArrayList<City> ResultList = new ArrayList<City>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.name, country.name, city.District, city.Population, country.code, city.CountryCode "
                            + "FROM country, city "
                            + "WHERE country.code = city.CountryCode AND country.Continent LIKE 'Asia'"
                            + "ORDER BY city.Population DESC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                //Define database attributes in Java
                City cityCont = new City();

                cityCont.ID = rset.getInt("ID");
                cityCont.Name = rset.getString("Name");
                cityCont.Population = rset.getInt("Population");
                cityCont.CountryCode = rset.getString("CountryCode");
                cityCont.District = rset.getString("District");


                Country myCountry = new Country();


                myCountry.code = rset.getString("Code");

                myCountry.countryName = rset.getString("Name");


                cityCont.country = myCountry;
                ResultList.add(cityCont);
            }
            return ResultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;

        }
    }

    public ArrayList<City> CityRegionLargetoSmall() {

        ArrayList<City> ResultList = new ArrayList<City>();

        try {

            //Create SQL Statement
            Statement stmt = con.createStatement();
            //Create String for SQL statement
            String strSelect =
                    "SELECT city.ID, city.name, country.name, city.District, city.Population, country.code, city.CountryCode "
                            + "FROM country, city "
                            + "WHERE country.code = city.CountryCode AND country.Region LIKE 'North America'"
                            + "ORDER BY city.Population DESC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);


            while (rset.next()) {
                //Define database attributes in Java
                City cityReg = new City();

                cityReg.ID = rset.getInt("ID");
                cityReg.Name = rset.getString("Name");
                cityReg.Population = rset.getInt("Population");
                cityReg.CountryCode = rset.getString("CountryCode");
                cityReg.District = rset.getString("District");


                Country myCountry = new Country();


                myCountry.code = rset.getString("Code");

                myCountry.countryName = rset.getString("Name");


                cityReg.country = myCountry;
                ResultList.add(cityReg);
            }


            return ResultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }

    }

    public ArrayList<City> CityCountryLargetoSmall() {

        ArrayList<City> ResultList = new ArrayList<City>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.name, country.name, city.District, city.Population, country.code, city.CountryCode "
                            + "FROM country, city "
                            + "WHERE country.code = city.CountryCode AND country.Name LIKE 'Canada'"
                            + "ORDER BY city.Population DESC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                //Define database attributes in Java
                City cityCountry = new City();

                cityCountry.ID = rset.getInt("ID");
                cityCountry.Name = rset.getString("Name");
                cityCountry.Population = rset.getInt("Population");
                cityCountry.CountryCode = rset.getString("CountryCode");
                cityCountry.District = rset.getString("District");


                Country myCountry = new Country();


                myCountry.code = rset.getString("Code");

                myCountry.countryName = rset.getString("Name");


                cityCountry.country = myCountry;
                ResultList.add(cityCountry);
            }
            return ResultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;

        }
    }

    public ArrayList<City> CityDistrictLargetoSmall() {

        ArrayList<City> ResultList = new ArrayList<City>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.name, city.District, city.Population, city.CountryCode "
                            + "FROM city "
                            + "WHERE city.District LIKE 'Noord-Brabant'"
                            + "ORDER BY city.Population DESC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                //Define database attributes in Java
                City cityDistrict = new City();

                cityDistrict.ID = rset.getInt("ID");
                cityDistrict.Name = rset.getString("Name");
                cityDistrict.Population = rset.getInt("Population");
                cityDistrict.CountryCode = rset.getString("CountryCode");
                cityDistrict.District = rset.getString("District");



                ResultList.add(cityDistrict);
            }
            return ResultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;

        }
    }


    public void displaycity(City city) {
        if (city != null) {
            System.out.println(

                    "City Name: " + city.Name + " |"
                            + "Country: " + city.country.countryName + " |"
                            + "City District: " + city.District + " |"
                            + "Population: " + city.Population + "|");

        } else {
            System.out.println("City is Null");
        }


    }

    public void displayCityContLargeToSmall(City CityCont) {

        if (CityCont != null) {
            System.out.println(
                    "City Name: " + CityCont.Name + " |"
                            + "Country: " + CityCont.country.countryName + " |"
                            + "City District: " + CityCont.District + " |"
                            + "Population: " + CityCont.Population + "|");


        }
    }

    public void displayCityRegLargeToSmall(City CityReg) {

        if (CityReg != null) {
            System.out.println(
                    "City Name: " + CityReg.Name + " |"
                            + "Country: " + CityReg.country.countryName + " |"
                            + "City District: " + CityReg.District + " |"
                            + "Population: " + CityReg.Population + "|");


        }
    }

    public void displayCityCountryLargeToSmall(City CityReg) {

        if (CityReg != null) {
            System.out.println(
                    "City Name: " + CityReg.Name + " |"
                            + "Country: " + CityReg.country.countryName + " |"
                            + "City District: " + CityReg.District + " |"
                            + "Population: " + CityReg.Population + "|");


        }
    }

    public void displayCityDistrictLargeToSmall(City CityDistrict) {

        if (CityDistrict != null) {
            System.out.println(
                    "City Name: " + CityDistrict.Name + " |"
                            + "City District: " + CityDistrict.District + " |"
                            + "Population: " + CityDistrict.Population + "|");


        }
    }
}