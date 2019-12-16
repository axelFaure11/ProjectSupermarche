package modele.DAO;

import java.util.List;
import modele.Utilisateur;

/**
 *
 * @author rebecca
 */
public interface UtilisateurDao 
{
    public int addUser(Utilisateur utilisateur);
    public int deleteUser(String userId);
    public int getUser(String userId);
    public int updateUser(Utilisateur utilisateur);
    public List<Utilisateur> liste();

}
