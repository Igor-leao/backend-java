package com.api.banco.model;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;
import java.util.List;

@Data
public class Cobranca {
    private UUID idCobranca;
    private String seuNumero;
    private String dataVencimento;
    private Integer numDiasAgenda;
    private Object pagador;
    private Object mensagem;
    private List<Object> descontos;
    private Object multa;
    private Object mora;
    private Object beneficiarioFinal;


}
