package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal extends EntidadeBaseInteger {

    //@MapsId -Mapeando chave primaría e estrangeira na mesma coluna

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id" )
    private Pedido pedido;

    // @Lob - Objeto grande
    @Column(nullable = false)
    @Lob
    private byte[] xml;

    @Column(name = "data_emissao", nullable = false)
    private Date dataEmissao;
}