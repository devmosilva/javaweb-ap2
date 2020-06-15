
package frb.edu.br.matheus.controller;

import frb.edu.br.matheus.contracts.IAddress;
import frb.edu.br.matheus.entity.AddressDto;
import frb.edu.br.matheus.repository.AddressRepository;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class AddressController {
    private AddressDto endereco;
    private List<AddressDto> enderecos = null;

    private IAddress enderecoRepositorio = new AddressRepository();

    public AddressController() {
    }

    public AddressDto getEndereco() {
        return endereco;
    }

    public void setEndereco(AddressDto endereco) {
        this.endereco = endereco;
    }

    public List<AddressDto> getEnderecos() {
        if(enderecos == null){
            enderecos = enderecoRepositorio.getListaTodos();
        }
        return enderecos;
    }

    public String prepararInclusao(){
        endereco = new AddressDto();
        return "vaiParaIncluir";
    }

    public String finalizaInclusao(){

        enderecoRepositorio.incluir(endereco);
        enderecos = null;
        return "voltaParaListagem";
    }

    public String finalizaEdicao(){
        enderecoRepositorio.alterar(endereco);
        enderecos = null;
        return "voltaParaListagem";
    }

    public String finalizaDelecao(){
        enderecoRepositorio.deletar(endereco.getEndereco_id());
        enderecos = null;
        return "";
    }




}
