
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Anirudh Maheshwari 
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(3);
        runModel(markovWord, st,100,643); 
    } 
    
    public void runMarkovTwo(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordTwo markovWord = new MarkovWordTwo();
        runModel(markovWord, st, 120,549);  
    }
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    
    public void compareMethods(){
       FileResource fr = new FileResource(); 
       String st = fr.asString(); 
       st = st.replace('\n', ' '); 
       long start,end,time1,time2;
       start = System.nanoTime();
       MarkovWord mak = new MarkovWord(2);
       runModel(mak, st,200,42);
       end = System.nanoTime();
       time1 = end - start;
       start = System.nanoTime();
       EfficientMarkovWord emak = new EfficientMarkovWord(2);
       runModel(emak, st,200,42);
       end = System.nanoTime();
       time2 = end - start;
       System.out.println("Normal time : "+time1/1e9);
       System.out.println("Efficient time : "+time2/1e9);
    }
}
