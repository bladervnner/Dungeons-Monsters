import java.util.*;

/**
 * 
 * In this project, the user creates a character
 * in this game which takes place in a dungeon maze
 * packed with numerous monsters. The player was to
 * move his character in specific directions while
 * encountering and defeating various enemies along
 * the way. After defeating a monster, the user was
 * given gold to spend and experience to level up.
 * The user can also find keys to unlock additional
 * areas of the map and potions to heal oneself if
 * they've been attacked by the monster.
 * 
 */

public class Main {
  public static void main(String[] args) {

    Main m = new Main();
    Scanner in = new Scanner(System.in);
    System.out.println("You there! What is your name? ");
    String heroName = in.nextLine();
    Map.getInstance().loadMap(1);
    Hero hero = new Hero(heroName);

    while (mainMenu(hero) != 5) {

    }
    System.out.println("GAME OVER!!!");
  }

  public static int mainMenu(Hero h) {

    char tempChar = 'x';
    System.out.println(h.toString());

    while (tempChar == 'x') {
      System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
      int input = CheckInput.getIntRange(1, 5);
      if (input == 1) {
        tempChar = h.goNorth();
      } else if (input == 2) {
        tempChar = h.goSouth();
      } else if (input == 3) {
        tempChar = h.goEast();
      } else if (input == 4) {
        tempChar = h.goWest();
      } else if (input == 5) {
        return 5;
      }
    }
    System.out.println(Map.getInstance().mapToString(h.getLocation()));
    while (true) {
      if (tempChar == 'n') {
        System.out.println("nothing here");
        break;
      } else if (tempChar == 's') {
        store(h);
        break;
      } else if (tempChar == 'f') {
        if (h.hasKey()) {
          System.out.println("You found a locked gate. Luckily, you have a key. You proceed to the next area.");
          h.levelUp();
          int mapLevel = h.getLevel() - 1;
          if ((h.getLevel() - 1) % 3 == 0) {
            mapLevel = 1;
          } else if ((h.getLevel() - 1) % 3 == 1) {
            mapLevel = 2;
          } else if ((h.getLevel() - 1) % 3 == 2) {
            mapLevel = 3;
          }

          Map.getInstance().loadMap(mapLevel);
          Map.getInstance().reveal(h.getLocation());
          h.useKey();

          break;
        } else {
          System.out.println(
              "You do not have a key. You must either find one or buy one. Otherwise, this is the end of your journey.");
          break;
        }
      } else if (tempChar == 'i') {
        int tempRand = (int) (Math.random() * 2) + 1;
        if (tempRand == 1) {
          System.out.println("You found a key");
          h.pickUpKey();
          Map.getInstance().removeCharAtLoc(h.getLocation());
          break;
        } else {
          System.out.println("You found a potion");
          h.pickUpPotion();
          Map.getInstance().removeCharAtLoc(h.getLocation());
          break;
        }
      } else if (tempChar == 'm') {
        EnemyGenerator eg = new EnemyGenerator();

        Enemy en = eg.generateEnemy(h.getLevel());
        System.out.println("You have encountered a " + en.getName());
        System.out.println(en.toString());
        if (!monsterRoom(h, en) && h.getHp() > 0) {
          Map.getInstance().removeCharAtLoc(h.getLocation());
          System.out.println("You defeated the " + en.getName());
          int droppedGold = (int) (Math.random() * 10) + 3;
          h.collectGold(droppedGold);
          System.out.println("You find " + droppedGold + " gold on the corpse");
        } else if (h.getHp() <= 0) {
          System.out.println("Unfortunately, you're dead!");
          return 5;
        }
        break;

      }
    }

    return 1;
  }

  /**
   * monsterRoom creates the menu when the hero faces an enemy and calls the
   * movement methods allowing the hero to move anywhere
   * 
   * @param h hero class to call certain functions
   * @param e enemy class to call certain functions
   */

  public static boolean monsterRoom(Hero h, Enemy e) {
    boolean monsterAlive = false;
    e.toString();
    int range = 2;
    int input;
    while (true) {
      System.out.println("1. Fight\n2. Run Away");
      if (h.hasPotion()) {
        range = 3;
        System.out.println("3. Drink potion");
      }
      input = CheckInput.getIntRange(1, range);
      if (input == 1) {
        monsterAlive = fight(h, e);
        if (h.getHp() <= 0) {
          System.out.println("Wasted!");
          break;
        } else if (!monsterAlive) {
          System.out.println("Monster is dead");
          break;

        }
      } else if (input == 2) {
        int randRm = (int) (Math.random() * 4) + 1;
        if (randRm == 1) {
          h.goNorth();
        } else if (randRm == 2) {
          h.goSouth();
        } else if (randRm == 3) {
          h.goEast();
        } else {
          h.goWest();
        }
        return true;
      } else if (input == 3) {
        h.usePotion();
        System.out.println("\nYou drank a potion and your health is now at full (25/25)");
        System.out.println("");
      }

    }
    return monsterAlive;
  }

  /**
   * fight The hero's menu for which attack they want to use and attacks the enemy
   * 
   * @param h hero class to call certain functions
   * @param e enemy class to call certain functions
   */

  public static boolean fight(Hero h, Enemy e) {
    System.out.println("1. Physical Attack \n2. Magical Attack \n3. Ranged Attack");
    int input = CheckInput.getIntRange(1, 3);
    System.out.println(h.getSubAttackMenu(input));
    int subAttack = CheckInput.getIntRange(1, 2);
    System.out.println(h.attack(e, input, subAttack));
    if (e.getHp() > 0) {
      System.out.println(e.attack(h));
      return true;
    }
    return false;
  }

  /**
   * store Creates the store allowing our hero to purchase keys and potions for a
   * cost of the gold they've earned
   * 
   * @param h hero class used to call certain functions
   */

  public static void store(Hero h) {
    System.out.println(
        "Welcome to the store. What would you like to buy?\n1. Health Potion - 25g\n2. Key - 50g\n3. Nothing, just browsing...");
    int tempInt = CheckInput.getIntRange(1, 3);
    while (tempInt != 3) {
      if (tempInt == 1) {
        if (h.spendGold(25)) {
          h.pickUpPotion();
          System.out.println("You bought a potion.");
          break;
        } else {
          System.out.println("You do not have enough gold!");
          break;
        }

      } else if (tempInt == 2) {
        if (h.spendGold(50)) {
          h.pickUpKey();
          System.out.println("You bought a key.");
          break;
        } else {
          System.out.println("You do not have enough gold!");
          break;
        }

      } else {
        System.out.println("Testing");
      }

    }
  }
}