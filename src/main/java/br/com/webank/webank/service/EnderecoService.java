package br.com.webank.webank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.webank.webank.dto.endereco.EnderecoRequestDTO;
import br.com.webank.webank.dto.endereco.EnderecoResponseDTO;
import br.com.webank.webank.model.Endereco;
import br.com.webank.webank.paser.EnderecoParser;
import br.com.webank.webank.repository.EnderecoRepository;

@Service
public class EnderecoService {
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ModelMapper mapper;

    public List<EnderecoResponseDTO> obterTodos(){
        // Aqui preciso converter a minha lista de endereco em uma lista de enderecoResponseDTO
        List<Endereco> enderecosModel = enderecoRepository.findAll();
        
        List<EnderecoResponseDTO> enderecosResponse = new ArrayList<>();

        for (Endereco endereco : enderecosModel) {
            // enderecosResponse.add(new EnderecoResponseDTO(endereco));
            enderecosResponse.add(mapper.map(endereco, EnderecoResponseDTO.class));
        }

        return  enderecosResponse;
    }

    public EnderecoResponseDTO obterPorId(long id){
        Optional<Endereco> optEndereco = enderecoRepository.findById(id);

        if(optEndereco.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        // return new EnderecoResponseDTO(optEndereco.get());
        return mapper.map(optEndereco.get(), EnderecoResponseDTO.class);
    }

    // O save serve tanto para adicionar quanto para atualizar.
    // se tiver id, ele atualiza, s enão tiver id ele adiciona.
    public EnderecoResponseDTO adicionar(EnderecoRequestDTO enderecoRequest){

        Endereco enderecoModel = mapper.map(enderecoRequest, Endereco.class);
        // enderecoModel.setTitular(new Titular(enderecoRequest.getIdTitular()));

        enderecoModel = enderecoRepository.save(enderecoModel);

        // return new EnderecoResponseDTO(enderecoModel);
        return mapper.map(enderecoModel, EnderecoResponseDTO.class);
    }

    public EnderecoResponseDTO atualizar(long id, EnderecoRequestDTO endereco){

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        Endereco enderecoModel = EnderecoParser.converterEnderecoRequestEmEnderecoModel(endereco);
        enderecoModel.setId(id);

        enderecoModel = enderecoRepository.save(enderecoModel);

        return EnderecoParser.converterEnderecoModelEmEnderecoResponseDTO(enderecoModel);
    }

    public void deletar(Long id){
        obterPorId(id);

        enderecoRepository.deleteById(id);
    }

}
