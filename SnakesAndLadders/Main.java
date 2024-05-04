package SnakesAndLadders;

import java.util.*;

class JumpVehicles {
    int startPoint;
    int endPoint;

    JumpVehicles(int startPoint, int endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
}
class Snake extends JumpVehicles {
    Snake(int start, int end) {
        super(start, end);
    }

}
class Ladder extends JumpVehicles{
    Ladder(int start, int end) {
        super(start, end);
    }
}
enum Player {
    PLAYERA,
    PLAYERB,
}
class Dice {
    static int rollDice() {
        Random r = new Random();
        int num = r.nextInt(6)+1;
        return num;
    }
}
class GameEngine {
    Deque<Player> playerQueue;
    HashMap<Player, Integer> playerPosition;
    HashMap<Integer, Integer> snakePositions;
    HashMap<Integer, Integer> ladderPositions;
    GameEngine(List<Snake> snakes, List<Ladder> ladders) {
        snakePositions = new HashMap<>();
        ladderPositions = new HashMap<>();
        playerPosition = new HashMap<>();
        playerQueue = new ArrayDeque<>();
        playerPosition.put(Player.PLAYERA, 0);
        playerPosition.put(Player.PLAYERB, 0);
        playerQueue.add(Player.PLAYERA);
        playerQueue.add(Player.PLAYERB);

        for(Snake s: snakes) {
            int startPoint = s.startPoint;
            int endPoint = s.endPoint;
            snakePositions.putIfAbsent(startPoint, endPoint);
        }
        for(Ladder l: ladders) {
            int startPoint = l.startPoint;
            int endPoint = l.endPoint;
            ladderPositions.putIfAbsent(startPoint, endPoint);
        }
    }
    void run() {
        while(playerPosition.get(playerQueue.peek()) != 100) {
            Player currentPlayer = playerQueue.peek();
            System.out.println("Current turn for Player : " + currentPlayer);

            int generatedNumber = Dice.rollDice();
            System.out.println("You rolled: " + generatedNumber);
            int currentPosition = playerPosition.get(currentPlayer);
            if(currentPosition + generatedNumber > 100) {
                System.out.println("Number exceeded 100. Player plays again..");
                continue;
            }

            currentPosition += generatedNumber;

            if(snakePositions.get(currentPosition) != null) {
                int downgradedPosition = snakePositions.get(currentPosition);
                System.out.println("Ooops! Encountered a snake. Downgrading to position: " + downgradedPosition);
                currentPosition = downgradedPosition;
            } else if(ladderPositions.get(currentPosition) != null) {
                int upgradedPosition = ladderPositions.get(currentPosition);
                System.out.println("Wooh! Encountered a ladder. Upgrading to position: " + upgradedPosition);
                currentPosition = upgradedPosition;
            }

            playerPosition.put(currentPlayer, currentPosition);
            System.out.println("New positions: ");
            System.out.println("Player A: " + playerPosition.get(Player.PLAYERA) +
                    "\tPlayer B: " + playerPosition.get(Player.PLAYERB));

            if(currentPosition == 100) {
                System.out.println("Congratulations! Player: " + currentPlayer + " has won the game.");
                break;
            }

            playerQueue.offerLast(playerQueue.peek());
            playerQueue.pollFirst();
        }
        System.out.println("Game over.");
    }

}

public class Main {
    public static void main(String[] args) {
        Snake s1 = new Snake(75, 30);
        Snake s2 = new Snake(34, 22);
        Snake s3 = new Snake(95, 78);
        Ladder l1 = new Ladder(21, 45);
        Ladder l2 = new Ladder(56, 89);
        Ladder l3 = new Ladder(67, 93);
        GameEngine gameEngine = new GameEngine(Arrays.asList(s1, s2, s3), Arrays.asList(l1, l2, l3));
        gameEngine.run();
    }
}
