
package Controls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author sahba
 */
public class DS_CL {
    @FXML
    Button btn_delete,Back;
    @FXML
    DatePicker date_tx;
    @FXML 
    Label matiers,groupes,seances,profs,dure_tx,error;
    @FXML
    TextArea objective_txa,observations_txa,disposiition_txa,derouletment_txa,outils_txa;
    @FXML
    Pane main;
    
//    @FXML
//    public void initialize() throws SQLException{
//        
//    }
    
    Statement state;
    
    public void clear(Event e){
        objective_txa.setText("");
        disposiition_txa.setText("");
        observations_txa.setText("");
        derouletment_txa.setText("");
        outils_txa.setText("");
        matiers.setText("-");
        groupes.setText("-");
        seances.setText("-");
        profs.setText("-");
        dure_tx.setText("-");
    }
    
    public void getSeances(Event e) throws SQLException{
        clear(e);
        main.setVisible(true);
        error.setVisible(false);
        getallSeances(date_tx.getValue().toString());
    }
    
    public void getallSeances(String date) throws SQLException{
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT `date`, `duree`, `matiere`, `groupe`, `stype`, `prof`, `objective`, `derouletment`, `outils`, `observations`, `disposiition` FROM `seances` where date ='"+date+"'");
        if(result.isBeforeFirst()){
           while(result.next()){
           dure_tx.setText(result.getString(2));
           matiers.setText(result.getString(3));
           groupes.setText(result.getString(4));
           seances.setText(result.getString(5));
           profs.setText(result.getString(6));
           objective_txa.setText(result.getString(7));
           derouletment_txa.setText(result.getString(8));
           outils_txa.setText(result.getString(9));
           observations_txa.setText(result.getString(10));
           disposiition_txa.setText(result.getString(11));
           }
           ConnectionDB.closeConnection();
        }
        else{
            main.setVisible(false);
            error.setVisible(true);
        }
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
    
    public void delete(Event e){
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("DELETE FROM `seances` WHERE date ='"+date_tx.getValue().toString()+"'");
            ConnectionDB.closeConnection();
            clear(e);
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(DS_CL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
