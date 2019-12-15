/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;



/**
 *
 * @author rebecca
 */
public class Commande 
{
    private int numCommande;
    private Client client;
    private Date saisieLe;
    private Date envoyeeLe;
    private double port;
    private String destinataire;
    private String adresseLivraison;
    private String villeLivraison;
    private String regionLivraison;
    private String codePostal;
    private String paysLivraison;
    private double remise;

    public Commande() {
    }

    public Commande(int numCommande, Client client, double port, String destinataire, String adresseLivraison, String villeLivraison, String regionLivraison, String codePostal, String paysLivraison, double remise) {
        this.numCommande = numCommande;
        this.client = client;
        this.saisieLe = getDate();
        this.envoyeeLe = null;
        this.port = port;
        this.destinataire = destinataire;
        this.adresseLivraison = adresseLivraison;
        this.villeLivraison = villeLivraison;
        this.regionLivraison = regionLivraison;
        this.codePostal = codePostal;
        this.paysLivraison = paysLivraison;
        this.remise = remise;
    }
    
    private java.sql.Date getDate(){
        return new Date(System.currentTimeMillis());
    }
    
    public String getPaysLivraison() {
        return paysLivraison;
    }

    public void setPaysLivraison(String paysLivraison) {
        this.paysLivraison = paysLivraison;
    }

    public Date getSaisieLe() {
        return saisieLe;
    }

    public void setSaisieLe(Date saisieLe) {
        this.saisieLe = saisieLe;
    }

    public Date getEnvoyeeLe() {
        return envoyeeLe;
    }

    public void setEnvoyeeLe(Date envoyeeLe) {
        this.envoyeeLe = envoyeeLe;
    }


    public String getVilleLivraison() {
        return villeLivraison;
    }

    public void setVilleLivraison(String villeLivraison) {
        this.villeLivraison = villeLivraison;
    }

    public String getRegionLivraison() {
        return regionLivraison;
    }

    public void setRegionLivraison(String regionLivraison) {
        this.regionLivraison = regionLivraison;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    
    
    
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

}
