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
public class Ligne {
    private int commande;
    private int produit;
    private int quantite;
    
    //getters
    public int getCommande()
    {
        return commande;
    }
    
    public int getProduit()
    {
        return produit;
    }
    
    public int getQuantite()
    {   
        return quantite;
    }
    
    //setters
    
    public void setCommande(int commande)
    {
        this.commande=commande;
    }
    
    public void setProduit(int Produit)
    {
        this.produit=produit;
    } 
    
    public void setQuantite(int quantite)
    {
        this.quantite=quantite;
    }
}
