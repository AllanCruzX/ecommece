package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConsultandoRegistrosTest extends EntityManagerTest {

    @Test
    public void busarPorIdentificador() {
        Produto produto = entityManager.find(Produto.class, 1);
//        Produto produto = entityManager.getReference(Produto.class, 1);

        Assert.assertNotNull(produto);
        Assert.assertEquals("Kindle", produto.getNome());
    }

    @Test
    public void atualizarAReferencia() {
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setNome("Microfone Samson");

        entityManager.refresh(produto);

        Assert.assertEquals("Kindle", produto.getNome());
    }

}
