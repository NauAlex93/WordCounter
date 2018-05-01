package mycounter;
import java.util.*;

public class MyCounter {

    public static void main(String[] args) {
        SimpleParser sp=new SimpleParser();  
        sp.Parse(args);
        
        //WordCounter wc=new WordCounter(null,null);
        WordCounter wc=new WordCounter(sp.getInFile(), null);
        wc.countWords();
        
        Map tm = wc.getByValue();
        for (Object obj : tm.keySet())
            System.out.println(obj);

//        Set keys=wc.getWords().keySet();
//        for(Object obj:keys){
//            int n=(int)wc.getWords().get(obj);
//            System.out.println(obj.toString()+" n="+n);
//        }

//        Enumeration keys = wc.getWords().keys();
//        while(keys.hasMoreElements()){
//           String w=keys.nextElement().toString(); 
//           int n= (int)wc.getWords().get(w);
//           System.out.println(w+"   "+n);
//        }             
    }
}
