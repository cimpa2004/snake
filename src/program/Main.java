package program;
import tests.*;

public class Main {
    public static void main(String[] args) {
        if (args.length >0){
            if (args[0].equals("test")){
                TestMain.runTests();
            }
        }

        while (true){//fo ciklus
            System.out.println("run");
            System.exit(0);
        }

    }
}