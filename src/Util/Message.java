package Util;

import Service.Player;

import java.util.List;

public class Message {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";


    public static void intro() {
        System.out.println("""

                 _    _  _____  _      _____  _____ ___  ___ _____     _____  _____\s
                | |  | ||  ___|| |    /  __ \\|  _  ||  \\/  ||  ___|   |_   _||  _  |
                | |  | || |__  | |    | /  \\/| | | || .  . || |__       | |  | | | |
                | |/\\| ||  __| | |    | |    | | | || |\\/| ||  __|      | |  | | | |
                \\  /\\  /| |___ | |____| \\__/\\\\ \\_/ /| |  | || |___      | |  \\ \\_/ /
                 \\/  \\/ \\____/ \\_____/ \\____/ \\___/ \\_|  |_/\\____/      \\_/   \\___/\s

                                
                ______  _____  _    _  _      _____  _   _  _____     _____   ___  ___  ___ _____\s
                | ___ \\|  _  || |  | || |    |_   _|| \\ | ||  __ \\   |  __ \\ / _ \\ |  \\/  ||  ___|
                | |_/ /| | | || |  | || |      | |  |  \\| || |  \\/   | |  \\// /_\\ \\| .  . || |__ \s
                | ___ \\| | | || |/\\| || |      | |  | . ` || | __    | | __ |  _  || |\\/| ||  __|\s
                | |_/ /\\ \\_/ /\\  /\\  /| |____ _| |_ | |\\  || |_\\ \\   | |_\\ \\| | | || |  | || |___\s
                \\____/  \\___/  \\/  \\/ \\_____/ \\___/ \\_| \\_/ \\____/    \\____/\\_| |_/\\_|  |_/\\____/\s
                                                                    
                             
                """);
    }

    public static void askNumberOfPlayer() {
        System.out.print("""
                ______  _   _  _____   _____  _   _  _____   _   _  _   _ ___  _________  _____ ______   _____ ______  ______  _       ___  __   __ _____ ______  _____\s
                | ___ \\| | | ||_   _| |_   _|| | | ||  ___| | \\ | || | | ||  \\/  || ___ \\|  ___|| ___ \\ |  _  ||  ___| | ___ \\| |     / _ \\ \\ \\ / /|  ___|| ___ \\/  ___|
                | |_/ /| | | |  | |     | |  | |_| || |__   |  \\| || | | || .  . || |_/ /| |__  | |_/ / | | | || |_    | |_/ /| |    / /_\\ \\ \\ V / | |__  | |_/ /\\ `--.\s
                |  __/ | | | |  | |     | |  |  _  ||  __|  | . ` || | | || |\\/| || ___ \\|  __| |    /  | | | ||  _|   |  __/ | |    |  _  |  \\ /  |  __| |    /  `--. \\
                | |    | |_| |  | |     | |  | | | || |___  | |\\  || |_| || |  | || |_/ /| |___ | |\\ \\  \\ \\_/ /| |     | |    | |____| | | |  | |  | |___ | |\\ \\ /\\__/ /
                \\_|     \\___/   \\_/     \\_/  \\_| |_/\\____/  \\_| \\_/ \\___/ \\_|  |_/\\____/ \\____/ \\_| \\_|  \\___/ \\_|     \\_|    \\_____/\\_| |_/  \\_/  \\____/ \\_| \\_|\\____/\s
                              
                                
                ___  ___  ___  __   __ _____ ___  ___ _   _ ___  ___      ___\s
                |  \\/  | / _ \\ \\ \\ / /|_   _||  \\/  || | | ||  \\/  |     /   |
                | .  . |/ /_\\ \\ \\ V /   | |  | .  . || | | || .  . |    / /| |
                | |\\/| ||  _  | /   \\   | |  | |\\/| || | | || |\\/| |   / /_| |
                | |  | || | | |/ /^\\ \\ _| |_ | |  | || |_| || |  | |   \\___  |
                \\_|  |_/\\_| |_/\\/   \\/ \\___/ \\_|  |_/ \\___/ \\_|  |_/       |_/
                                                                             \s
      
                \u001B[31m예) 3명인 경우 -> 3 \u001B[0m
                """);
        System.out.print(ANSI_CYAN + "참가자 수를 입력해 주세요. (최대 4인) : " + ANSI_RESET);
    }

    public static void showRound(int frame) {
        switch (frame) {
            case 1 -> System.out.println("""
                                        
                    ______  _____  _   _  _   _ ______          __ \s
                    | ___ \\|  _  || | | || \\ | ||  _  \\        /  |\s
                    | |_/ /| | | || | | ||  \\| || | | | ______ `| |\s
                    |    / | | | || | | || . ` || | | ||______| | |\s
                    | |\\ \\ \\ \\_/ /| |_| || |\\  || |/ /         _| |_
                    \\_| \\_| \\___/  \\___/ \\_| \\_/|___/          \\___/
                    """);
            case 2 -> System.out.println("""
                                        
                    ______  _____  _   _  _   _ ______          _____\s
                    | ___ \\|  _  || | | || \\ | ||  _  \\        / __  \\
                    | |_/ /| | | || | | ||  \\| || | | | ______ `' / /'
                    |    / | | | || | | || . ` || | | ||______|  / / \s
                    | |\\ \\ \\ \\_/ /| |_| || |\\  || |/ /         ./ /___
                    \\_| \\_| \\___/  \\___/ \\_| \\_/|___/          \\_____/
                    """);
            case 3 -> System.out.println("""
                                        
                    ______  _____  _   _  _   _ ______          _____\s
                    | ___ \\|  _  || | | || \\ | ||  _  \\        |____ |
                    | |_/ /| | | || | | ||  \\| || | | | ______     / /
                    |    / | | | || | | || . ` || | | ||______|    \\ \\
                    | |\\ \\ \\ \\_/ /| |_| || |\\  || |/ /         .___/ /
                    \\_| \\_| \\___/  \\___/ \\_| \\_/|___/          \\____/           
                    """);
            case 4 -> System.out.println("""
                                        
                    ______  _____  _   _  _   _ ______            ___\s
                    | ___ \\|  _  || | | || \\ | ||  _  \\          /   |
                    | |_/ /| | | || | | ||  \\| || | | | ______  / /| |
                    |    / | | | || | | || . ` || | | ||______|/ /_| |
                    | |\\ \\ \\ \\_/ /| |_| || |\\  || |/ /         \\___  |
                    \\_| \\_| \\___/  \\___/ \\_| \\_/|___/              |_/        
                    """);
            case 5 -> System.out.println("""
                                        
                    ______  _____  _   _  _   _ ______          _____\s
                    | ___ \\|  _  || | | || \\ | ||  _  \\        |  ___|
                    | |_/ /| | | || | | ||  \\| || | | | ______ |___ \\\s
                    |    / | | | || | | || . ` || | | ||______|    \\ \\
                    | |\\ \\ \\ \\_/ /| |_| || |\\  || |/ /         /\\__/ /
                    \\_| \\_| \\___/  \\___/ \\_| \\_/|___/          \\____/\s             
                    """);
            case 6 -> System.out.println("""

                    ______  _____  _   _  _   _ ______           ____\s
                    | ___ \\|  _  || | | || \\ | ||  _  \\         / ___|
                    | |_/ /| | | || | | ||  \\| || | | | ______ / /___\s
                    |    / | | | || | | || . ` || | | ||______|| ___ \\
                    | |\\ \\ \\ \\_/ /| |_| || |\\  || |/ /         | \\_/ |
                    \\_| \\_| \\___/  \\___/ \\_| \\_/|___/          \\_____/
                    """);
            case 7 -> System.out.println("""
                                        
                    ______  _____  _   _  _   _ ______          ______
                    | ___ \\|  _  || | | || \\ | ||  _  \\        |___  /
                    | |_/ /| | | || | | ||  \\| || | | | ______    / /\s
                    |    / | | | || | | || . ` || | | ||______|  / / \s
                    | |\\ \\ \\ \\_/ /| |_| || |\\  || |/ /         ./ /  \s
                    \\_| \\_| \\___/  \\___/ \\_| \\_/|___/          \\_/   \s      
                    """);
            case 8 -> System.out.println("""
                                        
                    ______  _____  _   _  _   _ ______          _____\s
                    | ___ \\|  _  || | | || \\ | ||  _  \\        |  _  |
                    | |_/ /| | | || | | ||  \\| || | | | ______  \\ V /\s
                    |    / | | | || | | || . ` || | | ||______| / _ \\\s
                    | |\\ \\ \\ \\_/ /| |_| || |\\  || |/ /         | |_| |
                    \\_| \\_| \\___/  \\___/ \\_| \\_/|___/          \\_____/        
                    """);
            case 9 -> System.out.println("""
                                        
                    ______  _____  _   _  _   _ ______          _____\s
                    | ___ \\|  _  || | | || \\ | ||  _  \\        |  _  |
                    | |_/ /| | | || | | ||  \\| || | | | ______ | |_| |
                    |    / | | | || | | || . ` || | | ||______|\\____ |
                    | |\\ \\ \\ \\_/ /| |_| || |\\  || |/ /         .___/ /
                    \\_| \\_| \\___/  \\___/ \\_| \\_/|___/          \\____/\s              
                    """);
            case 10 -> System.out.println("""
                                        
                    ______  _____  _   _  _   _ ______          __   _____\s
                    | ___ \\|  _  || | | || \\ | ||  _  \\        /  | |  _  |
                    | |_/ /| | | || | | ||  \\| || | | | ______ `| | | |/' |
                    |    / | | | || | | || . ` || | | ||______| | | |  /| |
                    | |\\ \\ \\ \\_/ /| |_| || |\\  || |/ /         _| |_\\ |_/ /
                    \\_| \\_| \\___/  \\___/ \\_| \\_/|___/          \\___/ \\___/\s               
                    """);
            default -> System.out.println("라운드 입력 값 에러");
        }
    }

    public static void askFirst(Player player) {
        System.out.print(coloredName(player) + " 투구 결과 : ");
    }

    public static void askSpare(Player player) {
        System.out.print(coloredName(player) + " 스페어 결과 : ");
    }

    public static void askBonus(Player player) {
        System.out.print(coloredName(player) + " 보너스 결과 : ");
    }

    public static void showDisplayFrame(List<Player> playerList) {
        System.out.printf("""
        |---------|---------|---------|---------|---------|---------|---------|---------|---------|---------|---------|
        |%9s|%9d|%9d|%9d|%9d|%9d|%9d|%9d|%9d|%9d|%9d|
        |---------|---------|---------|---------|---------|---------|---------|---------|---------|---------|---------|
        """, " ",1,2,3,4,5,6,7,8,9,10);

        String scoreTable = """
                |%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|
                |---------|---------|---------|---------|---------|---------|---------|---------|---------|---------|---------|
                """;

        String calTable = """
                |%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|%9s|
                |---------|---------|---------|---------|---------|---------|---------|---------|---------|---------|---------|
                """;

        for (Player player : playerList) {
            System.out.printf(scoreTable, coloredName(player)+ "  ",
                    player.getDisplayFrame(0, player),
                    player.getDisplayFrame(1, player),
                    player.getDisplayFrame(2, player),
                    player.getDisplayFrame(3, player),
                    player.getDisplayFrame(4, player),
                    player.getDisplayFrame(5, player),
                    player.getDisplayFrame(6, player),
                    player.getDisplayFrame(7, player),
                    player.getDisplayFrame(8, player),
                    player.getDisplayFrame(9, player)
            );

            System.out.printf(calTable, " ",
                    player.getFrameTotalScore(0),
                    player.getFrameTotalScore(1),
                    player.getFrameTotalScore(2),
                    player.getFrameTotalScore(3),
                    player.getFrameTotalScore(4),
                    player.getFrameTotalScore(5),
                    player.getFrameTotalScore(6),
                    player.getFrameTotalScore(7),
                    player.getFrameTotalScore(8),
                    player.getFrameTotalScore(9)
            );
        }
    }

    public static void outro() {
        System.out.println("""
                                
                 _____   ___  ___  ___ _____   _____  _   _ ______\s
                |  __ \\ / _ \\ |  \\/  ||  ___| |  ___|| \\ | ||  _  \\
                | |  \\// /_\\ \\| .  . || |__   | |__  |  \\| || | | |
                | | __ |  _  || |\\/| ||  __|  |  __| | . ` || | | |
                | |_\\ \\| | | || |  | || |___  | |___ | |\\  || |/ /\s
                 \\____/\\_| |_/\\_|  |_/\\____/  \\____/ \\_| \\_/|___/ \s
                                
                """);
    }

    private static String coloredName(Player player) {
        switch (player.getName()) {
            case "Player1" -> {
                return ANSI_GREEN + player.getName() + ANSI_RESET;
            }
            case "Player2" -> {
                return ANSI_YELLOW + player.getName() + ANSI_RESET;
            }
            case "Player3" -> {
                return ANSI_BLUE + player.getName() + ANSI_RESET;
            }
            case "Player4" -> {
                return ANSI_PURPLE + player.getName() + ANSI_RESET;
            }
            default -> {
                return player.getName();
            }
        }
    }

    public static void showRank(int previousRank, Player player) {
        System.out.print(previousRank +"위 : ");
        System.out.println(coloredName(player) + " " + player.getFrameTotalScore(9));
    }

    public static void InputMismatchExceptionPrint() {
        System.out.println(ANSI_RED + "숫자만 입력 가능합니다." + ANSI_RESET);
    }
    public static void InputOutOfRangeExceptionPrint(String errorMessage) {
        System.out.println(ANSI_RED + errorMessage + ANSI_RESET);
    }
}
