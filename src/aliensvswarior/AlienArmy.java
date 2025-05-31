package aliensvswarior;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ΑΝΕΣΤΗΣ
 */

/*Η κλάση Alien Army αντιπροσωπεύει τον στρατό των εξωγήινων.
Περιέχει λίστα από Alien αντικείμενα, χρησιμοποιεί AttackStrategy για να επιτίθεται
και ειδοποιεί παρατηρητές (Satellite, Telescope) με βάση το Observer Pattern.
*/
public class AlienArmy {
    private List<Alien> aliens = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
    private AttackStrategy strategy;

    //Ο κατασκευαστής. Δημιουργεί army με συγκεκριμένο αριθμό εξωγήινων.
    public AlienArmy(int size){
        for(int i = 1; i <= size; i++ ){
            aliens.add(new Alien(i));
        }
    }
    
    //Μέθοδος που εκτελεί την επίθεση που έχει οριστεί.
    public void performAttack(Warrior warrior) {
        if (strategy != null) {
            strategy.attack(warrior, this);
        } else {
            System.out.println("No attack strategy set.");
        }
    }

    //Μέθοδος που προσθέτει ένα παρατηρητή. Ειδοποιείται κάθε φορά που γίνεται επίθεση.
    public void attachObserver(Observer observer) {
    observers.add(observer);
    }
    
    //Επιστρέφει όλους τους παρατηρητές του AlienArmy.
    public List<Observer> getObservers() {
        return observers;
    }    
    
    //Μέθοδος που ορίζει τη στρατηγική που θα χρησιμοποιηθεί στην επόμενη επίθεση.
    public void setStrategy(AttackStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Alien> getAliens(){
        return aliens;
    }
    
    //Μέθοδος που Αφαιρεί από τον στρατό τους εξωγήινους που καταστράφηκαν από τον πολεμιστή.
    public void removeAliens(List<Alien> toRemove){
        aliens.removeAll(toRemove);
    }
    
    //Μέθοδος που επιστρέφει true αν δεν υπάρχουν άλλοι εξωγήινοι.
    public boolean isDefeated(){
        return aliens.isEmpty();
    }
}
