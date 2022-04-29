/** Fighter constructor is used to initialize certain methods for other classes since it's an interface class
*/

public interface Fighter {

  public static final String FIGHTER_MENU = "1. Sword\n2. Axe";

  public static final int NUM_FIGHTER_MENU_ITEMS = 2;

  public String sword(Entity e);

  public String axe(Entity e);

}