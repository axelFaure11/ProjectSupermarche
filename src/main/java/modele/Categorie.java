package modele;

/**
 *
 * @author rebecca
 */
public class Categorie {
    private int code;
    private String libelle;
    private String description;

    public Categorie(int code, String libelle, String description) {
        this.code = code;
        this.libelle = libelle;
        this.description = description;
    }

    public Categorie() {
    }
    
    
    //getters
    public int getCode()
    {
        return code;
    }
    
    public String getLibelle()
    {
        return libelle;
    }
    
    public String getDescription()
    {
        return description;
    }
       
     //setters
    public void setCode(int code)
    {
        this.code=code;
    }
    
    public void setLibelle(String libelle)
    {
        this.libelle=libelle;
    }
    
        public void setDescription(String Description)
    {
        this.description=description;
    }
        
        
}
