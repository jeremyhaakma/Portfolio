/*
  File:  TesterUserPass.java
  July 2002
*/

import java.io.*;
import java.util.*;

/**
 * A simple program to test UserPass class.
 *
 * @author Paul Werstein
 */

public class TestUserPass {

    public static void main (String[] args) {

	// Code similar th these 3 lines are required 
	// to use UserPass.
	UserPass x = new UserPass();
	String user = x.getUserName();
	String pass = x.getPassWord();

	// Make sure the file format is correct.
	System.out.println("x" + user + "x");
	System.out.println("x" + pass + "x");
    }

}  // end class TestUserPass
