
package Controls;

import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class AHome_CL {
    @FXML
    Button btn_ns,btn_groupes,btn_matiers,btn_s,btn_exit;
    
    public void openNSeance(Event e) throws IOException{
        Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();                  
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/View/NS.fxml"));       
            Scene scene = new Scene(root);       
            stage.setScene(scene);
            stage.show();
    }
    public void openGroupes(Event e) throws IOException{
        Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();                  
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/View/Groupes.fxml"));       
            Scene scene = new Scene(root);       
            stage.setScene(scene);
            stage.show();
    }
    public void openMatiers(Event e) throws IOException{
        Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();                  
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/View/Matier.fxml"));       
            Scene scene = new Scene(root);       
            stage.setScene(scene);
            stage.show();
    }
    public void openSeances(Event e) throws IOException{
        Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();                  
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/View/DS.fxml"));       
            Scene scene = new Scene(root);       
            stage.setScene(scene);
            stage.show();
    }
    

    
    public void Close(Event e) {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }
}
