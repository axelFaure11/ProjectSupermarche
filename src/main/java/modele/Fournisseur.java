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
public class Fournisseur {
    private int codeFournisseur;
    private String adresse;
    private String nom;
    
    //getters
    public int getCodeFournisser()
    {
        return codeFournisseur;
    }
    
    public String adresse()
    {
        return adresse;
    }
    
    public String nom()
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
