package modele.DAO;
import java.util.List;
import modele.Categorie;

/**
 *
 * @author rebecca
 */
public interface CategorieDao 
{
    public int addCategorie (Categorie categorie);
    public int deleteCategorie (int code);
    public int updateCategorie (Categorie categorie);
    public Categorie getCategorie(int code);
    public List<Categorie> liste();
    
    
}
