package Service;

import Exceptions.InputOutOfRangeException;
import Util.InputData;
import Util.Message;

import java.util.*;

public class BowlingService {

    private final InputData inputData = new InputData();


    public List<Player> makePlayerList(int numberOfPlayer) {
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < numberOfPlayer; i++) {
            Player player = new Player("Player" + (i + 1));
            playerList.add(player);
        }
        return playerList;
    }

    /**
     * playGame() : 게임 진행 로직
     * 아래 두 가지 케이스로 나누어서 구현
     * 1. 1 ~ 9 프레임 케이스
     * 2. 10 프레임 케이스
     */
    public void playGame(List<Player> playerList) {
        for (int frame = 0; frame < 10; frame++) {
            Message.showRound(frame + 1);

            for (Player player : playerList) {

                while (frame < 9) {
                    Message.askFirst(player);
                    try {
                        int first = inputData.numberOfPin();
                        if (0 > first || first > 10) { // First 입력 오류
                            throw new InputOutOfRangeException("투구 결과는 0 ~ 10 사이의 값만 입력 가능합니다.");
                        }

                        if (first == 10) { //STRIKE 성공, 1~9 라운드
                            player.setFrameScore(frame, first);
                            break;
                        }

                        if (first < 10) { // STRIKE 실패
                            Message.askSpare(player);
                            int spare = inputData.numberOfPin();
                            if (0 > spare || spare > 10 || first + spare > 10) { // Spare 입력 오류
                                throw new InputOutOfRangeException("스페어 결과는 0 ~ " + (10 - first) + " 사이의 값만 입력 가능합니다. \n재입력 바랍니다.");
                            }
                            player.setFrameScore(frame, first, spare);
                            break;
                        }
                    } catch (InputOutOfRangeException e) {
                        Message.InputOutOfRangeExceptionPrint(e.getMessage());
                    } catch (InputMismatchException e) {
                        Message.InputMismatchExceptionPrint();
                        inputData.cleanBuffer(); // 버퍼를 비워 주지 않으면 무한 반복이 일어 난다고 함...
                    }
                }//while - 1~9 Frame  END

                while (frame == 9) {
                    try {
                        Message.askFirst(player);
                        int first = inputData.numberOfPin();
                        if (0 > first || first > 10) { // First 입력 오류
                            throw new InputOutOfRangeException("투구 결과는 0 ~ 10 사이의 값만 입력 가능합니다.");
                        }

                        int spare = 0;
                        if (first == 10) {
                            Message.askSpare(player);
                            spare = inputData.numberOfPin();
                            if (0 > spare || spare > 10) { // Spare 입력 오류
                                throw new InputOutOfRangeException("스페어 결과는 0 ~ 10 사이의 값만 입력 가능합니다. \n재입력 바랍니다.");
                            }
                        }

                        if (first < 10) {
                            Message.askSpare(player);
                            spare = inputData.numberOfPin();
                            if (0 > spare || spare > 10 || first + spare > 10) { // Spare 입력 오류
                                throw new InputOutOfRangeException("스페어 결과는 0 ~ " + (10 - first) + " 사이의 값만 입력 가능합니다. \n재입력 바랍니다.");
                            }
                        }

                        if (first + spare == 20 || first + spare == 10) {
                            Message.askBonus(player);
                            int bonus = inputData.numberOfPin();
                            if (0 > bonus || bonus > 10) { // Bonus 입력 오류
                                throw new InputOutOfRangeException("보너스 결과는 0 ~ 10 사이의 값만 입력 가능합니다. \n재입력 바랍니다.");
                            }
                            player.setFrameScore(frame, first, spare, bonus);
                            break;
                        }

                        if (first == 10 && spare < 10) {
                            Message.askBonus(player);
                            int bonus = inputData.numberOfPin();
                            if (0 > bonus || bonus > 10 || spare + bonus > 10) { // Bonus 입력 오류
                                throw new InputOutOfRangeException("보너스 결과는 0 ~ " + (10 - spare) + " 사이의 값만 입력 가능합니다. \n재입력 바랍니다.");
                            }
                            player.setFrameScore(frame, first, spare, bonus);
                            break;
                        }

                        if (first + spare < 10) { //STRIKE 또는 SPARE 실패하여 보너스 없는 경우
                            player.setFrameScore(frame, first, spare);
                            break;
                        }

                    } catch (InputOutOfRangeException e) {
                        Message.InputOutOfRangeExceptionPrint(e.getMessage());
                    } catch (InputMismatchException e) {
                        Message.InputMismatchExceptionPrint();
                        inputData.cleanBuffer(); // 버퍼를 비워 주지 않으면 무한 반복이 일어 난다고 함...
                    }
                }//while - 10 Frame  END
                scoreCalculator(player, frame);
                Message.showDisplayFrame(playerList);
            }//for - playerList END
        }//for - frame END
    }

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

                //1-1. 이전 프레임이 스페어 성공
                if (player.getFrameScore(frame - 1).size() == 2 && player.getFrameScore(frame - 1).get(0) + player.getFrameScore(frame - 1).get(1) == 10) {
                    player.setFrameTotalScore(frame - 1,
                            player.getFrameTotalScore(frame - 2)
                                    + player.getFrameScore(frame - 1).get(0)
                                    + player.getFrameScore(frame - 1).get(1)
                                    + player.getFrameScore(frame).get(0));
                }

                /*
                  맨 처음과 코드가 중복인데 이는 아래와 같은 이유가 있다
                  1. 1 프레임과 같이 이전 값이 없을 경우 바로 현재 프레임 스코어를 계산해야 하기 때문에 맨 위에 중복코드가 있고
                  2. 이 코드는 위의 조건을 거쳐 온 후에 현재 프레임 스코어를 다시 계산해야 하기 때문에 있다.
                  => 예외가 발생하면 발생한 곳 이후의 코드가 실행되지 않기 때문에 2가 없으면 1의 경우에 계산이 되지 않는 문제가 있기 때문.
                */
                player.setFrameTotalScore(frame,
                        player.getFrameTotalScore(frame - 1)
                                + player.getFrameScore(frame).get(0)
                                + player.getFrameScore(frame).get(1));
            }

            //현 스페어 성공
            if (player.getFrameScore(frame).size() == 2 && player.getFrameScore(frame).get(0) + player.getFrameScore(frame).get(1) == 10) {
                //이전 스페어 성공
                if (player.getFrameScore(frame - 1).size() == 2 && player.getFrameScore(frame - 1).get(0) + player.getFrameScore(frame - 1).get(1) == 10) {
                    player.setFrameTotalScore(frame - 1,
                            player.getFrameTotalScore(frame - 2)
                                    + player.getFrameScore(frame - 1).get(0)
                                    + player.getFrameScore(frame - 1).get(1)
                                    + player.getFrameScore(frame).get(0));
                }
            }
            //현재 프레임이 스트라이크
            if (player.getFrameScore(frame).size() == 1) {
                //이전이 스페어 성공
                if (player.getFrameScore(frame - 1).size() == 2 && player.getFrameScore(frame - 1).get(0) + player.getFrameScore(frame - 1).get(1) == 10) {
                    player.setFrameTotalScore(frame - 1,
                            player.getFrameTotalScore(frame - 2)
                                    + player.getFrameScore(frame - 1).get(0)
                                    + player.getFrameScore(frame - 1).get(1)
                                    + player.getFrameScore(frame).get(0));
                }
            }


            // 이전 프레임이 스트라이크인 경우
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

            // 3. 전전 스트라이크 + 전 스트라이크 (더블)
            if (player.getFrameScore(frame - 2).size() == 1 && player.getFrameScore(frame - 1).size() == 1) {
                player.setFrameTotalScore(frame - 2,
                        player.getFrameTotalScore(frame - 3)
                                + player.getFrameScore(frame - 2).get(0)
                                + player.getFrameScore(frame - 1).get(0)
                                + player.getFrameScore(frame).get(0));

                // 3-1. 현재 스페어 실패
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

                // 3-2. 현재 스페어 성공
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
        } catch (IndexOutOfBoundsException doNothing) {
        }
    }//scoreCalculator() END

    /**
     * winnerList() : 순위 구현 로직
     */
    public void winnerList(List<Player> playerList) {
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
