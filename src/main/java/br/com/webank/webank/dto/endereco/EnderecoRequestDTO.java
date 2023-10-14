package br.com.webank.webank.dto.endereco;

import br.com.webank.webank.dto.titular.TitularRequestDTO;

public class EnderecoRequestDTO extends EnderecoBaseDTO {
    
    private TitularRequestDTO titular;

    public TitularRequestDTO getTitular() {
        return titular;
    }

    public void setTitular(TitularRequestDTO titular) {
        this.titular = titular;
    }

    
}
