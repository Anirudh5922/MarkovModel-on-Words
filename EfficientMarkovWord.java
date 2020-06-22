import java.util.*;
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author Anirudh Maheshwari 
 * @version 1.0
 */
public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> map;
    
    public EfficientMarkovWord(int n) {
        myRandom = new Random();
        myOrder = n;
        //System.out.println(myOrder);
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        map = buildMap();
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
            //System.out.println(kGram+" : "+follows);
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
        //int hc = kGram.hashCode();
        follows = map.get(kGram);
        return follows;
     }
     
     public void test(){
          String s = "this is just a test yes this is a simple test";
          String[] w1 = {"this","is","a","this","is","a"};
          //int myOrder = 3;
          WordGram w = new WordGram(w1,0,3);
          int idx = w.hashCode();
          System.out.println(idx);
          w = new WordGram(w1,3,3);
          idx = w.hashCode();
          System.out.println(idx);
          String s1= "this is a";
          String s2 = "this is a";
          System.out.println(s1.hashCode()+"  "+s2.hashCode());
          //myText=s.split("\\s+");
          //System.out.println(this.getFollows(w));
          //s=s+" "+w;
          //System.out.println(s);
          
        }
    
     public HashMap<WordGram,ArrayList<String>> buildMap(){
         WordGram w ; 
         HashMap<WordGram,ArrayList<String>> mymap = new HashMap<WordGram,ArrayList<String>>();
         ArrayList<String> arr;
         for(int i=0;i<myText.length-myOrder;i++){
           w = new WordGram(myText,i,myOrder); 
           //int hc = w.hashCode();
           if(mymap.containsKey(w)){
             arr = mymap.get(w);
            }
            
           else {
             arr = new ArrayList<String>();
            } 
           arr.add(myText[i+myOrder]);
           mymap.put(w,arr);           
            }
         w = new WordGram(myText,myText.length-myOrder,myOrder);
         arr = new ArrayList<String>();
         //int hc = w.hashCode();
         mymap.put(w,arr);
         return mymap;
        }  
        
    public void printHashMapInfo(){
       String s ="this is a test yes this is really a test yes a test this is wow";
       //EfficientMarkovWord emw = new EfficientMarkovWord(3);
       ArrayList<WordGram> ret = new ArrayList<WordGram>();
       this.setTraining(s);
       int max = 0; 
       for(WordGram  i:map.keySet()){
         System.out.println(i+" : "+map.get(i));
         if(max<map.get(i).size())
             max = map.get(i).size();
        }
       
       for(WordGram  i:map.keySet()){
         
         if(max==map.get(i).size())
             ret.add(i);
        } 
       System.out.println("Maximum key size : "+max);
       System.out.println("At values : "+ret);
    }    
}