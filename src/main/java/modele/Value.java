package modele;

/**
 *
 * @author Axel
 */
public class Value<E> {
    
    E v;

    public Value(E value) {
        this.v = value;
    }

    public E getValue() {
        return v;
    }

    public void setValue(E value) {
        this.v = value;
    }
    
    
}
