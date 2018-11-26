/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


/**
 *
 * @author Rabelais
 */
public class Reservation 
{
    private String salle;
    private String jour;
    private String horaire;
    private String association;

    public Reservation(String salle, String jour, String horaire, String association) {
        this.salle = salle;
        this.jour = jour;
        this.horaire = horaire;
        this.association = association;
    }
    
    public Reservation()
    {
        
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }
    
    
}
