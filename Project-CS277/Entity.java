class Entity{
  
  private String name;
  // Current hit points
  private int hp;
  // Maximum hit points an entity can have
  private int maxHp;

  /** Constructor of Class Entity
   * @param n is the name to set the Entity
   * @param mHp is the max hits points to set the Entity
   */
  public Entity(String n, int mHp)
  {
    name = n;
    maxHp = mHp;
    hp = maxHp;
  }

  /** Getting the name of the Entity
   * @return a String name of the Entity
   */
  public String getName()
  {
    return name;
  }

  /** Getting the current hp of the entity
   * @return an int hp of Entity
   */
  public int getHp()
  {
    return hp;
  }

  /** Setting the max hp of the entity
   * @param mHP: the maxhp to set to
   */
  public void setmaxHp(int mHp)
  {
    maxHp = mHp;
  }

  /** Healing the Entity to max hit points/ full restore
   */
  public void heal()
  {
    hp = maxHp;
  }

  /** Decreasing the Entity's hp by the amount passed in, but never go below 0
   * @param d is the damage taken
   */
  public void takeDamage(int d)
  {
    if (hp - d < 0)            // if taking the damage making the hp goes below 0, just set it to 0
    {
      hp = 0;
    }
    else
    {
      hp = hp - d;
    }
  }

  /** Output format of Entity: name and hp over maxHp
   * @return a String format of Entity
   */
  @Override
  public String toString()
  {
    return name + "\nHP: " + hp + "/" + maxHp;
  }
}