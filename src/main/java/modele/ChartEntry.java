package modele;

import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class ChartEntry {
    
    ArrayList<Value> c;

    public ChartEntry() {
        this.c = new ArrayList<Value>();
    }

    public ArrayList<Value> getC() {
        return c;
    }

    public void setC(ArrayList<Value> c) {
        this.c = c;
    }
    
}
