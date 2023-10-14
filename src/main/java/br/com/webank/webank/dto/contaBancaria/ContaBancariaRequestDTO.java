package br.com.webank.webank.dto.contaBancaria;


public class ContaBancariaRequestDTO extends ContaBancariaBaseDTO {
   
    private Long idTitular;

    public Long getIdTitular() {
        return idTitular;
    }

    public void setIdTitular(Long idTitular) {
        this.idTitular = idTitular;
    }    
}
