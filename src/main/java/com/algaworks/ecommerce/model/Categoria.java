package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "categoria")
public class Categoria extends EntidadeBaseInteger {

   // @EqualsAndHashCode.Include
   // @Id
  //  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
 //   @SequenceGenerator(name = "seq", sequenceName = "categoria_sequencias_chave_primaria")
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
 //   private Integer id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;

    @OneToMany(mappedBy = "categoriaPai")
    private List<Categoria> categorias;

    @ManyToMany(mappedBy = "categorias")
    private  List<Produto> produtos;
}