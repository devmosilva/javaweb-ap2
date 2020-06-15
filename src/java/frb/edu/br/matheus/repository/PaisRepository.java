
package frb.edu.br.matheus.repository;

import frb.edu.br.matheus.contracts.IPais;
import frb.edu.br.matheus.data.DaoUtil;
import frb.edu.br.matheus.entity.PaisDto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m_oli
 */
 public class PaisRepository extends DaoUtil implements IPais{

    @Override
    public boolean incluir(PaisDto pais) {
      String sql = "INSERT INTO pais (pais) VALUES(?)";
      
        PreparedStatement stmt;
        int ret = -1;
                
        try {
            
        stmt = getPreparedStatement(sql);
        stmt.setString(1, pais.getPais() );
       
        ret = stmt.executeUpdate();
        stmt.close();
                          
        } catch (ClassNotFoundException e) {
            Logger.getLogger(PaisRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException x) {
            Logger.getLogger(PaisRepository.class.getName()).log(Level.SEVERE, null, x);
        }
            return ret >0;
    }

    @Override
    public boolean alterar(PaisDto pais) {
           String sql = "UPDATE pais SET pais=? "
                + " WHERE pais_id=?";

        PreparedStatement stmt;
        int ret = -1;
        
        
        try {

            stmt = getPreparedStatement(sql);

          
            stmt.setString(1, pais.getPais());
            stmt.setInt(2, pais.getPais_id());
        

            ret = stmt.executeUpdate();
            stmt.close();
            
            
    }catch (ClassNotFoundException e) {
            Logger.getLogger(PaisDto.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException x) {
            Logger.getLogger(PaisDto.class.getName()).log(Level.SEVERE, null, x);
        }
        
        return ret >0;
    }

    @Override
    public boolean deletar(int id) {
         String sql = "DELETE FROM pais WHERE pais_id=?";
        PreparedStatement ps;
        int ret=-1;
        try {
            ps = getPreparedStatement(sql);
            ps.setInt(1, id);
            ret = ps.executeUpdate();
            ps.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaisRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PaisRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret > 0;
    }

    @Override
    public PaisDto getRegistroPorId(int id) {
        PaisDto pais = new PaisDto();
        
        String sql = "SELECT pais_id, pais, ultima_atualizacao FROM pais  " +
                "WHERE pais_id=?";
        
        try {
            PreparedStatement stmt = getPreparedStatement(sql);
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
             
            while ( rs.next() ) {                
                pais = new PaisDto(rs.getInt("pais_id"),
                        rs.getString("pais") 
                       /* rs.getDate("ultima_atualizacao") */);
            }
             rs.close();
            stmt.close();

        } catch (ClassNotFoundException e) {
            Logger.getLogger(PaisRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            Logger.getLogger(PaisRepository.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return pais;
        
    }

    @Override
    public List<PaisDto> getListaTodos() {
          List<PaisDto> p = new LinkedList<PaisDto>();
      
        String sql = "SELECT * FROM pais ";
       
        try {
            
            PreparedStatement stmt = getPreparedStatement(sql);
            ResultSet rs = stmt.executeQuery();
           
         
            while(rs.next()){

                p.add( new PaisDto(rs.getInt("pais_id"),
                                      rs.getString("pais")
                                     /* rs.getTimestamp("ultima_atualizacao")*/) );
            }
          
            
            rs.close();
            stmt.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaisRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PaisRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }

    @Override
    public List<PaisDto> getPesquisa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
