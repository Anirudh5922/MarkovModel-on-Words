
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author Anirudh Maheshwari
 * @version 1.0
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
     private int indexOf(String[] words,String target,int start){
          for(int i=start;i<words.length;i++){
              if(target.equals(words[i]))
                  return i;
            }
          return -1;  
        }   
    
     public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
     }
    
        private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int i=0;
        while(i<myText.length-1){
           int idx = indexOf(myText,key,i);
           if(idx==-1)
              break;
           else
              { i=idx+1;
                if(i<myText.length)
                  follows.add(myText[i]);
                }
           }
        return follows;
     }
     
     public void testIndexOf(){
          String s = "this is just a test yes this is a simple test";
          int idx = indexOf(s.split("\\s+"),"yes",6);
          System.out.println(idx);
          myText=s.split("\\s+");
          System.out.println(getFollows("simple"));
          
        }
        
}
