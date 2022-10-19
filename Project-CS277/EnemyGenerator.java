import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class EnemyGenerator{
  private HashMap<String, Integer> enemy;                    // Store all the enemies info from the file
  private static EnemyGenerator instance = null;
  
   /**
  * Constructor of EnemyGenerator.
  * Open the Enemies.txt and read in all the enemy info
  * **/
  private EnemyGenerator(){
    enemy = new HashMap<String, Integer>();
    try {
      
      File fileIn = new File("Enemies.txt");
      Scanner fileReader = new Scanner(fileIn);
      
      while(fileReader.hasNext()) {
        
        String line = fileReader.nextLine();                                      // Line includes name and hp of the enemy
        String monName = line.substring(0, line.indexOf(','));                    // Get the name of the enemy, before the ','
        int monHp = Integer.parseInt(line.substring(line.indexOf(',') + 1));      // Get the hp of the enemy, after the ',', convert it to an Integer
        enemy.put(monName, monHp);                                                // add enemy info the HashMap
        
      }

      fileReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
    }
  }
  
  /**
  * Gets the instance of the EnemyGenerator.
  * @return the instance of EnemyGenerator.
  * **/
  public static EnemyGenerator getInstance(){
    if (instance == null) {
      instance = new EnemyGenerator();
    }
    return instance;
  }

  /**
   * Generate a random Enemy of random type
   * @param level: the level of the Hero, to adjust the hp of the Enemy
   * @return an Enemy that was randomly generated
   */
  public Enemy generateEnemy(int level){
    
    String[] enemies = new String[enemy.size()];                                            // Get all the names of the Monsters, put into an Array - easier randomly access
    enemy.keySet().toArray(enemies);                                                        // using Set built in function toArray(). convert Set to Array
    
    int enemyChoice = (int)(Math.random() * enemies.length);     // Randomly choose an index from the list of names of Monster

    int typeChoice = (int)(Math.random() * 3);                   // Randomly choose a type for the Enemy: 0 for Warrior, 1 for Wizard, 2 for Ranger

    if (typeChoice == 0)                                         // Generate enemy base on the randomized choices.
    {
      Enemy newEn = new Warrior(enemies[enemyChoice], (enemy.get(enemies[enemyChoice]) * level) );
      return newEn;
    }
    else if (typeChoice == 1)
    {
      Enemy newEn = new Wizard(enemies[enemyChoice], (enemy.get(enemies[enemyChoice]) * level) );
      return newEn;
    }
    else 
    {
      Enemy newEn = new Ranger(enemies[enemyChoice], (enemy.get(enemies[enemyChoice]) * level) );
      return newEn;
    }
  };
}