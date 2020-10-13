package com.example.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Venda {
    private String BREAK = System.lineSeparator();
    private Date datahora;
    private String ccf;
    private String coo;
    private Loja loja;

    public Venda(Loja loja,Date datahora, String ccf, String coo) {
        this.datahora = datahora;
        this.ccf = ccf;
        this.coo = coo;
        this.loja = loja;
    }

    public static Boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public void validarCamposObrigatorio(){
        if (isNullOrEmpty(this.ccf)){
            throw new RuntimeException("O campo ccf da venda é obrigatório");
        }

        if (isNullOrEmpty(this.coo)){
            throw new RuntimeException("O campo coo da venda é obrigatório");
        }
    }


    public String dadosVenda(){
        this.validarCamposObrigatorio();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy H:m:s");
        String _texto = "";
        _texto = String.format("%sV CCF:%s COO: %s",formato.format(this.datahora),this.ccf,this.coo);

        return _texto;
    }

    public String imprimeCupom(){

        String dadosLoja = this.loja.dadosLoja();
        String dadosVenda = this.dadosVenda();

        return String.format("%s------------------------------%s%s",dadosLoja,this.BREAK,dadosVenda);
    }
}
