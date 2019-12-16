package modele.DAO;
import java.util.List;
import modele.Produit;
/**
 *
 * @author rebecca
 */
public interface ProduitDao        
{
    public List<Produit> getAll();
    void addProduit (Produit produit);
    void deleteProduit (int ref);
    void upadteProduit (Produit produit);
    public Produit getProduit(int ref);
    public List<Produit> liste();
}
