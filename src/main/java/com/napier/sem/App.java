package com.napier.sem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;

@SpringBootApplication
@RestController

public class App {
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        if (args.length < 1)
        {
            a.connect("localhost:33060");
         //a.connect("35.246.82.142:80");
        }
        else
        {
            a.connect(args[0]);
        }
        SpringApplication.run(App.class, args);


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

        //Find countries with population organised large to small
        ArrayList<Country> countryPopLargeToSmall = a.countryPopLargeToSmall();

        //Display results
        for (int i = 0; i < countryPopLargeToSmall.size(); i++){
            a.displayCountryPopLargeToSmall(countryPopLargeToSmall.get(i));
        }

        //Find countries with population in Europe organised large to small
        ArrayList<Country> countryContLargeToSmall = a.countryContPopLargeToSmall();

        //Display results
        for (int i = 0; i < countryContLargeToSmall.size(); i++){
            a.displayCountryContPopLargeToSmall(countryContLargeToSmall.get(i));
        }
        //Find countries with population in the Caribbean organised large to small
        ArrayList<Country> countryRegLargeToSmall = a.countryRegPopLargeToSmall();

        //Display results
        for (int i = 0; i < countryRegLargeToSmall.size(); i++){
            a.displayCountryRegPopLargeToSmall(countryRegLargeToSmall.get(i));
        }

    }

    /**
     * Connection to MySQL database.
     */
    private static Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public static void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public static void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    @RequestMapping("CityPopLargeToSmall")
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

    @RequestMapping("CityContLargetoSmall")
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
    @RequestMapping("CityRegionLargetoSmall")
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
    @RequestMapping("CityCountryLargetoSmall")
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

    @RequestMapping("CityDistrictLargetoSmall")
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


        } else {
            System.out.println("City matched with continent is Null");
        }
    }

    public void displayCityRegLargeToSmall(City CityReg) {

        if (CityReg != null) {
            System.out.println(
                    "City Name: " + CityReg.Name + " |"
                            + "Country: " + CityReg.country.countryName + " |"
                            + "City District: " + CityReg.District + " |"
                            + "Population: " + CityReg.Population + "|");


        }else {
            System.out.println("City matched with region is Null");
        }
    }

    public void displayCityCountryLargeToSmall(City CityCountry) {

        if (CityCountry != null) {
            System.out.println(
                    "City Name: " + CityCountry.Name + " |"
                            + "Country: " + CityCountry.country.countryName + " |"
                            + "City District: " + CityCountry.District + " |"
                            + "Population: " + CityCountry.Population + "|");


        }else{
            System.out.println("City matched with country is Null");
        }
    }

    public void displayCityDistrictLargeToSmall(City CityDistrict) {

        if (CityDistrict != null) {
            System.out.println(
                    "City Name: " + CityDistrict.Name + " |"
                            + "City District: " + CityDistrict.District + " |"
                            + "Population: " + CityDistrict.Population + "|");


        }else {
            System.out.println("City matched with district is Null");
        }
    }

    public void addCity(City c){

        try {
            Statement stmt = con.createStatement();
            String strUpdate =
                    "INSERT INTO city (city.ID, city.name, city.District, city.Population, city.CountryCode)"
                    +"VALUES  (" + c.ID + ", '" + c.Name + "', '" + c.District + "', '" + c.Population +"', '" + c.CountryCode + "')";

            stmt.execute(strUpdate);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to add city");
        }
    }
    /**
     * Get a single employee record.
     * @param cityID cityID of the city record to get.
     * @return return city
     */
    @RequestMapping("city")
    public City getCity(@RequestParam(value = "City") String cityID){
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.Name, city.District, city.Population "
                            + "FROM city "
                            + "WHERE city.ID = " + cityID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city if valid.
            // Check one is returned
            if (rset.next())
            {
                City c = new City();
                c.ID = rset.getInt("ID");
                c.Name = rset.getString("Name");
                c.District = rset.getString("District");
                c.Population = rset.getInt("Population");
                return c;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }


    }

    @RequestMapping("countryPopLargeToSmall")
    public ArrayList<Country> countryPopLargeToSmall() {

        ArrayList<Country> ResultList = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.continent, country.region, country.Population, country.capital "
                            + "FROM country "
                            + "ORDER BY country.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
            // Check one is returned
            while (rset.next()) {

                //Define database attributes in Java
                Country myCountry = new Country();

                myCountry.code = rset.getString("Code");
                myCountry.countryName = rset.getString("Name");
                myCountry.Continent = rset.getString("continent");
                myCountry.Region = rset.getString("region");
                myCountry.population = rset.getInt("Population");
                myCountry.Capital = rset.getString("capital");










                ResultList.add(myCountry);
            }

            return ResultList;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    @RequestMapping("countryContPopLargeToSmall")
    public ArrayList<Country> countryContPopLargeToSmall() {

        ArrayList<Country> ResultList = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.continent, country.region, country.Population, country.capital "
                            + "FROM country "
                            + "WHERE country.continent LIKE 'Europe' "
                            + "ORDER BY country.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
            // Check one is returned
            while (rset.next()) {

                //Define database attributes in Java
                Country myCountry = new Country();

                myCountry.code = rset.getString("Code");
                myCountry.countryName = rset.getString("Name");
                myCountry.Continent = rset.getString("continent");
                myCountry.Region = rset.getString("region");
                myCountry.population = rset.getInt("Population");
                myCountry.Capital = rset.getString("capital");










                ResultList.add(myCountry);
            }

            return ResultList;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get European country details");
            return null;
        }
    }

    @RequestMapping("countryRegPopLargeToSmall")
    public ArrayList<Country> countryRegPopLargeToSmall() {

        ArrayList<Country> ResultList = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.continent, country.region, country.Population, country.capital "
                            + "FROM country "
                            + "WHERE country.region LIKE 'Caribbean' "
                            + "ORDER BY country.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
            // Check one is returned
            while (rset.next()) {

                //Define database attributes in Java
                Country myCountry = new Country();

                myCountry.code = rset.getString("Code");
                myCountry.countryName = rset.getString("Name");
                myCountry.Continent = rset.getString("continent");
                myCountry.Region = rset.getString("region");
                myCountry.population = rset.getInt("Population");
                myCountry.Capital = rset.getString("capital");










                ResultList.add(myCountry);
            }

            return ResultList;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country Caribbean country details");
            return null;
        }
    }


    public void displayCountryPopLargeToSmall(Country countryPop) {

        if (countryPop != null) {
            System.out.println(
                    "Country code: " + countryPop.code + " |"
                            + "Country name: " + countryPop.countryName + " |"
                            + "Country continent: " + countryPop.Continent + " |"
                            + "Country region: " + countryPop.Region + " |"
                            + "Country population: " + countryPop.population + " |"
                            + "Country capital: " + countryPop.Capital + " |");


        }else {
            System.out.println("country Population statement is Null");
        }
    }


    public void displayCountryContPopLargeToSmall(Country countryPopCont) {

        if (countryPopCont != null) {
            System.out.println(
                    "Country code: " + countryPopCont.code + " |"
                            + "Country name: " + countryPopCont.countryName + " |"
                            + "Country continent: " + countryPopCont.Continent + " |"
                            + "Country region: " + countryPopCont.Region + " |"
                            + "Country population: " + countryPopCont.population + " |"
                            + "Country capital: " + countryPopCont.Capital + " |");


        }else {
            System.out.println("country Population in Europe statement is Null");
        }
    }


    public void displayCountryRegPopLargeToSmall(Country countryPopReg) {

        if (countryPopReg != null) {
            System.out.println(
                    "Country code: " + countryPopReg.code + " |"
                            + "Country name: " + countryPopReg.countryName + " |"
                            + "Country continent: " + countryPopReg.Continent + " |"
                            + "Country region: " + countryPopReg.Region + " |"
                            + "Country population: " + countryPopReg.population + " |"
                            + "Country capital: " + countryPopReg.Capital + " |");


        }else {
            System.out.println("country Population in the Caribbean statement is Null");
        }
    }

}