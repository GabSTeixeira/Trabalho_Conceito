package br.com.webank.webank.dto.contaBancaria;

import java.util.Date;

public class ContaBancariaResponseDTO extends ContaBancariaBaseDTO {
    
    private Date dataCadastro;
    
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
