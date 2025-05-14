package controllers;

import com.lowagie.text.DocumentException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Commande;
import model.CommandeProduit;
import model.Facture;
import model.produit;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.xhtmlrenderer.pdf.ITextRenderer;
import service.CommandeService;
import service.FactureService;
import service.PanierService;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FactureController {

    private static final Logger logger = Logger.getLogger(FactureController.class.getName()); // Initialisation du logger

    @FXML
    private TextField adresseLivraisonField;
    @FXML
    private TextField prixTotalField;
    @FXML
    private ComboBox<String> typePaiementComboBox;
    @FXML
    private Button validerFactureButton;

    @FXML
    private Button downloadFactureButton;

    private PanierService panierService = new PanierService();
    private CommandeService commandeService = new CommandeService();
    private Commande commande;
    private Facture facture;
    public FactureController() throws SQLException {
    }

    public void setCommande(Commande commande) {
        if (commande == null) {
            logger.severe("❌ ERREUR : La commande est NULL !");
            return;
        }

        this.commande = commande;
        prixTotalField.setText(String.valueOf(commande.getPrixCommande()));
        logger.info("✅ Commande définie avec ID: " + commande.getIdCommande());
    }

    @FXML
    public void validerFacture() throws SQLException {
        String adresseLivraison = adresseLivraisonField.getText();
        String typePaiement = typePaiementComboBox.getValue();

        if (commande == null || commande.getIdCommande() == 0) {
            logger.warning("❌ Aucune commande associée à cette facture !");
            return;
        }

        if (adresseLivraison.isEmpty() || typePaiement == null) {
            logger.warning("❌ Veuillez entrer tous les champs.");
            return;
        }

        // Créer la facture à partir des informations saisies
        facture = new Facture(commande, commande.getIdUtilisateur(), commande.getPrixCommande(), typePaiement, adresseLivraison, new Date(System.currentTimeMillis()), "Non payée");

        // Enregistrer la facture
        panierService.add(facture);
        logger.info("✅ Facture créée avec succès !");

        String emailBody = buildEmailBody(facture);

        String from = facture.getUtilisateur().getEmail();

        if (from == null || from.isEmpty()) {
            from = "molkatouati24@gmail.com";
        }

        String subject = "Détails de votre facture - Commande " + commande.getIdCommande();
        showAlert(Alert.AlertType.INFORMATION, "Succès", "Votre Facture Est Validée !!");

        // Envoyer l'email
        sendEmail(from, subject, emailBody);

       // showSuccessAlertWithDownloadButton(facture);


    }

    private String buildEmailBody(Facture facture) {
        StringBuilder body = new StringBuilder();

        body.append("<html><body>");
        body.append("<h2>Bonde Commande</h2>");
        body.append("<p>Merci pour votre commande ! Voici les détails de votre commande :</p>");

        // Détails des produits
        body.append("<table border='1' cellpadding='5' cellspacing='0' style='border-collapse: collapse;'>");
        body.append("<tr><th>Nom Produit</th><th>Description</th><th>Prix Unitaire</th><th>Quantité</th><th>Prix Total</th></tr>");

        // Vérifier si la liste des produits est nulle ou vide avant de la parcourir
        List<CommandeProduit> produits = commandeService.getProduitsByCommandeId(commande.getIdCommande());
        if (produits != null && !produits.isEmpty()) {
            for (CommandeProduit cp : produits) {
                produit p = cp.getProduit();  // Produit associé à cette commande
                int quantiteProduit = cp.getQuantite();  // Quantité du produit dans la commande
                double prixProduit = p.getPrix();  // Prix unitaire du produit
                double prixTotalProduit = prixProduit * quantiteProduit;  // Prix total du produit (quantité * prix unitaire)

                // Construction des lignes du tableau pour chaque produit
                body.append("<tr>");
                body.append("<td>").append(p.getNom()).append("</td>");
                body.append("<td>").append(p.getDescription()).append("</td>");
                body.append("<td>").append(String.format("%.2f", prixProduit)).append(" €</td>");
                body.append("<td>").append(quantiteProduit).append("</td>");
                body.append("<td>").append(String.format("%.2f", prixTotalProduit)).append(" €</td>");
                body.append("</tr>");
            }
        } else {
            body.append("<tr><td colspan='5'>Aucun produit dans la commande.</td></tr>");
        }

        body.append("</table>");

        // Détails supplémentaires de la commande
        body.append("<br>");
        body.append("<p><strong>Prix Total de la Commande :</strong> ").append(String.format("%.2f", facture.getPrixTotal())).append(" €</p>");
        body.append("<p><strong>Adresse de Livraison :</strong> ").append(facture.getAdresseLivraison()).append("</p>");
        body.append("<p><strong>Type de Paiement :</strong> ").append(facture.getTypePaiement()).append("</p>");
        body.append("<p><strong>Statut de la Commande :</strong> ").append(facture.getStatutFacture()).append("</p>");
        body.append("<p><strong>Date de la Commande :</strong> ").append(facture.getDateFacture()).append("</p>");

        body.append("<br>");
        body.append("<p>Nous vous remercions pour votre commande et nous vous informons que celle-ci est en cours de traitement.</p>");
        body.append("<p>Nous restons à votre disposition pour toute question ou information supplémentaire.</p>");

        body.append("<p>Cordialement,</p>");
        body.append("<p><strong>Votre entreprise</strong></p>");

        body.append("</body></html>");

        return body.toString();
    }


    private void sendEmail(String recipient, String subject, String body) {
        if (recipient == null || recipient.isEmpty()) {
            logger.warning("❌ L'adresse email du destinataire est vide ou null.");
            showAlert(Alert.AlertType.ERROR, "Erreur", "L'adresse email du destinataire est vide ou incorrecte.");
            return;
        }

        final String username = "raninbakouri8@gmail.com";
        final String password = "ijxzpxkkkzcyxzzj";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setContent(body, "text/html; charset=utf-8");
            Transport.send(message);
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Email envoyé avec succès.");
        } catch (MessagingException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible d'envoyer l'email.");
        }
    }



    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void backH(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Home.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void downloadFacture(ActionEvent event) throws IOException {
        if (commande == null || commande.getIdCommande() == 0) {
            logger.warning("❌ Aucune commande associée à cette facture !");
            return;
        }

        // Générer le PDF pour la facture à partir du contenu HTML
        generatePDFFromHTML(buildHTMLContent(facture));

        showAlert(Alert.AlertType.INFORMATION, "Succès", "Votre facture est prête à être téléchargée.");
    }

    private void generatePDFFromHTML(String htmlContent) throws IOException {
        String outputFilePath = "facture_commande.pdf";

        // Création du renderer
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();

        // Sauvegarde du PDF dans un fichier
        try (FileOutputStream os = new FileOutputStream(outputFilePath)) {
            renderer.createPDF(os);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        System.out.println("✅ Facture PDF générée et sauvegardée sous : " + outputFilePath);
    }

    private String buildHTMLContent(Facture facture) {
        StringBuilder htmlContent = new StringBuilder();

        // Début du HTML avec le style CSS
        htmlContent.append("<html><head>");
        htmlContent.append("<style>");
        htmlContent.append("body {font-family: Arial, sans-serif; font-size: 12px; color: #333;}");
        htmlContent.append("h2 {color: #4CAF50;}");
        htmlContent.append("table {border-collapse: collapse; width: 100%; margin-top: 20px;}");
        htmlContent.append("th, td {border: 1px solid #ddd; padding: 8px; text-align: left;}");
        htmlContent.append("th {background-color: #f2f2f2;}");
        htmlContent.append("tr:nth-child(even) {background-color: #f9f9f9;}");
        htmlContent.append("p {font-size: 14px; line-height: 1.6;}");
        htmlContent.append(".total {font-weight: bold; font-size: 16px;}");
        htmlContent.append(".header {border-bottom: 2px solid #4CAF50; padding-bottom: 10px; margin-bottom: 20px;}");
        htmlContent.append(".footer {margin-top: 30px; font-size: 10px; color: #888; text-align: center;}");
        htmlContent.append("</style>");
        htmlContent.append("</head><body>");

        // Titre et informations générales
        htmlContent.append("<div class='header'>");
        htmlContent.append("<h2>Facture - Commande #" + facture.getCommande().getIdCommande() + "</h2>");
        htmlContent.append("<p><strong>Client :</strong>    TOUATI Molka</p>");
        htmlContent.append("<p><strong>Adresse de Livraison :</strong> " + facture.getAdresseLivraison() + "</p>");
        htmlContent.append("<p><strong>Type de Paiement :</strong> " + facture.getTypePaiement() + "</p>");
        htmlContent.append("<p><strong>Date :</strong> " + facture.getDateFacture() + "</p>");
        htmlContent.append("</div>");

        // Détails des produits
        htmlContent.append("<h3>Détails des produits</h3>");
        htmlContent.append("<table>");
        htmlContent.append("<tr><th>Nom Produit</th><th>Description</th><th>Prix Unitaire</th><th>Quantité</th><th>Total</th></tr>");

        List<CommandeProduit> produits = commandeService.getProduitsByCommandeId(facture.getCommande().getIdCommande());
        double totalCommande = 0.0;
        for (CommandeProduit cp : produits) {
            produit p = cp.getProduit();
            double prixProduit = p.getPrix();
            double totalProduit = prixProduit * cp.getQuantite();
            totalCommande += totalProduit;

            htmlContent.append("<tr>");
            htmlContent.append("<td>" + p.getNom() + "</td>");
            htmlContent.append("<td>" + p.getDescription() + "</td>");
            htmlContent.append("<td>" + String.format("%.2f", prixProduit) + " €</td>");
            htmlContent.append("<td>" + cp.getQuantite() + "</td>");
            htmlContent.append("<td>" + String.format("%.2f", totalProduit) + " €</td>");
            htmlContent.append("</tr>");
        }

        htmlContent.append("</table>");

        // Total de la commande
        htmlContent.append("<p class='total'>Total Commande : " + String.format("%.2f", totalCommande) + " €</p>");
        htmlContent.append("<p class='total'>Montant Total : " + String.format("%.2f", facture.getPrixTotal()) + " €</p>");

        // Message de remerciement
        htmlContent.append("<p>Merci de votre commande ! Si vous avez des questions, n'hésitez pas à nous contacter.</p>");

        // Footer
        htmlContent.append("<div class='footer'>");
        htmlContent.append("<p>Adresse : Votre société, 123 rue de l'exemple, Paris</p>");
        htmlContent.append("<p>Téléphone : +33 1 23 45 67 89</p>");
        htmlContent.append("<p>Email : contact@votresite.com</p>");
        htmlContent.append("</div>");

        htmlContent.append("</body></html>");

        return htmlContent.toString();
    }

//
//
//
//    private void showSuccessAlertWithDownloadButton(Facture facture) {
//        // Créer une alerte de succès
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Facture envoyée !");
//        alert.setHeaderText("L'email a été envoyé avec succès.");
//
//        // Créer un bouton personnalisé pour télécharger le PDF
//        Button downloadButton = new Button("Télécharger le PDF");
//        downloadButton.setOnAction(event -> {
//            try {
//                generatePDF(facture);  // Génère et enregistre le PDF
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            alert.close();  // Fermer l'alerte après le téléchargement
//        });
//
//        // Ajout du bouton dans l'alerte
//        alert.getDialogPane().setExpandableContent(downloadButton);
//
//        // Afficher l'alerte
//        alert.showAndWait();
//    }

}
