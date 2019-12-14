/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modele.Categorie;
import modele.Commande;
import modele.Client;
import modele.Ligne;
import modele.Produit;
/**
 *
 * @author rebecca
 */



public class CommandeDaoImpl {
    private DB db = new DB();
    private int ok;
    private ResultSet rs;
        
   //ajout d'une commande          
    public int addCommande(Commande commande,Map<Produit, Integer> produitsEtQuantites)  throws SQLException
    {     
        String sql= "INSERT INTO Commande VALUES(?,?,?,?,?,?,?,?,?) ";
        try
        {
            Date saisieLe = new Date();
            Date envoyeeLe = new Date();             
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //s
            db.getCnx().setAutoCommit(false);
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setInt(1, commande.getNumCommande());
            db.getPstm().setString(2, sdf.format(saisieLe)); 
            db.getPstm().setString(3, sdf.format(envoyeeLe));           
            db.getPstm().setDouble(4, commande.getPort());
            db.getPstm().setString(5, commande.getDestinataire());
            db.getPstm().setString(6, commande.getAdresseLivraison());
            db.getPstm().setString(7, commande.getVilleLivraison());
            db.getPstm().setString(8, commande.getRegionLivraison());
            db.getPstm().setString(9, commande.getCodePostal());
            db.getPstm().setString(10, commande.getPaysLivraison());
            db.getPstm().setDouble(11, commande.getRemise());
            db.getPstm().setString(12, commande.getClient().getCode());
            // Execution de la requete
            ok=db.executeMaj();
            if(ok!=-1)
            {
            // On récupère l'id de la commande qui vient d'etre temporairement créée
            ResultSet clesGenere = db.getPstm().getGeneratedKeys();
            // on place le curseur sur le premier enregistrement pour verifier si on a au moins une valeur retourné
            if(clesGenere.next())
            {
              // on récupère la cle généré par l'insertion c-à-d le code de la commande
              int cleCommande = clesGenere.getInt(1);
              // On parcourt la map grace à entrySet
              for(Map.Entry<Produit, Integer>  pEQ: produitsEtQuantites.entrySet())
              {
                Ligne ligne = new Ligne();
                ligne.setCommande(cleCommande);
                // on récupère la clé d'une set qui correspond au produit
                ligne.setProduit(pEQ.getKey());
                // on récupère la clé d'une set qui correspond à la quantité du produit vendu
                ligne.setQuantite(pEQ.getValue());
                new LigneDaoImpl().addLigne(ligne);
              }
            }
          }
          // On valide la transaction
          db.getCnx().commit();
          // On desactive le mode transaction
          db.getCnx().setAutoCommit(true);    
        }        
        catch(Exception e)
        {
            throw new SQLException(e); 
        }
        return ok;
    }
    
    
    //mise à jour de la commande
    public int updateCommande (Commande commande,Map<Produit, Integer> produitsEtQuantites) throws SQLException
    {
        String sql="UPDATE commande SET saisieLe=?, envoyeeLe=?, port=?, destinataire=?,"
                + " adresseLivraison=?, remise=?, client=? , state=? WHERE Numero = ?";          
        ok=0;
        try
        {
            Date saisieLe = new Date();
            Date envoyeeLe = new Date();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //Initialisation de la requete
            db.initPrepare(sql);
            // Passage des valeurs
            
            db.getPstm().setString(1, sdf.format(saisieLe)); 
            db.getPstm().setString(2, sdf.format(envoyeeLe));  
            db.getPstm().setDouble(3, commande.getPort());
            db.getPstm().setString(4, commande.getDestinataire());
            db.getPstm().setString(5, commande.getAdresseLivraison());
            db.getPstm().setString(6, commande.getVilleLivraison());
            db.getPstm().setString(7, commande.getRegionLivraison());
            db.getPstm().setString(8, commande.getCodePostal());
            db.getPstm().setString(9, commande.getPaysLivraison());
            db.getPstm().setDouble(10, commande.getRemise());
            db.getPstm().setString(11, commande.getClient().getCode());
            db.getPstm().setInt(13, commande.getNumCommande());
               // Execution de la requete
            ok=db.executeMaj();
            if(ok!=-1)
            {
            // On récupère l'id de la commande qui vient d'etre temporairement crée
            ResultSet clesGenere = db.getPstm().getGeneratedKeys();
            // on place le curseur sur le premier enregistrement pour verifier si on a au moins une valeur retourné
            if(clesGenere.next())
            {
              // on récupère la cle généré par l'insertion c-à-d le code de la commande
              int cleCommande = clesGenere.getInt(1);
              // On parcourt la map grace à entrySet
              for(Map.Entry<Produit, Integer>  pEQ: produitsEtQuantites.entrySet())
              {
                Ligne ligne = new Ligne();
                ligne.setCommande(cleCommande);
                // on récupère la clé d'une set qui correspond au produit
                ligne.setProduit(pEQ.getKey());
                // on récupère la clé d'une set qui correspond à la quantité du produit vendu
                ligne.setQuantite(pEQ.getValue());
                new LigneDaoImpl().updateLigne(ligne);
              }
            }
          }
          // On valide la transaction
          db.getCnx().commit();
          // On desactive le mode transaction
          db.getCnx().setAutoCommit(true);      
        }
        catch (Exception e)
        {
            throw new SQLException(e);
        }
        return ok;
    }
    
    //suppression d'une commande
    public int deleteCommande(Commande commande ,Produit produit , Map<Produit, Integer> produitsEtQuantites) throws SQLException
    {
        String sql="DELETE * FROM commande WHERE Numero=?";
        ok=0;
        try
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setInt(1, commande.getNumCommande());
            ok=db.executeMaj();
            if(ok!=-1)
          {
            // On récupère l'id de la commande qui vient d'etre temporairement crée
            ResultSet clesGenere = db.getPstm().getGeneratedKeys();
            // on place le curseur sur le premier enregistrement pour verifier si on a au moins une valeur retourné
            if(clesGenere.next())
            {
              // on récupère la cle généré par l'insertion c-à-d le code de la commande
              int cleCommande = clesGenere.getInt(1);
              // On parcourt la map grace à entrySet
              for(Map.Entry<Produit, Integer>  pEQ: produitsEtQuantites.entrySet())
              {
                Ligne ligne = new Ligne();
                ligne.setCommande(cleCommande);
                // on récupère la clé d'une set qui correspond au produit
                ligne.setProduit(pEQ.getKey());
                // on récupère la clé d'une set qui correspond à la quantité du produit vendu
                ligne.setQuantite(pEQ.getValue());
                new LigneDaoImpl().deleteLigne(commande, produit);
              }
            }
          }
          // On valide la transaction
          db.getCnx().commit();
          // On desactive le mode transaction
          db.getCnx().setAutoCommit(true);
        }
        catch(Exception e)
        {
            throw new SQLException(e);
        }
        return ok;     
    }
       
    //get commande by numero
    public Commande getCommande(int numCommande) throws SQLException
    {
      Commande commande = null;
        String sql = "SELECT * FROM commande WHERE Numero = ?";  
        try 
        {
            db.initPrepare(sql);
            db.getPstm().setInt(1, numCommande);
            rs=db.executeSelect();
            if (rs.next())
            {
                commande=new Commande();
                commande.setNumCommande(rs.getInt(1));
                commande.setSaisieLe(rs.getDate(2));   
                commande.setEnvoyeeLe(rs.getDate(3)); 
                commande.setPort(rs.getDouble(4));
                commande.setDestinataire(rs.getString(5));
                commande.setAdresseLivraison(rs.getString(6));
                commande.setVilleLivraison(rs.getString(7));
                commande.setRegionLivraison(rs.getString(8));
                commande.setCodePostal(rs.getString(9));
                commande.setPaysLivraison(rs.getString(10));
                commande.setRemise(rs.getDouble(11));
                commande.setClient(new ClientDaoImpl().getClient(rs.getString(12)));
            }
        }
        catch(Exception e)
        {
            throw new SQLException(e);
        }
        return commande;
        }
    
    //liste de toutes les commandes
    public List<Commande> liste()  throws SQLException
    {
        List<Commande> commandes = new ArrayList<>();
        String sql= "SELECT * FROM commande";
        try 
        {
            db.initPrepare(sql);
            rs= db.executeSelect();
            while(rs.next())
            {
                Commande commande=new Commande();
                 commande.setNumCommande(rs.getInt(1));
                commande.setSaisieLe(rs.getDate(2));   
                commande.setEnvoyeeLe(rs.getDate(3)); 
                commande.setPort(rs.getDouble(4));
                commande.setDestinataire(rs.getString(5));
                commande.setAdresseLivraison(rs.getString(6));
                commande.setVilleLivraison(rs.getString(7));
                commande.setRegionLivraison(rs.getString(8));
                commande.setCodePostal(rs.getString(9));
                commande.setPaysLivraison(rs.getString(10));
                commande.setRemise(rs.getDouble(11));
                commande.setClient(new ClientDaoImpl().getClient(rs.getString(12)));
            }
        }
        catch (Exception e)
        {
            throw new SQLException(e);
        }
        return commandes;
    }
    
    //liste de toutes les commandes d'un client
    public List<Commande> getCommandeFromClient(Client client)  throws SQLException
    {
        List<Commande> commandes = new ArrayList<>();
        String sql= "SELECT * FROM commande WHERE client = ?";
        try 
        {
            db.initPrepare(sql);
            db.getPstm().setString(1, client.getCode());
            rs = db.executeSelect();
            Commande commande;
            while(rs.next())
            {
                commande=new Commande();
                commande.setNumCommande(rs.getInt(1));
                commande.setSaisieLe(rs.getDate(3));   
                commande.setEnvoyeeLe(rs.getDate(4)); 
                commande.setPort(rs.getDouble(5));
                commande.setDestinataire(rs.getString(6));
                commande.setAdresseLivraison(rs.getString(7));
                commande.setVilleLivraison(rs.getString(8));
                commande.setRegionLivraison(rs.getString(9));
                commande.setCodePostal(rs.getString(10));
                commande.setPaysLivraison(rs.getString(11));
                commande.setRemise(rs.getDouble(12));
                commande.setClient(new ClientDaoImpl().getClient(rs.getString(2)));
                commandes.add(commande);
            }
        }
        catch (Exception e)
        {
            throw new SQLException(e);
        }
        return commandes;
    }
    
    //liste des commandes impayées d'un client
    public List<Commande> getUnpaidCommandeFromClient(Client client) throws SQLException
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
                commande.setSaisieLe(rs.getDate(2));   
                commande.setEnvoyeeLe(rs.getDate(3)); 
                commande.setPort(rs.getDouble(4));
                commande.setDestinataire(rs.getString(5));
                commande.setAdresseLivraison(rs.getString(6));
                commande.setVilleLivraison(rs.getString(7));
                commande.setRegionLivraison(rs.getString(8));
                commande.setCodePostal(rs.getString(9));
                commande.setPaysLivraison(rs.getString(10));
                commande.setRemise(rs.getDouble(11));
                commande.setClient(new ClientDaoImpl().getClient(rs.getString(12)));  
                commandes.add(commande);
            }
        }
        catch (Exception e)
        {
            throw new SQLException(e);
        }
        return commandes;
    }
    
    
   public Map<Categorie, Double> chiffreAffCategorie (Date dateDebut, Date dateFin) throws SQLException
    {
         
        Map<Categorie, Double> tabChifCat = new HashMap<>();
        String sql="SELECT p.categorie, SUM(p.prix_unitaire * l.quantite_par_unite) AS CHIFFRE_D_AFFAIRE "
                        + "FROM Commande c "
                        + "JOIN ligne l ON c.numero=l.commande "
                        + "JOIN produit p ON p.reference=l.produit "
                        + "WHERE saisieLe BETWEEN ? AND ? "
                        + "GROUP BY p.categorie";                    
        try
        {
                        
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");         
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            
            db.getPstm().setString(1, sdf.format(dateDebut)); 
            db.getPstm().setString(2, sdf.format(dateFin));
            rs = db.executeSelect();

            while (rs.next())
            {
                Categorie categorie = new CategorieDaoImpl().getCategorie(rs.getInt(1));
                tabChifCat.put(categorie, rs.getDouble(1));
                
            }
        }
        catch (Exception e)
        {
            throw new SQLException(e);
        }
        return tabChifCat;
    }
    
   public Map<String, Double> chiffreAffPays (Date dateDebut, Date dateFin) throws SQLException
    {
         
        Map<String, Double> tabChifPays = new HashMap<>();
        String sql="SELECT c.pays_livraison, SUM(p.prix_unitaire * l.quantite_par_unite) AS CHIFFRE_D_AFFAIRE "
                        + "FROM Commande c "
                        + "JOIN ligne l ON c.numero=l.commande "
                        + "JOIN produit p ON p.reference=l.produit "
                        + "WHERE saisieLe BETWEEN ? AND ? "
                        + "GROUP BY c.pays_livraison";                    
        try
        {
                        
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");         
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            
            db.getPstm().setString(1, sdf.format(dateDebut)); 
            db.getPstm().setString(2, sdf.format(dateFin));
            rs = db.executeSelect();

            while (rs.next())
            {
                
                tabChifPays.put(rs.getString(1), rs.getDouble(2));
                
            }
        }
        catch (Exception e)
        {
            throw new SQLException(e);
        }
        return tabChifPays;
    }
   
      
   public Map<Client, Double> chiffreAffClient(Date dateDebut, Date dateFin) throws SQLException
    {
         
        Map<Client, Double> tabChifClient = new HashMap<>();
        String sql="SELECT cl.reference, SUM(p.prix_unitaire * l.quantite_par_unite) AS CHIFFRE_D_AFFAIRE "
                        + "FROM Client cl "
                        + "JOIN commande c ON cl.code=c.client"               
                        + "JOIN ligne l ON c.numero=l.commande "
                        + "JOIN produit p ON p.reference=l.produit "
                        + "WHERE saisieLe BETWEEN ? AND ? "
                        + "GROUP BY client.code";                    
        try
        {
                        
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");         
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            
            db.getPstm().setString(1, sdf.format(dateDebut)); 
            db.getPstm().setString(2, sdf.format(dateFin));
            rs = db.executeSelect();

            while (rs.next())
            {
                Client client = new ClientDaoImpl().getClient(rs.getString(1));
                tabChifClient.put(client, rs.getDouble(1));
                
            }
        }
        catch (Exception e)
        {
            throw new SQLException(e);
        }
        return tabChifClient;
    }
                
}
      