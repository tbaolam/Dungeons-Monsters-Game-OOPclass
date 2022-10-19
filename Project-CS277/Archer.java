// Interface of Archer attacks
public interface Archer{
  static final String ARCHER_MENU = "1. Arrow\n2. Fire Arrow";
  static final int NUM_ARCHER_MENU_ITEMS = 2;

  /* Arrow attack
   * @param e: an entity to attack
   * @return a String: description of the arrow attack.
   */
  public String arrow(Entity e);
  
  /* FireArrow attack
   * @param e: an entity to attack
   * @return a String: description of the fireArrow attack.
   */
  public String fireArrow(Entity e);
}