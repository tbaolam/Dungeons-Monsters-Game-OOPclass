class Enemy extends Entity{
  /**
      * Creates an Enemy with a specified name
      * and HP/maxHP.
      * @param n the name of the Pokemon.
      * **/
  public Enemy(String n, int mHp)
  {
    super(n, mHp);
  }
  /* Attack of an Enemy to a Hero
   * @param h: the Hero to attack
   * @return String: description of the attack
   */
  public String attack(Hero h)
  {
    return "Attacking";
  }

  /* Display of an Enemy
   * @return String: description of an Enemy
   */
  @Override
  public String toString()
  {
    return super.toString();
  }
}