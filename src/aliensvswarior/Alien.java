package aliensvswarior;
/**
 *
 * @author ΑΝΕΣΤΗΣ
 */

//Η κλάση Alien αντιπρωπεύει έναν εξωγήινο του στρατού.
public class Alien {
    //κάθε alien έχει ένα μοναδικό id.
    private final int id;
    
    public Alien(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    @Override
    public String toString(){
        return "Alien#" + id;
    }
}
