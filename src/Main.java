import Service.BowlingService;
import Service.Player;
import Util.InputData;
import Util.Message;


import java.util.List;

public class Main {


    public static void main(String[] args) {
        Message message = new Message();
        BowlingService bowlingService = new BowlingService();
        InputData inputData = new InputData();

        message.introImage();

        int people = inputData.numberOfPlayers();
        List<Player> playerList = bowlingService.makePlayerList(people);

        bowlingService.playGame(playerList);

        /*for (Player player : playerList) {
            System.out.println(Arrays.toString(player.getScoreboard()));
        }*/

    }
}