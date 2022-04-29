public class Wizard extends Enemy implements Magical {

  /** Wizard class extends from Enemy and incorporates the methods of attacks and abilities that the enemies have
  @param n the name of the character
  @param mHp the maximum health points of the character
  */
  
    public Wizard(String n, int mHp) {
    super(n, mHp);
  }

  /** attack the choice of attack that the Wizard will use
  */
   
   @Override
  public String attack(Hero h) {
    int attack;
    attack = (int)(Math.random() * 2) + 1;
    if(attack == 1){
      return magicMissile(h);
    }
    else{
      return fireball(h);
    }
  }

  /** magicMissile one of the two attacks that the Wizard has
  @param e the character which correlates to the attack
  */
  
  @Override
  public String magicMissile (Entity e) {
    int damage = (int)( Math.random() * 5) + 3;
    e.takeDamage(damage);
    return getName() + " hits " +  e.getName() + " with a magic missile for " + damage + " damage"; 
  }

  /** fireball one of the two attacks that the Wizard has
  @param e the character which correlates to the attack
  */
  
  @Override
  public String fireball (Entity e) {
    int damage = (int)( Math.random() * 5) + 3;
    e.takeDamage(damage);
    return getName() + " hits " +  e.getName() + " with a fireball for " + damage + " damage"; 
  }
}