
package Controls;

import Model.Matier;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author sahba
 */
public class Matiers_CL {
    @FXML
    Button btn_add,btnBack,btn_search;
    @FXML
    TextField Name_tx;
   
    @FXML
    private TableView matiers_view;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn nome;
    @FXML
    
    int ID;
    
    @FXML
    public void initialize() throws SQLException {
        DisplayMatiers();
    }
    
        
    
    public void DisplayMatiers() throws SQLException{
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        matiers_view.setItems(getAllMatiers());
    }
        
    
    Statement state ;
    
    public void add() throws SQLException{
        Matier m = new Matier();
        m.setNom(Name_tx.getText());
        insert(m);
        clear();
        DisplayMatiers();
    }
    public void del(Event e){
        delete(ID);
        clear();
    }
    public void edit(Event e){
        Matier m = new Matier();
        m.setNom(Name_tx.getText());
        update(m);
        clear();
    }
    
    public void clickTable(Event e)
    {
       Matier m =  (Matier) matiers_view.getSelectionModel().getSelectedItem();
       Name_tx.setText(m.getNom());
       ID=m.getId();
    }
    
    public void insert(Matier m)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("INSERT INTO `matiers` (`Nom`) VALUES ('"+m.getNom()+"') ");
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(Matiers_CL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("Delete FROM `matiers` WHERE id = " + id);
            ConnectionDB.closeConnection();
            DisplayMatiers();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(Matiers_CL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Matier m)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("UPDATE matiers set  `Nom` = "+"'"+m.getNom()+"' WHERE id = "+ID );
            ConnectionDB.closeConnection();
            DisplayMatiers();
        } catch (SQLException ex) {
            Logger.getLogger(Matiers_CL.class.getName()).log(Level.SEVERE, null, ex);
            ConnectionDB.closeConnection();
        }
    }
    
    public ObservableList<Matier> getAllMatiers() throws SQLException{
        ObservableList ml =FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT * FROM matiers");
        while(result.next()){
            Matier obj = new Matier();
            obj.setId(result.getInt(1));
            obj.setNom(result.getString(2));
            ml.add(obj);
        }
        ConnectionDB.closeConnection();
        return ml;
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
     
    
    public void clear(){
        Name_tx.setText("");
    }
    
    public void pro(Event e) throws SQLException{
        DisplayMatiers();
        clear();
    }
}
