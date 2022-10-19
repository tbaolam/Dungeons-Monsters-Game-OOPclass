import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
  * this class creates a Map that the Fighter keeps is
  * going to be exploring.
**/

public class Map {
  private char [][] map;
  private boolean [][] revealed;
  private static Map instance = null;


  
  /**
	 * Creates a 5x5 Map that is empty and
   * a 5x5 boolean Map that is initially 
   * set to all False.
	 * **/
  
  public Map() {
    map = new char[5][5];                        // empty map
    revealed = new boolean[5][5];
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        revealed[i][j] = false;                  // not reveling the map
      }
    }
  }

  /**
	 * Gets the instance of the map.
   * @return the instance of Map.
	 * **/

  public static Map getInstance() {
    if(instance == null) {
      instance = new Map();
    }
    return instance;
  }
  
  /**
    * Loads in the characters of a text
    * file to the Map.
    * **/

  public void loadMap(int mapNum) throws FileNotFoundException{
    try {                                                    // read in the map from text file
      File fileIN = new File("Map" + mapNum + ".txt");
      Scanner fileReader = new Scanner(fileIN);
      int row = 5;
      int column = 5;
      while (fileReader.hasNext()) {
        for (int i = 0; i < row; ++i)
          for (int j = 0; j < column; ++j)
          {
            String c = fileReader.next();
            map[i][j] = c.charAt(0);
          }
      }

      // Initialize the boolean reveal map, not revealing map
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          revealed[i][j] = false;
        }
      }

      fileReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
    }
  }

  /**
    * Returns the character at the Point's
    * location.
    * @param p the Point to be checked for its character.
    * @return the value of the position on the map as a char.
    * **/

  public char getCharAtLoc(Point p) {
    return map[p.getX()][p.getY()];
  }

  /**
    * Returns the Map in the format of a String.
    * @param p the Point of the Trainer's location.
    * @return the map displayed as a String.
    * **/

  public String mapToString(Point p) {
    String mapString = "";
    for (int i = 0; i < 5; i++) { 
      for (int j = 0; j < 5; j++) { 
        if (i == p.getX() && j == p.getY()) {
          mapString += "* ";
        } else if (!revealed[i][j]) {
          mapString += "x ";
        } else {
          mapString += map[i][j] + " "; 
        }
      }
      mapString += "\n";
    }
    return mapString;
  }

  /**
    * Returns the starting point of the Map.
    * @return the starting location as a Point.
    * **/ 

  public Point findStart() {
    Point start = new Point();
    for (int i = 0; i < 5; i++) { 
      for (int j = 0; j < 5; j++) { 
        if (map[i][j] == 's') {
          start.setLocation(i, j);
          reveal(start);
          break;
        }
      }
    }
    return start;
  }

  /**
    * Sets the specified Point in the boolean
    * Map to true once it is explored.
    * @param p the Point to be revealed.
    * **/

  public void reveal(Point p) {
    revealed[p.getX()][p.getY()] = true;
  }

  /**
    * Removes the character at the given Point,
    * denoted by the letter 'n'.
    * @param p the Point to remove the character from.
    * **/

  public void removeCharAtLoc(Point p) {
    map[p.getX()][p.getY()] = 'n';
  }
}