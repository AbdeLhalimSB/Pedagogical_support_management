
package Controls;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Admin;

/**
 *
 * @author sahba
 */
public class RegisterForm_CL {
    
    @FXML
    Label goto_log;
    @FXML
    Button Singup;
    @FXML
    TextField Email_tx,Password_tx,Name_tx,LName_tx,CIN_tx;
    
    
    
    Statement state ;
    
    public void Goto_Log(Event e) throws IOException{
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();                  
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));       
        Scene scene = new Scene(root);       
        stage.setScene(scene);
        stage.show();
    }
    public void register(Event e) throws SQLException{
        Admin ad =new Admin();
        ad.setNome(Name_tx.getText());
        ad.setPrenom(LName_tx.getText());
        ad.setCIN(CIN_tx.getText());
        ad.setEmail(Email_tx.getText());
        ad.setMdp(Password_tx.getText());
        state = ConnectionDB.openConnection().createStatement();
        state.executeUpdate("insert into users (`Nome`,`Prenom`,`CIN`,`Email`,`mdp`) values ( '"+ad.getNome()+"' , '"+ad.getPrenom()+"' , '"+ad.getCIN()+"' , '"+ad.getEmail()+"' , '"+ad.getMdp()+"' ) ");
        ConnectionDB.closeConnection();
    }
}
