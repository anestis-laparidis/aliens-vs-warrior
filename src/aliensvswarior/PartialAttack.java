package aliensvswarior;

import java.util.List;

/**
 *
 * @author ΑΝΕΣΤΗΣ
 */

//Μερική επίθεση.
class PartialAttack implements AttackStrategy {
    private int numAttackers;

    public PartialAttack(int numAttackers) {
        this.numAttackers = numAttackers;
    }

    @Override
    public void attack(Warrior warrior, AlienArmy army) {
        List<Alien> aliens = army.getAliens();
        int limit = Math.min(numAttackers, aliens.size());
        List<Alien> attackers = aliens.subList(0, limit);

        System.out.println("AlienArmy performs a PARTIAL attack!");
        
        warrior.reduceStrength(attackers.size(), attackers.size());
        for (Observer obs : army.getObservers()) {
            if (obs instanceof Satellite) {
                obs.update(attackers.subList(0, attackers.size() / 2));  //ενημέρωση παρατηρητών (satellite).
            }
        }
    }
}
