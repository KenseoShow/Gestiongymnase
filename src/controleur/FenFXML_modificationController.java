/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Reservation;

/**
 * FXML Controller class
 *
 * @author Rabelais
 */
public class FenFXML_modificationController implements Initializable 
{
    private Main main;
    
    private Stage dialogStage;
    private Reservation reservation = new Reservation();
    private boolean okClick = false;
    
    @FXML
    private TextField txtSalle;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtHeure;
    @FXML
    private TextField txtAssociation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }
    
    public void setMainApp(Main main)
    {
        this.main = main;
    }
    
    public void setDialogStage(Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }
    public void setReservation(Reservation reservation)
    {
        txtSalle.setText(reservation.getSalle());
        txtDate.setText(reservation.getJour());
        txtHeure.setText(reservation.getHoraire());
        txtAssociation.setText(reservation.getAssociation());
    }
    public boolean isOkClick()
    {
        return okClick;
    }

    @FXML
    private void handleOk()
    {
        if (isInputValid())
        {
            reservation.setSalle(txtSalle.getText());
            reservation.setJour(txtDate.getText());
            reservation.setHoraire(txtHeure.getText());
            reservation.setAssociation(txtAssociation.getText());
            okClick = true;
            dialogStage.close();
        }
        main.getReservationDonnee().add(reservation);
    }

    @FXML
    private void handleAnnul()
    {
        dialogStage.close();
    }
    
    private boolean isInputValid()
    {
        String messageErreur = "";
        boolean retour = true;
        
        if (txtSalle.getText() == null || txtSalle.getText().length() ==0)
        {
            messageErreur = "Salle invalide";
        }
        if (txtDate.getText() == null || txtDate.getText().length() ==0)
        {
            messageErreur = "Date invalide";
        }
        if (txtHeure.getText() == null || txtHeure.getText().length() ==0)
        {
            messageErreur = "Heure invalide";
        }
        if (txtAssociation.getText() == null || txtAssociation.getText().length() ==0)
        {
            messageErreur = "Association invalide";
        }
        if (messageErreur.length() > 0)
        {
            Dialogs.showErrorDialog(dialogStage, messageErreur, "Remplir les champs invalides", "Champs(s) invalide(s)");
            retour = false;
        }
        return retour;
    }
}
