// Enemy of type Wizard
class Wizard extends Enemy implements Magical
{
  // Constructor of Wizard
  public Wizard(String n, int mHp)
  {
    super(n, mHp);
  }

   /* Attack of a Wizard to a Hero
   * @param h: the Hero to attack
   * @return String: description of the attack
   */
  @Override
  public String attack(Hero h)
  {
    int attackChoice = (int)(Math.random() * 2);
    if (attackChoice == 0)
      return magicMissile(h);
    else
      return fireball(h);
  }

  /* Attack an Entity with a magicMissile
   * @param e: the Entity to attack
   * @return a String: description of the magicMissile attack
   */
  @Override
  public String magicMissile(Entity e)
  {
    int damage = ((int)(Math.random() * 2) + 3);                             // Randomize dmg 3-4
    e.takeDamage(damage);
    return super.getName() + " fires missiles at " + e.getName() + " for " + damage + " damage.";
  }

  /* Attack an Entity with a FireBall
   * @param e: the Entity to attack
   * @return a String: description of the FireBall attack
   */
  @Override
  public String fireball(Entity e)
  {
    int damage = ((int)(Math.random() * 5) + 2);                             // Randomize dmg 2-6
    e.takeDamage(damage);
    return super.getName() + " throws a Fireball at " + e.getName() + " for " + damage + " damage.";
  }
}