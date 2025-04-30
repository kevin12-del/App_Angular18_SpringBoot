package com.kevin.produits.service;

import com.kevin.produits.entities.Categorie;
import com.kevin.produits.entities.Produit;
import com.kevin.produits.repos.ImageRepository;
import com.kevin.produits.repos.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService{

    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    ImageRepository imageRepository;

    @Override
    public Produit saveProduit(Produit p) {
        return produitRepository.save(p);
    }

    @Override
    public Produit updateProduit(Produit p) {
       /* Long oldProdImageId =
                this.getProduit(p.getIdProduit()).getImage().getIdImage();
        Long newProdImageId = p.getImage().getIdImage();*/
        Produit prodUpdated = produitRepository.save(p);
        /*if (oldProdImageId != newProdImageId) //si l'image a été modifiée
            imageRepository.deleteById(oldProdImageId);*/
        return prodUpdated;
    }

    @Override
    public void deleteProduit(Produit p) {
        produitRepository.delete(p);

    }

    @Override
    public void deleteProduitById(Long id) {
        Produit p = getProduit(id);
        //suuprimer l'image avant de supprimer le produit
        try {
            Files.delete(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        produitRepository.deleteById(id);
    }

    @Override
    public Produit getProduit(Long id) {
        return produitRepository.findById(id).get();
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public List<Produit> findByNomProduit(String nom) {
        return produitRepository.findByNomProduit(nom);
    }

    @Override
    public List<Produit> findByNomProduitContains(String nom) {
        return produitRepository.findByNomProduitContains(nom);
    }

    @Override
    public List<Produit> findByNomPrix(String nom, Double prix) {
        return produitRepository.findByNomPrix(nom, prix);
    }

    @Override
    public List<Produit> findByCategorie(Categorie categorie) {
        return produitRepository.findByCategorie(categorie);
    }

    @Override
    public List<Produit> findByCategorieIdCat(Long id) {
        return produitRepository.findByCategorieIdCat(id);
    }

    @Override
    public List<Produit> findByOrderByNomProduitAsc() {
        return produitRepository.findByOrderByNomProduitAsc();
    }

    @Override
    public List<Produit> trierProduitsNomsPrix() {
        return produitRepository.trierProduitsNomsPrix();
    }

    @Override
    public List<Produit> findByOrderByPrixProduitAsc() {
        return produitRepository.findByOrderByPrixProduitAsc();
    }

    @Override
    public List<Produit> trierProduitsDatePrix() {
        return produitRepository.trierProduitsDatePrix();
    }
}
