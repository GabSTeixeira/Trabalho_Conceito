package br.com.webank.webank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.webank.webank.dto.contaBancaria.ContaBancariaRequestDTO;
import br.com.webank.webank.dto.contaBancaria.ContaBancariaResponseDTO;
import br.com.webank.webank.model.ContaBancaria;
import br.com.webank.webank.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    public List<ContaBancariaResponseDTO> obterTodos(){
        
        List<ContaBancaria> listaContaBancaria = contaBancariaRepository.findAll();
        List<ContaBancariaResponseDTO> listaResponse = new ArrayList<>();

        for (ContaBancaria conta :  listaContaBancaria) {

            listaResponse.add(modelMapper.map(conta, ContaBancariaResponseDTO.class));
        }

        return listaResponse; 
    }

    public ContaBancariaResponseDTO obterPorId(long id){
        Optional<ContaBancaria> optContaBancaria = contaBancariaRepository.findById(id);

        if(optContaBancaria.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        ContaBancariaResponseDTO contaBancariaResponse = modelMapper.map(optContaBancaria.get(), ContaBancariaResponseDTO.class);

        return contaBancariaResponse;
    }

    // O save serve tanto para adicionar quanto para atualizar.
    // se tiver id, ele atualiza, s enão tiver id ele adiciona.
    public ContaBancariaResponseDTO adicionar(ContaBancariaRequestDTO contaBancariaRequest){
        
        
        ContaBancaria contaBancaria = modelMapper.map(contaBancariaRequest, ContaBancaria.class);
        contaBancaria.setId(0);
        
        contaBancaria = contaBancariaRepository.save(contaBancaria);
        
        return modelMapper.map(contaBancaria, ContaBancariaResponseDTO.class); 
    }

    public ContaBancariaResponseDTO atualizar(long id, ContaBancariaRequestDTO contaBancariaRequest){

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        ContaBancaria contaBancaria = modelMapper.map(contaBancariaRequest, ContaBancaria.class);

        contaBancaria.setId(id);
        contaBancaria = contaBancariaRepository.save(contaBancaria);

        return modelMapper.map(contaBancaria, ContaBancariaResponseDTO.class);
    }

    public void deletar(Long id){
        obterPorId(id);

        contaBancariaRepository.deleteById(id);
    }

}
