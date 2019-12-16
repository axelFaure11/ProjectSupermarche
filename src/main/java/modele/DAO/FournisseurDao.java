package modele.DAO;

import java.util.List;
import modele.Fournisseur;
/**
 *
 * @author rebecca
 */
public interface FournisseurDao {
    void addFournisseur (Fournisseur fournisseur);
    void updateFournisseur (Fournisseur fournisseur);
    public int deleteFournisseur(String codeFournisseur);
    public int getFournisseur(String codeFournisseur);
    List<Fournisseur> list();
   
}     
