import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;
import java.util.Stack;
import java.awt.*;


/** Hero creates different methods in which our hero incorporates and uses
*/

public class Hero extends Entity implements Fighter, Magical, Archer{
  private Point loc;
  private int level;
  private int gold;
  private int keys;
  private int potions; 
  //private Map mapSingleton;

  /** Hero Constructor
  @param n takes in the name of the hero
  */
  
  public Hero(String n){
    super(n, 25);
    //Map mapSingleton = Map.getInstance();
    
    //String heroName = getName();
    level = 1;
    loc = Map.getInstance().findStart();
    gold = 50;
    keys = 2;
    potions = 3;
    //int hp = getHp();
  }

  /** returns the hero's stats which include thier level, gold, potions, keys, and where they are on the map
  */
  
  @Override
  public String toString(){
    String s = super.toString();
    //return s + "\nLevel: " + level + "\nGold: " + gold + "\nP: " + potions + "  K:" + keys + "\n" + mapSingleton.mapToString(loc);
    return s + "\nLevel: " + level + "\nGold: " + gold + "\nP: " + potions + "  K:" + keys + "\n" + Map.getInstance().mapToString(loc);
  }

  /** getLocation returns the location of the hero
  */
  
  public Point getLocation(){
    return loc;
  }


  /** levelUp increases the level of the hero and loads the next map
  */
  
  public void levelUp(){
    level ++;
    if(level < 3){
      //mapSingleton.loadMap(level);
      Map.getInstance().loadMap(level);
    }
  }

  /** getLevel returns the hero's level
  */
  
  public int getLevel(){
    return level;
  }

  /** goSouth moves the hero towards the south direction (bottom)
  */
  
  public char goSouth(){
    
    if(loc.x + 1 != 5){
      loc.x = loc.x + 1;
       Map.getInstance().reveal(loc);//not too sure if the reveals should go here since if player runs away without defeating the monster, it will return n, but I can always code it within the run away option to just return it
    }
    else if(loc.x + 1 == 5){
      System.out.println("Are you trying to escape the matrix? Location is inaccessible! ");
      return 'x';
    }
    //return mapSingleton.getCharAtLoc(loc);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /** goNorth moves the hero towards the North direction (top)
  */

  public char goNorth(){
    if(loc.x - 1 != -1){
      loc.x = loc.x - 1;
       Map.getInstance().reveal(loc);
    }
    else if(loc.x -1 == 1){
      System.out.println("Are you trying to escape the matrix? Location is inaccessible! ");
      return 'x';
    }
    // return mapSingleton.getCharAtLoc(loc);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /** goEast moves the hero towards the East direction (right)
  */
  
  public char goEast(){
    if(loc.y + 1 != 5){
      loc.y = loc.y + 1;
      Map.getInstance().reveal(loc);
    }
    else if(loc.y + 1 == 5){
      System.out.println("Are you trying to escape the matrix? Location is inaccessible! ");
      return 'x';
    }
    //return mapSingleton.getCharAtLoc(loc);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /** goWest moves the hero towards the West direction (left)
  */

  public char goWest(){
    if(loc.y - 1 != - 1){
      loc.y = loc.y - 1;
       Map.getInstance().reveal(loc);
    }
    else if(loc.y - 1 == - 1){
      System.out.println("Are you trying to escape the matrix? Location is inaccessible! ");
      return 'x';
    }
    //return mapSingleton.getCharAtLoc(loc);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /** getAttackMenu returns the different types of attacks the hero could use
  */
  
  public String getAttackMenu(){
    return "1. Physical Attack \n2.Magical Attack \n3. Ranged Attack";
  }

  /** getNumAttackMenuItems returns the amount of attacks that are in the menu
  */
  
  public int getNumAttackMenuItems(){
    return 3;
  }

  /** getSubAttackMenu returns the different type of enemies menus
  @param choice chooses between which type of enemy will show
  */
  
  //@Override
  public String getSubAttackMenu(int choice){
    
    if(choice == 1){
    return FIGHTER_MENU;
    }
    else if(choice == 2){
      return MAGIC_MENU;
    }
    return ARCHER_MENU;
  }


  /** getNumSubAttackMenuItems returns the different types of items that the enemies can use
  @param choice chooses between which type of enemy's items will be displayed
  */
  
  public int getNumSubAttackMenuItems(int choice){
    if(choice == 1){
      return NUM_FIGHTER_MENU_ITEMS;
    }
    else if(choice == 2){
      return NUM_MAGIC_MENU_ITEMS;
    }
    return NUM_ARCHER_MENU_ITEMS;
  }
  
  /** attack returns the type of attack which the enemies will use
  @param e enemy
  @param choice type of enemy
  @param subChoice type of attack from enemy
  */
  
  public String attack(Enemy e, int choice, int subChoice){
    String attack ="";
    if(choice == 1){
      if(subChoice == 1){
        attack = sword(e);
      }
      else{
        attack = axe(e);
      }
    }
    else if( choice == 2){
      if(subChoice == 1){
        attack = magicMissile(e);
        
      }
      else{
        attack = fireball(e);
      }
    }
    else{
      if(subChoice == 1){
        attack = arrow(e);
      }
      else{
        attack = fireArrow(e);
      }
    }
    //return getName() + " " + attack + " " + e.getName() + "for" + damage + "damage\n" + e.toString(); 
    return attack + "\n" + e.toString();
  }

  /** getGold returns the gold of the hero
  */
  
  public int getGold(){
    return gold;
  }

  /** collectGold collects the gold that the hero earns
  @param g gold
  */

  public void collectGold(int g){
    gold += g;
  }

  /** spendGold allows the user to spend the gold which he earns if not enough then they can't buy
  @param g the amount of gold it cost for item
  */
  
  public boolean spendGold(int g){
    if(gold >= g){
      gold -= g;
      return true;
    }
    return false;
  }

  /** hasKey checks to see if the hero has keys which they can use
  */
  
  public boolean hasKey(){
    if(keys > 0){
      return true;
    }
    return false;
  }

  /** pickUpKey allows the user to earn a key
  */
  
  public void pickUpKey(){
    keys++;
  }

  /** useKey allows the user to use the key
  */
  
  public boolean useKey(){
    if(keys > 0){
      keys--;
      return true;
    }
    return false;
  }

  /** hasPotion checks to see if the user has a potion to use
  */
  
  public boolean hasPotion(){
    if(potions > 0){
      return true;
    }
    return false;
  }

  /** pickUpPotion allows the user to pick up a potion
  */
  
  public void pickUpPotion(){
    potions ++;
  }

  /** usePotion allows the user to use a potion
  */
  
  public boolean usePotion(){
    if(potions > 0){
      potions--;
      heal();
      return true;
    }
    return false;
  }

  /** sword one of the two attacks that the Warrior has and returns the damage that the hero deals from their abilities
  @param e the character which correlates to the attack
  */
  
  @Override
  public String sword(Entity e){
    int damage = (int)( Math.random() * 5 ) + 3;
    e.takeDamage(damage);
    return getName() + " Slashes " +  e.getName() + " for " + damage + " damage"; 
  }

  /** axe one of the two attacks that the Warrior has and returns the damage that the hero deals from their abilities
  @param e the character which correlates to the attack
  */
  
  @Override
  public String axe(Entity e){
    int damage = (int)( Math.random() * 5 ) + 3;
    e.takeDamage(damage);

    return getName() + " Slashes " +  e.getName() + " for " + damage + " damage"; 
    
  }

  /** magicMissile one of the two attacks that the Wizard has and returns the damage that the hero deals from their abilities
  @param e the character which correlates to the attack
  */
  
  @Override
  public String magicMissile(Entity e){
    int damage = (int)( Math.random() * 5 ) + 3;
    e.takeDamage(damage);
    return getName() + " hits " +  e.getName() + " with a magic missile for " + damage + " damage"; 
  }

  /** fireball one of the two attacks that the Wizard has and returns the damage that the hero deals from their abilities
  @param e the character which correlates to the attack
  */
  
  @Override
  public String fireball(Entity e){
    int damage = (int)( Math.random() * 5 ) + 3;
    e.takeDamage(damage);
    return getName() + " hits " +  e.getName() + " with a fireball for " + damage + " damage"; 
  }

  /** arrow one of the two attacks that the Ranger has and returns the damage that the hero deals from their abilities
  @param e the character which correlates to the attack
  */
  
  @Override
  public String arrow (Entity e){
    int damage = (int)( Math.random() * 5 ) + 3;
    e.takeDamage(damage);
    return getName() + " hits " +  e.getName() + " with an arrow for " + damage + " damage"; 
  }

  /** fireArrow one of the two attacks that the Ranger has and returns the damage that the hero deals from their abilities
  @param e the character which correlates to the attack
  */
  
  @Override
  public String fireArrow (Entity e){
    int damage = (int)( Math.random() * 5 ) + 3;
    e.takeDamage(damage);
    return getName() + " hits " +  e.getName() + " with a fire arrow for " + damage + " damage"; 
  }
}