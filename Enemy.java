/** Enemy class is used to define common definition of attack allowing other classes to share 
*/

public abstract class Enemy extends Entity{

  /** Enemy Constructor
  @param n   name of enemy
  @param mHp maximum health points of enemy
  */
  
  public Enemy(String n, int mHp){
    super(n, mHp);
  }

  /** attack method
  @param h hero class
  */
  
  //@Override
  public abstract String attack(Hero h);
  
}