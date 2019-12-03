/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;
import modele.Client;
import modele.DAO.ClientDaoImpl;
import modele.DAO.ClientDao;
import java.util.Scanner;
/**
 *
 * @author rebecca
 */
public class ClientTest {

    
    public static void main (String[] args )
    {
   
        Client client = new Client();
        client.setCode("REB");
        client.setSociete("REB");
        client.setContact("REB");
        client.setFonction("REB");
        client.setAdresse("REB");
        client.setVille("REB");
        client.setRegion("REB");
        client.setCodePostal("REB");
        client.setPays("REB");
        client.setTel("REB");
        client.setFax("REB");
        client.setNom("REB");
        client.setPrenom("REB");
        client.setEmail("REB");
        client.setPassword("REB");

        ClientDaoImpl clientDaoImpl= new ClientDaoImpl();
        clientDaoImpl.addClient(client);
        
        
    }
    
    
    
}
