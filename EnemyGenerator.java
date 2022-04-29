import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;


/**EnemyGenerator is used to create random enemies in which the user encounters. 
*/

public class EnemyGenerator{
  private HashMap<String, Integer> enemies = new HashMap<String, Integer>();
  
  /**EnemyGenerator constructor used to read our enemies text file and puts them into a hashmap
  */
  
  public EnemyGenerator(){
    try{
      Scanner read = new Scanner( new File("Enemies.txt"));
  
      while(read.hasNext()){
        
        String s = read.nextLine();
        String temp [];
        
        temp = s.split(",");
        enemies.put(temp[0],Integer.parseInt(temp[1]));
      }
      read.close();
    }catch(FileNotFoundException fnf){
      System.out.println("File was not found");
    }
    
  }

  /** generateEnemy used to randomly select an enemy and their ability type
  @param level sets level of our enemy
  */
  
  public Enemy generateEnemy(int level){
   
    Random gen = new Random();
    int rand = gen.nextInt(enemies.size());
    Set<String> keys = enemies.keySet();
    String temp [] = keys.toArray(new String[keys.size()]);

    
    int randEnemy = (int)( Math.random() * 3 ) + 1;
    if(randEnemy == 1){
      Warrior enemy = new Warrior(temp[rand] + " Warrior", enemies.get(temp[rand]) * level);
      return enemy;
    }
    else if(randEnemy == 2){
      Wizard enemy = new Wizard(temp[rand] + " Wizard", enemies.get(temp[rand]) * level);
      return enemy;
    }
    else{
      Ranger enemy = new Ranger(temp[rand] + " Ranger", enemies.get(temp[rand]) * level);
      return enemy;
    }
    
  }
}