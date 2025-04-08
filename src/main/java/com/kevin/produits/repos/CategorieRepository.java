package com.kevin.produits.repos;

import com.kevin.produits.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(path = "cat")
@CrossOrigin("http://localhost:4200/")
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
