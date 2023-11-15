package program;
import program.graphics.Game;
import program.graphics.SnakeWindow;
import tests.*;



public class Main {
    private static Data highscores = new Data();
    public static Data getHighscores(){
        return highscores;
    }

    public static void main(String[] args) {
        if (args.length >0){
            if (args[0].equals("test")){
                TestMain.runTests();
            }
        }

        highscores.readFromXML("rangletra.xml");
        Game.Start();


    }

    public static void exit(){
        //mentesek meg minden//ne irogassuk at most minden futasra
        //highscores.writeToXML("rangletra.xml");
        //
        System.exit(0);
    }
}