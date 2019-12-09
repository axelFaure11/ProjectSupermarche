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
public interface ElementPanierDao {
    
    public int addElementPanier(ElementPanier elementPanier);
    public int deleteElementPanier(String elementPanierId);
    public int deleteAll(Panier panier); 
    public List<ElementPanier> liste();
    
}
