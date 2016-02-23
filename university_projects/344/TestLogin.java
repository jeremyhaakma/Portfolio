/*
  File: TestLogin.java
  August 2002
*/

import java.io.*;
import java.util.*;
import java.sql.*;

/**
 * This program reads a user's pass.dat and connects to Oracle.
 *
 * @author Paul Werstein
 */

public class TestLogin {


    public static void main (String[] args) {
	new TestLogin().go();
    }

    // This is the function that does all the work
    private void go() {

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


