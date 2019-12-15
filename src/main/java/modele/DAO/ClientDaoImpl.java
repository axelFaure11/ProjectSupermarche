/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.Client;
import modele.DAO.DB;


    public class ClientDaoImpl {
        private DB db = new DB();
	private int ok;
	private ResultSet rs;
              
    public int addClient(Client client) throws SQLException {     
        String sql= "INSERT INTO Client VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        try
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setString(1, client.getCode());
            db.getPstm().setString(2, client.getSociete());
            db.getPstm().setString(3, client.getContact());
            db.getPstm().setString(4, client.getFonction());
            db.getPstm().setString(5, client.getAdresse());
            db.getPstm().setString(6, client.getVille());
            db.getPstm().setString(7, client.getRegion());
            db.getPstm().setString(8, client.getCodePostal());
            db.getPstm().setString(9, client.getPays());
            db.getPstm().setString(10, client.getTel());
            db.getPstm().setString(11, client.getFax());
            // Execution de la requete
            ok=db.executeMaj();
            db.getCnx().close();
        
        }
        catch(Exception e)
        {
            throw new SQLException(e);
        }
        return ok;
    }
    
    public int updateClient (Client client) throws SQLException{
        String sql="UPDATE client SET societe = ?, contact = ?, fonction = ?, adresse = ?, ville = ?, region= ?, "
            + "code_postal = ?, pays = ?, telephone = ?, fax = ? "
            + "WHERE code = ?";
        ok=0;
        try
        {
            //Initialisation de la requete
            db.initPrepare(sql);
            // Passage des valeurs
            db.getPstm().setString(1, client.getSociete());
            db.getPstm().setString(2, client.getContact());
            db.getPstm().setString(3, client.getFonction());
            db.getPstm().setString(4, client.getAdresse());
            db.getPstm().setString(5, client.getVille());
            db.getPstm().setString(6, client.getRegion());
            db.getPstm().setString(7, client.getCodePostal());
            db.getPstm().setString(8, client.getPays());
            db.getPstm().setString(9, client.getTel());
            db.getPstm().setString(10, client.getFax());
            db.getPstm().setString(11, client.getCode());
            
            ok=db.executeMaj();
            db.getCnx().close();

        }
        catch (Exception e)
        {
            throw new SQLException(e);
        }
        return ok;
    }
    
    
    public int deleteClient(String code) throws SQLException {
        String sql="DELETE FROM client WHERE code=?";
        ok=0;
        try
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setString(1, code);
            ok=db.executeMaj();
            db.getCnx().close();
        }
        catch(Exception e)
        {
            throw new SQLException(e);
        }
        return ok;     
    }
       
    
        public Client getClient(String code) throws SQLException {
        Client client = null;
        String sql = "SELECT * FROM client WHERE code = ?";
        try 
        {
            db.initPrepare(sql);
            db.getPstm().setString(1, code);
            rs=db.executeSelect();
            if (rs.next())
            {
                client=new Client();
                client.setCode(rs.getString(1));
                client.setSociete(rs.getString(2));
                client.setContact(rs.getString(3));
                client.setFonction(rs.getString(4));
                client.setAdresse(rs.getString(5));
                client.setVille(rs.getString(6));
                client.setRegion(rs.getString(7));
                client.setCodePostal(rs.getString(8));
                client.setPays(rs.getString(9));
                client.setTel(rs.getString(10));
                client.setFax(rs.getString(11));
      
            }
            rs.close();
            db.getCnx().close();
        }
            catch(Exception e)
        {
            throw new SQLException(e);
        }
        return client;
        }
    

    public List<Client> liste() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql= "SELECT * FROM client";
        try 
        {
            db.initPrepare(sql);
            rs= db.executeSelect();
            while(rs.next())
            {
                Client client = new Client();
                client.setCode(rs.getString(1));
                client.setSociete(rs.getString(2));
                client.setContact(rs.getString(3));
                client.setFonction(rs.getString(4));
                client.setAdresse(rs.getString(5));
                client.setVille(rs.getString(6));
                client.setRegion(rs.getString(7));
                client.setCodePostal(rs.getString(8));
                client.setPays(rs.getString(9));
                client.setTel(rs.getString(10));
                client.setFax(rs.getString(11));
                clients.add(client);
            }
            rs.close();
            db.getCnx().close();
        }
        catch (Exception e)
        {
            throw new SQLException(e);
        }
        return clients;
    }
    
}
