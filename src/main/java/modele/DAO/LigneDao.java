/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import java.util.List;
import modele.Ligne;

/**
 *
 * @author rebecca
 */
public interface LigneDao 
{
    public int addLigne (Ligne ligne);
    public int updateLigne(Ligne ligne);
    public int deleteLigne(int commande,int produit);
    public int getLigne(int commande,int produit);
    public List<Ligne> liste();
    
}
