package program;
import program.graphics.SnakeWindow;



public class Main {
    private static Data highscores = new Data();
    public static Data getHighscores(){
        return highscores;
    }
    public static void setHighscores(Data highscores){
        Main.highscores =highscores;
    }

    public static void main(String[] args) {


        highscores.readFromXML("rangletra.xml");
        Data highscores = Main.getHighscores();
        SnakeWindow window = new SnakeWindow("snake",highscores);


    }

    public static void exit(){
        //mentesek meg minden//ne irogassuk at most minden futasra
        //highscores.writeToXML("rangletra.xml");
        //
        System.exit(0);
    }
}