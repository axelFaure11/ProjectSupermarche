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
public class Produit {
    private int ref;
    private String nom;
    private int codeFournisseur;
    private int categorie;
    private String quantite;
    private double prix;
    private int unitesEnStock;
    private int unitesCommandees;
    private int niveauReapprovi;
    
   //getters
    public int getRef()
    {
        return ref;
    }
    
    public String getNom()
    {
       return nom; 
    }
    
    public int codeFournisseur()
    {
        return codeFournisseur;
    }
    
    public int categorie ()
    {
        return categorie;
    }
    
    public String quantite()
    {
        return quantite;
    }
    
    public double prix()
    {
        return prix;
    }
    
    public int unitesEnStock()
    {
        return unitesEnStock;
    }
    
     public int unitesCommandees()
    {
        return unitesCommandees;
    }
     
      public int niveauReapprovi()
    {
        return niveauReapprovi;
    }
      
    //setters
      
    public void setRef(int ref)
    {
            this.ref=ref;
    }
    
     public void setNom(String nom)
    {
            this.nom=nom;
    }
     
       public void setCodeFournisseur(int codeFournisseur)
    {
            this.codeFournisseur=codeFournisseur;
    }
       
         public void setCategorie(int categorie)
    {
            this.categorie=categorie;
    }
         
           public void setQuantite(String quantite)
    {
            this.quantite=quantite;
    }
         
            
      
      
}
