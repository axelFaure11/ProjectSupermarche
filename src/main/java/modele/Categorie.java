/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author rebecca
 */
public class Categorie {
    private int code;
    private String libelle;
    private String description;
    
    
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
