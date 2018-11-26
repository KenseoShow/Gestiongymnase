/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.Reservation;

/**
 *
 * @author Rabelais
 */
public class Main extends Application 
{
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private ObservableList<Reservation> ReservationDonnee = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) 
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gymnase");
        
        try
        {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/vue/FenFXML_main.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            FenFXML_mainController controller = loader.getController();
            controller.setMainApp(this);
        }
        catch(IOException e)
        {
           e.printStackTrace();
        }
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
        
    }
    
    public Main ()
    {
        ReservationDonnee.add(new Reservation("A","01/01/01","19","COST"));
        ReservationDonnee.add(new Reservation("B","02/02/02","18","CAST"));
        ReservationDonnee.add(new Reservation("C","03/03/03","19","COST"));
        ReservationDonnee.add(new Reservation("D","03/03/02","18","CAST"));
    }
    
    public ObservableList<Reservation> getReservationDonnee()
    {
        return ReservationDonnee;
    }
    
    public boolean afficheReservationEditDialog(Reservation reservation)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/vue/FenFXML_modification.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edition Reservation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            FenFXML_modificationController controleur = loader.getController();
            controleur.setDialogStage(dialogStage);
            controleur.setReservation(reservation);
            controleur.setMainApp(this);
            dialogStage.showAndWait();
            return controleur.isOkClick();
        }
        catch(IOException ioe)
        {
            System.out.println("ERREUR chargement boite dialogue:" + ioe.getMessage());
            ioe.printStackTrace();
            return false;
        }
    }
}
