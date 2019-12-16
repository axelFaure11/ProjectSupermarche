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
