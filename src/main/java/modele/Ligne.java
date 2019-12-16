package modele;

/**
 *
 * @author rebecca
 */
public class Ligne {
    private int commande;
    private Produit produit;
    private int quantite;

    public Ligne() {
    }

    public Ligne(int commande, Produit produit, int quantite) {
        this.commande = commande;
        this.produit = produit;
        this.quantite = quantite;
    }
    
    //getters
    public int getCommande()
    {
        return commande;
    }
    
    public Produit getProduit()
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
    
    public void setProduit(Produit produit)
    {
        this.produit=produit;
    } 
    
    public void setQuantite(int quantite)
    {
        this.quantite=quantite;
    }
}
