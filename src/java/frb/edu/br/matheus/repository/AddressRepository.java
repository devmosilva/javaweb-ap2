
package frb.edu.br.matheus.repository;

import com.sun.xml.ws.security.opt.api.tokens.Timestamp;
import frb.edu.br.matheus.contracts.IAddress;
import frb.edu.br.matheus.data.DaoUtil;
import frb.edu.br.matheus.entity.AddressDto;
import frb.edu.br.matheus.entity.CityDto;
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
public class AddressRepository extends DaoUtil implements IAddress{

    @Override
    public boolean incluir(AddressDto address) {
        String sql = "INSERT INTO endereco (endereco,endereco2,bairro,cidade_id,cep,telefone)"+
                "VALUES (?,?,?,?,?,?)";
        
        PreparedStatement stmt;
        int ret=-1;
        
        try{    
            stmt = getPreparedStatement(sql);
             
            stmt.setString(1, address.getEndereco());
            stmt.setString(2, address.getEndereco2());
            stmt.setString(3, address.getBairro());
            //suspeito de erro aqui em baixo
            stmt.setInt(4, address.getCidade_id().getCidade_id());
            stmt.setString(5, address.getCep());
            stmt.setString(6, address.getTelefone());
            
            ret = stmt.executeUpdate();
            stmt.close();

        }  catch (ClassNotFoundException e) {
            Logger.getLogger(AddressRepository.class.getName()).log(Level.SEVERE, null, e);
        }catch (SQLException ex){
            Logger.getLogger(AddressRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret >0;
    }

    @Override
    public boolean alterar(AddressDto address) {
       String sql = "UPDATE endereco SET endereco = ?, endereco2 = ?, bairro = ?, cidade_id = ?, cep = ?, telefone = ? WHERE endereco_id=?";
        PreparedStatement ps;
        int ret=-1;
        try {
            ps = getPreparedStatement(sql);
            ps.setString(1, address.getEndereco());
            ps.setString(2, address.getEndereco2());
            ps.setString(3, address.getBairro());
            ps.setInt(4, address.getCidade_id().getCidade_id());
            ps.setString(5, address.getCep());
            ps.setString(6, address.getTelefone());

            ret = ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException e) {
            Logger.getLogger(AddressRepository.class.getName()).log(Level.SEVERE, null, e);
        }catch (SQLException ex){
            Logger.getLogger(AddressRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret >0;
    }

    @Override
    public boolean deletar(int id) {
          String sql = "DELETE FROM endereco " +
                     " WHERE endereco_id=?";
        PreparedStatement ps;
        int ret=-1;
        try {
            ps = getPreparedStatement(sql);
            ps.setInt(1, id);
            ret = ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException e) {
            Logger.getLogger(AddressRepository.class.getName()).log(Level.SEVERE, null, e);
        }catch (SQLException ex){
            Logger.getLogger(AddressRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret >0;
    }

    @Override
    public AddressDto getRegistroPorId(int id) {
          AddressDto endereco = new AddressDto();

        String sql = "SELECT endereco_id, endereco, endereco2, bairro, cidade_id, cep, telefone, ultima_atualizacao FROM endereco WHERE endereco_id=?";

        CityRepository city = new CityRepository();
        try {
            PreparedStatement ps = getPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                endereco = new AddressDto(rs.getInt("endereco_id"), 
                        rs.getString("endereco"), 
                        rs.getString("endereco2"),
                        rs.getString("bairro"),
                        city.getRegistroPorId(rs.getInt("cidade_id")),
                        rs.getString("cep"),
                        rs.getString("telefone"),
                        rs.getDate("ultima_atualizacao"));
            }
            rs.close();
            ps.close();
        } catch (ClassNotFoundException e) {
            Logger.getLogger(AddressRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            Logger.getLogger(AddressRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return endereco;
    }

    @Override
    public List<AddressDto> getListaTodos() {
         List<AddressDto> endereco = new LinkedList<AddressDto>();
       String sql = "SELECT * FROM endereco";

        CityRepository cidade = new CityRepository();

        try {
            PreparedStatement ps = getPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {

                CityDto city = cidade.getRegistroPorId(rs.getInt("cidade_id"));
                endereco.add( new AddressDto( 
                        rs.getInt("endereco_id"),
                        rs.getString("endereco"), 
                        rs.getString("endereco2"),
                        rs.getString("bairro"),
                        city,
                        rs.getString("cep"),
                        rs.getString("telefone"),
                        rs.getTimestamp("ultima_atualizacao")
                        )
                );
            }
            rs.close();
            ps.close();
        } catch (ClassNotFoundException e) {
            Logger.getLogger(CityRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            Logger.getLogger(CityRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return endereco;
    }
    
}
