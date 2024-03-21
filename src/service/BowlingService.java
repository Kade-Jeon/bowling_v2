package service;

import util.InputData;
import util.Message;

import java.util.*;

public class BowlingService {

    private final InputData inputData = new InputData();
    private final List<Player> playerList = new ArrayList<>();


    private void makePlayerList(int numberOfPlayer) {
        for (int i = 0; i < numberOfPlayer; i++) {
            Player player = new Player("Player" + (i + 1));
            playerList.add(player);
        }
    }

    /**
     * playGame() : 게임 진행 로직
     * 아래 두 가지 케이스로 나누어서 구현
     * 1. 1 ~ 9 프레임 케이스
     * 2. 10 프레임 케이스
     */
    public void playGame(int numberOfPlayer) {
        makePlayerList(numberOfPlayer);

        for (int frame = 0; frame < 10; frame++) {
            Message.showRound(frame + 1);

            for (Player player : playerList) {

                while (frame < 9) {
                    int first = inputData.resultOfFirst(player);
                    if (first == 10) { //STRIKE 성공, 1~9 라운드
                        player.setFrameScore(frame, first);
                        break;
                    }

                    if (first < 10) { // STRIKE 실패
                        int spare = inputData.resultOfSpare(player, first);
                        player.setFrameScore(frame, first, spare);
                        break;
                    }
                }//while - 1~9 Frame  END

                while (frame == 9) {
                    int first = inputData.resultOfFirst(player);
                    int spare = 0;

                    if (first == 10) {
                        spare = inputData.resultOfSpare(player, first);
                    }
                    if (first < 10) {
                        spare = inputData.resultOfSpare(player, first);
                    }

                    if (first + spare == 20 || first + spare == 10) {
                        int bonus = inputData.resultOfBonus(player, first, spare);
                        player.setFrameScore(frame, first, spare, bonus);
                        break;
                    }
                    if (first == 10 && spare < 10) {
                        int bonus = inputData.resultOfBonus(player, first, spare);
                        player.setFrameScore(frame, first, spare, bonus);
                        break;
                    }

                    if (first + spare < 10) { //STRIKE 또는 SPARE 실패하여 보너스 없는 경우
                        player.setFrameScore(frame, first, spare);
                        break;
                    }
                }//while - 10 Frame  END
                scoreCalculator(player, frame);
                Message.showDisplayFrame(playerList);
            }//for - playerList END
        }//for - frame END
    }//playGame() END

    /**
     * scoreCalculator() : 점수 계산 로직으로 외부에서는 호출할 필요가 없어 private으로 구현
     */
    private void scoreCalculator(Player player, int frame) {
        try {
            // 1. 현 프레임이 스페어 실패인 경우
            if (player.getFrameScore(frame).size() == 2 && player.getFrameScore(frame).get(0) + player.getFrameScore(frame).get(1) < 10) {
                player.setFrameTotalScore(frame,
                        player.getFrameTotalScore(frame - 1)
                                + player.getFrameScore(frame).get(0)
                                + player.getFrameScore(frame).get(1));
            }


            //2. 이전 프레임이 스페어 성공
            if (player.getFrameScore(frame - 1).size() == 2 && player.getFrameScore(frame - 1).get(0) + player.getFrameScore(frame - 1).get(1) == 10) {
                player.setFrameTotalScore(frame - 1,
                        player.getFrameTotalScore(frame - 2)
                                + player.getFrameScore(frame - 1).get(0)
                                + player.getFrameScore(frame - 1).get(1)
                                + player.getFrameScore(frame).get(0));

                //현 프레임이 스페어 실패
                if (player.getFrameScore(frame).size() == 2 && player.getFrameScore(frame).get(0) + player.getFrameScore(frame).get(1) < 10) {
                    player.setFrameTotalScore(frame,
                            player.getFrameTotalScore(frame - 1)
                                    + player.getFrameScore(frame).get(0)
                                    + player.getFrameScore(frame).get(1));
                }

            }

            // 3. 이전 프레임이 스트라이크인 경우
            if (player.getFrameScore(frame - 1).size() == 1 && player.getFrameScore(frame).size() == 2) {
                player.setFrameTotalScore(frame - 1,
                        player.getFrameTotalScore(frame - 2)
                                + player.getFrameScore(frame - 1).get(0)
                                + player.getFrameScore(frame).get(0)
                                + player.getFrameScore(frame).get(1));

                // 현재 프레임 스페어 실패
                if (player.getFrameScore(frame).get(0) + player.getFrameScore(frame).get(1) < 10) {
                    player.setFrameTotalScore(frame,
                            player.getFrameTotalScore(frame - 1)
                                    + player.getFrameScore(frame).get(0)
                                    + player.getFrameScore(frame).get(1));
                }
            }

            // 4. 전전 스트라이크 + 전 스트라이크 (더블)
            if (player.getFrameScore(frame - 2).size() == 1 && player.getFrameScore(frame - 1).size() == 1) {
                player.setFrameTotalScore(frame - 2,
                        player.getFrameTotalScore(frame - 3)
                                + player.getFrameScore(frame - 2).get(0)
                                + player.getFrameScore(frame - 1).get(0)
                                + player.getFrameScore(frame).get(0));

                // 4-1. 현재 스페어 실패
                if (player.getFrameScore(frame).get(0) + player.getFrameScore(frame).get(1) < 10) {
                    player.setFrameTotalScore(frame - 1,
                            player.getFrameTotalScore(frame - 2)
                                    + player.getFrameScore(frame - 1).get(0)
                                    + player.getFrameScore(frame).get(0)
                                    + player.getFrameScore(frame).get(1));

                    player.setFrameTotalScore(frame,
                            player.getFrameTotalScore(frame - 1)
                                    + player.getFrameScore(frame).get(0)
                                    + player.getFrameScore(frame).get(1));
                }

                // 4-2. 현재 스페어 성공
                if (player.getFrameScore(frame).get(0) + player.getFrameScore(frame).get(1) == 10) {
                    player.setFrameTotalScore(frame - 1,
                            player.getFrameTotalScore(frame - 2)
                                    + player.getFrameScore(frame - 1).get(0)
                                    + player.getFrameScore(frame).get(0)
                                    + player.getFrameScore(frame).get(1));
                }
            }

            if (frame == 9) { // 10프레임

                // 1. 전전 프레임 점수가 계산되지 않은 경우 (스트라이크)
                if (player.getFrameTotalScore(frame - 2) == 0) {
                    player.setFrameTotalScore(frame - 2,
                            player.getFrameTotalScore(frame - 3)
                                    + player.getFrameScore(frame - 2).get(0)
                                    + player.getFrameScore(frame - 1).get(0)
                                    + player.getFrameScore(frame).get(0));
                }

                // 2. 전 프레임 점수가 계산되지 않은 경우 (스트라이크 / 스페어 성공)
                if ((player.getFrameTotalScore(frame - 1) == 0)) {
                    player.setFrameTotalScore(frame - 1,
                            player.getFrameTotalScore(frame - 2)
                                    + player.getFrameScore(frame - 1).get(0)
                                    + player.getFrameScore(frame).get(0)
                                    + player.getFrameScore(frame).get(1));
                }

                // 3. 10 프레임에 보너스 샷을 못얻은 경우
                if (player.getFrameScore(frame).size() == 2) {
                    player.setFrameTotalScore(frame,
                            player.getFrameTotalScore(frame - 1)
                                    + player.getFrameScore(frame).get(0)
                                    + player.getFrameScore(frame).get(1));
                }

                // 4. 10 프레임에 보너스 샷을 얻은 경우
                if (player.getFrameScore(frame).size() == 3) {
                    player.setFrameTotalScore(frame,
                            player.getFrameTotalScore(frame - 1)
                                    + player.getFrameScore(frame).get(0)
                                    + player.getFrameScore(frame).get(1)
                                    + player.getFrameScore(frame).get(2));
                }
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
    }//scoreCalculator() END

    /**
     * winnerList() : 순위 구현 로직
     */
    public void winnerList() {
        List<Player> resultList = new ArrayList<>(playerList);
        resultList.sort(Comparator.comparing(player -> player.getFrameTotalScore(9), Comparator.reverseOrder()));

        int previousScore = 0;
        int previousRank = 1;
        for (int i = 0; i < resultList.size(); i++) {
            Player player = resultList.get(i);
            if (previousScore == player.getFrameTotalScore(9) || i == 0) {
                Message.showRank(previousRank, player);
            } else {
                previousRank++;
                Message.showRank(previousRank, player);
            }
            previousScore = player.getFrameTotalScore(9);
        }
    }
}
