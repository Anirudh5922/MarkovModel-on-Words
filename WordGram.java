
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
        //System.out.println(myWords[0]);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        for(int i=0;i<this.length();i++)
         {  ret = ret + myWords[i]+" ";
            }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if(this.length()!=other.length())
                return false;
        for(int k=0;k<myWords.length;k++){
           if(!myWords[k].equals(other.wordAt(k)))
                 return false;
        }        
        return true;
    }

    public WordGram shiftAdd(String word) {	
       // String[] newWords = new String[this.length()];
       
        for(int i=1;i<this.length();i++)
           myWords[i-1]=myWords[i];
        //System.out.println("length :"+this.length());
        myWords[this.length()-1]=word;   
        WordGram out = new WordGram(myWords, 0, myWords.length); 
        return out;
    }
    
    public int hashCode(){
       String s = this.toString();
       int hc = s.hashCode();
       return hc;
    } 
}