/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Plongee {

	public Site lieu;

	public Moniteur chefDePalanquee;

	public LocalDate date;

	public int profondeur;

	public int duree;
                
        public ArrayList<Licence>listPalanquee;

	public Plongee(Site lieu, Moniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
		this.lieu = lieu;
		this.chefDePalanquee = chefDePalanquee;
		this.date = date;
		this.profondeur = profondeur;
		this.duree = duree;
                this.listPalanquee=new ArrayList<>();
	}
        
        public Plongee(Moniteur chef){
            this.chefDePalanquee=chef;
            this.listPalanquee=new ArrayList<>();

        }

	public void ajouteParticipant(Plongeur participant) throws Exception {
		// TODO: Implémenter cette méthode
		//throw new UnsupportedOperationException("Pas encore implémenté");
                listPalanquee.add(participant.derniereLicence());
        }

	public LocalDate getDate() {
		return date;
	}

	/**
	 * Détermine si la plongée est conforme. 
	 * Une plongée est conforme si tous les plongeurs de la palanquée ont une
	 * licence valide à la date de la plongée
	 * @return vrai si la plongée est conforme
	 */
	public boolean estConforme() throws Exception {
		// TODO: Implémenter cette méthode
		//throw new UnsupportedOperationException("Pas encore implémenté");
                for(Licence l :listPalanquee){
                   if(!l.estValide(this.date)){
                       return false;
                   } 
                }
                return true;        
        
        }

    public ArrayList<Licence> getListPalanquee() {
        return listPalanquee;
    }

}
