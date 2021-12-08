package FFSSM;

import java.time.LocalDate;
import java.util.Objects;

public class Embauche {

    private LocalDate debut;

    private LocalDate fin;

    private final Moniteur employe;

    private final Club employeur;

    public Embauche(LocalDate debut, Moniteur employe, Club employeur) {
        this.debut = debut;
        this.employe = employe;
        this.employeur = employeur;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.debut);
        hash = 53 * hash + Objects.hashCode(this.fin);
        hash = 53 * hash + Objects.hashCode(this.employe);
        hash = 53 * hash + Objects.hashCode(this.employeur);
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
        final Embauche other = (Embauche) obj;
        if (!Objects.equals(this.debut, other.debut)) {
            return false;
        }
        if (!Objects.equals(this.fin, other.fin)) {
            return false;
        }
        if (!Objects.equals(this.employe, other.employe)) {
            return false;
        }
        if (!Objects.equals(this.employeur, other.employeur)) {
            return false;
        }
        return true;
    }

    /**
     * Termine cette embauche
     * @param dateFin la date à laquelle cette embauche est terminée
     */
    public void terminer(LocalDate dateFin)throws Exception {
         // TODO: Implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");	      
         if (!this.estTerminee()){
            this.fin=dateFin;            
         }else{
             throw new Exception("le moniteur n'a pas d'embauches en cours ");
             
         }
    }
    
    /**
     * Est-ce que cette embauche est terminée ?
     * @return vrai si terminée, faux sinon.
     */
    public boolean estTerminee() {
        return (fin != null);
    }
    /**
     * Get the value of employeur
     *
     * @return the value of employeur
     */
    public Club getEmployeur() {
        return employeur;
    }

    /**
     * Get the value of employe
     *
     * @return the value of employe
     */
    public Moniteur getEmploye() {
        return employe;
    }

    /**
     * Get the value of fin
     *
     * @return the value of fin
     */
    public LocalDate getFin() {
        return fin;
    }

    /**
     * Set the value of fin
     *
     * @param fin new value of fin
     */
    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    /**
     * Get the value of debut
     *
     * @return the value of debut
     */
    public LocalDate getDebut() {
        return debut;
    }

    
}
