package modele;

import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class tempPanier extends ArrayList<Pair> {
    
    public double prixTotal;
    
    public tempPanier() {
        super();
    }
    
    public void updatePrixTotal(double val){
        this.prixTotal = this.prixTotal + val;
    }
    
    public void viderPanier(){
        this.clear();
        this.prixTotal = 0;
    }
    
    public double getPrixTotal(){
        return prixTotal;
    }
    
}
