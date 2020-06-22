import java.util.*;
/**
 * Write a description of MarkovWord here.
 * 
 * @author Anirudh Maheshwari 
 * @version 1.0
 */
public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int n) {
        myRandom = new Random();
        myOrder = n;
        //System.out.println(myOrder);
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
     private int indexOf(String[] words,WordGram target,int start){
         WordGram w; 
         for(int i=start;i<words.length-myOrder;i++){
              w = new WordGram(words,i,myOrder);
              if(target.equals(w))
                  return i;
              //System.out.println(w);
            }
          return -1;  
        }   
    
     public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        //System.out.println(k+myOrder);
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText,index,myOrder);
        sb.append(kGram);
        sb.append(" ");
        //System.out.println(kGram+" : "+myOrder);
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
            
        }
        
        return sb.toString().trim();
     }
    
        private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int i=0;
        while(i<myText.length-myOrder){
           int idx = indexOf(myText,kGram,i);
           if(idx==-1)
              break;
           else
              { 
                if(i<myText.length)
                  follows.add(myText[idx+myOrder]);
                i = idx+1;  
                }
           }
        return follows;
     }
     
     public void testIndexOf(){
          String s = "this is just a test yes this is a simple test";
          String[] w1 = {"this","is","a","simple"};
          //int myOrder = 3;
          WordGram w= new WordGram(w1,0,3);
          int idx = this.indexOf(s.split("\\s+"),w,0);
          System.out.println(idx);
          myText=s.split("\\s+");
          System.out.println(this.getFollows(w));
          s=s+" "+w;
          System.out.println(s);
          
        }
        
}


