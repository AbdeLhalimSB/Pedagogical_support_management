
package Controls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Model.Admin;

/**
 *
 * @author sahba
 */
public class LoginControl {
    Statement st,st2;
     String type;
    public boolean checker(Admin ad) throws SQLException{
        st=ConnectionDB.openConnection().createStatement();
        ResultSet res = st.executeQuery("select * from users where Email='"+ad.getEmail()+"' and mdp='"+ad.getMdp()+"'");
        //ResultSet per = st.executeQuery("select Permission from users where Email='"+ad.getEmail()+"' and Password='"+ad.getPassword()+"'");
//        ad.setPermission("employee");
        if(res.next()){
            return true;
        }
        else{
            return false;
        }
    }
    
        
}
