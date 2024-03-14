package Service;

import java.util.ArrayList;
import java.util.List;

/**
 * name = 플레이어 이름
 * scoreboard = 해당 프레임의 투구 결과
 * totalScore = 해당 프레임의 점수 결과
 */
public class Player {

    private final String name;
    private final List<Integer>[] scoreboard;
    private final int[] totalScore;


    public Player(String name) {
        this.name = name;
        this.scoreboard = new ArrayList[10];
        for (int i = 0; i < 9; i++) {
            scoreboard[i] = new ArrayList<>(2);
        }
        scoreboard[9] = new ArrayList<>(3);
        this.totalScore = new int[10];
    }

    public String getName() {
        return name;
    }

    /**
     * serFrameScore() 메서드는 오버로딩을 통해서 세 가지 케이스를 다룰 수 있게 구현했다.
     * 1. 스트라이크로 한 번에 프레임이 끝난 경우
     * 2. 스페어 까지 두 번에 프레임이 끝난 경우
     * 3. 10 프레임에서 보너스 기회를 얻은 경우
     */
    public void setFrameScore(int frame, int first) {
        scoreboard[frame].add(0, first);
    }

    public void setFrameScore(int frame, int first, int spare) {
        scoreboard[frame].add(0, first);
        scoreboard[frame].add(1, spare);
    }

    public void setFrameScore(int frame, int first, int spare, int bonus) {
        scoreboard[frame].add(0, first);
        scoreboard[frame].add(1, spare);
        scoreboard[frame].add(2, bonus);
    }

    public void setFrameTotalScore(int frame, int score) {
        totalScore[frame] = score;
    }

    public int getFrameTotalScore(int frame) {
        if (frame < 0) {
            return 0;
        }
        return totalScore[frame];
    }

    /**
     * scoreboard의 프레임마다 결과를 가지고 오는 메서드
     * ArrayList가 동적으로 늘어나는 것을 이용하여 사이즈를 통해 구분하여 반환하도록 처리했다.
     */
    public List<Integer> getFrameScore(int frame) {

        List<Integer> frameScore = new ArrayList<>(1);
        if (scoreboard[frame].size() == 1) {
            frameScore.add(scoreboard[frame].get(0));
            return frameScore;
        }
        if (scoreboard[frame].size() == 3) {
            frameScore.add(scoreboard[frame].get(0));
            frameScore.add(scoreboard[frame].get(1));
            frameScore.add(scoreboard[frame].get(2));
            return frameScore;
        }
        frameScore.add(scoreboard[frame].get(0));
        frameScore.add(scoreboard[frame].get(1));
        return frameScore;
    }

    /**
     * player가 scoreboard에 가지고 있는 값들을 통해 출력될 점수판에 알맞은 표기로 바꾸어주는 로직.
     * 알고자하는 frame과 player를 넘겨주면 scoreboard의 값을 변환하여 String으로 반환한다.
     */
    public String getDisplayFrame(int frame, Player player) {
        try {
            if (player.getFrameScore(frame).size() == 1) { //스트라이크
                return "X";
            }

            if (player.getFrameScore(frame).size() == 2 && player.getFrameScore(frame).get(0) + player.getFrameScore(frame).get(1) == 10) { //스페어 성공
                if (player.getFrameScore(frame).get(0) == 0) {
                    return "- | " + player.getFrameScore(frame).get(1);
                }
                return player.getFrameScore(frame).get(0) + " | /";
            }

            if (player.getFrameScore(frame).size() == 2 && player.getFrameScore(frame).get(0) + player.getFrameScore(frame).get(1) < 10) { // 스페어 실패
                if (player.getFrameScore(frame).get(0) == 0 && player.getFrameScore(frame).get(1) != 0) {
                    return "- | " + player.getFrameScore(frame).get(1);
                }
                if (player.getFrameScore(frame).get(0) != 0 && player.getFrameScore(frame).get(1) == 0) {
                    return player.getFrameScore(frame).get(0) + " | -";
                }
                if (player.getFrameScore(frame).get(0) == 0 && player.getFrameScore(frame).get(1) == 0) {
                    return "- | -";
                }
                return player.getFrameScore(frame).get(0) + " | " + player.getFrameScore(frame).get(1);
            }

            if (frame == 9) {
                StringBuilder result = new StringBuilder("");
                if (player.getFrameScore(frame).get(0) == 10) { //스트라이크
                    result.append("X");

                    //스페어 성공
                    if (player.getFrameScore(frame).get(1) + player.getFrameScore(frame).get(2) == 10) {
                        return result.append(" | ")
                                .append(player.getFrameScore(frame).get(1))
                                .append(" | ")
                                .append("/")
                                .toString();
                    }
                    //스페어 실패
                    if (player.getFrameScore(frame).get(1) + player.getFrameScore(frame).get(2) < 10) {
                        return result.append(" | ")
                                .append(player.getFrameScore(frame).get(1))
                                .append(" | ")
                                .append(player.getFrameScore(frame).get(2))
                                .toString();
                    }

                    //더블
                    if (player.getFrameScore(frame).get(1) == 10) {
                        result.append(" | ")
                                .append("X");

                        //터키
                        if (player.getFrameScore(frame).get(2) == 10) {
                            return result.append(" | ")
                                    .append("X")
                                    .toString();
                        }

                        if (player.getFrameScore(frame).get(2) < 10) {
                            return result.append(" | ")
                                    .append(player.getFrameScore(frame).get(2))
                                    .toString();
                        }
                    }
                }

                //스페어 성공
                if (player.getFrameScore(frame).get(0) + player.getFrameScore(frame).get(1) == 10) {
                    result.append(player.getFrameScore(frame).get(0))
                            .append(" | ")
                            .append("/");

                    //보너스 스트라이크
                    if (player.getFrameScore(frame).get(2) == 10) {
                        return result.append(" | ")
                                .append("X")
                                .toString();
                    }

                    if (player.getFrameScore(frame).get(2) < 10) {
                        return result.append(" | ")
                                .append(player.getFrameScore(frame).get(2))
                                .toString();
                    }
                }

                //스페어 실패
                if (player.getFrameScore(frame).get(0) + player.getFrameScore(frame).get(1) < 10) {
                    return result.append(player.getFrameScore(frame).get(0))
                            .append(" | ")
                            .append(player.getFrameScore(frame).get(1))
                            .toString();
                }
            }

            return "    ";
        } catch (IndexOutOfBoundsException e) {

        }
        return "    ";
    }
}
