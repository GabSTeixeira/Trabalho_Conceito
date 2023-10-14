package br.com.webank.webank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.webank.webank.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}
