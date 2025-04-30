package com.kevin.produits.service;

import com.kevin.produits.entities.Image;
import com.kevin.produits.entities.Produit;
import com.kevin.produits.repos.ImageRepository;
import com.kevin.produits.repos.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ProduitService produitService;

    ProduitRepository produitRepository;

    @Override
    public Image uplaodImage(MultipartFile file) throws IOException {
            /*Ce code commenté est équivalent au code utilisant le design pattern Builder
            * Image image = new Image(null, file.getOriginalFilename(),
            file.getContentType(), file.getBytes(), null);
            return imageRepository.save(image);*/

        return imageRepository.save(Image.builder()
                                          .name(file.getOriginalFilename())
                                          .type(file.getContentType())
                                          .image(file.getBytes()).build() );
    }
    @Override
    public Image getImageDetails(Long id) throws IOException{
        final Optional<Image> dbImage = imageRepository. findById (id);
        return Image.builder()
                .idImage(dbImage.get().getIdImage())
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(dbImage.get().getImage()).build() ;
    }
    @Override
    public ResponseEntity<byte[]> getImage(Long id) throws IOException{
        final Optional<Image> dbImage = imageRepository. findById (id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(dbImage.get().getImage());
    }
    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Image uplaodImageProd(MultipartFile file,Long idProd)
            throws IOException {
        Produit p = new Produit();
        p.setIdProduit(idProd);
        return imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(file.getBytes())
                .produit(p).build() );
    }
    @Override
    public List<Image> getImagesParProd(Long prodId) {
        Produit p = produitRepository.findById(prodId).get();
        return p.getImages();
    }

   /* @Override
    public void deleteProduitById(Long id) {
        Produit p = produitRepository.getProduit(id);
        //suuprimer l'image avant de supprimer le produit
        try {
            Files.delete(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        produitRepository.deleteById(id);
    }*/
}
