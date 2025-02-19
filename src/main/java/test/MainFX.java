package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Charger la première scène
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterProduit.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ajouter Produit");
            primaryStage.show();

            // Exemple de logique pour changer la scène après un événement (ici un clic de bouton)
            // Tu peux ajouter un événement pour changer la scène, par exemple :
            // Button button = (Button) root.lookup("#changeSceneButton");
            // button.setOnAction(event -> changeScene(primaryStage));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeScene(Stage primaryStage) {
        try {
            // Charger la scène de TousLesProduits
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TousLesProduits.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Liste des Produits");
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de TousLesProduits.fxml");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
