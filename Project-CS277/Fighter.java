// Interface of Fighter attacks
public interface Fighter{
  static final String FIGHTER_MENU = "1. Sword\n2. Axe";
  static final int NUM_FIGHTER_MENU_ITEMS = 2;

  /* Sword attack
   * @param e: an entity to attack
   * @return a String: description of the sword attack.
   */
  public String sword(Entity e);
    
  /* Axe attack
   * @param e: an entity to attack
   * @return a String: description of the axe attack.
   */
  public String axe(Entity e);
}