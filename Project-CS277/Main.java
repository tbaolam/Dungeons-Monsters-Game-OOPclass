
/* 
*Group 2
* Kiet Nguyen - 027353519
* Brian Bui - 027119493
* Bao Lam - 028741698
* April 15 , 2022


*/




import java.io.FileNotFoundException;


// Prompt to get players name and get user input 
class Main {
  public static void main(String[] args) throws FileNotFoundException {
    System.out.print("What is your name, traveler?");                            // Get the name of the player
    String name = CheckInput.getString();
    
    
    Map m = Map.getInstance();                                                  // Load the 1st map
    try {
      m.loadMap(1);
    }
    catch (FileNotFoundException e) {
      System.out.println("File not found.");
    }

    
    Hero h = new Hero(name);                                                    //Create the Hero using the name given
    EnemyGenerator enGenerator = EnemyGenerator.getInstance();                  // Get the enemyGenerator

    
    System.out.println(h);                                                      // Print Hero information
    
    //initialize option: the user input & ch: the location of the player move to
    int option = 0;
    char ch = 'a';
    
    while (option != 5 && h.getHp() != 0) {                        // If player doesn't quit or the Hero is still alive
      option = mainMenu();                                         // Prompt and get the direction option
      if (option == 1) {
        ch = h.goNorth();
      }
      else if (option == 2) {
        ch = h.goSouth();
      }
      else if (option == 3) {
        ch = h.goEast();
      }
      else if (option == 4) {
        ch = h.goWest();
      }
      else {
        break;
      }

      // Check the move choice and move the player on the map
      if (ch == 'k')                                                    // Location out of bound
        System.out.println("You cannot go that way.");
      else if (ch == 'x') 
      {
      }
      else if (ch == 'i')                                               // Item location
      {
        int random = (int)(Math.random() * 3);                          // Key chance 1/3, potion chance 2/3
        if (random == 0) {
          System.out.println("You found a key.");
          h.pickUpKey();
          m.removeCharAtLoc(h.getLocation());                           // Remove the character after reaching the location 
          }
          else {
          System.out.println("You found a potion.");
          h.pickUpPotion();
          m.removeCharAtLoc(h.getLocation());                           // Remove the character after reaching the location 
        }
        }
      else if (ch == 's')                                               // Store location
      {
        store(h);
      }
      else if (ch == 'm')                                               // Monster encounter location
      {
        Enemy monster = enGenerator.generateEnemy(h.getLevel());        //Create a random Enemy
        
        boolean mDefeated = monsterRoom(h, monster);                    // Have the Hero and Enemy enters monsterRoom
        if (mDefeated && m.getCharAtLoc(h.getLocation()) != 's')        // If the player defeat the Enemy, clear the monster room
        {                                                               // If the player loses or run away, keep the monster room
          m.removeCharAtLoc(h.getLocation());
        }
        
      }
      else if (ch == 'f'){                                               // Reach the finish location, check if the player has a key
        if (h.hasKey() == true){
          
          //if the Hero has a key, then increase the Hero’s level and load the next map
          System.out.println("You find a locked gate. Luckily you have a key! You proceed to the next area.");
          h.levelUp();
        }
        else { //if no key, have the player stay on the map to find the key.
          System.out.println("You find a locked gate but you don't have the key. Go find one.!");
        }       
      }
      else {                                                            // The room is empty
        System.out.println("There's nothing here...");
      }

      System.out.println(h);
    }
  }


      
   /**
    * Prompts the Player with the main menu and returns
    * their selection as an int.
    * @return the Player's input as an int.
    * **/
  public static int mainMenu() {
    System.out.println("Main Menu:\n1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
    return CheckInput.getIntRange(1, 5);
    }



  
   /**
    * Monster room: where Hero and a monster Enemy fight each other, end with Hero wins, Hero runs away or Monster wins
    * @param h: the Hero
    * @param e: the Enemy
    * @return a boolean: true if Hero win, false other wise.
    * **/
  public static boolean monsterRoom(Hero h, Enemy e){
    System.out.println("You have encountered a " + e);                          // Display Enemy info

    int playerChoice;
    while ( (h.getHp() != 0) && (e.getHp() != 0) )                             // While hero and monster is alive
    {
      // Prompting what to do when facing the monster
      System.out.println("1. Fight");
      System.out.println("2. Run Away");
      if (h.hasPotion())                                                       // Display 3rd option: use potion only when Hero has a potion
      {
        System.out.println("3. Drink Potion");
        playerChoice = CheckInput.getIntRange(1, 3);
      }
      else                                                                     // else just 2 options
        playerChoice = CheckInput.getIntRange(1, 2);

      // Actions base on the playerChoice
      if (playerChoice == 1)                                                    // Fight chosen
      {
        boolean fightEnd = fight(h, e);                                        // Check if the Hero has defeated the monster
        if (fightEnd)                                                          // If the Hero won, give Hero a random number of gold (0 - 6 golds)
        {
          int gold = (int)(Math.random() * 7);
          System.out.println("You found " + gold + " on the corspe");
          h.collectGold(gold);
          break;
        }
        else
        {
          System.out.println(e);                                                // If the Hero hasn't won, display the info of Enemy
        }
      }
      else if (playerChoice == 3)                                               // Use potion chosen
      {
        h.usePotion();
      }
      else                                                                    // Run away chosen
      {
        while (true){
          char ch = 'z';                                                     // Random a direction for the Hero to run
          int option = (int)(Math.random() * 4 + 1);
          
          if (option == 1) {
            ch = h.goNorth();
          }
          else if (option == 2) {
            ch = h.goSouth();
          }
          else if (option == 3) {
            ch = h.goEast();
          }
          else if (option == 4) {
            ch = h.goWest();
          }

          // Check the move choice and move the player on the map
          if (ch == 'k') {                                                  // if the direction is out of bound, randomize again the direction
            continue;
          }
          else if (ch == 'i') {                                              // Item location after running away
            int random = (int)(Math.random() * 3);                           // Key chance: 1/3, Potion chance: 2/3
            // when found a key
            if (random == 0) {
              System.out.println("You found a key.");
              h.pickUpKey(); 
              Map.getInstance().removeCharAtLoc(h.getLocation());            // Remove the character after reaching the location
              break;                                                         // Exit the monsterRoom
            }
          // * return a message when found a potion and pick up
            else {
              System.out.println("You found a potion.");
              h.pickUpPotion();
              Map.getInstance().removeCharAtLoc(h.getLocation());            // Remove the character after reaching the location
              break;                                                         // Exit the monsterRoom
            }
          }
          else if (ch == 's') {                                              // Store location after running away
            store(h);
            break;                                                           // Exit the monsterRoom
          }
          else if (ch == 'm')                                                // Enter another monsterRoom after running away
          {
            EnemyGenerator enGenerator = EnemyGenerator.getInstance();
            Enemy monster = enGenerator.generateEnemy(h.getLevel());         // Generate monster
            
            boolean mDefeated = monsterRoom(h, monster);                      // Hero and monster enter monsterRoom
            
            if (mDefeated && Map.getInstance().getCharAtLoc(h.getLocation()) != 's') // If the player defeat the Enemy, clear the monster room
            {                                                                        // If the player lose or run away, keep the monster room
              Map.getInstance().removeCharAtLoc(h.getLocation());
            }
            break;                                                          // Exit the monsterRoom
          }
          else if (ch == 'f'){                                                // Finish location after running away

            // * player found a locked gate with the key collected, levelUp and load the nextMap.
            if (h.hasKey() == true){
              System.out.println("You find a locked gate. Luckily you have a key! You proceed to the next area.");
              h.levelUp();
              break;                                                        // Exit the monsterRoom
            }
          // * otherwise display message when player doesnt have the key collected, have the Hero stay in the map to find one
            else {
              System.out.println("You find a locked gate but you don't have the key. Go find one.!");
              break;                                                        // Exit the monsterRoom
            }       
          }
          else {
            System.out.println("There's nothing here...");
            break;                                                        // Exit the monsterRoom
          }
        }
        break;                                                        // Exit the runAway loop
      }
    }

    if (h.getHp() != 0)
      return true;      // Player is alive
    else
      return false;    // Player is dead
    
  }

  
  /**
    * A single round fight between Hero and an Enemy
    * @param h: the hero.
    * @param e: the enemy
    * @return true hero wins, false if not finished or hero loses.
    * **/
  public static boolean fight(Hero h, Enemy e){
    System.out.println(h.getAttackMenu());                                                        // Get attack Choice for the Hero
    int attackChoice = CheckInput.getIntRange(1, h.getNumAttackMenuItems());
    
    System.out.println(h.getSubAttackMenu(attackChoice));
    int subAttackChoice = CheckInput.getIntRange(1, h.getNumSubAttackMenuItems(attackChoice));   // Get subAttackChoice for the Hero after picking the attackChoice
    
    System.out.println(h.attack(e, attackChoice, subAttackChoice));                              // Start the Hero's attack

    if (e.getHp() != 0)                                                            // If enemy is alive, enemy strikes back, display the attack description
    {
      System.out.println(e.attack(h));
      return false;
    }
    else                                                                          // If enemy is defeated, display wining message
    {
      System.out.println("You defeated the " + e.getName());
      return true;
    }
  }



  /**
    * Triggers when the Hero moves to a 's' on the map.
    * Allows the Hero to enter the store, where they can buy
    * potions, keys, or leave.
    * @param h the hero that is buying from the store.
    * **/
  public static void store(Hero h){
    String storeMenu = "Welcome to the store. What would you like to buy?\n1. Health Potions - 25g\n2. key - 50g\n3. Nothing, just browsing...";
    System.out.println(storeMenu);
    int input = CheckInput.getIntRange(1, 3);
    while (input != 3) {
      if (input == 1) {
        // * display a message when player gold is enough to buy a potion 
        if (h.spendGold(25)) {      
          h.pickUpPotion();
          System.out.println("Here's your potion.");
        }
          // * otherwise return a message saying they don't have enough money
        else {
          System.out.println("You do not have enough money.");
        }
      }
      else {
        // * display a message when player gold is enough to buy a potion 
        if (h.spendGold(50)) {
          h.pickUpKey();
          System.out.println("Here's your Key.");
        }
          // * otherwise return a message saying they don't have enough money
        else {
          System.out.println("You do not have enough money.");
        }
      }
      System.out.println(storeMenu);
      input = CheckInput.getIntRange(1, 3);
    }
    // * return a goodbye message when user chose to leave
    System.out.println("Thank you, come again soon!");
  }
}