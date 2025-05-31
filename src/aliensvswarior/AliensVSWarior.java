package aliensvswarior;

import java.util.Scanner;

/**
 *
 * @author ΑΝΕΣΤΗΣ
 */

//Η κύρια κλάση
public class AliensVSWarior {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // Δημιουργία παρατηρητών
        Satellite satellite = new Satellite();
        Telescope telescope = new Telescope();

        //έλεγχος εγκυρότητας εισόδου. Θετικος αριθμός εξωγήινων.
        int aliens = -1;
        while(aliens < 0){
            System.out.println("Give number of aliens for the alien army:");
            if(scanner.hasNextInt()){
                aliens = scanner.nextInt();
                if(aliens <= 0){
                    System.out.println("Enter a number greater than 0.");
                }
            }else{
                System.out.println("Invalid input. Please enter a number");
                scanner.next();
            }  
        }
        
        // Δημιουργία alien army και warrior
        AlienArmy army = new AlienArmy(aliens);
        Warrior warrior = new Warrior(satellite, telescope);

        // Σύνδεση παρατηρητών με alien army
        army.attachObserver(satellite);
        army.attachObserver(telescope);

        boolean turn = true; // true = alienArmy, false = warrior

        while (!warrior.isDefeated() && !army.isDefeated()) {
            System.out.println("\n===== New Round =====");
            System.out.println("Warrior strength: " + warrior.getStrength() + ".");
            System.out.println("Aliens remaining: " + army.getAliens().size() + ".");
            System.out.printf("Warrior has %.2f visibility. \n", warrior.getVisibility());
            
            if (turn) {
                //Επίθεση από alienArmy με έλεγχο εγκυρότητας. Εισαγωγή p ή f.
                //Κατά την μερική επίθεση ο αριθμός των εξωγήινων να μην είναι αρνητκός
                //ή ίσος με τους απομείναντες εξωγήινους.
                String choice = "";
                while(!choice.equals("f") && !choice.equals("p")){
                    System.out.println("\nPick battle  strategy: f = Full Attack, p = Partial Attack.");
                    choice = scanner.next();
                    if(!choice.equals("f") && !choice.equals("p")){
                        System.out.println("Invalid choice. Please enter 'f' or 'p'");
                    }
                }
                if (choice.equals("f") ) {
                    army.setStrategy(new FullAttack());
                }else{
                    int attackers = -1;
                    int remainingAliens = army.getAliens().size();
                    while(attackers <= 0 || attackers == remainingAliens || attackers > remainingAliens){
                        System.out.println("How many aliens will attack?");
                        if(scanner.hasNextInt()){
                            attackers = scanner.nextInt();
                            if(attackers <= 0 || attackers == remainingAliens || attackers > remainingAliens){
                                System.out.println("Invalid number. Try again."); 
                            }
                        }else{
                            System.out.println("Invalid input. Please enter a number.");
                            scanner.next();
                        }
                    }
                    army.setStrategy(new PartialAttack(attackers));
                }
                army.performAttack(warrior);
            } else {
                // Επίθεση από warrior
                warrior.attack(army);
            }

            // Εναλλαγή σειράς
            turn = !turn;      
        }

        System.out.println("\n===== GAME OVER =====");
        if (warrior.isDefeated()) {
            System.out.println("AlienArmy Is Victorious!");
        } else {
            System.out.println("The Warrior Is Victorious!");
        }
    }
}