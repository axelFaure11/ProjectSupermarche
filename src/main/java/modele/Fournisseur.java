package modele;

/**
 *
 * @author rebecca
 */
public class Fournisseur {
    private int codeFournisseur;
    private String adresse;
    private String nom;

    public Fournisseur() {
    }

    public Fournisseur(int codeFournisseur, String adresse, String nom) {
        this.codeFournisseur = codeFournisseur;
        this.adresse = adresse;
        this.nom = nom;
    }
    
    
    //getters
    public int getCodeFournisseur()
    {
        return codeFournisseur;
    }
    
    public String getAdresse()
    {
        return adresse;
    }
    
    public String getNom()
    {
        return nom;
    }
    
    //setters
    
    public void setCodeFournisseur(int codeFournisseur)
    {
        this.codeFournisseur=codeFournisseur;
    }
    
        public void setAdresse(String adresse)
    {
        this.adresse=adresse;
    }
       
    public void setNom (String nom)
    {
        this.nom=nom;
    }
    

}
