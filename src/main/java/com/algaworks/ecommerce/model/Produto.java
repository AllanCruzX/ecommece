package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter

@EntityListeners({GenericoListener.class})
@Entity
@Table(name = "produto")
public class Produto extends EntidadeBaseInteger {


    //updatable = false - para que o atributo não seja atualizado
    @Column(name = "data_criacao" , updatable = false)
    private LocalDateTime dataCriacao;

    //insertable = false - para que o atributo não tenha valor na inclusão
    @Column(name = "data_ultima_atualizacao" , insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;


    @OneToOne(mappedBy = "produto" )
    private Estoque estoque;


    @ElementCollection
    @CollectionTable(name = "produto_tag",
            joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "tag")
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_atributo",
            joinColumns = @JoinColumn(name = "produto_id"))
    private List<Atributo> atributos;
}
