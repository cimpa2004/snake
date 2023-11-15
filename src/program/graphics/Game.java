package program.graphics;


import program.Data;
import program.Main;

public class Game{

    public static void Start(){
        Data highscores = Main.getHighscores();
        SnakeWindow window = new SnakeWindow("snake",highscores);
    }

}