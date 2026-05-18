package BoardGames;

public abstract class Player {
    protected String name;
    protected int score;
    
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public abstract int getInput(int range);

    public void updateScore(){
        score++;
    }

    public void resetScore(){
        score = 0;
    }

    public int getScore(){
        return this.score;
    }

}
