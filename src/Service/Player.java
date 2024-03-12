package Service;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private List<Integer>[] scoreboard;
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

    public int[] getTotalScore() {
        return totalScore;
    }

    public List<Integer>[] getScoreboard() {
        return scoreboard;
    }

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

    public List<Integer> getFrameScore(int frame) {
        //ArrayList가 자동으로 늘어나는 것을 이용
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
}
