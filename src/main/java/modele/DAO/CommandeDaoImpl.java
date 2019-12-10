/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modele.Commande;
import modele.Client;
/**
 *
 * @author rebecca
 */



public class CommandeDaoImpl {
    private DB db = new DB();
	private int ok;
	private ResultSet rs;
        
        
        
        
              
    public int addCommande(Commande commande) 
    {     
        String sql= "INSERT INTO Commande VALUES(?,?,?,?,?,?,?,?) ";
        try
        {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setInt(1, commande.getNumCommande());
            db.getPstm().setString(2, sdf.format(date));
            db.getPstm().setDouble(3, commande.getPort());
            db.getPstm().setString(4, commande.getDestinataire());
            db.getPstm().setString(5, commande.getAdresseLivraison());
            db.getPstm().setDouble(6, commande.getRemise());
            db.getPstm().setString(7, commande.getClient().getCode());
           db.getPstm().setBoolean(8, commande.getState());
            // Execution de la requete
            ok=db.executeMaj();
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ok;
    }
    
    public int updateCommande (Commande commande)
    {
        String sql="UPDATE commande SET date=?, port=?, destinataire=?,"
                + " adresseLivraison=?, remise=?, client=? , state=? WHERE numCommande = ?";          
        ok=0;
        try
        {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //Initialisation de la requete
            db.initPrepare(sql);
            // Passage des valeurs
            db.getPstm().setInt(1, commande.getNumCommande());
            db.getPstm().setString(2, sdf.format(date));
            db.getPstm().setDouble(3, commande.getPort());
            db.getPstm().setString(4, commande.getDestinataire());
            db.getPstm().setString(5, commande.getAdresseLivraison());
            db.getPstm().setDouble(6, commande.getRemise());
            db.getPstm().setString(7, commande.getClient().getCode());
            db.getPstm().setBoolean(8, commande.getState());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ok;
    }
    
    
    public int deleteCommande(int numCommande)
    {
        String sql="DELETE * FROM commande WHERE numCommande=?";
        ok=0;
        try
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setInt(1, numCommande);
            ok=db.executeMaj();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ok;     
    }
       
    
        public Commande getCommande(int numCommande)
    {
        Commande commande = null;
        String sql = "SELECT * FROM commande WHERE numCommande = ?";
        try 
        {
            db.initPrepare(sql);
            db.getPstm().setInt(1, numCommande);
            rs=db.executeSelect();
            if (rs.next())
            {
                commande=new Commande();
                commande.setNumCommande(rs.getInt(1));
                commande.setDate(rs.getDate(2));                
                commande.setPort(rs.getDouble(3));
                commande.setDestinataire(rs.getString(4));
                commande.setAdresseLivraison(rs.getString(5));
                commande.setRemise(rs.getDouble(6));               
                commande.setClient(new ClientDaoImpl().getClient(rs.getString(7)));  
                commande.setState(rs.getBoolean(8)); 
                
      
            }
        }
            catch(Exception e)
        {
            e.printStackTrace();
        }
        return commande;
        }
    

    public List<Commande> liste() 
    {
        List<Commande> commandes = new ArrayList<>();
        String sql= "SELECT * FROM commande";
        try 
        {
            db.initPrepare(sql);
            rs= db.executeSelect();
            while(rs.next())
            {
                Commande commande = new Commande();
                commande.setNumCommande(rs.getInt(1));
                commande.setDate(rs.getDate(2)); 
                commande.setPort(rs.getDouble(3));
                commande.setDestinataire(rs.getString(4));
                commande.setAdresseLivraison(rs.getString(5));
                commande.setRemise(rs.getDouble(6));
                commande.setClient(new ClientDaoImpl().getClient(rs.getString(7)));
                commande.setState(rs.getBoolean(8)); 
                commandes.add(commande);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(); //
        }
        return commandes;
    }
    
    
    public List<Commande> getCommandeFromClient(Client client) 
    {
        List<Commande> commandes = new ArrayList<>();
        String sql= "SELECT * FROM commande,client WHERE commande.client=client.code";
        try 
        {
            db.initPrepare(sql);
            rs= db.executeSelect();
            while(rs.next())
            {
                Commande commande = new Commande();
                commande.setNumCommande(rs.getInt(1));
                commande.setDate(rs.getDate(2)); 
                commande.setPort(rs.getDouble(3));
                commande.setDestinataire(rs.getString(4));
                commande.setAdresseLivraison(rs.getString(5));
                commande.setRemise(rs.getDouble(6));
                commande.setClient(new ClientDaoImpl().getClient(rs.getString(7)));
                commande.setState(rs.getBoolean(8)); 
                commandes.add(commande);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(); //
        }
        return commandes;
    }
    
    
    public List<Commande> getUnpaidCommandeFromClient(Client client)
     {
        List<Commande> commandes = new ArrayList<>();
        String sql= "SELECT * FROM commande,client WHERE commande.client=client.code AND "
                + "commande.state=0";
        try 
        {
            db.initPrepare(sql);
            rs= db.executeSelect();
            while(rs.next())
            {
                Commande commande = new Commande();
                commande.setNumCommande(rs.getInt(1));
                commande.setDate(rs.getDate(2)); 
                commande.setPort(rs.getDouble(3));
                commande.setDestinataire(rs.getString(4));
                commande.setAdresseLivraison(rs.getString(5));
                commande.setRemise(rs.getDouble(6));
                commande.setClient(new ClientDaoImpl().getClient(rs.getString(7)));
                commande.setState(rs.getBoolean(8)); 
                commandes.add(commande);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(); //
        }
        return commandes;
    }
    
}
