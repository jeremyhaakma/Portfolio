import java.util.Scanner;
import java.util.*;
import java.util.HashMap;

/*
 * Etude 8: Joined Up Writing
 * Jeremy Haakma
 * Scott Wyngaarden
 */

public class etude8 {
  /* first word to be joined, comes from args[0] */
  static String firstWord;
  /* word to be joined at end, comes from args[1] */
  static String secondWord;
  /* dictionary: list of words to be used in joining firstWord and secondWord */
  static ArrayList<String> dictionary = new ArrayList<String>();
  /* mapSingle: hashmap of dictionary words that can be singly joined to each other */
  static HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
  
  
  
  public static void main (String[] args) {
    //command line arguments: two words to join up
    firstWord = args[0];
    secondWord = args[1];
    
    //std in provides dictionary of words for joining up
    Scanner s = new Scanner(System.in);
    while(s.hasNext() ){
      dictionary.add(s.next() );
    }
    
    /* input: starting with firstWord, fed into recursive algorithm. 
     * Ends up as list of words that have been joined up */
    ArrayList<String> input = new ArrayList<String>();
    input.add(firstWord);
    
    /*for single joined words: */
    //ArrayList<String> singleList = new ArrayList<String>(recursiveJoin(true, input));//call recursive method
    ArrayList<String> singleList = new ArrayList<String>(makeList(true));
      
    if(!singleList.get(singleList.size()-1).equals(secondWord)){
      //if no result, just print 0
      System.out.println("0");
    } else {
      //otherwise, print number of words required to join, followed by the words
      System.out.print(singleList.size() + " ");
      for(String word : singleList){
        System.out.print(word + " ");
      }
      System.out.print("\n");
    }
    
    /*for double joined words: */
    //ArrayList<String> doubleList = new ArrayList<String>(recursiveJoin(false, input));//call recursive method
    map = new HashMap<String, ArrayList<String>>();
    ArrayList<String> doubleList = new ArrayList<String>(makeList(false));
    
    if(!doubleList.get(doubleList.size()-1).equals(secondWord) ){
      //if no result, just print 0
      System.out.println("0");
    } else {
      //otherwise, print number of words required to join, followed by the words
      System.out.print(doubleList.size() + " ");
      for(String word : doubleList){
        System.out.print(word + " ");
      }
      System.out.print("\n");
    }     
  }//end main
  
  public static ArrayList<String> makeList(boolean isSingle) {
    ArrayList<String> list = new ArrayList<String>();
    list.add(firstWord);
    
    Queue<ArrayList<String>> queue = new LinkedList<ArrayList<String>>();
    queue.add(list);
    
    while(!queue.isEmpty()){
      ArrayList<String> tempWords = queue.remove();
      String lastWord = tempWords.get(tempWords.size()-1);
      
      if( join(isSingle, lastWord, secondWord) ){
        tempWords.add(secondWord);
        return tempWords;
      }      
      //if not in hashmap, add to hashmap
      if ( !map.containsKey(lastWord) ){
        ArrayList<String> newEntry = new ArrayList<String>();
        for(String s : dictionary){
          if(join(isSingle, lastWord, s) ) { 
            newEntry.add(s);
          }
        }
        map.put(lastWord, newEntry);
      }      
      /* for each item in hashmap value add a new list to the queue */
      for(String s : map.get(lastWord)){
        //check whether word in hashmap is already used
        boolean isInList = false;
        for(String inList : tempWords){          
          if (inList.equals(s) ) isInList = true;          
        }
        if(!isInList){
          ArrayList<String> newList = new ArrayList<String>(tempWords);
          newList.add(s);
          queue.add(newList);
        }
      }      
    }
    
    ArrayList<String> result = new ArrayList<String>();
    result.add(firstWord);
    return result;
  }
  
  /* Method to attempt to join two words
   * First param: isSingle, true if only attempting singly joined */
  public static boolean join (boolean isSingle, String first, String second) {   
    //common part longer than first word
    //iterate substrings starting from character position 0 up to/including half the length, to the end of word
    for (int i = 0; i < first.length(); i++){
      //if substring from i of first word matches start of second word
      if (second.startsWith(first.substring(i)) ){
        int commonLength = first.substring(i).length();
        
        /* check for singly joined */
        if (isSingle){
          //is the common section larger than half of either word??
          if (commonLength >= (Math.ceil(first.length()/2.0)) 
                || commonLength >= (Math.ceil(second.length()/2.0))  ) {
            return true;
          }
        } else {
          //is the common section larger than half of both words??
          if (commonLength >= (Math.ceil(first.length()/2.0)) 
                && commonLength >= (Math.ceil(second.length()/2.0))  ) {
            return true;
          }
        }
      }
    }
    return false;
  }//end join
  
}//end class