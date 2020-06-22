import java.util.*;
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author Anirudh Maheshwari
 * @version 1.0
 */
public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
     private int indexOf(String[] words,String target1,String target2,int start){
          for(int i=start;i<words.length-1;i++){
              if(target1.equals(words[i])&&target2.equals(words[i+1]))
                      return i;
            }
          return -1;  
        }   
    
     public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key1,key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
     }
    
        private ArrayList<String> getFollows(String key1,String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int i=0;
        while(i<myText.length-2){
           int idx = indexOf(myText,key1,key2,i);
           if(idx==-1)
              break;
           else
              { i=idx+2;
                if(i<myText.length)
                  follows.add(myText[i]);
                }
           }
        return follows;
     }
     
     public void testIndexOf(){
          String s = "this is just a test yes this is a simple test";
          int idx = indexOf(s.split("\\s+"),"this","is",0);
          System.out.println(idx);
          myText=s.split("\\s+");
          System.out.println(getFollows("this","is"));
          
        }
        
}
