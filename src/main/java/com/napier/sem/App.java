package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        // Get city
        ArrayList<City> cityList = a.getAllCities();
        // Display results
        for (int i = 0; i < cityList.size(); i++){
            a.displaycity(cityList.get(i));
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
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
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
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
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
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
    public ArrayList<City> getAllCities()
    {

        ArrayList<City> ResultList = new ArrayList<City>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.name, country.name, city.District, city.Population, country.code, city.CountryCode "
                            +"FROM country, city "
                            +"WHERE country.code = city.CountryCode "
                            +"ORDER BY city.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
            // Check one is returned
            while (rset.next())
            {

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

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    public void displaycity(City city)
    {
        if (city != null)
        {
            System.out.println(

                             "City Name: " + city.Name + " |"
                            + "Country: " + city.country.countryName + " |"
                            + "City District: " + city.District + " |"
                            + "Population: " + city.Population + "|");

        }
        else
        {
            System.out.println("City is Null");
        }


    }
}