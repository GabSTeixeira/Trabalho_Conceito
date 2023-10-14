package br.com.webank.webank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.webank.webank.model.Titular;

public interface TitularRepository extends JpaRepository<Titular, Long> {
    
}
