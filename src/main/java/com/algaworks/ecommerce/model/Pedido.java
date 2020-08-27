
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

@Getter
@Setter

@EntityListeners({GerarNotaFiscalListener.class , GenericoListener.class})
@Entity
@Table(name = "pedido")
public class Pedido extends EntidadeBaseInteger {



    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao" , insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao" )
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @Column(precision = 19, scale = 2 , nullable = false)
    private BigDecimal total;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    // optional = false - torna obrigatorio setar cliente (O JPA pode usar o inner join ao invez de left outer join)(left join e menos performatico que o inner join )
    @ManyToOne(optional = false , cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cliente_id" , nullable = false ,
            foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido" , cascade = CascadeType.PERSIST )
    private List<ItemPedido> itens;

    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamento;

    public boolean isPago() {
        return StatusPedido.PAGO.equals(status);
    }

    //    @PrePersist
//    @PreUpdate
    public void calcularTotal() {
        if (itens != null) {
            total = itens.stream().map(ItemPedido::getPrecoProduto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    @PrePersist
    public void aoPersistir() {
        dataCriacao = LocalDateTime.now();
        calcularTotal();
    }

    @PreUpdate
    public void aoAtualizar() {
        dataUltimaAtualizacao = LocalDateTime.now();
        calcularTotal();
    }

    @PostPersist
    public void aposPersistir() {
        System.out.println("Após persistir Pedido.");
    }

    @PostUpdate
    public void aposAtualizar() {
        System.out.println("Após atualizar Pedido.");
    }

    @PreRemove
    public void aoRemover() {
        System.out.println("Antes de remover Pedido.");
    }

    @PostRemove
    public void aposRemover() {
        System.out.println("Após remover Pedido.");
    }

    @PostLoad
    public void aoCarregar() {
        System.out.println("Após carregar o Pedido.");
    }
}