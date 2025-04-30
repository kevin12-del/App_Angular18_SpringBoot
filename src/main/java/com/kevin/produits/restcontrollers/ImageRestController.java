package com.kevin.produits.restcontrollers;

import com.kevin.produits.entities.Image;
import com.kevin.produits.entities.Produit;
import com.kevin.produits.service.ImageService;
import com.kevin.produits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/image")
@CrossOrigin("*")
public class ImageRestController {

    @Autowired
    ImageService imageService;

    @Autowired
    ProduitService produitService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Image uploadImage(@RequestParam("image") MultipartFile file) throws IOException{
        return imageService.uplaodImage(file);
    }

    @RequestMapping(value = "/get/info/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Image getImageDetails(@PathVariable("id") Long id) throws IOException{
        return imageService.getImageDetails(id);
    }
    @RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException{
        return imageService.getImage(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteImage(@PathVariable("id") Long id){
        imageService.deleteImage(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Image UploadImage(@RequestParam("image") MultipartFile file) throws IOException{
        return imageService.uplaodImage(file);
    }

    @RequestMapping(value = "/uplaodImageProd/{idProd}", method = RequestMethod.POST )
    @PreAuthorize("hasAuthority('ADMIN')")
    public Image uploadMultiImages(@RequestParam("image")MultipartFile file,
                                   @PathVariable("idProd") Long idProd)
            throws IOException {
        return imageService.uplaodImageProd(file,idProd);
    }

    @RequestMapping(value = "/getImagesProd/{idProd}" , method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Image> getImagesProd(@PathVariable("idProd") Long idProd)
            throws IOException {
        return imageService.getImagesParProd(idProd);
    }

    @RequestMapping(value = "/uploadFS/{id}" , method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void uploadImageFS(@RequestParam("image") MultipartFile
                                      file,@PathVariable("id") Long id) throws IOException {
        Produit p = produitService.getProduit(id);
        p.setImagePath(id+".jpg");
        Files.write(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath())
                , file.getBytes());
        produitService.saveProduit(p);
    }
    @RequestMapping(value = "/loadfromFS/{id}" ,
            method = RequestMethod.GET,
            produces = MediaType.ALL_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<byte[]>  getImageFS(@PathVariable("id") Long id) throws IOException {
        Produit p = produitService.getProduit(id);

             byte[] data =   Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath()));

          String mimeType = Files.probeContentType(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath()));
                if (mimeType == null) {
                    mimeType = MediaType.IMAGE_JPEG_VALUE;
                }

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .body(data);

    }
}
