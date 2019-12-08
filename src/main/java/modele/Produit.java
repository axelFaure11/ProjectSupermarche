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
    
    public int getCodeFournisseur()
    {
        return codeFournisseur;
    }
    
    public int getCategorie ()
    {
        return categorie;
    }
    
    public String getQuantite()
    {
        return quantite;
    }
    
    public double getPrix()
    {
        return prix;
    }
    
    public int getUnitesEnStock()
    {
        return unitesEnStock;
    }
    
     public int getUnitesCommandees()
    {
        return unitesCommandees;
    }
     
      public int getNiveauReapprovi()
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
         
        public void setPrix(int prix)
    {
            this.prix=prix;
    }
    
        public void setUnitesEnStock(int unitesEnStock)
    {
            this.unitesEnStock=unitesEnStock;
    }
            
        public void setUnitesCommandees(int unitesCommandees)
    {
            this.unitesCommandees=unitesCommandees;
    }
        
        public void setNiveauReapprovi(int niveauReapprovi)
    {
            this.niveauReapprovi=niveauReapprovi;
    }
        @Override
        public boolean equals(Object o){
            if(o instanceof Produit){
                return ((Produit) o).getCategorie() == (this.getCategorie())
                        && ((Produit) o).getCodeFournisseur() == (this.getCodeFournisseur())
                        && ((Produit) o).getNiveauReapprovi() == (this.getNiveauReapprovi())
                        && ((Produit) o).getNom().equals(this.getNom())
                        && ((Produit) o).getRef() == (this.getRef());
            } else {
                return false;
            }
        }
            
      
      
}
