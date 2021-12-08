/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FFSSM;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author hikhl
 */
public class TestUnitaires {
//LES ATTRIBUTS
    Moniteur ikhlass;
    Plongeur hamza;
    Plongeur defne;
    Club club1;


    // BEFORE EACH POUR LES INSTANCES DE TEST
    @BeforeEach
    public void setUp() {

        ikhlass = new Moniteur("TEDTYV", "HAJJAM", "Ikhlass", "56 rue PIEERE FABRE 55644 TOURCOING", "087437864", LocalDate.of(2000, 3,7), 76320);
        hamza = new Plongeur("6TRFTFV","MAKRI", "hamza", "44 rue HUBAC 81100 Castres ", "08753368", LocalDate.of(2000, 12,11));
        defne = new Plongeur("YTFTDEXJ","KESKIN", "defne", "7 rue EMILE ZOLA 81100 Castres ", "9875324567", LocalDate.of(2000, 11,06));
        club1 = new Club(ikhlass,"Petite sirene", "0476532345");

    }

    // ========================== TESTS PLONGEUR ==========================
    @Test
    public void testAjouteLicence() throws Exception {

        Licence licence1 = new Licence(ikhlass, "24DZF", LocalDate.of(2021, 7,5), club1);
        Licence licence2 = new Licence(ikhlass, "GHHC88", LocalDate.of(2021, 8,2), club1);

        assertThrows(Exception.class, () -> {
            ikhlass.derniereLicence();
        });

        ikhlass.ajouteLicence("24DZF", LocalDate.of(2021, 7,5), club1);

        System.out.println(ikhlass.derniereLicence());
        assertEquals(ikhlass.derniereLicence(), licence1);

        ikhlass.ajouteLicence("GHHC88", LocalDate.of(2021, 8,2), club1);

        assertEquals(ikhlass.derniereLicence(), licence2);
    }
    // ========================== TESTS MONITEUR ==========================

    @Test
    public void testEmployeurActuel() throws Exception {
        
        assertEquals(ikhlass.employeurActuel(), Optional.empty());  // On vérifie qu'un optional vide est renvoyé si la liste d'embauche est vide

        ikhlass.nouvelleEmbauche(club1, LocalDate.of(2017, 8,9));

        assertEquals(ikhlass.employeurActuel(), Optional.of(club1)); // On vérifie qu'on revoie bien le club quand on a une seule embauche en cours

        Club club2 = new Club(ikhlass,"Berkane", "0985426656");

        ikhlass.terminerEmbauche(LocalDate.now());
        ikhlass.nouvelleEmbauche(club2, LocalDate.now());

        assertEquals(ikhlass.employeurActuel(), Optional.of(club2)); // On vérifie que ça marche même avec plus d'une embauche

        ikhlass.terminerEmbauche(LocalDate.now());

        assertEquals(ikhlass.employeurActuel(), Optional.empty());  // On vérifie que s'il n'y a pas d'embauche en cours on renvoie un optional vide
        
    }


    @Test
    public void testNouvelEmbaucheEtEmplois() {
      Embauche e1 =new Embauche(LocalDate.of(2017,2,3),ikhlass,club1);
      Embauche e2 =new Embauche(LocalDate.of(2018,5,6),ikhlass,club1);
      
      ArrayList<Embauche>listEmbauches=new ArrayList<>();
      listEmbauches.add(e1);
      listEmbauches.add(e2);
      
      ikhlass.nouvelleEmbauche(club1, LocalDate.of(2017,2,3));
      ikhlass.nouvelleEmbauche(club1, LocalDate.of(2018,5,6));
      
      assertEquals(listEmbauches,ikhlass.emplois());
    }

    @Test
    public void testTerminerEmbauche() throws Exception {

        assertThrows(Exception.class, () -> {
           ikhlass.terminerEmbauche(LocalDate.of(2020,5,6));
        });
        
        ikhlass.nouvelleEmbauche(club1, LocalDate.of(2020,5,6));
        
        ikhlass.terminerEmbauche(LocalDate.now());
        assertTrue(ikhlass.emplois().get(ikhlass.emplois().size()-1).estTerminee());
        
        
        
       
    }

    // ========================== TESTS EMBAUCHE ==========================


    @Test
    public void testEstTermineeEtTerminer() throws Exception{
        // On a ajouté un emploi au moniteur, il a donc un emploi en cours, donc on check si estTerminee() return false
        ikhlass.nouvelleEmbauche(club1, LocalDate.of(2002, 3, 7));
        assertFalse(ikhlass.emplois().get(ikhlass.emplois().size()-1).estTerminee());


        // On a terminé l'embauche, on vérifie donc que estTerminee() retourne True
        ikhlass.terminerEmbauche(LocalDate.of(2021, 3, 7));
        assertTrue(ikhlass.emplois().get(ikhlass.emplois().size()-1).estTerminee());


        // On va ajouter une nouvelle embauche dans le club 2 au moniteur
        // Et la terminer d'une autre manière (càd avec la fonction terminer() de la class Embauche)
        // on vérifie donc que estTerminee() retourne True
        // et que la date de fin assignée est la bonne

        Club club2 = new Club(ikhlass,"Berkane", "0985426656");
        ikhlass.nouvelleEmbauche(club2, LocalDate.of(2002, 3, 7));
        ikhlass.emplois().get(ikhlass.emplois().size()-1).terminer(LocalDate.of(2021, 9, 2));

        assertTrue(ikhlass.emplois().get(ikhlass.emplois().size()-1).estTerminee());
        assertEquals(ikhlass.emplois().get(ikhlass.emplois().size()-1).getFin(),LocalDate.of(2021, 9, 2) );

        
        
    }
// ==========================   TESTS CLUB   ==========================

    @Test
    public void testPlongeesNonConformes() throws Exception{
        
        
        Plongee plongee1 = new Plongee(new Site("Mediteranee", "blablabla"),ikhlass, LocalDate.of(2021,12,7),20,2);
        Plongee plongee2 = new Plongee(new Site("Pacifique", "bleblebleble"),ikhlass, LocalDate.of(2021,12,7),20,2);
        Plongee plongee3 = new Plongee(new Site("Atlantique", "tototo"),ikhlass, LocalDate.of(2021,12,7),20,2);

        ikhlass.ajouteLicence("65FHVF", LocalDate.of(2021, 12,6), club1);
        defne.ajouteLicence("889VVT", LocalDate.of(2021, 12,7), club1);
        hamza.ajouteLicence("BJGV4", LocalDate.of(2010, 1,1), club1); // Cette licence est expirée

        plongee1.ajouteParticipant(ikhlass);
        plongee1.ajouteParticipant(defne);

        plongee2.ajouteParticipant(defne);
        plongee2.ajouteParticipant(hamza);  // Cette plongée n'est plus conforme

        plongee3.ajouteParticipant(hamza);  // Cette plongée n'est plus conforme
        plongee3.ajouteParticipant(ikhlass);

        HashSet<Plongee> plongeesNonConformes = new HashSet<>();

        club1.organisePlongee(plongee1);

        assertEquals(club1.plongeesNonConformes(), plongeesNonConformes);

        plongeesNonConformes.add(plongee2);
        club1.organisePlongee(plongee2);

        assertEquals(club1.plongeesNonConformes(), plongeesNonConformes);

        plongeesNonConformes.add(plongee3);
        club1.organisePlongee(plongee3);

        assertEquals(club1.plongeesNonConformes(), plongeesNonConformes);

    }

    

   
   
    // ========================== TESTS PLONGEE  ==========================

    @Test
    public void testAjouterParticipantEtEstConforme() throws Exception{
        Plongee plongee1 = new Plongee(new Site("Mediteranee", "blablabla"),ikhlass, LocalDate.of(2021,12,7),20,2);

        ikhlass.ajouteLicence("65FHVF", LocalDate.of(2021, 12,6), club1);
        defne.ajouteLicence("889VVT", LocalDate.of(2021, 12,7), club1);
        hamza.ajouteLicence("BJGV4", LocalDate.of(2010, 1,1), club1); // Cette licence est expirée


        plongee1.ajouteParticipant(ikhlass);
        plongee1.ajouteParticipant(defne);

        ArrayList<Licence> listLicences = new ArrayList<>();

        listLicences.add(ikhlass.derniereLicence());
        listLicences.add(defne.derniereLicence());

        assertTrue(plongee1.estConforme());  // On verifie que la plongée est conforme

        plongee1.ajouteParticipant(hamza);
        listLicences.add(hamza.derniereLicence());  // On ajoute une licence non conforme

        assertFalse(plongee1.estConforme());  // La plongée ne doit plus être conforme
        assertEquals(plongee1.getListPalanquee(), listLicences); // On vérifie que les ajouts se sont bien fai
    }

    

    // ========================== TESTS LICENCE  ==========================

    @Test
    public void testEstValide() throws Exception{
        
        Licence l1 = new Licence(hamza, "LC873HE", LocalDate.of(2010, 1,1), club1);

        assertTrue(l1.estValide(LocalDate.of(2010, 1,3)));
        assertFalse(l1.estValide(LocalDate.of(2015, 1,1)));
        assertTrue(l1.estValide(LocalDate.of(2010, 1,1)));

        assertThrows(Exception.class, () -> {
            l1.estValide(LocalDate.of(2009, 1,1));
        });

    }

}
