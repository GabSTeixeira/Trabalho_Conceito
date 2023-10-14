package br.com.webank.webank.dto.titular;

import java.util.List;

import br.com.webank.webank.dto.contaBancaria.ContaBancariaRequestDTO;
import br.com.webank.webank.dto.endereco.EnderecoRequestDTO;

public class TitularRequestDTO  extends TitularBaseDTO{

    private EnderecoRequestDTO endereco;
    private List<ContaBancariaRequestDTO> contas;

    public EnderecoRequestDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoRequestDTO endereco) {
        this.endereco = endereco;
    }

    public List<ContaBancariaRequestDTO> getContas() {
        return contas;
    }

    public void setContas(List<ContaBancariaRequestDTO> contas) {
        this.contas = contas;
    }
}
