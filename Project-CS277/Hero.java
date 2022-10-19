import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



class Hero extends Entity implements Fighter, Archer, Magical{
  // Location of the Hero on a 2D map
  private Point loc;
  // Level of the Hero
  private int level;
  // Gold the Hero has
  private int gold;
  // Number of keys the Hero have
  private int keys;
  // Number of health potions
  private int potions;

  /** Constructor of the Hero with default values
   * @param n is the name of the Hero
   */
  public Hero(String n)
  {
    super(n, 25);
    level = 1;
    loc = new Point();
    loc.setLocation(Map.getInstance().findStart());              // Put the Hero at the start location
    gold = 25;
    keys = 0;
    potions = 1;
  }


  /** Getting the current level of the Hero
   * @return an int level of Hero
   */
  public int getLevel()
  {
    return level;
  }

  /** Level up the Hero by 1
   */
  public void levelUp()
  {
    ++level;
    Map m = Map.getInstance();
    m.removeCharAtLoc(getLocation());

    // Load the next Map base on the Hero's level. Map 1,2,3 then 1,2,3 - Level 1,2,3,4,5,6,..
    if (level % 3 == 1) {
      try {
        Map.getInstance().loadMap(1);
      }
      catch (FileNotFoundException e) {
        System.out.println("File not found.");
      }
    } 
    else if (level % 3 == 2) {
      try {
        Map.getInstance().loadMap(2);
      }
      catch (FileNotFoundException e) {
        System.out.println("File not found.");
      }
    } 
    else if (level % 3 == 0) {
        try {
          Map.getInstance().loadMap(3);
        }
        catch (FileNotFoundException e) {
          System.out.println("File not found.");
        }
    }
    
    loc.setLocation(Map.getInstance().findStart());            // Find the start and reveal the start location
    Map.getInstance().reveal(getLocation());
  }
  

  /** Getting the current gold of the Hero
   * @return an int gold the Hero has
   */
  public int getGold()
  {
    return gold  ;
  }

  /**
	 * If the Hero has enough money to buy something,
   * subtracts money by amt, and returns true. Otherwise,
   * returns false.
   * @param amt amount of money to be spent.
   * @return true if the Trainer has enough money to spend,
   * false if not.
	 * **/

  public boolean spendGold(int amt) {
    if (gold >= amt) {
      gold -= amt;
      return true;
    }
    return false;
  }

  /** 
	 * Gives the Hero money.
   * @param amt amount of money to be given.
	 * **/

  public void collectGold(int amt) {
    gold += amt;
  }
  
  /** 
	 * Checks if the Hero has a potion.
   * @return true if the Hero has a potion,
   * false if not.
	 * **/

  public boolean hasPotion() {
    if (potions > 0) {
      return true;
    }
    return false;
  }

  /** 
	 * Gives the Hero one potion.
	 * **/

  public void pickUpPotion() {
    potions++;
  }

  /** 
	 * Use a potion on the hero.
   * @return true if potion is used.
	 * **/

  public boolean usePotion() {
    if (hasPotion())
    {
      super.heal();
      --potions;
      return true;
    }
    else
      return false;
  }

  /** 
	 * Checks if the Hero has any key.
   * @return true if the Hero has at least a key,
   * false if not.
	 * **/

  public boolean hasKey() {
    if (keys > 0) {
      return true;
    }
    return false;
  }

  /** 
	 * Gives the Hero a key.
	 */

  public void pickUpKey() {
    ++keys;
  }

  /** 
	 * Use a key by the hero.
   * @return true if key is used.
	 * **/

  public boolean useKey() {
    if (hasKey())
    {
      levelUp();
      --keys;
      return true;
    }
    else
      return false;
  }
  

  /**
	 * Gets the Hero's current location.
   * @return the location of the Hero as a Point.
	 * **/

  public Point getLocation() {
    return loc;
  }

  /**
	 * Moves the Hero north of the map. If they move out of bounds,
   * returns 'k'. If they move anywhere else, returns the char at
   * that location.
   * @throws FileNotFoundException if the file fails to load.
   * @return the revealed point on the map as a char.
	 * **/

  public char goNorth() {
    loc.setLocation(loc.getX() - 1, loc.getY());
    if (loc.getX() < 0) {                                                // If out of bound, undo the move
      loc.setLocation(loc.getX() + 1, loc.getY());
      return 'k';
    }
    Map.getInstance().reveal(loc);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /**
	 * Moves the Trainer south of the map. If they move out of bounds,
   * returns 'k'. If they move anywhere else, returns the char at
   * that location.
   * @throws FileNotFoundException if the file fails to load.
   * @return the revealed point on the map as a char.
	 * **/

  public char goSouth() {
    loc.setLocation(loc.getX() + 1, loc.getY());
    if (loc.getX() > 4) {                                                // If out of bound, undo the move
      loc.setLocation(loc.getX() - 1, loc.getY());
      return 'k';
    }
    Map.getInstance().reveal(loc);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /**
	 * Moves the Trainer east of the map. If they move out of bounds,
   * returns 'k'. If they move anywhere else, returns the char at
   * that location.
   * @throws FileNotFoundException if the file fails to load.
   * @return the revealed point on the map as a char.
	 * **/

  public char goEast() {
    loc.setLocation(loc.getX(), loc.getY() + 1);
    if (loc.getY() > 4) {                                                // If out of bound, undo the move
      loc.setLocation(loc.getX(), loc.getY() - 1);
      return 'k';
    }
    Map.getInstance().reveal(loc);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /**
	 * Moves the Trainer west of the map. If they move out of bounds,
   * returns 'k'. If they move anywhere else, returns the char at
   * that location.
   * @throws FileNotFoundException if the file fails to load.
   * @return the revealed point on the map as a char.
	 * **/

  public char goWest() {
    loc.setLocation(loc.getX(), loc.getY() - 1);
    if (loc.getY() < 0) {                                                // If out of bound, undo the move
      loc.setLocation(loc.getX(), loc.getY() + 1);
      return 'k';
    }
    Map.getInstance().reveal(loc);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /* Prompt the user the AttackMenu and get the option
   * @return a String, AttackMenu option
   */
  public String getAttackMenu()
  {
    return "1. Physical Attack\n2. Magical Attack\n3. Ranged Attack"; 
  }

  /* Get number of items of AttackMenu
   * @return an int, number of AttackMenu options
   */
  public int getNumAttackMenuItems()
  {
    return 3; 
  }
  
  
  /* Prompt the user the SubAttackMenu and get the option
   * @param choice: the type of Attack chosen from the AttackMenu
   * @return a String, SubAttackMenu option
   */
  public String getSubAttackMenu(int choice)
  {
    if (choice == 1)
      return "1. Sword\n2. Axe";
    if (choice == 2)
      return "1. Magic Missile\n2. Fireball";
    else
      return "1. Arrow\n2. Fire Arrow";
  }

  /* Get number of items of SubAttackMenu
   * @return an int, number of SubAttackMenu options for the given Attack
   */
  public int getNumSubAttackMenuItems(int choice)
  {
    if (choice == 1)
      return 2;
    if (choice == 2)
      return 2;
    if (choice == 3)
      return 2;
    else
      return 2;
  }
  

  /* 
   * @param e: the Enemy to attack
   * @param choice: the type of Attack
   * @param subChoice: the weapon of the Attack
   * @return a String:
   */
  public String attack(Enemy e, int choice, int subChoice)
  {
    if (choice == 1)                                // Figher 
    {
      if (subChoice == 1)
      {
        return sword(e);
      }
      else
      {
        return axe(e);
      }
    }
    else if (choice == 2)                          // MAgical
    {
      if (subChoice == 1)
      {
        return magicMissile(e);
      }
      else
      {
        return fireball(e);
      }
    }
    else                                         // Archer
    {
      if (subChoice == 1)
      {
        return arrow(e);
      }
      else
      {
        return fireArrow(e);
      }
    }
  }

  
  /* Attack an Entity with a sword
   * @param e: the Entity to attack
   * @return a String: description of the sword attack
   */
  @Override
  public String sword(Entity e)
  {
    int damage = ((int)(Math.random() * 4) + 1) * level;                            // randomize damage 1-4 * level
    e.takeDamage(damage);
    return super.getName() + " slashes a " + e.getName() + " for " + damage + " damage.";
  }

  /* Attack an Entity with an axe
   * @param e: the Entity to attack
   * @return a String: description of the axe attack
   */
  @Override
  public String axe(Entity e)
  {
    int damage = ((int)(Math.random() * 5) + 2) * level;                            // randomize damage 2-6 * level
    e.takeDamage(damage);
    return super.getName() + " chops a " + e.getName() + " for " + damage + " damage.";
  }

  /* Attack an Entity with a magicMissile
   * @param e: the Entity to attack
   * @return a String: description of the magicMissile attack
   */
  @Override
  public String magicMissile(Entity e)
  {
    int damage = ((int)(Math.random() * 2) + 2) * level;                            // randomize damage 2-3 * level
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
    int damage = ((int)(Math.random() * 3) + 3) * level;                            // randomize damage 3-5 * level
    e.takeDamage(damage);
    return super.getName() + " throws a Fireball at " + e.getName() + " for " + damage + " damage.";
  }


  /* Attack an Entity with an arrow
   * @param e: the Entity to attack
   * @return a String: description of the arrow attack
   */
  @Override
  public String arrow(Entity e)
  {
    int damage = ((int)(Math.random() * 2) + 1) * level;                            // randomize damage 1-2 * level
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
    int damage = ((int)(Math.random() * 4) + 2) * level;                            // randomize damage 2-5 * level
    e.takeDamage(damage);
    return super.getName() + " set flames to " + e.getName() + " for " + damage + " damage.";
  }
  

  /* String representation of a Hero
   * @return a String: description of the Hero
   */
  @Override
  public String toString()
  {
    String heroStr = super.toString() + "\nLevel: " + level + "\nGold: " + gold + "\nP: " + potions + " K: " + keys + "\n" + Map.getInstance().mapToString(loc);
    return heroStr;
  }
}