package com.kevin.produits.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categorie {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idCat;

    private String nomCat;
    private String descriptionCat;

    @OneToMany(mappedBy = "categorie")
    @JsonIgnore
    private List<Produit> produits;

    public Categorie(String nomCat, String descriptionCat, List<Produit> produits) {
        super();
        this.nomCat = nomCat;
        this.descriptionCat = descriptionCat;
        this.produits = produits;
    }
}
