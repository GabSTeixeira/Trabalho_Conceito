package br.com.webank.webank.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
// @Table(name = "titular")
public class Titular {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTitular")
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpfOuCnpj; //cpf_ou_cnpj

    @Column(nullable = false, unique = true) 
    private String email;

    @Column(nullable = false)
    private String telefone;

    private Date dataNascimento;

    @OneToOne(mappedBy = "titular") // Aqui eu coloco o nome da tabela forte, aquela que vai ter seu id na tabela fraca.
    private Endereco endereco;

    @OneToMany(mappedBy = "titular")
    private List<ContaBancaria> contas;

    public Titular(long id, String nome, String cpfOuCnpj, String email, String telefone,
            Date dataNascimento, Endereco endereco, ContaBancaria conta) {
        this.id = id;
        this.nome = nome;
        this.cpfOuCnpj = cpfOuCnpj;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.contas = new ArrayList<ContaBancaria>();
        this.contas.add(conta);
    }

    
    public Titular(long id) {
        this.id = id;
    }

    public Titular(){}

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<ContaBancaria> getContas() {
        return contas;
    }

    public void setContas(List<ContaBancaria> contas) {
        this.contas = contas;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
