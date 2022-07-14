
package Controls;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Admin;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author sahba
 */
public class LoginForm_CL {
    @FXML
    TextField email_tx;
    @FXML
    PasswordField pass_tx;
    @FXML
    Button btnlogin;
    @FXML
    Label lbmsg,goto_reg;
    
    Admin ad =new Admin();
    LoginControl lg = new LoginControl();
    Statement st;
    
    public void isSign(Event e) throws SQLException, IOException{
        ad.setEmail(email_tx.getText());
        ad.setMdp(pass_tx.getText());
        if(lg.checker(ad)){
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();                  
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));       
            Scene scene = new Scene(root);       
            stage.setScene(scene);
            stage.show();
        }
        else{
            lbmsg.setText("Email or Password is wrong !!");
        }
    }
    
    public void Goto_Reg(Event e) throws IOException{
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();                  
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/View/Register.fxml"));       
        Scene scene = new Scene(root);       
        stage.setScene(scene);
        stage.show();
    }
    
}
