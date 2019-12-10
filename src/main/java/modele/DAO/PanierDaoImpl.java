/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import modele.Categorie;
import modele.Panier;
import modele.Client;
import java.lang.Object;
import modele.ElementPanier;


/**
 *
 * @author rebecca
 */
public class PanierDaoImpl {
    
    private DB db = new DB();
    private int ok;
    private ResultSet rs;
    
    public int createPanier (Panier panier){
    String sql= "INSERT INTO panier VALUES (?,?,?,?)";
    try 
    {
        // Initialisation de la requete
        db.initPrepare(sql);
        //Passage de valeurs
        db.getPstm().setString(1, panier.getPanierId());
        db.getPstm().setString(2, panier.getClient().getCode());
        db.getPstm().setDouble(3, panier.getPrixTotal());
        db.getPstm().setString(4, panier.getElementPanier().getElementPanierId());
        //Execution de la requete
        ok = db.executeMaj();
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    return ok;  
}
    
     public int updatePanier(Panier panier){
        String sql= "UPDATE panier SET client = ?, prixtotal=?,"
                + " elementPanier=?,WHERE panierId=?";
        ok=0;
        try
        {
           // Initalisation de la requete 
            db.initPrepare(sql);
           //  Passage des valeurs
           db.getPstm().setString(1, panier.getPanierId());
           db.getPstm().setString(2, panier.getClient().getCode());
           db.getPstm().setDouble(3, panier.getPrixTotal());
                           panier.setElementPanier((List<ElementPanier>) new ElementPanierDaoImpl().getElementPanier(rs.getString(4)));

           db.getPstm().setString(4, panier.getElementPanier().getElementPanierId());

        }  
        catch(Exception e)
        {
           e.printStackTrace(); 

        }
        return ok;
    }
     
     public int deletePanier(String panierId){
        String sql= "DELETE FROM panier WHERE panierId = ?";
        ok=0;
        try
        {   
           // Initialisation de la requete
           db.initPrepare(sql);
           // Passage des valeurs 
           db.getPstm().setString(1, panierId);
           // Execution  de la requete
           ok= db.executeMaj();
        }
        catch(Exception e)
        {
           e.printStackTrace(); 

        }
        return ok;            
    }
     
    public Panier getPanier(String panierId){
        Panier panier = null;  
        String sql= "SELECT * FROM panier WHERE panierId= ?";
        try
        {
            db.initPrepare(sql);
            db.getPstm().setString(1, panierId);
            ok = db.executeMaj();
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return panier;  
    
    }
    
    public int validatePanier(Panier panier){
        String sql= "UPDATE panier SET client = ?, prixTotal=?,"
                + " elementPanier=?,WHERE panierId=?";
        ok=0;
        try
        {
           // Initalisation de la requete 
            db.initPrepare(sql);
           //  Passage des valeurs
           db.getPstm().setString(1, panier.getPanierId());
           db.getPstm().setString(2, panier.getClient().getCode());
           db.getPstm().setDouble(3, panier.getPrixTotal());
           db.getPstm().setString(4, panier.getElementPanier().getElementPanierId());

        }  
        catch(Exception e)
        {
           e.printStackTrace(); 

        }
        return ok;
    }
    
    public List<Panier> liste() {
        List<Panier> paniers = new ArrayList<>();
        String sql= "SELECT * FROM panier";
        try 
        {
            db.initPrepare(sql);
            rs= db.executeSelect();
            while(rs.next())
            {
                Panier panier = new Panier();
                panier.setPanierId(rs.getString(1));
                panier.setClient(new ClientDaoImpl().getClient(rs.getString(2)));
                panier.setPrixTotal(rs.getDouble(3)); 
                panier.setElementPanier((List<ElementPanier>) new ElementPanierDaoImpl().getElementPanier(rs.getString(4)));
                paniers.add(panier);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(); //
        }
        return paniers;
    }

}
