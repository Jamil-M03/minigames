package BoardGames;

import java.util.Random;

public class AIPlayer extends Player {
    Random random;

    public AIPlayer(String name) {
        super(name);
        random = new Random();
    }

    @Override
    public int getInput(int range) {
        // Get the current thread's stack trace
        // StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        // // The caller of the current method is at index 2
        // StackTraceElement caller = stackTrace[2];

        // // Get the class name of the caller
        // // String callingClassName = caller.getClassName();
        // if (caller.getClassName().equals("ConnectFour.ConnectFour")){
        //     return random.nextInt(7);
        // }
        
        // if (caller.getClassName().equals("ConnectFour.ConnectThree")){
        //     return random.nextInt(4);
        // }

        return random.nextInt(range);
    }
}
