
package Controls;

import Model.Groupe;
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
public class Groupes_CL {
    @FXML
    Button btn_add,btnBack,btn_search;
    @FXML
    TextField Name_tx;
   
    @FXML
    private TableView groupes_view;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn nome;
    @FXML
    
    int ID;
    
    @FXML
    public void initialize() throws SQLException {
        DisplayGroupes();
    }
    
        
    
    public void DisplayGroupes() throws SQLException{
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        groupes_view.setItems(getallgroupes());
    }
        
    
    Statement state ;
    
    public void add() throws SQLException{
        Groupe g = new Groupe();
        g.setNom(Name_tx.getText());
        insert(g);
        clear();
        DisplayGroupes();
    }
    public void del(Event e){
        delete(ID);
        clear();
    }
    public void edit(Event e){
        Groupe g = new Groupe();
        g.setNom(Name_tx.getText());
        update(g);
        clear();
    }
    
    public void clickTable(Event e)
    {
       Groupe g =  (Groupe) groupes_view.getSelectionModel().getSelectedItem();
       Name_tx.setText(g.getNom());
       ID=g.getId();
    }
    
    public void insert(Groupe  g)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("INSERT INTO `groupes` (`Nom`) VALUES ('"+g.getNom()+"') ");
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(Groupes_CL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("Delete FROM `groupes` WHERE id = " + id);
            ConnectionDB.closeConnection();
            DisplayGroupes();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(Groupes_CL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Groupe g)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("UPDATE groupes set  `Nom` = "+"'"+g.getNom()+"' WHERE id = "+ID );
            ConnectionDB.closeConnection();
            DisplayGroupes();
        } catch (SQLException ex) {
            Logger.getLogger(Groupes_CL.class.getName()).log(Level.SEVERE, null, ex);
            ConnectionDB.closeConnection();
        }
    }
    
    public ObservableList<Groupe> getallgroupes() throws SQLException{
        ObservableList gl =FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT * FROM groupes");
        while(result.next()){
            Groupe obj = new Groupe();
            obj.setId(result.getInt(1));
            obj.setNom(result.getString(2));
            gl.add(obj);
        }
        ConnectionDB.closeConnection();
        return gl;
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
    
}
