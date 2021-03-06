/** Etude 7: Emails
  * Author: Jeremy Haakma
  */

import java.util.*;

public class etude7 {
  
  public static void main(String[] args){
    
    /* List of valid extensions */
    String[] extentions = new String[]{"co.nz", "com.au", "ca", "com", "co.us", "co.uk"};
    
    /* Scan in potential email addresses */
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> inputList = new ArrayList<String>();
    ArrayList<String> emails = new ArrayList<String>();
    while (scanner.hasNextLine()){
      String input = scanner.nextLine().toLowerCase();
      inputList.add(input);
      emails.add(input);
    }
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> domains = new ArrayList<String>();
    ArrayList<String> extensions = new ArrayList<String>();
    ArrayList<String> validList = new ArrayList<String>();
    
    for(int i = 0; i < emails.size(); i++){
      names.add("");
      domains.add("");
      extensions.add("");
      validList.add("");
    }
    
    String domainError = " <- Invalid domain";
    String atError = " <- Missing '@' symbol";
    String nameError = " <- Invalid name";
    String extError = " <- Invalid extension";

    
    for (int i = 0; i < inputList.size(); i++){
      String email = emails.get(i);
      //Replace _at_ with @
      email = email.replaceAll("_at_", "@");
      emails.set(i, email);
      // check for @s
      if(!email.contains("@")){
        validList.set(i, atError);
        //Split by @
      } else{
        //set names to string before the "@"
        names.set( i, email.substring(0, email.lastIndexOf("@")) );
        //set domains to the string after the "@"
        domains.set(i, email.substring(email.lastIndexOf("@") + 1) );
        
        //check names
        if(!names.get(i).matches("([a-z0-9]+[\\.\\_\\-]?)*[a-z0-9]+") || names.get(i).length() == 0){
          validList.set(i, nameError);
          //check domain+ext is IP format
        } else{
          if(domains.get(i).matches("^\\[(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])\\]$")){
            //isValidList.set(i, "valid"); ?
            //if domain is valid IP and name is valid == whole email should be valid
            
            //remove extensions
          } else{
           boolean extRemoved = false;
            for(String ext : extentions){
              //set extensions to ext, set domains to string before extension
              if(domains.get(i).endsWith(ext)){
                extensions.set(i, ext);
                domains.set(i, domains.get(i).substring(0, domains.get(i).lastIndexOf(ext)));
                extRemoved = true;
              }
            }
            if( !extRemoved ){
              //no extension removed: invalid extension
              validList.set(i, extError);
              //System.out.println(domains.get(i));
            } else {
              //replace _dot_ before extension
              domains.set(i, domains.get(i).replaceAll("_dot_", "."));
              if (!domains.get(i).matches("([a-z0-9]+\\.)+")){
                validList.set(i, domainError);
                //System.out.println(domains.get(i));
              } else {
                validList.set(i, "valid");
              }
            }
          }
        }
      }
    }
    for (int i= 0; i < inputList.size(); i++){
      String output = "";
      if (validList.get(i).equals("valid")){
        output = names.get(i) + "@" + domains.get(i) + extensions.get(i);
      } else {
        output = inputList.get(i) + validList.get(i);
      }
      System.out.println(output);
    }
    
  }
  
  
}