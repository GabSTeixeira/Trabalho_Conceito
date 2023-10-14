package br.com.webank.webank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.webank.webank.dto.contaBancaria.ContaBancariaRequestDTO;
import br.com.webank.webank.dto.contaBancaria.ContaBancariaResponseDTO;
import br.com.webank.webank.dto.endereco.EnderecoRequestDTO;
import br.com.webank.webank.dto.endereco.EnderecoResponseDTO;
import br.com.webank.webank.dto.titular.TitularRequestDTO;
import br.com.webank.webank.dto.titular.TitularResponseDTO;
import br.com.webank.webank.model.ContaBancaria;
import br.com.webank.webank.model.Endereco;
import br.com.webank.webank.model.Titular;
import br.com.webank.webank.repository.TitularRepository;

@Service
public class TitularService {
    
    @Autowired
    private TitularRepository titularRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ContaBancariaService contaBancariaService;

    @Autowired
    private ModelMapper mapper;

    public List<TitularResponseDTO> obterTodos(){

       List<Titular> titulares = titularRepository.findAll();

        return titulares
            .stream()
            .map(titular -> mapper.map(titular, TitularResponseDTO.class))
            .collect(Collectors.toList());
    }

    public TitularResponseDTO obterPorId(long id){
        Optional<Titular> optTitular = titularRepository.findById(id);

        if(optTitular.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return  mapper.map(optTitular.get(), TitularResponseDTO.class);
    }

    @Transactional
    public TitularResponseDTO adicionar(TitularRequestDTO titularRequest){

        // Cadastrando o titular... 
        Titular titularModel = adicionarTitular(titularRequest);
        EnderecoRequestDTO enderecoRequest = titularRequest.getEndereco();
        
        titularRequest.setId(titularModel.getId());

        enderecoRequest.setTitular(titularRequest);
        Endereco enderecoModel = adicionarEndereco(enderecoRequest);

        titularModel.setEndereco(enderecoModel);

        List<ContaBancaria> contas = adicionarContas(titularRequest.getContas(), titularModel);
        titularModel.setContas(contas);

        return mapper.map(titularModel, TitularResponseDTO.class);
    }

    public TitularResponseDTO atualizar(long id, TitularRequestDTO titular){

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        titular.setId(id);
        Titular titularModel = titularRepository.save(mapper.map(titular, Titular.class));

        return mapper.map(titularModel, TitularResponseDTO.class);
    }

    public void deletar(Long id){
        obterPorId(id);

        titularRepository.deleteById(id);
    }

    private Titular adicionarTitular(TitularRequestDTO titularRequest){

        Titular titularModel = mapper.map(titularRequest, Titular.class);
        titularModel.setId(0);
        titularModel = titularRepository.save(titularModel);

        return titularModel;
    }

    private Endereco adicionarEndereco(EnderecoRequestDTO enderecoRequest){
        
        EnderecoResponseDTO enderecoResponse = enderecoService.adicionar(enderecoRequest);

        return mapper.map(enderecoResponse, Endereco.class);
    }

    private List<ContaBancaria> adicionarContas(List<ContaBancariaRequestDTO> contasRequest, Titular titularModel){
        
        List<ContaBancaria> adicionadas = new ArrayList<>();

        for(ContaBancariaRequestDTO contaBancariaRequest : contasRequest){
         
            ContaBancariaResponseDTO contaBancariaResponse = contaBancariaService.adicionar(contaBancariaRequest);
            
            ContaBancaria contaBancaria = mapper.map(contaBancariaResponse, ContaBancaria.class);
            contaBancaria.setTitular(titularModel);
            
            adicionadas.add(contaBancaria);
        }

        return adicionadas;
    }

}
