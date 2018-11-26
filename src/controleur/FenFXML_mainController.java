/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Reservation;

/**
 * FXML Controller class
 *
 * @author Rabelais
 */


public class FenFXML_mainController implements Initializable 
{

    @FXML
    private TableView<Reservation> reservationTable;
    @FXML
    private TableColumn<Reservation, String> salleColonne;
    @FXML
    private TableColumn<Reservation, String> jourColonne;
    @FXML
    private TableColumn<Reservation, String> horaireColonne;
    @FXML
    private TableColumn<Reservation, String> associationColonne;
    
    @FXML
    private Label SalleLabel;
    @FXML
    private Label DateLabel;
    @FXML
    private Label HeureLabel;
    @FXML
    private Label AssociationLabel;

    private Main main;
    
    public FenFXML_mainController()
    {
        
    }
    
    public void setMainApp(Main main)
    {
        this.main = main;
        
        reservationTable.setItems(main.getReservationDonnee());
    }
    
    @FXML
    private void handleSupprimerReservation()
    {
        int indexSelectionne = reservationTable.getSelectionModel().getSelectedIndex();
        reservationTable.getItems().remove(indexSelectionne);
    }
    
    @FXML
    private void handleInsererReservation()
    {
        Reservation tempReservation = new Reservation();
        boolean okClick = main.afficheReservationEditDialog(tempReservation);
    }
    
    @FXML
    private void handleModifierReservation()
    {
        Reservation ReservationSelectionne = reservationTable.getSelectionModel().getSelectedItem();
        int indexSelectionne = reservationTable.getSelectionModel().getSelectedIndex();
        reservationTable.getItems().remove(indexSelectionne);
        if (ReservationSelectionne != null)
        {
            boolean okClick = main.afficheReservationEditDialog(ReservationSelectionne);
            if (okClick)
            {
                rafraichirReservationTable();
                afficheDetailsReservation(ReservationSelectionne);
            }
        }
        else
        {
            Dialogs.showWarningDialog(main.getPrimaryStage(), "Vous devez selectionner une réservation.", "Aucune de selectionné","Pas de sélection");
        }
    }
    
    public void rafraichirReservationTable()
    {
        int indexSelectionne = reservationTable.getSelectionModel().getSelectedIndex();
        reservationTable.setItems(null);
        reservationTable.layout();
        reservationTable.setItems(main.getReservationDonnee());
        reservationTable.getSelectionModel().select(indexSelectionne);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        salleColonne.setCellValueFactory(new PropertyValueFactory<Reservation, String>("salle"));
        jourColonne.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date"));
        horaireColonne.setCellValueFactory(new PropertyValueFactory<Reservation, String>("horaire"));
        associationColonne.setCellValueFactory(new PropertyValueFactory<Reservation, String>("Association"));
        
        afficheDetailsReservation(null);
        
        reservationTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reservation>()
            {
                @Override
                public void changed(ObservableValue<? extends Reservation> observable, Reservation oldValue, Reservation newValue)
                {
                    afficheDetailsReservation(newValue);
                }
            });
        // TODO
    }    
    
    private void afficheDetailsReservation (Reservation reservation)
    {
        if(reservation == null)
        {
            SalleLabel.setText("");
            DateLabel.setText("");
            HeureLabel.setText("");
            AssociationLabel.setText("");
        }
        else
        {
            SalleLabel.setText(reservation.getSalle());
            DateLabel.setText(reservation.getJour());
            HeureLabel.setText(reservation.getHoraire());
            AssociationLabel.setText(reservation.getAssociation());
        }
    }
    
}
