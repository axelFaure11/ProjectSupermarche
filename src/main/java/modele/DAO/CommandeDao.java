/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import java.util.List;
import modele.Commande;

/**
 *
 * @author rebecca
 */
public interface CommandeDao {
    public int addCommande (Commande commande);
    public int deleteCommande (int numCommande);
    public int updateCommande(Commande commande);
    public Commande get(int numCommande);
    public List<Commande> liste();
}
