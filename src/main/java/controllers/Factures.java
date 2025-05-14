package controllers;

import javax.mail.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Facture;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import service.FactureService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class Factures {

    @FXML
    private ListView<String> listViewFacture;
    @FXML
    private Button backH;

    private final FactureService factureService = new FactureService();
    private ObservableList<String> factureList = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> TrierPrix;
    @FXML
    private ChoiceBox<String> TrierType;
    @FXML
    private TextField search;

    public Factures() throws SQLException {
    }

    @FXML
    public void initialize() {
        loadFactures();
        TrierPrix.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                sortFacturesByPrix(newValue);
            }
        });

        TrierType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                sortFacturesByType(newValue);
            }
        });

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filterFacturesBySearch(newValue);
        });
    }

    private void loadFactures() {
        List<Facture> factures = factureService.display();
        for (Facture facture : factures) {
            String factureString = String.format("ID: %d, Prix Total: %.2f, Type Paiement: %s, Adresse Livraison: %s, Statut: %s, Date: %s",
                    facture.getIdFacture(),
                    facture.getPrixTotal(),
                    facture.getTypePaiement(),
                    facture.getAdresseLivraison(),
                    facture.getStatutFacture(),
                    facture.getDateFacture());
            factureList.add(factureString);
        }
        listViewFacture.setItems(factureList);
    }

    @FXML
    public void SupprimerFacture(ActionEvent actionEvent) {
        String selectedFactureString = listViewFacture.getSelectionModel().getSelectedItem();
        if (selectedFactureString != null) {
            factureList.remove(selectedFactureString);
        }
    }

    @FXML
    public void ModifierFacture(ActionEvent actionEvent) {
        String selectedFactureString = listViewFacture.getSelectionModel().getSelectedItem();
        if (selectedFactureString != null) {
            try {
                int factureId = extractFactureIdFromString(selectedFactureString);

                Facture facture = factureService.getFactureById(factureId);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EditFacture.fxml"));
                Parent root = loader.load();

                EditFacture controller = loader.getController();
                controller.initData(facture);

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int extractFactureIdFromString(String factureString) {
        String[] parts = factureString.split(",");
        String idPart = parts[0];  // "ID: 123"
        return Integer.parseInt(idPart.split(":")[1].trim());
    }

    @FXML
    public void AjouterFacture(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterFacture.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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





    private void sortFacturesByPrix(String order) {
        if (order.equals("Prix croissant")) {
            factureList.sort((f1, f2) -> {
                float prix1 = extractPrix(f1);
                float prix2 = extractPrix(f2);
                return Float.compare(prix1, prix2);
            });
        } else if (order.equals("Prix décroissant")) {
            factureList.sort((f1, f2) -> {
                float prix1 = extractPrix(f1);
                float prix2 = extractPrix(f2);
                return Float.compare(prix2, prix1);
            });
        }
        listViewFacture.setItems(factureList);
    }

    private float extractPrix(String factureString) {
        String[] parts = factureString.split(",");
        for (String part : parts) {
            if (part.trim().startsWith("Prix Total:")) {
                return Float.parseFloat(part.trim().split(":")[1].trim());
            }
        }
        return 0;
    }

    private void sortFacturesByType(String type) {
        ObservableList<String> filteredFactures = FXCollections.observableArrayList();

        for (String facture : factureList) {
            String typePaiement = extractType(facture);
            if (type.equals("Carte") && typePaiement.equals("Carte")) {
                filteredFactures.add(facture);
            } else if (type.equals("Espèce") && typePaiement.equals("Espèce")) {
                filteredFactures.add(facture);
            }
        }

        listViewFacture.setItems(filteredFactures);
    }

    private String extractType(String factureString) {
        String[] parts = factureString.split(",");
        for (String part : parts) {
            if (part.trim().startsWith("Type Paiement:")) {
                return part.trim().split(":")[1].trim();
            }
        }
        return "";
    }


    private void filterFacturesBySearch(String searchText) {
        ObservableList<String> filteredFactures = FXCollections.observableArrayList();

        for (String facture : factureList) {
            if (facture.toLowerCase().contains(searchText.toLowerCase())) {
                filteredFactures.add(facture);
            }
        }

        listViewFacture.setItems(filteredFactures);
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



}