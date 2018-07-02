/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycounter;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

/**
 *
 * @author Alexandr1
 */
public class GridPaneController implements Initializable {
    @FXML private TextField pathId;
    @FXML private ListView<String> listId;
    
    private Task task=null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyCounterFX.items.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change obj) {
                listId.setItems(obj.getList());
            }
        });
    }    
    @FXML
    protected void btnFindClick(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Please, choose txt file!");
        
        WordCounter wc=new WordCounter(pathId.getText());
        if(!pathId.getText().endsWith(".txt")) alert.showAndWait();
        wc.countWords();
        
        Map tm = wc.getByValue();
        for (Object obj : tm.keySet())
        System.out.println(obj);
        
//        String path=pathId.getText();
//        String end =endId.getText();
//        
//        MyCounterFX.items.clear();
//        //barId.setProgress(-1);
//        
//        task = new FindTask(MyCounterFX.items, path, end);
//        barId.progressProperty().bind(task.progressProperty());
//        
//        //run asynk Task in concurrent version
//        Thread thread=new Thread(task);
//        thread.setDaemon(true);
//        thread.start();
//
//        //barId.setProgress(1);
    }
    @FXML
    protected void btnCancelClick(ActionEvent event) {
        System.out.println("Try to Cancel!");
        if (task!=null) task.cancel();
    }
}
