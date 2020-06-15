
package frb.edu.br.matheus.repository;

import frb.edu.br.matheus.contracts.ICity;
import frb.edu.br.matheus.data.DaoUtil;
import frb.edu.br.matheus.entity.CityDto;
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
 * @author Matheus
 */
public class CityRepository extends DaoUtil implements ICity{

    @Override
    public boolean incluir(CityDto cidade) {
String sql = "INSERT INTO cidade(cidade, pais_id) VALUES (?,?)";

        PreparedStatement stmt;
        int ret = -1;
        try {

            stmt = getPreparedStatement(sql);;

            /*Dentro da classe VendaDTO, idProduto foi declarado como um produto.
             por isso é possivel acessar os atributos do produto */
            stmt.setString(1, cidade.getCidade());
            stmt.setInt(2, cidade.getPais_id().getPais_id());
           

            ret = stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException e) {
            Logger.getLogger(CityRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException x) {
            Logger.getLogger(CityRepository.class.getName()).log(Level.SEVERE, null, x);
        }

        return ret > 0;
    }
    //11-06 00:02 editando o SQL pois não estava cadastrando.
    @Override
    public boolean alterar(CityDto cidade) {
        String sql = "UPDATE cidade SET  cidade=? , pais_id=? WHERE cidade_id=?";
        
        PreparedStatement stmt;
        int ret = -1;
        try {

            stmt = getPreparedStatement(sql);

            /*Dentro da classe VendaDTO, idProduto foi declarado como um produto.
             por isso é possivel acessar os atributos do produto */
            stmt.setString(1, cidade.getCidade());
            stmt.setInt(2, cidade.getPais_id().getPais_id());
            stmt.setInt(3, cidade.getCidade_id());
           

            ret = stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException e) {
            Logger.getLogger(CityRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException x) {
            Logger.getLogger(CityRepository.class.getName()).log(Level.SEVERE, null, x);
        }

        return ret > 0;
        
    }
//00:21 11/06 SQL
    @Override
    public boolean deletar(int id) {
       String sql = "DELETE FROM cidade WHERE cidade_id=?";

        PreparedStatement stmt;
        int ret = -1;
        try {
            stmt = getPreparedStatement(sql);
            
            stmt.setInt(1, id);
          
            ret = stmt.executeUpdate();
            stmt.close();
           

            ret = stmt.executeUpdate();
            stmt.close();
            
        } catch (ClassNotFoundException e) {
            Logger.getLogger(CityRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException x) {
            Logger.getLogger(CityRepository.class.getName()).log(Level.SEVERE, null, x);
        }

        return ret > 0;
    }

    @Override
    public CityDto getRegistroPorId(int id) {
           CityDto cidade = new CityDto();

        String sql = "SELECT cidade_id, cidade, pais_id, ultima_atualizacao FROM cidade WHERE cidade_id=?";

        PaisRepository pais = new PaisRepository();
        try {
            PreparedStatement ps = getPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                cidade = new CityDto(rs.getInt("cidade_id"), 
                        rs.getString("cidade"), 
                        pais.getRegistroPorId(rs.getInt("pais_id")),
                        rs.getTimestamp("ultima_atualizacao"));
            }
            rs.close();
            ps.close();
        } catch (ClassNotFoundException e) {
            Logger.getLogger(CityRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            Logger.getLogger(CityRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cidade;
    }

    @Override
    public List<CityDto> getListaTodos() {
      List<CityDto> cidade = new LinkedList<>();
 String sql = "SELECT cidade_id, cidade, pais_id,"  +
         "ultima_atualizacao FROM cidade";
 
         PaisRepository country = new PaisRepository();
         
         try {
            PreparedStatement ps = getPreparedStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next() ){
                PaisDto pais = country.getRegistroPorId(rs.getInt("pais_id"));
                cidade.add( new CityDto(rs.getInt("cidade_id"),
                        rs.getString("cidade"),
                            pais,
                        rs.getTimestamp("ultima_atualizacao")
                ));        
               
            }
             rs.close();
             ps.close();
            
        }  catch (ClassNotFoundException e) {
            Logger.getLogger(CityRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            Logger.getLogger(CityRepository.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
         return cidade;
    }

}
