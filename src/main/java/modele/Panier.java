package modele;

import java.util.List;

/**
 *
 * @author rebecca
 */
public class Panier {
    private String panierId;
    private Client client;
    private double prixTotal;
    private List<ElementPanier> ElementPanier;
    
    
    //getters
    public String getPanierId()
    {
        return panierId;
    }
      public Client getClient()
    {
        return client;
    }
    
    public double prixTotal()
    {
        return prixTotal;
    }
    
    public List <ElementPanier> getElementPanier()
    {
        return ElementPanier;
    }
    
  
    
    
    //setters
    
    public void setPanierId(String panierId)
    {
        this.panierId=panierId;
    }
    
        public void setClient (Client client)
    {
        this.client=client;
    }
        
    public void setPrixTotal(double prixTotal)
    {
        this.prixTotal=prixTotal;
    }
    
    public void setElementPanier(List<ElementPanier> ElementPanier)
    {
        this.ElementPanier=ElementPanier;
    }

    
    
    
}
