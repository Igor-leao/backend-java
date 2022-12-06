package dev.rinaldo.designpatterns.structural;

import java.math.BigDecimal;

/**
 * Java Design Patterns - Adapter (3)
 * 
 * @author youtube.com/RinaldoDev
 */
public class Adapter_3 {
    
    public static void main(String[] args) {
        MeuPagamentoCredito3 credito = new MeuPagamentoCredito3();
        credito.debitar(new BigDecimal("100"));
    }
    
}
/*
 * VANTAGENS:
 * HERANÇA, REUTILIZAÇÃO DE CÓDIGO, TWO WAY (MÃO DUPLA)
 * REUSO
 * SRP - SOLID MANTER SÓ UMA RESPONSABILIDADE DENTRO DA MINHA CLASSE
 * NÃO ALTERO A CLASSE SDK, PERMITO QUE OUTROS USEM A MINHA CLASSE ADATPTER
 * ------------
 * DESVANTAGENS:
 * HERANÇA, FICO PRESO A UTILIZAÇÃO DE HERANÇA (NÃO TÃO PERSONALIZÁVEL)
 */


//----------------------------------------------------

interface ProcessorPagamento3 {
    
    void debitar(BigDecimal valor);
    
    void creditar(BigDecimal valor);
    
}

//----------------------------------------------------

class MeuPagamentoCredito3 extends SdkPagamentoCredito3 
                           implements ProcessorPagamento3 {

    // ...
    
    public void debitar(BigDecimal valor) {
        super.autorizar(valor);
        super.capturar(valor);
    }
    
    public void creditar(BigDecimal valor) {
        super.creditar(valor);
    }
    
//    @Override
//    void autorizar(BigDecimal valor) {
//        // mudou o comportamento - não é mais two-way
//    }
    
}

//----------------------------------------------------

class SdkPagamentoCredito3 {
    
    void autorizar(BigDecimal valor) {
        // autoriza
    }
    
    void capturar(BigDecimal valor) {
        // captura
    }
    
    void creditar(BigDecimal valor) {
        // credita
    }
    
}

/*
 * /
 * twitter.com/rinaldodev
 * linkedin.com/in/rinaldodev
 * twitch.tv/rinaldodev
 * github.com/rinaldodev
 * facebook.com/rinaldodev
 * www.rinaldo.dev
 * /
 */

/*
 * ATENÇÃO: Esse arquivo é um material que acompanha a explicação em vídeo no YouTube. Não se baseie nesse exemplo para copiar e
 * colar diretamente no seu código sem anter entender do que se trata. Nem todas as boas práticas estão sendo aplicadas aqui, e
 * muitas coisas são omitidas por uma questão didática. Assista o vídeo completo no canal RinaldoDev no YouTube.
 */