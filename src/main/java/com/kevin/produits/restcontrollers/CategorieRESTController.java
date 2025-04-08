package com.kevin.produits.restcontrollers;

import com.kevin.produits.entities.Categorie;
import com.kevin.produits.repos.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cat")
@CrossOrigin("*")
public class CategorieRESTController {

    @Autowired
    CategorieRepository categorieRepository;

    @RequestMapping(method= RequestMethod.GET)
    public List<Categorie> getAllCategories()
    {
        return categorieRepository.findAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Categorie getCategorieById(@PathVariable("id") Long id) {
        return categorieRepository.findById(id).get();
    }
}
