// This file contains the Game class and Action classes

/**
 * Represents the state of the game and the player.
 */
public class Game {
    private int x;
    private int y;
    private int score;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void process(BoundedQueue<? extends Action> actions){
        while(!actions.empty()){
            actions.get().actOn(this);
        }
    }

    public BoundedQueue<? super MoveAction> generateMovements(BoundedQueue<? super MoveAction> mActions){
        MoveAction ma = new MoveAction(7, -12);
        mActions.put(ma);
        ma = new MoveAction(-5, 9);
        mActions.put(ma);
        return mActions;
    }
}

abstract class Action {
    public abstract void actOn(Game game);
}

class MoveAction extends Action {
    private int x;
    private int y;
    public MoveAction(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public void actOn(Game game) {
            game.setX(game.getX() + x);
            game.setY(game.getY() + y);
    }
}


class ScoreAction extends Action {
    private int scoreChange;

    public ScoreAction(int scoreChange) {
        this.scoreChange = scoreChange;
    }

    public void actOn(Game game) {
        int newScore = game.getScore() + scoreChange;
        game.setScore(newScore);
    }
}
