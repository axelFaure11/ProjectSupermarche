/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import java.util.List;
import modele.Client;
import modele.ElementPanier;
import modele.Panier;

/**
 *
 * @author rebecca
 */
public interface PanierDao 
{
    public int createPanier (Panier panier);
    public int updatePanier (Panier panier);    
    public int deletePanier (Panier panier);
    public Panier getPanier(String panierId);
    public int validaterPanier (Panier panier);
    public List<Panier> liste();
    public List<ElementPanier> liste(ElementPanier elementPanier, Client client );
    public boolean statePanier(String panierId);
    
}
