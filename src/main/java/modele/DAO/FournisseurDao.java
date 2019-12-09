/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
