/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
import java.util.Date;



/**
 *
 * @author rebecca
 */
public class Commande 
{
    private int numCommande;
    private Date date;
    private double port;
    private String destinataire;
    private String adresseLivraison;
    private double remise;
    private Client client;
    
    
    
    //getters
    public int getNumCommande()
    {
        return numCommande;
    }
    
    public double getPort()
    {
        return port;
    }
    
    public String getDestinataire()
    {
        return destinataire;
    }
    
    public String getAdresseLivraison()
    {
        return adresseLivraison;
    }
    
    public double getRemise()
    {
        return remise;
    }
    
    public Client getClient()
    {
        return client;
    }
    
     public Date getDate()
    {
        return date;
    }
    
    //setters
    
    public void setNumCommande(int numCommande)
    {
        this.numCommande=numCommande;
    }
    
    public void setPort(double port)
    {
        this.port=port;
    }
    
    public void setDestinataire(String destinataire)
    {
        this.destinataire=destinataire;
    }
    
    public void setAdresseLivraison(String adresseLivraison)
    {
        this.adresseLivraison=adresseLivraison;
    }
    
    public void setRemise(double remise)
    {
        this.remise=remise;
    }

    public void setClient(Client client) 
    {
        this.client=client;
     
    }
    
     public void setDate(Date date) 
    {
        this.date=date;
     
    }

}
