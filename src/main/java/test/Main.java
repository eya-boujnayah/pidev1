package test;

import model.categorie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.produit;
import service.ProduitService;
import service.CategorieService;

import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Convert the string to a Date object
        Date dateCreation = dateFormat.parse("2022-02-16");
        categorie Categorie = new categorie("ref001", "Electronics", "Catégorie pour produits électroniques", dateCreation);
        CategorieService CategorieService = new CategorieService();
        CategorieService.add(Categorie);

        ProduitService ProduitService = new ProduitService();
       /* produit newProduit = new produit("ref001", "Smartphone", "Dernier modèle de smartphone",
                20.65, "samsung", 20, "bleu",
                "dispo", "image.jpg",35);
        ProduitService.add(newProduit);*/
        produit updatedProduit = new produit(5, "ref051", "Nokiaold", "updatee",
                "xiaomi", 99.99, 200, "blanc",
                "Indispo", "newimg.jpg", 36);
        ProduitService.update(updatedProduit);
        int produitIdToDelete = 7;  // Remplacez par l'ID du produit que tu souhaites supprimer
        ProduitService.delete(produitIdToDelete);

        List<produit> produits = ProduitService.display();
        for (produit p : produits) {
            System.out.println(p);
        }

        /********************** Categorie ******************************************   **/
// Update the category with idCategorie = 1
        categorie updatedCategorie = new categorie(41, "ref001Update", "Updated Electronics", "Updated description", dateFormat.parse("2029-09-16"));
        CategorieService.update(updatedCategorie);

        CategorieService.delete(48);

        // Display all categories
        List<categorie> categories = CategorieService.display();
        for (categorie c : categories) {
            System.out.println(c);
        }

    }
}

