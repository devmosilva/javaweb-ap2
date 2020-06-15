/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frb.edu.br.matheus.contracts;

import frb.edu.br.matheus.entity.PaisDto;
import java.util.List;

/**
 *
 * @author m_oli
 */
public interface IPais {
    
    boolean incluir(PaisDto pais);
    boolean alterar(PaisDto pais);
    boolean deletar(int id);
    PaisDto getRegistroPorId(int id);
    List<PaisDto> getListaTodos();
    List<PaisDto> getPesquisa();
}

