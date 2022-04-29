public class Ranger extends Enemy implements Archer {

  /** Ranger class extends from Enemy and incorporates the methods of attacks and abilities that the enemies have
  @param n the name of the character
  @param mHp the maximum health points of the character
  */
  
  public Ranger(String n, int mHp) {
    super(n, mHp);
  }

  /** attack the choice of attack that the ranger will use
  */
  
  @Override
  public String attack(Hero h) {
    int attack;
    attack = (int)(Math.random() * 2) + 1;
    if(attack == 1){
      return arrow(h);
    }
    else{
      return fireArrow(h);
    }
  }

  /** arrow one of the two attacks that the Ranger has
  @param e the character which correlates to the attack
  */
  
  @Override
  public String arrow (Entity e) {
    int damage = (int)( Math.random() * 5) + 3;
    e.takeDamage(damage);
    return getName() + " hits " +  e.getName() + " with an arrow for " + damage + " damage"; 
  }

  /** fireArrow one of the two attacks that the Ranger has
  @param e the character which correlates to the attack
  */
  
  @Override
  public String fireArrow (Entity e) {
    int damage = (int)( Math.random() * 5) + 3;
    e.takeDamage(damage);
    return getName() + " hits " +  e.getName() + " with a fire arrow for " + damage + " damage"; 
  }
}