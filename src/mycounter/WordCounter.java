package mycounter;
import java.io.*;
import java.util.*;

class MyKey implements Comparable<MyKey>{
    String s;
    int n;
    public MyKey(String s, int n) {
        this.s = s;
        this.n = n;
    }   
    public String toString() {
        return s + " - "+ n;
    }
    public int compareTo(MyKey key) {
        int res = n-key.n;
        if (res == 0) res = s.compareTo(key.s);
        return res;
    }
}
public class WordCounter {
    private String inFile;
    private String outFile;
    private static String testString = " fhfghgf  hello   o wtryh\n  hello o o ";

    public WordCounter(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    private Map words = new TreeMap();
    public  Map getWords() { return words; }
    
    public Map getByValue () {
        Map tm = new TreeMap();
        for (Object obj : words.keySet()) {
            int n = (int)words.get(obj);
            tm.put(new MyKey((String)obj, n), null);
        }
        return tm;
    }
    public void countWords(){ 
        int num=0;
        try {
            Reader reader;
            if ( inFile == null ) 
                    reader = new StringReader(testString);
            else    reader = new FileReader(inFile);

            BufferedReader br=new BufferedReader(reader);
            for (String line = br.readLine(); line != null; line = br.readLine()) {

                StringTokenizer st =new  StringTokenizer(line," \t\n\r\f.,;:\"");  
                while(st.hasMoreTokens()) {
                    String token=st.nextToken();
                        if (!words.containsKey(token)){
                                words.put(token,1);
                                num++;
                        }
                        else {
                            Object val = words.get(token);
                            int n=(int) val;
                            n++;
                            words.put(token,n);
                        }
                }
            }
            br.close(); 
            System.out.println("num="+num);
        }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}
