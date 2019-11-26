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
    void ajouterFournisseur (Fournisseur fournisseur);
    void supprimerFournisseur (Fournisseur fournisseur);
    List<Fournisseur> list();
   
}     
