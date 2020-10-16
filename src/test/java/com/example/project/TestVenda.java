package com.example.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestVenda {

    private String BREAK = System.lineSeparator();

    private String dataRecebida;
    private SimpleDateFormat formato;
    private Date dataFormatada;

    private String ccf = "021784";
    private String coo = "035804";

    private String NOME_LOJA = "Loja 1";
    private String LOGRADOURO = "Log 1";
    private int NUMERO = 10;
    private String COMPLEMENTO = "C1";
    private String BAIRRO = "Bai 1";
    private String MUNICIPIO = "Mun 1";
    private String ESTADO = "E1";
    private String CEP = "11111-111";
    private String TELEFONE = "(11) 1111-1111";
    private String OBSERVACAO = "Obs 1";
    private String CNPJ = "11.111.111/1111-11";
    private String INSCRICAO_ESTADUAL = "123456789";


    private String TEXTO_ESPERADO_CUPOM_COMPLETO = "Loja 1" + BREAK + "Log 1, 10 C1" + BREAK + "Bai 1 - Mun 1 - E1"
            + BREAK + "CEP:11111-111 Tel (11) 1111-1111" + BREAK + "Obs 1" + BREAK + "CNPJ: 11.111.111/1111-11" + BREAK
            + "IE: 123456789" + BREAK + "------------------------------" + BREAK + "13/10/2020 15:39:0V CCF:021784 COO: 035804";

    public TestVenda() throws ParseException {
        this.dataRecebida = "13/10/2020 15:39:00";
        this.formato = new SimpleDateFormat("dd/MM/yyyy H:m:s");
        this.dataFormatada = formato.parse(dataRecebida);
    }


    @Test
    public void testVenda(){
        Endereco endereco = new Endereco(LOGRADOURO,NUMERO,COMPLEMENTO,BAIRRO,MUNICIPIO,ESTADO,CEP);
        Loja loja = new Loja(NOME_LOJA,endereco,TELEFONE,OBSERVACAO,CNPJ,INSCRICAO_ESTADUAL);

        Venda venda = loja.vender(dataFormatada,ccf,coo);
        rodarTestarRetorno(TEXTO_ESPERADO_CUPOM_COMPLETO, venda);
    }

    @Test
    public void testVendaCcfObrigatorio(){
        Endereco endereco = new Endereco(LOGRADOURO,NUMERO,COMPLEMENTO,BAIRRO,MUNICIPIO,ESTADO,CEP);
        Loja loja = new Loja(NOME_LOJA,endereco,TELEFONE,OBSERVACAO,CNPJ,INSCRICAO_ESTADUAL);

        Venda venda = loja.vender(dataFormatada,"",coo);
        verificarCampoObrigatorio("O campo ccf da venda é obrigatório", venda);
    }

    @Test
    public void testVendaCooObrigatorio(){
        Endereco endereco = new Endereco(LOGRADOURO,NUMERO,COMPLEMENTO,BAIRRO,MUNICIPIO,ESTADO,CEP);
        Loja loja = new Loja(NOME_LOJA,endereco,TELEFONE,OBSERVACAO,CNPJ,INSCRICAO_ESTADUAL);

        Venda venda = loja.vender(dataFormatada,ccf,"");
        verificarCampoObrigatorio("O campo coo da venda é obrigatório", venda);
    }


    private void rodarTestarRetorno(String expected, Venda venda) {

        //  action
        String retorno = venda.imprimeCupom();

        //  assertion
        assertEquals(expected, retorno);
    }


    private void verificarCampoObrigatorio(String mensagemEsperada, Venda venda) {
        try {
            venda.imprimeCupom();
        } catch (RuntimeException e) {
            assertEquals(mensagemEsperada, e.getMessage());
        }
    }

//    @Test
//	public void validarX() {
//        fail();
//    }
}