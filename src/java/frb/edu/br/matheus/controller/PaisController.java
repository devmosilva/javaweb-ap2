/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frb.edu.br.matheus.controller;

import frb.edu.br.matheus.contracts.IPais;
import frb.edu.br.matheus.entity.PaisDto;
import frb.edu.br.matheus.repository.PaisRepository;
import java.util.List;

/**
 *
 * @author m_oli
 */
public class PaisController {
    private PaisDto pais;
    private List<PaisDto> paises = null;

    private IPais paisRepositorio = new PaisRepository();

    public PaisController() {
    }

    public PaisDto getPais() {
        return pais;
    }

    public void setPais(PaisDto pais) {
        this.pais = pais;
    }

    public List<PaisDto> getPaises() {
        if(paises == null){
            paises = paisRepositorio.getListaTodos();
        }
        return paises;
    }

    public String prepararInclusao(){
        pais = new PaisDto();
        return "vaiParaIncluir";
    }

    public String finalizaInclusao(){

        paisRepositorio.incluir(pais);
        paises = null;
        return "voltaParaListagem";
    }

    public String finalizaEdicao(){
        paisRepositorio.alterar(pais);
        paises = null;
        return "voltaParaListagem";
    }

    public String finalizaDelecao(){
        paisRepositorio.deletar( pais.getPais_id() );
        paises = null;
        return "";
    }

}