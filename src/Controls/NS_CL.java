
package Controls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author sahba
 */
public class NS_CL {
    @FXML
    Button btn_save,btn_clear,Back;
    @FXML
    TextField dure_tx;
    @FXML
    DatePicker date_tx;
    @FXML 
    ComboBox matiers,groupes,seances,profs;
    @FXML
    TextArea objective_txa,observations_txa,disposiition_txa,derouletment_txa,outils_txa;
    
    @FXML
    public void initialize() throws SQLException{
        getallmatiers();
        getallgroupes();
        getallprofs();
        seances.getItems().removeAll(seances.getItems());
        seances.getItems().addAll("Cours/TP-TD", "CC", "EFCF Th","EFCF Pr");
        seances.getSelectionModel().select("Cours/TP-TD");
    }
    
    Statement state;
    
    public void clear(Event e){
        objective_txa.setText("");
        disposiition_txa.setText("");
        observations_txa.setText("");
        derouletment_txa.setText("");
        outils_txa.setText("");
    }
    
    public void getallmatiers() throws SQLException{
        ObservableList<String> l =FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT Nom FROM `matiers`");
        while(result.next()){
            l.add(result.getString(1));
        }
        ConnectionDB.closeConnection();
        matiers.setItems(l);
        matiers.getSelectionModel().select("Choose...");
    }
    public void getallgroupes() throws SQLException{
        ObservableList<String> l =FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT Nom FROM `groupes`");
        while(result.next()){
            l.add(result.getString(1));
        }
        ConnectionDB.closeConnection();
        groupes.setItems(l);
        groupes.getSelectionModel().select("Choose...");
    }
    public void getallprofs() throws SQLException{
        ObservableList<String> l =FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT Nome FROM `users`");
        while(result.next()){
            l.add(result.getString(1));
        }
        ConnectionDB.closeConnection();
        profs.setItems(l);
        profs.getSelectionModel().select("Choose...");
    }
    
    public void Back(Event e)
    {
        try {
              Node node = (Node) e.getSource();
              Stage stage = (Stage) node.getScene().getWindow();                  
              stage.close();

              Parent root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));       
              Scene scene = new Scene(root);       
              stage.setScene(scene);
              stage.show();

       } catch (Exception ex) {
           System.out.println("y"+ex.getMessage());
       }
    }
    
    public void save(Event e){
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("INSERT INTO `seances`(`date`, `duree`, `matiere`, `groupe`, `stype`,`prof`, `objective`, `derouletment`, `outils`, `observations`, `disposiition`) VALUES ( '"+date_tx.getValue().toString()+"', '"+dure_tx.getText()+"' , '"+matiers.getValue().toString()+"' , '"+groupes.getValue().toString()+"' , '"+seances.getValue().toString()+"' , '"+profs.getValue().toString()+"' , '"+objective_txa.getText()+"' , '"+derouletment_txa.getText()+"' , '"+derouletment_txa.getText()+"' ,'"+observations_txa.getText()+"','"+disposiition_txa.getText()+"')");
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(NS_CL.class.getName()).log(Level.SEVERE, null, ex);
        }
        clear(e);
    }
    
}
