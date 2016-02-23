/*
  File: SelectDept.java
  2015
*/

import java.io.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.*;

/**
 * This program  selects one row of data from the department
 * table based on the mgrssn field. 
 *
 * @author Paul Werstein
 */

public class SelectDept{


    public static void main (String[] args) {
        if(args.length > 0){
            try{
                int mgrssn = Integer.parseInt(args[0]);
                new SelectDept().go(mgrssn);
            }catch (NumberFormatException e){
                System.err.println("Argument " + args[0] +
                                   " must be an integer.");
                System.exit(1);
            }
        } else {
            System.out.println("No mgrssn given");
        }
    }

    // This is the function that does all the work
    private void go(int mgrssn) {

	// Read pass.dat
	UserPass login = new UserPass();
	String user = login.getUserName();
	String pass = login.getPassWord();
	String host = "silver";

	Connection con = null;
	try {
	    // Register the driver and connect to Oracle
	    DriverManager.registerDriver 
		(new oracle.jdbc.driver.OracleDriver());
	    String url = "jdbc:oracle:thin:@" + host + ":1527:cosc344";
            System.out.println("url: " + url);
	    con = DriverManager.getConnection(url, user, pass);
	    System.out.println("Connected to Oracle");
            /* select one row of data from department table from mgrssn
               parameter and print */
            Statement stmt = con.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM department "+
                                                  "WHERE mgrssn = "+mgrssn);
            /* print results */
            ResultSetMetaData rsmd = results.getMetaData();
            while(results.next()){
                System.out.print(results.getString(1) + "  ");
                System.out.print(results.getInt(2) + "  ");
                System.out.print(results.getInt(3) + "  ");
                System.out.print(results.getString(4) + "\n");
            }

            
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

	} finally {
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    quit(e.getMessage());
		}
	    }
	}
    }  // end go()


      


    
    // Used to output an error message and exit
    private void quit(String message) {
	System.err.println(message);
	System.exit(1);
    }

} // end class TestLogin


