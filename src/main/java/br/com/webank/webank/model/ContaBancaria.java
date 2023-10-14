package br.com.webank.webank.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ContaBancaria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContaBancaria")
    private long id;

    @Column(nullable = false, length = 4)
    private String agencia;
    
    @Column(nullable = false, length = 6)
    private String numero; 

    @Column(nullable = false)
    private double saldo;

    @Column(nullable = false)
    private Date dataCadastro;

    @ManyToOne
    @JoinColumn(name = "idTitular", nullable = false) // Aqui vai gerar no banco uma coluna na tabela contaBancaria com o id_titular
    @JsonBackReference
    private Titular titular;

    public ContaBancaria(long id, String agencia, String numero, double saldo) {
        this.id = id;
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.dataCadastro = new Date();
    }   

    public ContaBancaria() {
        this.dataCadastro = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }    
        
    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

}
