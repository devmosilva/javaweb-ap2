
package frb.edu.br.matheus.controller;

import frb.edu.br.matheus.contracts.ICity;
import frb.edu.br.matheus.entity.CityDto;
import frb.edu.br.matheus.repository.CityRepository;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class CityController {
  private CityDto cidade;
    private List<CityDto> cidades = null;

    private ICity cidadeRepositorio = new CityRepository();

    public CityController() {
    }

    public CityDto getCidade() {
        return cidade;
    }

    public void setCidade(CityDto cidade) {
        this.cidade = cidade;
    }

    public List<CityDto> getCidades() {
        if(cidades == null){
            cidades = cidadeRepositorio.getListaTodos();
        }
        return cidades;
    }
    public String prepararInclusao(){
        cidade = new CityDto();
        return "vaiParaIncluir";
    }

    public String finalizaInclusao(){

        cidadeRepositorio.incluir(cidade);
        cidades = null;
        return "voltaParaListagem";
    }

    public String finalizaEdicao(){
        cidadeRepositorio.alterar(cidade);
        cidades = null;
        return "voltaParaListagem";
    }

    public String finalizaDelecao(){
        cidadeRepositorio.deletar( cidade.getCidade_id());
        cidades = null;
        return "";
    }

}
