package com.example.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Loja {

    private String nomeLoja;
    private Endereco endereco;
    private String telefone;
    private String observacao;
    private String cnpj;
    private String inscricaoEstadual;
    private List<Venda> vendas = new ArrayList<Venda>();

    public void setVendas(Venda venda) {
        this.vendas.add(venda);
    }

    public List<Venda> getVendas(){
        return this.vendas;
    }

    public Venda vender(Date dataHora,String ccf, String coo){
        Venda venda = new Venda(this,dataHora,ccf,coo);
        this.setVendas(venda);
        return venda;
    }

    public Loja(String nomeLoja, Endereco endereco, String telefone,
                String observacao, String cnpj, String inscricaoEstadual) {
        this.nomeLoja = nomeLoja;
        this.endereco = endereco;
        this.telefone = telefone;
        this.observacao = observacao;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getNomeLoja() {
        return this.nomeLoja;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getObservacao() {
        return this.observacao;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getInscricaoEstadual() {
        return this.inscricaoEstadual;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public static Boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public void validarCamposObrigatorio(){
        if (isNullOrEmpty(this.getNomeLoja())){
            throw new RuntimeException("O campo nome da loja é obrigatório");
        }

        if (isNullOrEmpty(this.getCnpj())){
            throw new RuntimeException("O campo cnpj da loja é obrigatório");
        }

        if (isNullOrEmpty(this.getInscricaoEstadual())) {
            throw new RuntimeException("O campo inscrição estadual da loja é obrigatório");
        }
    }


    public String dadosLoja() {
		// Implemente aqui
        validarCamposObrigatorio();

        String _CEP = "";
        String _TELEFONE = "";

        if (!isNullOrEmpty(endereco.getCep())) {
            _CEP = "CEP:" + endereco.getCep();
            if (!isNullOrEmpty(this.getTelefone())){
                _TELEFONE = " Tel " + this.getTelefone();
            }
        } else {
            _CEP = "";
            if (!isNullOrEmpty(this.getTelefone())){
                _TELEFONE = "Tel " + this.getTelefone();
            }
        }

        String _OBSERVACAO = "";
        if (!isNullOrEmpty(this.getObservacao())){
            _OBSERVACAO = this.getObservacao();
        }

        String _texto = "";
        _texto = String.format("%s\n",this.getNomeLoja());
        _texto += endereco.dadosEndereco();
        _texto += String.format("%s%s\n",_CEP,_TELEFONE);
        _texto += String.format("%s\n",_OBSERVACAO);
        _texto += String.format("CNPJ: %s\n",this.getCnpj());
        _texto += String.format("IE: %s\n",this.getInscricaoEstadual());
        return _texto;
	}

}