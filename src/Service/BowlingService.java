package Service;

import Exceptions.InputOutOfRangeException;
import Util.InputData;
import Util.Message;

import java.util.*;

public class BowlingService {

    private final InputData inputData = new InputData();

    public List<Player> makePlayerList(int people) {
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < people; i++) {
            Player player = new Player("Player" + (i + 1));
            playerList.add(player);
        }
        return playerList;
    }

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
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("숫자만 입력 가능합니다.");
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
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("숫자만 입력 가능합니다.");
                        inputData.cleanBuffer(); // 버퍼를 비워 주지 않으면 무한 반복이 일어 난다고 함...
                    }
                }//while - 10 Frame  END
                System.out.println(Arrays.toString(player.getScoreboard()));
                scoreCalculator(player, frame);
                System.out.println(Arrays.toString(player.getTotalScore()));
            }//for - playerList END
        }//for - frame END
    }

    private void scoreCalculator(Player player, int frame) {
        try {
            if (player.getFrameScore(frame).size() == 2 && player.getFrameScore(frame).get(0) + player.getFrameScore(frame).get(1) < 10) { // 스페어 실패 : 현 초구 + 현 스페어 합
                //System.out.println("시작" + frame);

                if (player.getFrameScore(frame - 1).size() == 2 && player.getFrameScore(frame - 1).get(0) + player.getFrameScore(frame - 1).get(1) < 10) {
                    //System.out.println("직전 2샷 - 실패");
                    player.setFrameTotalScore(frame - 1,
                            player.getFrameTotalScore(frame - 2)
                                    + player.getFrameScore(frame - 1).get(0)
                                    + player.getFrameScore(frame - 1).get(1));

                }
                if (player.getFrameScore(frame - 1).size() == 2 && player.getFrameScore(frame - 1).get(0) + player.getFrameScore(frame - 1).get(1) == 10) {
                    //System.out.println("직전 2샷 - 성공");
                    player.setFrameTotalScore(frame - 1,
                            player.getFrameTotalScore(frame - 2)
                                    + player.getFrameScore(frame - 1).get(0)
                                    + player.getFrameScore(frame - 1).get(1)
                                    + player.getFrameScore(frame).get(0));
                }

                if (player.getFrameScore(frame - 1).size() == 1) {
                    //System.out.println("직전 1샷");
                    player.setFrameTotalScore(frame - 1,
                            player.getFrameTotalScore(frame - 2)
                                    + player.getFrameScore(frame - 1).get(0)
                                    + player.getFrameScore(frame).get(0));
                }
                player.setFrameTotalScore(frame,
                        player.getFrameTotalScore(frame - 1)
                                + player.getFrameScore(frame).get(0)
                                + player.getFrameScore(frame).get(1));
            }

            if (player.getFrameScore(frame - 1).size() == 1 && player.getFrameScore(frame).size() == 2) { // 직전 스트라이크
                //System.out.println("중간" + frame);
                player.setFrameTotalScore(frame - 1,
                        player.getFrameTotalScore(frame - 2)
                                + player.getFrameScore(frame - 1).get(0)
                                + player.getFrameScore(frame).get(0)
                                + player.getFrameScore(frame).get(1));
                if (player.getFrameScore(frame).get(0) + player.getFrameScore(frame).get(1) < 10) { // 스페어 실패
                    //System.out.println("스페어 실패");
                    player.setFrameTotalScore(frame,
                            player.getFrameTotalScore(frame - 1)
                                    + player.getFrameScore(frame).get(0)
                                    + player.getFrameScore(frame).get(1));
                }
            }

            if (player.getFrameScore(frame - 2).size() == 1 && player.getFrameScore(frame - 1).size() == 1) { // 전전 스트라이크 직전 스트라이크
                //System.out.println("끝" + frame);
                player.setFrameTotalScore(frame - 2,
                        player.getFrameTotalScore(frame - 3)
                                + player.getFrameScore(frame - 2).get(0)
                                + player.getFrameScore(frame - 1).get(0)
                                + player.getFrameScore(frame).get(0));

                if (player.getFrameScore(frame).get(0) + player.getFrameScore(frame).get(1) < 10) { // 스페어 실패
                    //System.out.println("스페어 실패");
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
                if (player.getFrameScore(frame).get(0) + player.getFrameScore(frame).get(1) == 10) { // 스페어 성공
                    //System.out.println("스페어 성공");
                    player.setFrameTotalScore(frame - 1,
                            player.getFrameTotalScore(frame - 2)
                                    + player.getFrameScore(frame - 1).get(0)
                                    + player.getFrameScore(frame).get(0)
                                    + player.getFrameScore(frame).get(1));
                }
            }

            if (frame == 9) {
                //System.out.println("마지막 라운드");
                if (player.getFrameTotalScore(frame - 2) == 0) {
                    player.setFrameTotalScore(frame - 2,
                            player.getFrameTotalScore(frame - 3)
                                    + player.getFrameScore(frame - 2).get(0)
                                    + player.getFrameScore(frame - 1).get(0)
                                    + player.getFrameScore(frame).get(0));
                }

                if ((player.getFrameTotalScore(frame - 1) == 0)) {
                    player.setFrameTotalScore(frame - 1,
                            player.getFrameTotalScore(frame - 2)
                                    + player.getFrameScore(frame - 1).get(0)
                                    + player.getFrameScore(frame).get(0)
                                    + player.getFrameScore(frame).get(1));
                }

                if (player.getFrameScore(frame).size() == 2) {
                    player.setFrameTotalScore(frame,
                            player.getFrameTotalScore(frame - 1)
                                    + player.getFrameScore(frame).get(0)
                                    + player.getFrameScore(frame).get(1));
                }

                if (player.getFrameScore(frame).size() == 3) {
                    player.setFrameTotalScore(frame,
                            player.getFrameTotalScore(frame - 1)
                                    + player.getFrameScore(frame).get(0)
                                    + player.getFrameScore(frame).get(1)
                                    + player.getFrameScore(frame).get(2));
                }

            }
        } catch (
                IndexOutOfBoundsException doNothing) {
        }
    }//scoreCalculator() END


}
