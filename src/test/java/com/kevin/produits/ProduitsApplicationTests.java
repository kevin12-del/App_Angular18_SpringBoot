package com.kevin.produits;

import com.kevin.produits.entities.Categorie;
import com.kevin.produits.entities.Produit;
import com.kevin.produits.repos.ProduitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class ProduitsApplicationTests {

    @Autowired
    private ProduitRepository produitRepository;

    @Test
    public void testCreateProduit() {
        Produit prod = new Produit("PC Dell",2000.00,new Date());
        produitRepository.save(prod);
    }

    @Test
    public void testFindProduit()
    {
        Produit p = produitRepository.findById(1L).get();
        System.out.println(p);
    }

    @Test
    public void testUpdateProduit()
    {
        Produit p = produitRepository.findById(1L).get();
        p.setPrixProduit(1000.0);
        produitRepository.save(p);
    }

    @Test
    public void testDeleteProduit()
    {
        produitRepository.deleteById(1L);;
    }
    @Test
    public void testListerTousProduits()
    {
        List<Produit> prods = produitRepository.findAll();
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

    @Test
    public void testFindProduitByNom()
    {
        List<Produit> prods = produitRepository.findByNomProduit("PC Dell");
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

    @Test
    public void testFindProduitByNomContains()
    {
        List<Produit> prods = produitRepository.findByNomProduitContains("PC");
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

    @Test
    public void testfindByNomPrix()
    {
        List<Produit> prods = produitRepository.findByNomPrix("PC Dell", 2000.0);
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

    @Test
    public void testfindByCategorie()
    {
        Categorie cat = new Categorie();
        cat.setIdCat(1L);
        List<Produit> prods = produitRepository.findByCategorie(cat);
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

    @Test
    public void findByCategorieIdCat()
    {
        List<Produit> prods = produitRepository.findByCategorieIdCat(1L);
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

    @Test
    public void testfindByOrderByPrixProduitAsc()
    {
        List<Produit> prods = produitRepository.findByOrderByPrixProduitAsc();
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

    @Test
    public void testTrierProduitsDatePrix()
    {
        List<Produit> prods = produitRepository.trierProduitsDatePrix();
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

}
