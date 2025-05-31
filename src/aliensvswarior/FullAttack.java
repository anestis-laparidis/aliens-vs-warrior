package aliensvswarior;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ΑΝΕΣΤΗΣ
 */

//Πλήρης επίθεση. Κάθε εξωγήινος προκαλεί διπλή ζημιά. 
class FullAttack implements AttackStrategy {
    @Override
    public void attack(Warrior warrior, AlienArmy army) {
        List<Alien> attackers = new ArrayList<>(army.getAliens());
        System.out.println("AlienArmy performs a FULL attack!");
        warrior.reduceStrength(attackers.size() * 2, attackers.size());
        for (Observer obs : army.getObservers()) {
            if (obs instanceof Telescope) {
                obs.update(attackers);  //ενημέρωση παρατηρητών (telescope).
            }
        }
    }
}
