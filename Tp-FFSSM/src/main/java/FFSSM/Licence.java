/**
 * @(#) LicencePlongeur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Licence {

    public Personne possesseur;

    public String numero;

    public LocalDate delivrance;

    public Club club;
    
    
    

    public Licence(Personne possesseur, String numero, LocalDate delivrance, Club club) {
        this.possesseur = possesseur;
        this.numero = numero;
        this.delivrance = delivrance;
        this.club = club;
           
    }

    public Personne getPossesseur() {
        return possesseur;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDate getDelivrance() {
        return delivrance;
    }

    public Club getClub() {
        return club;
    }

    /**
     * Est-ce que la licence est valide à la date indiquée ?
     * Une licence est valide pendant un an à compter de sa date de délivrance
     * @param d la date à tester
     * @return vrai si valide à la date d
     **/
    public boolean estValide(LocalDate d) throws Exception {
         // TODO: Implémenter cette méthode

        if (d.isAfter(delivrance) || d.isEqual(delivrance)){
            if ( d.isAfter(delivrance.plusYears(1))){
                return false;
            }else{
                return true;
            }
        }else{
            throw new Exception("La date entrée en parametre est avant la date de délivrance");
        }

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.possesseur);
        hash = 97 * hash + Objects.hashCode(this.numero);
        hash = 97 * hash + Objects.hashCode(this.delivrance);
        hash = 97 * hash + Objects.hashCode(this.club);
        
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Licence other = (Licence) obj;
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.possesseur, other.possesseur)) {
            return false;
        }
        if (!Objects.equals(this.delivrance, other.delivrance)) {
            return false;
        }
        if (!Objects.equals(this.club, other.club)) {
            return false;
        }
       
        return true;
    }

}
