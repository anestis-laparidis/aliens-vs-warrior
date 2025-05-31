package aliensvswarior;

import java.util.*;

/**
 *
 * @author ΑΝΕΣΤΗΣ
 */

/* Ο Warrior δέχεται επιθέσεις από τον AlienArmy και αντεπιτίθεται χρησιμοποιώντας 
πληροφορίες που του δίνουν οι παρατηρητές (Satellite & Telescope).
*/
public class Warrior {
    private int strength = 100;
    private double visibility = 1.0;
    private Satellite satellite;
    private Telescope telescope;
    
    //Ο κατασκευαστής
    public Warrior(Satellite satellite, Telescope telescope){
        this.satellite = satellite;
        this.telescope = telescope;
    }
    
    //Ο warrior επιτίθεται στους εξογήινους που καταγράφηκαν από τον δορυφόρο και το τηλεσκόπιο.
    public void attack(AlienArmy army){
        System.out.println("Warrior is attacking using satellite and telescope data...");
        Set<Alien> targets = new HashSet<>(); //καταγραφή ενός εξογήινου μία φορά και απο τις δύο λίστες (telescope και tellite).
        targets.addAll(satellite.getObserved());
        targets.addAll(telescope.getObserved());
        
        int destroyedAliens = targets.size();
        
        //Καταστροφή των εξωγήινων που έχουν παρατηρηθεί
        army.removeAliens(new ArrayList<>(targets));
        System.out.println("Warrior destroyed " + targets.size() + " aliens!" + " {"+ targets +"}");//το <<" {"+ targets +"}" >> δεν είναι απαραιτητο.
        
        //άυξηση της ορατότητας κατά 0.1 για κάθε 10 εξωγήινους που κατέστρεψε.
        int visibilityIncrease = destroyedAliens / 10;
        if(visibilityIncrease > 0){
            increaseVisibility(0.1 * visibilityIncrease);   
        }
    }
    
    //Όταν ο πολεμιστής δέχεται επίθεση, χάνει δύναμη και ορατότητα. Λιγότερη ορατότητα σημαίνει περισσότερη ζημιά.
    public void reduceStrength(int baseDamage, int numAttackers){
        double attackFactor = numAttackers;
        int damage = (int) Math.round(baseDamage * (1 - visibility) * (attackFactor/10));
        strength -= damage;
        if(strength < 0){
            strength = 0;
        }
        System.out.println("Warrior lost " + damage + " strenght. Remaining strength: " + strength + ".");
        
        //Υπολογισμός απώλειας ορατότητας: 0.2 για κάθε 10 εξωγήινους που επιτέθηκαν.
        int visibilityReduction = numAttackers / 10;
        if(visibilityReduction > 0){
            decreaseVisibility(0.2 * visibilityReduction);
        }
        if(visibility < 0){
            visibility = 0;
        }
    }
    
    //Αυξάνει την ορατότητα.
    public void increaseVisibility(double amount){
        visibility += amount;
        if (visibility > 1.0) {
            visibility = 1.0;
        }
        System.out.println("Warrior gained " + amount + " visibility.");
    }
    
    //Μειώνει την ορατότητα.
    public void decreaseVisibility(double amount){
        visibility -= amount;
        if (visibility < 0.0) {
            visibility = 0.0;
        }
        System.out.printf("Warrior lost %.2f visibility.\n", amount);
    }
    
    //Επιστρέφει true όταν ο warrior δέν έχει άλλο δύναμη.
    public boolean isDefeated(){
        return strength <=0;
    }
    
    public int getStrength(){
        return strength;
    }
    
    public double getVisibility(){
        return visibility;
    }
}
