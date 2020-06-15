
package frb.edu.br.matheus.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Matheus
 */
public abstract class DaoUtil2 {
    private Connection cx = null;
    
    public Connection getConnection() throws ClassNotFoundException, SQLException{
         if(this.cx == null){
            String url = "jdbc:mysql://localhost:3306/regiao?zeroDateTimeBehavior=convertToNull";
            String psw = "";
            String usr = "root";
            String drive = "com.mysql.jdbc.Driver";
            
            Class.forName(drive);
            cx = DriverManager.getConnection(url, usr, psw);
            
         }
         
         return cx;
  
    }
    public void getFechaConnection() throws SQLException{
        if(this.cx != null){
            this.cx.close();
            this.cx = null;        
        
    }
    }
    
    private Statement getStatement() throws ClassNotFoundException, SQLException{
        return this.getConnection().createStatement();
    }
    private PreparedStatement get(String sql) throws ClassNotFoundException, SQLException{
        return this.getConnection().prepareStatement(sql);
    }
}
