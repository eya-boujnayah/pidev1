package test;
import model.categorie;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.produit;
 import service.ProduitService;
import service.CategorieService;

import utils.MyDatabse;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        //************************* Produit ***********************************

produit p = new  produit( "aa", "bb","cc","dd",20.35, 55,  "ee",85) ;

            ProduitService ProduitService  = new ProduitService();

        ProduitService.add(p);

        System.out.println("C bon ");
//display
        ProduitService produitService = new ProduitService();
        List<produit> produits = produitService.display();

        for (produit pi : produits) {
            System.out.println(pi);
        }


      //mettre a jour
         produit produitU = new produit(11,"ahahha", "palette", "xx", "stat", 200, 5, "bleu", 5);

         produitU.setNom("H");
        produitU.setDescription("H");
        produitU.setPrix(0);
        produitU.setStock(0);
        produitU.setIdCategorie(0);
        produitU.setCouleurs("VERT");
        produitU.setMarque("TEST");

         produitService.update(produitU);

        System.out.println("\nProduit mis à jour avec succès.");
//display
        ProduitService produitService1 = new ProduitService();
        List<produit> produits1 = produitService1.display();

        for (produit pi : produits1) {
            System.out.println(pi);
        }

//delete
        int idProduitASupprimer = 1;  // ID du produit à supprimer, assure-toi qu'il existe dans la base
        produitService.delete(idProduitASupprimer);
        System.out.println("\n Suppression effectuée pour le produit avec ID " + idProduitASupprimer);
//display
        //display
        ProduitService produitService2 = new ProduitService();
        List<produit> produits2 = produitService2.display();

        for (produit pi : produits2) {
            System.out.println(pi);
        }

        //************************* Categorie ***********************************

// Création d'une nouvelle catégorie
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date specificDate = sdf.parse("19-08-2017");
            categorie c = new categorie("Electronics", "Description de la catégorie", "Active", specificDate);
            CategorieService CategorieService = new CategorieService();

            CategorieService.add(c);

            // Affichage pour vérifier
            System.out.println("Catégorie ajoutée avec la date 19-08-2017 !");

        } catch (Exception e) {
            System.err.println("Erreur de format de date : " + e.getMessage());
        }

        CategorieService CategorieService = new CategorieService();
        List<categorie> categories = CategorieService.display();
        for (categorie cat : categories) {
            System.out.println(cat);
        }

// Mise à jour d'une catégorie
        categorie categorieU = new categorie(2, "Nouveaux produits", "Description mise à jour", "Inactive", new java.util.Date());

        categorieU.setNom("PADELLLL");
        categorieU.setDescription("Description ");
        categorieU.setStatus("Desactivé");
        categorieU.setDate_creation(new java.util.Date());

        CategorieService.update(categorieU);

        System.out.println("\nCatégorie mise à jour avec succès.");

        //display
        CategorieService CategorieService1 = new CategorieService();
        List<categorie> categories1 = CategorieService1.display();
        for (categorie cat : categories1) {
            System.out.println(cat);
        }

// Suppression d'une catégorie
        int idCategorieASupprimer = 29;  // ID de la catégorie à supprimer, assure-toi qu'il existe dans la base
        CategorieService.delete(idCategorieASupprimer);
        System.out.println("\nSuppression effectuée pour la catégorie avec ID " + idCategorieASupprimer);

// Affichage des catégories après suppression
        CategorieService CategorieService2 = new CategorieService();
        List<categorie> categories2 = CategorieService2.display();
        for (categorie cat : categories2) {
            System.out.println(cat);
        }

    }

}