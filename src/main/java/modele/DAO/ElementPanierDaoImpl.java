/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;


import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import modele.Client;
import modele.Panier;
import modele.ElementPanier;

/**
 *
 * @author rebecca
 */
public class ElementPanierDaoImpl {
    
    private DB db = new DB();
    private int ok;
    private ResultSet rs;
    
    public int addElementPanier (ElementPanier elementPanier){
        String sql= "INSERT INTO elementPanier VALUES (?,?,?,?,?,?)";
        try 
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            //Passage de valeurs
            db.getPstm().setString(1, elementPanier.getElementPanierId());
            db.getPstm().setString(2, elementPanier.getPanier().getPanierId());
            db.getPstm().setInt(3, elementPanier.getProduit().getRef());
            db.getPstm().setInt(4, elementPanier.getQuantite());
            db.getPstm().setDouble(5, elementPanier.getPrix());
            db.getPstm().setString(6, elementPanier.getDescription());
            //Execution de la requete
            ok = db.executeMaj();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ok;  
    }
    
    public int deleteElementPanier(String elementPanierId){
        String sql= "DELETE FROM elementPanier WHERE elementPanierId = ?";
        ok=0;
        try
        {   
           // Initialisation de la requete
           db.initPrepare(sql);
           // Passage des valeurs 
           db.getPstm().setString(1, elementPanierId);
           // Execution  de la requete
           ok= db.executeMaj();
        }
        catch(Exception e)
        {
           e.printStackTrace(); 

        }
        return ok;            
    }
    
    public int deleteAll(Panier panier){
        String sql= "DELETE * FROM panier WHERE elementPanierId = ?";
        ok=0;
        try
        {   
           // Initialisation de la requete
           db.initPrepare(sql);
           // Passage des valeurs
           db.getPstm().setList(1, panier.getElementPanier());
           // Execution  de la requete
           ok= db.executeMaj();
        }
        catch(Exception e)
        {
           e.printStackTrace(); 

        }
        return ok;            
    }
    
     public List<ElementPanier> liste() {
        List<ElementPanier> elementPaniers = new ArrayList<>();
        String sql= "SELECT * FROM elementPanier";
        try 
        {
            db.initPrepare(sql);
            rs= db.executeSelect();
            while(rs.next())
            {
                ElementPanier elementPanier = new ElementPanier();
                elementPanier.setElementPanierId(rs.getString(1));
                elementPanier.setPanier(rs.getString(2));
                elementPanier.setProduit(rs.getString(3));
                elementPanier.setQuantite(rs.getInt(4));
                elementPanier.setPrix(rs.getDouble(5));
                elementPanier.setDescription(rs.getString(6));
                elementPaniers.add(elementPanier);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(); //
        }
        return elementPaniers;
    }
    
    
    
    
    
    
    
}
