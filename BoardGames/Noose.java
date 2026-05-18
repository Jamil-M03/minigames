package BoardGames;

public class Noose {
    private int incorrectGuesses;
    private final int maxIncorrectGuesses = 6;
    private static final String state0 = "    ┌─────┐\n" +
                                         "    │     |\n" +
                                         "    │\n" +
                                         "    │\n" +
                                         "    │\n" +
                                         "────┴────";
    private static final String state1 = "    ┌─────┐\n" +
                                         "    │     |\n" +
                                         "    │     O\n" +
                                         "    │\n" +
                                         "    │\n" +
                                         "────┴────";
    private static final String state2 = "    ┌─────┐\n" +
                                         "    │     |\n" +
                                         "    │     O\n" +
                                         "    │     |\n" +
                                         "    │\n" +
                                         "────┴────";
    private static final String state3 = "    ┌─────┐\n" +
                                         "    │     |\n" +
                                         "    │     O\n" +
                                         "    │    /|\n" +
                                         "    │\n" +
                                         "────┴────";
    private static final String state4 = "    ┌─────┐\n" +
                                         "    │     |\n" +
                                         "    │     O\n" +
                                         "    │    /|\\\n" +
                                         "    │\n" +
                                         "────┴────";
    private static final String state5 = "    ┌─────┐\n" +
                                         "    │     |\n" +
                                         "    │     O\n" +
                                         "    │    /|\\\n" +
                                         "    │    /\n" +
                                         "────┴────";
    private static final String state6 = "    ┌─────┐\n" +
                                         "    │     |\n" +
                                         "    │     O\n" +
                                         "    │    /|\\\n" +
                                         "    │    / \\\n" +
                                         "────┴────";

    private static final String[] gamesStates = {state0, state1, state2, state3, state4, state5, state6};

    public Noose(){
        this.incorrectGuesses = 0;
    }

    public void incrementIncorrectGuesses(){
        if (isComplete()){
            throw new IllegalStateException("Cannot increment incorrect guesses: game is complete.");
        }
        incorrectGuesses++;
    }

    public int getIncorrectGuesses(){
        return incorrectGuesses;
    }

    public boolean isComplete() {
        return incorrectGuesses >= maxIncorrectGuesses;
    }

    public void reset() {
        incorrectGuesses = 0;
    }
    
    /* Noose Display samples:
     *       _______
     *      /      |
     *      |      O
     *      |     /|\
     *      |     / \
     *  ____|____
     */

    /*
     *       ______
     *      /      |
     *      │      O
     *      │     /|\
     *      │     / \
     *  ────┴────
     */
    public void displayNoose(){   
        if (incorrectGuesses >= 0 && incorrectGuesses <= maxIncorrectGuesses) {
            System.out.println(gamesStates[incorrectGuesses]);
        } else {
            System.out.println("Invalid guess number: " + incorrectGuesses);
        }     
    }
    // { "─", "│", "┌", "┐", "└", "┘", "┬", "┴", "├", "┤", "┼" };

    public static void displayLossAnimation(){
        try {  
            int delay = 250;
            for (int i = 0; i < 6; i++){
                Designer.clearTerminal();

                System.out.println("    ┌─────┐");
                System.out.println("    │     |");
                System.out.println("    │     O");
                System.out.println("    │    /|\\");
                System.out.println("    │    / \\");
                System.out.println("────┴────");
                
                Thread.sleep(delay);
                Designer.clearTerminal();
                
                System.out.println("    ┌─────┐");
                System.out.println("    │     |");
                System.out.println("    │    _O_");
                System.out.println("    │     |");
                System.out.println("    │    / \\");
                System.out.println("────┴────");
                
                Thread.sleep(delay);
                Designer.clearTerminal();
                if (i == 2){
                    delay = 100;
                }
                if (i == 3){
                    delay = 50;
                }
            }

            
            System.out.println("    ┌─────┐");
            System.out.println("    │     |");
            System.out.println(  "    │     " + StringColorer.color("X", "red"));
            System.out.println(  "    │    " + StringColorer.color("/|\\", "red"));
            System.out.println(  "    │    " + StringColorer.color("/ \\", "red"));
            System.out.println("────┴────");
        } catch (InterruptedException ie){
            System.out.println(ie.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException{
                Noose n = new Noose();
                
                for (int i = 0; i < 6; i++) {
                    Designer.clearTerminal();
                    n.incrementIncorrectGuesses();
                    n.displayNoose();
                    System.out.println("Stage: " + n.incorrectGuesses);
                    Thread.sleep(500);
                }
        displayLossAnimation();
        Thread.sleep(300);
        System.out.println(Designer.putInBoxColored(StringColorer.color("You lose", "red")));

    }
    
}
