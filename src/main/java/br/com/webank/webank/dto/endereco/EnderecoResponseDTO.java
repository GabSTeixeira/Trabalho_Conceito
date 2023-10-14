package br.com.webank.webank.dto.endereco;

import br.com.webank.webank.model.Endereco;

public class EnderecoResponseDTO extends EnderecoBaseDTO {
    
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnderecoResponseDTO(Endereco enderecoModel) {
        id = enderecoModel.getId();
        cep = enderecoModel.getCep();
        logradouro = enderecoModel.getLogradouro();
        bairro = enderecoModel.getBairro();
        cidade = enderecoModel.getCidade();
        numero = enderecoModel.getNumero();
    }

    public EnderecoResponseDTO(){}
    
}
