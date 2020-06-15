
package frb.edu.br.matheus.entity;

import java.sql.Timestamp;

/**
 *
 * @author Matheus
 */
public class CityDto {
    
    private int cidade_id;
    private String cidade;
    private PaisDto pais_id;
    private Timestamp ultima_atualizacao;

    public CityDto() {
        pais_id = new PaisDto();
    }


    
    public CityDto(int cidade_id, String cidade, PaisDto pais_id, Timestamp ultima_atualizacao) {
        this.cidade_id = cidade_id;
        this.cidade = cidade;
        this.pais_id = pais_id;
        this.ultima_atualizacao = ultima_atualizacao;
    }

    public int getCidade_id() {
        return cidade_id;
    }

    public void setCidade_id(int cidade_id) {
        this.cidade_id = cidade_id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public PaisDto getPais_id() {
        return pais_id;
    }

    public void setPais_id(PaisDto pais_id) {
        this.pais_id = pais_id;
    }

    public Timestamp getUltima_atualizacao() {
        return ultima_atualizacao;
    }

    public void setUltima_atualizacao(Timestamp ultima_atualizacao) {
        this.ultima_atualizacao = ultima_atualizacao;
    }
    
    
    
}
