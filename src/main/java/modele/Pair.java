package modele;

/**
 *
 * @author Axel
 */
public class Pair {
    
    Produit pr;
    int quantity;
    
    public Pair(Produit p, int q) {
        pr = p;
        quantity = q;
    }
    
    public Produit getKey(){
        return pr;
    }
    
    public int getQuantity(){
        return quantity;
    }
}
