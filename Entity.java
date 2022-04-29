/** Entity creates common definitions for different characters in the game
*/

public abstract class Entity {
  private String name;
  private int hp;
  private int maxHp;

  /** Entity Constructor
  @param n name of character
  @param mHp maximum health points of character
  */
  
  public Entity(String n, int mHp){
    name = n;
    hp = mHp;
    maxHp = mHp;
  }

  /** getName returns the name of character
  */
  
  public String getName(){
    return name;
  }

  /** getHp returns the health points of character
  */
  
  public int getHp(){
    return hp;
  }

  /** 
  */
  
  public void heal(){
    hp = maxHp;
  }

  public void takeDamage(int d){
    hp = hp - d;
    if(hp < 0){
      hp = 0;
    }
  }

  @Override
  public String toString(){
    return name +"\n" +"HP: " + hp + "/" + maxHp;
  }
  
}