/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import modele.Panier;
import modele.Client;


/**
 *
 * @author rebecca
 */
public class PanierDaoImpl {
    
    private DB db = new DB();
    private int ok;
    private ResultSet rs;
    
    public int createPanier (Panier panier){
    String sql= "INSERT INTO categorie VALUES (?,?,?,?)";
    try 
    {
        // Initialisation de la requete
        db.initPrepare(sql);
        //Passage de valeurs
        db.getPstm().setString(1, panier.getPanierId());
        db.getPstm().setString(2, panier.getClient().getCode());
        db.getPstm().setDouble(3, panier.getPrixTotal());
        db.getPstm().setList(4, panier.getElementPanier());
        //Execution de la requete
        ok = db.executeMaj();
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    return ok;  
}

}
