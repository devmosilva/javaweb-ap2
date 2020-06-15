    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frb.edu.br.matheus.contracts;

import frb.edu.br.matheus.entity.AddressDto;
import java.util.List;

/**
 *
 * @author m_oli
 */
public interface IAddress {
    
      boolean incluir(AddressDto address);
    boolean alterar(AddressDto address);
    boolean deletar(int id);
    AddressDto getRegistroPorId(int id);
    List<AddressDto> getListaTodos();
   
}
