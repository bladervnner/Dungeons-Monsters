import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** Map creates a 5x5 dungeon allowing the user to have their character go in any direction
*/


public class Map{
  private char [][] map;
  private boolean [][] revealed;
  private static Map instance = null;

  /** Map Constructor
  */
  
  private Map(){
    map = new char[5][5];
    revealed = new boolean[5][5];
    for(int i = 0; i < revealed.length; i++){
      for(int j = 0; j < revealed[0].length; j++){
        revealed[i][j] = false;
      }
    }
  }

  /** getInstance checks to see if there is a map 
  */

  public static Map getInstance(){
    if(instance == null){
      instance = new Map();
    }
    return instance;
  }

  /** loadMap loads the map in the text file
  */
  
  public void loadMap(int mapNum){
    String mapTxt = "Map" + mapNum + ".txt";
    //map = new char[5][5];
    char charList [];
    int a = 0;
    int b =0;
    try {
      Scanner read = new Scanner(new File(mapTxt));
      while(read.hasNext()){
        String line1 = read.next();
        charList = line1.toCharArray();
        map[a][b] = charList[0];
        if(b == 4){
          a +=1;
          b = -1;
        }
        b +=1;
      }
      read.close();
    } catch(FileNotFoundException fnf){
      System.out.println("File was not found");
    }

    for(int i = 0; i < revealed.length; i++){
      for(int j = 0; j < revealed[0].length; j++){
        revealed[i][j] = false;
      }
    }
  }

  /** getCharAtLoc returns the position of where the hero is on the map
  @param p the points where the hero is
  */
  
  public char getCharAtLoc(Point p){
    return map[p.x][p.y];
  }

  /** findStart finds the start of the map
  */
  
  public Point findStart(){
    Point p = new Point(0,0);
    for(int i = 0; i < map.length; i++){
      for(int j = 0; j < map[i].length; j++){
        if(map[i][j] == 's'){
          p = new Point(i,j);
          break;
        }
      }
    }
    return p;
  }

  /** reveal reveals the location
  @param p the points where the hero is
  */
  
  public void reveal(Point p){
    revealed[p.x][p.y] = true;
  }

  /** removeCharAtLoc replaces the position of hero with an n meaning nothing there
  @param p the points where the hero is
  */

  public void removeCharAtLoc(Point p){
    map[p.x][p.y]='n';
  }

  /** mapToString fills up our map depending on where things are on the map
  @param p the points where the hero is
  */
  public String mapToString(Point p){
    Point tempPoint;
    String s = "";
    for(int i =0; i < map.length; i++){
      for(int j = 0 ; j < map[i].length ; j++ ){
        
        if(p.x == i && p.y == j){
          s = s + "* " ;
          revealed[i][j] = true;
          
        }
        else if(revealed[i][j] == true){
          //Here we will have to code a way for the map to return n only after the player has defeated the monster, so if they run away, it doesnt show 'n' and rather 'm';
          //s = s + "n ";
          tempPoint = new Point(i, j);
          s = s + getCharAtLoc(tempPoint) + " " ;
        }
        else{
          s = s + "x ";
        }
      }
      s = s + "\n";
    }
    return s;
  }
}