/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;
import java.util.List;
import modele.Client;
/**
 *
 * @author rebecca
 */
public interface ClientDao 
{
    void ajouterClient (Client client);
    void supprimerClient (Client client);
    List<Client> getAllClients();
    
}
