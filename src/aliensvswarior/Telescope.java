package aliensvswarior;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ΑΝΕΣΤΗΣ
 */
//Παρακολουθεί τις κινήσεις των εξωγήινων και καταγράφει όσους του δώσει το AlienArmy.
public class Telescope implements Observer {
    private List<Alien> observed;

    //Αποθηκεύει τη λίστα των εξωγήινων που επιτέθηκαν κατα τη πλήρη επίθεση.
    @Override
    public void update(List<Alien> aliens) {
        observed = new ArrayList<>(aliens);
        System.out.println("Telescope recorded " + aliens.size() + " alien(s).");
    }

    //Επιστρέφει τη λίστα των εξωγήινων που κατέγραψε το τηλεσκόπιο.
    public List<Alien> getObserved() {
        return (observed != null) ? observed : new ArrayList<>();
    }
}

