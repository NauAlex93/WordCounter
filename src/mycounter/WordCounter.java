package mycounter;
import java.io.*;
import java.util.*;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

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
public class WordCounter extends Task<Void>{
    private String inFile;
    final private int    SIZE=Integer.MAX_VALUE;
    final private ObservableList<String> items;
    final private List<String> list=new LinkedList<>();

    public WordCounter(String inFile, ObservableList<String> items) {
        this.inFile = inFile;
        this.items = items;
    }

    private Map<String, Integer> words = new TreeMap();
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
            reader = new FileReader(inFile);

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
            Iterator iterator = words.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) iterator.next();
                list.add(pair.getKey()+" - "+pair.getValue()) ;
            }
            
            br.close(); 
        }
        catch (IOException ex) { ex.printStackTrace(); }
    }

    @Override
    protected Void call() throws Exception {
        countWords();
        return null;
    }
    
    @Override
    protected void cancelled() {
        items.add("canceled by user....");
	updateScene();
    }

    @Override
    protected void succeeded() {
        items.addAll(list);
	items.add("finded "+items.size()+" unique words");
	updateScene();
    }
    private void updateScene(){
        updateProgress(SIZE, SIZE);
    }
    
}
