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

        bowlingService.playGame(inputData.numberOfPlayers());

        Message.outro();

        bowlingService.winnerList();
    }
}