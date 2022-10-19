// Enemy of type Ranger
class Ranger extends Enemy implements Archer
{
  // Constructor of Ranger
  public Ranger(String n, int mHp)
  {
    super(n, mHp);
  }

  /* Attack of a Ranger to a Hero
   * @param h: the Hero to attack
   * @return String: description of the attack
   */
  @Override
  public String attack(Hero h)
  {
    int attackChoice = (int)(Math.random() * 2);
    if (attackChoice == 0)
      return arrow(h);
    else
      return fireArrow(h);
  }
  
  /* Attack an Entity with an arrow
   * @param e: the Entity to attack
   * @return a String: description of the arrow attack
   */
  @Override
  public String arrow(Entity e)
  {
    int damage = ((int)(Math.random() * 2) + 1);                              // Randomize dmg 1-2
    e.takeDamage(damage);
    return super.getName() + " shoots arrow at " + e.getName() + " for " + damage + " damage.";
  }


  /* Attack an Entity with a fireArrow
   * @param e: the Entity to attack
   * @return a String: description of the fireArrow attack
   */
  @Override
  public String fireArrow(Entity e)
  {
    int damage = ((int)(Math.random() * 3) + 3);                             // Randomize dmg 3-5
    e.takeDamage(damage);
    return super.getName() + " set flames to " + e.getName() + " for " + damage + " damage.";
  }
}