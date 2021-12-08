package FFSSM;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Plongeur extends Personne {

    private int niveau;
    private ArrayList<Licence>listLicence;
    private GroupeSanguin groupeSanguin;

    public Plongeur(int niveau, ArrayList<Licence> listLicence, GroupeSanguin groupeSanguin, String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
      
        this.niveau = niveau;
        this.listLicence = new ArrayList<>();
        this.groupeSanguin = groupeSanguin;
    }
    
 

    public Plongeur( String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        this.listLicence = new ArrayList<>();

        // j'ai cree un autre constructeur pour respecter le nombre de parametres dans la classe moniteur
      

    }    
    
    public void ajouteLicence(String numero,LocalDate delivrance, Club c){
       
        Licence l=new Licence(this, numero , delivrance, c);
        listLicence.add(l);
    }
    
    public Licence derniereLicence()throws Exception{
        if(!listLicence.isEmpty()){
             //je recupere le dernier element de ma liste
           return listLicence.get(listLicence.size()-1); 
        }else{
            throw new Exception ("La liste de licence est vide");
        }
       
        
        
    }
    
    
    
    
}
