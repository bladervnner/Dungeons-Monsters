public class Warrior extends Enemy implements Fighter {

  /** Warrior class extends from Enemy and incorporates the methods of attacks and abilities that the enemies have
  @param n the name of the character
  @param mHp the maximum health points of the character
  */
  
  public Warrior(String n, int mHp) {
    super(n, mHp);
  }

  /** attack the choice of attack that the Warrior will use
  */
  
  @Override
  public String attack(Hero h) {
    int attack;
    attack = (int)(Math.random() * 2) + 1;
    if(attack == 1){
      return sword(h);
    }
    else{
      return axe(h);
    }
  }

  /** sword one of the two attacks that the Warrior has
  @param e the character which correlates to the attack
  */
  
  @Override
  public String sword (Entity e) {
    int damage = (int)( Math.random() * 5) + 3;
    e.takeDamage(damage);
    return getName() + " slashes " +  e.getName() + " with a sword for " + damage + " damage"; 
  }

  /** axe one of the two attacks that the Warrior has
  @param e the character which correlates to the attack
  */
  
  @Override
  public String axe (Entity e) {
    int damage = (int)( Math.random() * 5) + 3;
    e.takeDamage(damage);
    return getName() + " slashes " +  e.getName() + " with an axe for " + damage + " damage"; 
  }
}