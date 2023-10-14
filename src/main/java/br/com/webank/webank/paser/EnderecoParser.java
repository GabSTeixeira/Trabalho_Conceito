package br.com.webank.webank.paser;

import br.com.webank.webank.dto.endereco.EnderecoRequestDTO;
import br.com.webank.webank.dto.endereco.EnderecoResponseDTO;
import br.com.webank.webank.model.Endereco;

public class EnderecoParser {
    
    public static Endereco converterEnderecoRequestEmEnderecoModel(EnderecoRequestDTO enderecoRequest){
        Endereco  endereco = new Endereco();
        endereco.setId(0l);
        endereco.setCep(enderecoRequest.getCep());
        endereco.setLogradouro(enderecoRequest.getLogradouro());
        endereco.setNumero(enderecoRequest.getNumero());
        endereco.setComplemento(enderecoRequest.getComplemento());
        endereco.setBairro(enderecoRequest.getBairro());
        endereco.setCidade(enderecoRequest.getCidade());
        // Nunca devolver a uf pois faz parte da regra xpto
        // endereco.setUf(enderecoRequest.getUf());

        return endereco;
    }

    public static EnderecoResponseDTO converterEnderecoModelEmEnderecoResponseDTO(Endereco enderecoModel){
        EnderecoResponseDTO endereco = new  EnderecoResponseDTO();
        endereco.setId(enderecoModel.getId());

        return endereco;
    }
}
