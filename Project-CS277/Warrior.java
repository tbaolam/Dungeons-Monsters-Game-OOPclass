//Enemy of type Warrior
class Warrior extends Enemy implements Fighter
{
  // Constructor of Warrior
  public Warrior(String n, int mHp)
  {
    super(n, mHp);
  }


   /* Attack of a Warrior to a Hero
   * @param h: the Hero to attack
   * @return String: description of the attack
   */
  @Override
  public String attack(Hero h)
  {
    int attackChoice = (int)(Math.random() * 2);
    if (attackChoice == 0)
      return sword(h);
    else
      return axe(h);
  }

  /* Attack an Entity with a sword
   * @param e: the Entity to attack
   * @return a String: description of the sword attack
   */
  @Override
  public String sword(Entity e)
  {
    int damage = ((int)(Math.random() * 2) + 2);                             // Randomize dmg 2-3
    e.takeDamage(damage);
    return super.getName() + " slashes " + e.getName() + " for " + damage + " damage.";
  }

  /* Attack an Entity with an axe
   * @param e: the Entity to attack
   * @return a String: description of the axe attack
   */
  @Override
  public String axe(Entity e)
  {
    int damage = ((int)(Math.random() * 2) + 3);                             // Randomize dmg 3-4
    e.takeDamage(damage);
    return super.getName() + " chops " + e.getName() + " for " + damage + " damage.";
  }
}