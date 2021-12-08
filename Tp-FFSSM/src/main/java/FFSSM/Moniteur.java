/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class Moniteur extends Plongeur {

    public int numeroDiplome;
    private ArrayList<Embauche>embauches;
    // un moniteur a plusieurs embauches
    private ArrayList<Plongee>listPlongee;

    public Moniteur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int numeroDiplome) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
     
        this.numeroDiplome = numeroDiplome;
        this.embauches=new ArrayList();
        this.listPlongee=new ArrayList();
        
    }

    /**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est terminée,
     * ce moniteur n'a pas d'employeur.
     * @return l'employeur actuel de ce moniteur sous la forme d'un Optional
     */
    public Optional<Club> employeurActuel() {
         // TODO: Implémenter cette méthode
        if (embauches.isEmpty() || embauches.get(embauches.size()-1).estTerminee()){
            return Optional.empty();
        }else{
            return Optional.ofNullable(embauches.get(embauches.size()-1).getEmployeur()) ;
        }
    }
    
    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) {   
        
        Embauche e =new Embauche(debutNouvelle,this,employeur); 
        embauches.add( e);
        // TODO: Implémenter cette méthode
       // throw new UnsupportedOperationException("Pas encore implémenté");	    
    }

    public List<Embauche> emplois() {
         // TODO: Implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        return embauches;
    }
    
    public void terminerEmbauche(LocalDate fin) throws Exception{
         if (!embauches.isEmpty()){
            embauches.get(embauches.size()-1).terminer(fin);
            // je recupere le dernier apres je vais le terminer
            
         }else{
             throw new Exception("le moniteur n'a pas d'embauches ");
             
         }
            
    }

}
