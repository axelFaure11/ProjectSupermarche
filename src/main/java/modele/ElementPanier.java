package modele;

/**
 *
 * @author rebecca
 */
public class ElementPanier {
    private String elementPanierId;
    private String description;
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

    public String getDescription()
    {
       return description;
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

    public void setDescription (String description)
    {
        this.description=description;
    }   
}