package com.example.project;

public class Endereco {

    private String BREAK = System.lineSeparator();

    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String municipio;
    private String estado;
    private String cep;

    public Endereco(String logradouro, int numero, String complemento, String bairro, String municipio, String estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.municipio = municipio;
        this.estado = estado;
        this.cep = cep;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public String getBairro() {
        return this.bairro;
    }

    public String getMunicipio() {
        return this.municipio;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getCep() {
        return this.cep;
    }

    public static Boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public void validarCamposObrigatorio(){

        if (isNullOrEmpty(this.getLogradouro())){
            throw new RuntimeException("O campo logradouro do endereço é obrigatório");
        }

        if (isNullOrEmpty(this.getMunicipio())){
            throw new RuntimeException("O campo município do endereço é obrigatório");
        }

        if (isNullOrEmpty(this.getEstado())){
            throw new RuntimeException("O campo estado do endereço é obrigatório");
        }
    }

    public String dadosEndereco() {
        validarCamposObrigatorio();
        String _COMPLEMENTO = isNullOrEmpty(this.complemento) ? "" : " " + this.complemento ;
        String _BAIRRO = isNullOrEmpty(this.bairro) ? "" : this.bairro + " - ";
        String _texto = "";
        if (this.getNumero() == 0){
            _texto += String.format("%s, %s%s" + BREAK,this.getLogradouro(),"s/n",_COMPLEMENTO);
        } else {
            _texto += String.format("%s, %d%s" + BREAK,this.getLogradouro(),this.getNumero(),_COMPLEMENTO);
        }
        _texto += String.format("%s%s - %s"+ BREAK,_BAIRRO,this.getMunicipio(),this.getEstado());

        return _texto;
    }
}