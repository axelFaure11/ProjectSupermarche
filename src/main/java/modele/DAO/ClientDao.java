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
    public int addClient (Client client);
    public int updateClient(Client client);
    public int deleteClient(String code);
    public int getClient(String code);
    public List<Client> liste();
    
}
