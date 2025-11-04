import java.util.Scanner;
import java.util.Random;
import java.text.DecimalFormat;
public class TextAdventure {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        DecimalFormat df = new DecimalFormat("0.00");

        // ===== Big ASCII art title  =====
        System.out.println("                                     _______");
        System.out.println("                                    |_______|");
        System.out.println("                                    |");
        System.out.println("                        -----------------------");
        System.out.println("                       |                       |");
        System.out.println("        _______________|_______________________|______________");
        System.out.println("       |   [ ]       [ ]       [ ]       [ ]       [ ]        |");
        System.out.println("       |                                                      |");
        System.out.println("       |   [ ]       [ ]       [ ]       [ ]       [ ]        |");
        System.out.println("       |                                                      |");
        System.out.println("       |                       _________                      |");
        System.out.println("       |                      |         |                     |");
        System.out.println("       |                      |  ____   |                     |");
        System.out.println("       |                      | |    |  |                     |");
        System.out.println("       |______________________|_|____|__|_____________________|");

        String again = " ";
        do { // Replay loop
            // ===================== STATE (variables) =====================
            int health = 3; // hearts 3.0
            int score = 0; // affects endings
            int coins = 0; // simple counter
            boolean hasFlashlight = false; // inventory example
            boolean hasKey = false; // inventory example
            boolean metJanitor = false; // affects a midgame path
            String choiceLog = ""; // simple bonus log

            System.out.println("\n==================== HAUNTED HALLS: LINCOLN HIGH ====================");
            System.out.println("=====================================================================\n");

            // ===================== Scene 1: Front Steps (1) =====================
            System.out.println("You stand at the front steps of Lincoln High at night.");
            System.out.println("1) Enter through the main doors");
            System.out.println("2) Search the side entrance");
            System.out.print("Enter your choice: ");
            String s1 = in.nextLine().trim();
            while (!(s1.equals("1") || s1.equals("2"))) { // input validation loop
                System.out.print("Invalid input. Choose 1 or 2: ");
                s1 = in.nextLine().trim();
            }
            if (s1.equals("1")) {
                choiceLog += "- Entered main doors.\n";
                score += 2;

                // ===================== Scene 2A: Lobby (2) =====================
                System.out.println("\nThe lobby is dark. A trophy case glints.");
                System.out.println("1) Check the case");
                System.out.println("2) Go to the office");
                System.out.print("Enter your choice: ");
                String s2a = in.nextLine().trim();
                while (!(s2a.equals("1") || s2a.equals("2"))) {
                    System.out.print("Invalid input. Choose 1 or 2: ");
                    s2a = in.nextLine().trim();
                }
                if (s2a.equals("1")) {
                    System.out.println("You pry open a loose panel and find 2 coins.");
                    coins += 2;
                    score += 3;
                    choiceLog += "- Looted trophy case (+2 coins).\n";
                    System.out.println("Coins now: " + df.format(coins));
                } else {
                    System.out.println("The office door is stuck. You scrape your hand (health -1).");
                    health--;
                    choiceLog += "- Tried office (health -1).\n";
                    System.out.println("Health now: " + df.format(health));
                }

                // ===================== Scene 3A: Hallway Fork (3) =====================
                System.out.println("\nThe hallway splits.");
                System.out.println("Where will you go? ");
                System.out.println("1) Science wing");
                System.out.println("2) Gym wing");
                System.out.print("Enter your choice: ");
                String s3a = in.nextLine().trim();
                while (!(s3a.equals("1") || s3a.equals("2"))) {
                    System.out.print("Invalid input. Choose 1 or 2: ");
                    s3a = in.nextLine().trim();
                }
                if (s3a.equals("1")) {
                    // ===================== Scene 4A: Lab (4) =====================
                    System.out.println("\nChemistry lab. A flashlight sits on a bench for 2 coins.");
                    System.out.println("1) Buy flashlight (2 coins)");
                    System.out.println("2) Leave it");
                    System.out.print("Enter your choice: ");
                    String buy = in.nextLine().trim();
                    while (!(buy.equals("1") || buy.equals("2"))) {
                        System.out.print("Invalid input. Choose 1 or 2: ");
                        buy = in.nextLine().trim();
                    }
                    if (buy.equals("1")) {
                        if (coins >= 2) {
                            coins -= 2;
                            hasFlashlight = true;
                            score += 5;
                            choiceLog += "- Bought flashlight.\n";
                            System.out.println("Purchased. Coins now: " + df.format(coins));
                        } else {
                            System.out.println("You don’t have enough coins.");
                            choiceLog += "- Wanted flashlight but broke.\n";
                        }
                    } else {
                        choiceLog += "- Skipped flashlight.\n";
                    }
                } else {
                    // ===================== Scene 4B: Gym (4) =====================
                    System.out.println("\nIn the gym, a vending machine flickers.");
                    System.out.println("1) Shake it (risky) ");
                    System.out.println("2) Ignore it");
                    System.out.print("Enter your choice: ");
                    String vend = in.nextLine().trim();
                    while (!(vend.equals("1") || vend.equals("2"))) {
                        System.out.print("Invalid input. Choose 1 or 2: ");
                        vend = in.nextLine().trim();
                    }
                    if (vend.equals("1")) {
                        System.out.println("It topples a little! You get a coin, but twist your ankle (health -1).");
                        coins += 1;
                        health--;
                        score += 1;
                        choiceLog += "- Shook vending: +1 coin, -1 health.\n";
                        System.out.println("Coins: " + df.format(coins) + " | Health: " + df.format(health));
                    } else {
                        System.out.println("You conserve energy and move on.");
                        score += 1;
                        choiceLog += "- Ignored vending.\n";
                    }

                }
            } else {  // side entrance path
                choiceLog += "- Checked side entrance.\n";
                score += 1;

                // ===================== Scene 2B: Boiler Room (2) =====================
                System.out.println("\nA door leads to the boiler room. You hear humming.");
                System.out.println("1) Enter boiler room");
                System.out.println("2) Stay outside and look around");
                System.out.print("Enter your choice: ");
                String s2b = in.nextLine().trim();
                while (!(s2b.equals("1") || s2b.equals("2"))) {
                    System.out.print("Invalid input. Choose 1 or 2: ");
                    s2b = in.nextLine().trim();
                }
                if (s2b.equals("1")) {
                    System.out.println("A tired janitor offers help if you bring him a lost key.");
                    metJanitor = true;
                    score += 2;
                    choiceLog += "- Met janitor (quest).\n";
                } else {
                    System.out.println("You find a dropped flashlight on the ground!");
                    hasFlashlight = true;
                    score += 4;
                    choiceLog += "- Found flashlight outside.\n";
                }

                // ===================== Scene 3B: Basement Corridor (3) =====================
                System.out.println("\nBasement corridor with two rooms.");
                System.out.println("1) Storage closet");
                System.out.println("2) Records office");
                System.out.print("Enter your choice: ");
                String s3b = in.nextLine().trim();
                while (!(s3b.equals("1") || s3b.equals("2"))) {
                    System.out.print("Invalid input. Choose 1 or 2: ");
                    s3b = in.nextLine().trim();
                }
                if (s3b.equals("1")) {
                    System.out.println("Boxes everywhere. You dig around and find a small coin pouch (+2).");
                    coins += 2;
                    score += 2;
                    choiceLog += "- Storage coins +2.\n";
                    System.out.println("Coins now: " + df.format(coins));
                } else {
                    System.out.println("You step on a tack in the dark (health -1).");
                    health--;
                    choiceLog += "- Records office accident (health -1).\n";
                    System.out.println("Health now: " + df.format(health));
                }

            }

            // ===================== Scene 5: Library Door (5) =====================
            System.out.println("\nYou reach the locked library. A keypad blinks.");
            if (!hasKey) {
                System.out.println("A note says: \"Master key held by the librarian… or crack the keypad.\"");
            } else {
                System.out.println("You already have the key. (You can still try the keypad for fun.)");
            }
            System.out.println("1) Try to crack keypad (3 tries)");
            System.out.println("2) Look for another route");
            System.out.print("Enter your choice: ");
            String s5 = in.nextLine().trim();
            while (!(s5.equals("1") || s5.equals("2"))) {
                System.out.print("Invalid input. Choose 1 or 2: ");
                s5 = in.nextLine().trim();
            }
            boolean doorOpened = false;
            if (s5.equals("1")) {
                // ===== for-loop mini-game: guess 1..3 in 3 tries =====
                int secret = rand.nextInt(3) + 1;
                System.out.println("Pick the right number 1–3. You have 3 tries.");
                for (int tries = 3; tries > 0; tries--) {
                    System.out.print("Enter 1, 2, or 3 (" + tries + " left): ");
                    String g = in.nextLine().trim();
                    while (!(g.equals("1") || g.equals("2") || g.equals("3"))) {
                        System.out.print("Invalid input. Enter 1, 2, or 3: ");
                        g = in.nextLine().trim();
                    }
                    int guess = Integer.parseInt(g);
                    if (guess == secret) {
                        doorOpened = true;
                        break;
                    } else System.out.println("Beep! Wrong digit.");
                }
                if (doorOpened) {
                    System.out.println("The keypad chirps and the lock releases!");
                    score += 6;
                    choiceLog += "- Cracked keypad.\n";
                } else {
                    System.out.println("Alarm flashes briefly—stress drains you (health -1).");
                    health--;
                    choiceLog += "- Failed keypad (health -1).\n";
                    System.out.println("Health now: " + df.format(health));
                }
            } else {
                System.out.println("You search the nearby office and—lucky!—find a brass key.");
                hasKey = true;
                score += 3;
                choiceLog += "- Found brass key.\n";
            }

            // If we have the key OR solved keypad, we can enter
            if (hasKey || doorOpened) {
                // ===================== Scene 6: Library (6) =====================
                System.out.println("\nThe library is full of shadows. A whisper says: \"Bring light.\"");
                if (hasFlashlight) {
                    System.out.println("You raise the flashlight. A hidden ladder drops from the ceiling.");
                    score += 4;
                    choiceLog += "- Used flashlight in library.\n";
                } else {
                    System.out.println("In the dark you bump into shelves (health -1). You still find the ladder.");
                    health--;
                    choiceLog += "- No flashlight; took damage.\n";
                    System.out.println("Health now: " + df.format(health));
                }
            } else {
                System.out.println("\nYou are stuck outside the library for a while (score -1).");
                score -= 1;
                choiceLog += "- Stuck at library.\n";
            }

            // ===================== Scene 7: Roof Access (7) =====================
            System.out.println("\nOn the roof access, cold wind howls. Cross the shaky catwalk?");
            System.out.println("1) Cross carefully");
            System.out.println("2) Run across");
            System.out.print("Enter your choice: ");
            String s7 = in.nextLine().trim();
            while (!(s7.equals("1") || s7.equals("2"))) {
                System.out.print("Invalid input. Choose 1 or 2: ");
                s7 = in.nextLine().trim();
            }
            if (s7.equals("1")) {
                System.out.println("Slow and steady. You make it safely (+2 score).");
                score += 2;
                choiceLog += "- Crossed carefully.\n";
            } else {
                if (rand.nextInt(100) < 50) {
                    System.out.println("You slip on frost and bruise your ribs (health -1) but survive.");
                    health--;
                    choiceLog += "- Ran and slipped (health -1).\n";
                    System.out.println("Health now: " + df.format(health));
                } else {
                    System.out.println("You dash across with a grin (+3 score).");
                    score += 3;
                    choiceLog += "- Ran and succeeded.\n";
                }
            }

            // ===================== Scene 8: Bell Tower (8) -> Endings =====================
            System.out.println("\nThe bell tower hums. A ghostly principal materializes.");
            System.out.println("He points at a sealed box: \"Free the school by returning the master key.\"");
            System.out.print("Return the key if you have it? (y/n): ");
            String yn = in.nextLine().trim().toLowerCase();
            while (!(yn.equals("y") || yn.equals("n") || yn.equals("yes") || yn.equals("no"))) {
                System.out.print("Invalid input. Type y or n: ");
                yn = in.nextLine().trim().toLowerCase();
            }
            boolean returned = (yn.startsWith("y") && hasKey);

            // ======= Endings (3+ distinct) based on state =======
            System.out.println("\n==================== ENDING ====================");
            if (health <= 0) {
                System.out.println("You collapse from injuries before the bell tolls. The halls stay haunted.");
                System.out.println(">> DEFEAT ENDING");
                choiceLog += "- Lost: health reached 0.\n";
            } else if (returned && hasFlashlight && score >= 10) {
                System.out.println("You return the key. Light floods the tower. The ghost smiles and fades.");
                System.out.println(">> TRUE ENDING: Lincoln High is finally at peace!");
                score += 5;
                choiceLog += "- True ending.\n";
            } else {
                if (metJanitor && !returned) {
                    System.out.println("You keep the key. The janitor appears, nodding: \"Meet me tomorrow.\"");
                    System.out.println(">> SECRET ENDING: You join the secret night watch.");
                    choiceLog += "- Secret ending with janitor.\n";
                } else {
                    System.out.println("The bell chimes. You leave with more questions than answers.");
                    System.out.println(">> ALTERNATIVE ENDING: To be continued…");
                    choiceLog += "- Alternative ending.\n";
                }
            }

            // ======= Final stats + simple choice log (bonus) =======
            System.out.println("\nHealth: " + df.format(health) + "   Coins: " + df.format(coins) + "   Score: " + df.format(score));
            System.out.println("\nYour choices:");
            System.out.print(choiceLog);

            // ======= Ask to replay (validation while loop) =======
            System.out.print("\nPlay again? (y/n): ");
            again = in.nextLine().trim().toLowerCase();
            while (!(again.equals("y") || again.equals("n") || again.equals("yes") || again.equals("no"))) {
                System.out.print("Invalid input. Type y or n: ");
                again = in.nextLine().trim().toLowerCase();
            }
        } while (again.equals("y") );
        System.out.println("\nThanks for playing HAUNTED HALLS!");


    }
}
