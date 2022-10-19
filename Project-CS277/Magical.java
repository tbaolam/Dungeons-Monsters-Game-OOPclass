// Interface of Magical attacks
public interface Magical{
  static final String MAGIC_MENU = "1. Magic Missile\n2. Fireball";
  static final int MAGIC_MENU_ITEMS = 2;

  /* MagicMissile attack
   * @param e: an entity to attack
   * @return a String: description of the magicMissile attack.
   */
  public String magicMissile(Entity e);

  /* Fireball Jutsu attack
   * @param e: an entity to attack
   * @return a String: description of the fireball attack.
   */
  public String fireball(Entity e);
}