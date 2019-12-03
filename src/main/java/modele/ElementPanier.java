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
public class ElementPanier {
    private String elementPanierId;
    private Panier panier;
    private Produit produit;
    private int quantite;
    private double prix;
    
    //getters
    
    public String getElementPanierId()
    {
        return elementPanierId;
    }
    
        public Panier getPanier()
    {
        return panier;
    }
        
    public Produit getProduit()
    {
        return produit;
    }
    
    public int getQuantite()
    {
        return quantite;
    }
    
    public double getPrix()
    {
       return prix;
    }

    //setters
    
    public void setElementPanierId(String elementPanierId)
    {
        this.elementPanierId=elementPanierId;
    }
    
    public void setPanier(Panier panier)
    {
        this.panier=panier;
    }
    
    public void setProduit(Produit produit)
    {
        this.produit=produit;
    }
    
    public void setQuantite(int quantite)
    {
        this.quantite=quantite;
    }
    
    public void setPrix (double prix)
    {
        this.prix=prix;
    }   
}
