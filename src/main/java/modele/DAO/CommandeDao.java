package modele.DAO;

import java.util.List;
import modele.Client;
import modele.Commande;

/**
 *
 * @author rebecca
 */
public interface CommandeDao {
    public int addCommande (Commande commande);
    public int deleteCommande (int numCommande);
    public int updateCommande(Commande commande);
    public Commande getCommande(int numCommande);
    public List<Commande> liste();
    public List<Commande> getCommandeFromClient(Client client) ;
    public List<Commande> getUnpaidCommandeFromClient(Client client);
}
