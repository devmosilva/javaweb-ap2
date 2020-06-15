/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frb.edu.br.matheus.contracts;

import frb.edu.br.matheus.entity.CityDto;
import java.util.List;

/**
 *
 * @author m_oli
 */
public interface ICity {
       boolean incluir(CityDto cidade);
    boolean alterar(CityDto cidade);
    boolean deletar(int id);
    CityDto getRegistroPorId(int id);
    List<CityDto> getListaTodos();
}
