import service.BowlingService;
import service.Player;
import util.InputData;
import util.Message;


import java.util.*;

public class Main {

    public static void main(String[] args) {

        BowlingService bowlingService = new BowlingService();
        InputData inputData = new InputData();

        Message.intro();

        int numberOfPlayer = inputData.numberOfPlayers();
        List<Player> playerList = bowlingService.makePlayerList(numberOfPlayer);
        bowlingService.playGame(playerList);

        Message.outro();
        bowlingService.winnerList(playerList);
    }
}