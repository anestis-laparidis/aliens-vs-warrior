package aliensvswarior;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ΑΝΕΣΤΗΣ
 */
//Παρακολουθεί τις κινήσεις των εξωγήινων και καταγράφει όσους του δίχνει ο "Alien Army".
class Satellite implements Observer {
    //Λίστα με τους εξωγήινους που κατέγραψε ο δορυφόρος.
    private List<Alien> observed;

    //Αποθηκεύει τη λίστα των εξωγήινων που επιτέθηκαν κατα τη μερική επίθεση.
    @Override
    public void update(List<Alien> aliens) {
        observed = new ArrayList<>(aliens);
        System.out.println("Satellite recorded " + aliens.size() + " alien(s).");
    }

    //Επιστρέφει τη λίστα των εξωγήινων που κατέγραψε ο δορυφόρος.
    public List<Alien> getObserved() {
        return (observed != null) ? observed : new ArrayList<>();
    }
}
