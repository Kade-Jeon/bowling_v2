package Util;

import Service.Player;

public class Message {
    /*public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";*/

    public void introImage() {
        System.out.println("""
                 _    _        _                                   _         \s
                | |  | |      | |                                 | |        \s
                | |  | |  ___ | |  ___   ___   _ __ ___    ___    | |_   ___ \s
                | |/\\| | / _ \\| | / __| / _ \\ | '_ ` _ \\  / _ \\   | __| / _ \\\s
                \\  /\\  /|  __/| || (__ | (_) || | | | | ||  __/   | |_ | (_) |
                 \\/  \\/  \\___||_| \\___| \\___/ |_| |_| |_| \\___|    \\__| \\___/\s
                 
                | ___ \\                 | |(_)             \s
                | |_/ /  ___  __      __| | _  _ __    __ _\s
                | ___ \\ / _ \\ \\ \\ /\\ / /| || || '_ \\  / _` |
                | |_/ /| (_) | \\ V  V / | || || | | || (_| |
                \\____/  \\___/   \\_/\\_/  |_||_||_| |_| \\__, |
                                                       __/ |
                                                      |___/\s
                  __ _   __ _  _ __ ___    ___\s
                 / _` | / _` || '_ ` _ \\  / _ \\
                | (_| || (_| || | | | | ||  __/
                 \\__, | \\__,_||_| |_| |_| \\___|
                  __/ |                       \s
                 |___/                        \s
                                
                         -~~.                \s
                         @@@*                \s
                        !@@@@                \s
                        .@@@@                \s
                         @@@@-...            \s
                         *@@@@@@@!           \s
                         $@@@@@@@@!          \s
                        -@@@@@@@@@@:         \s
                        @@@@@@@@@@@@!        \s
                       -@@@@@@@@@@@@#        \s
                       $@@@@@@@@@,@@@@.      \s
                       @@@@@@@@@@. @@@*      \s
                       @@@@@@@@@@= ,@@@:     \s
                       @@@@@@@@@@@  ,!@=     \s
                       @@@=@@@@@@@.   !@$    \s
                       @@@~@@@@@@@#    !@$   \s
                       @@@:@@@@@@@;     .;:  \s
                       @@@-@@@@@@@.          \s
                      :@@@ @@@@@@$           \s
                      ;@@~ ~@@@@@=           \s
                       @@-  @@@@@.           \s
                       @$   =@@@@            \s
                       @.   ~@@@;            \s
                      !@    =@@@*-           \s
                      @=    $@@@@=           \s
                      -!    @@@@@=           \s
                     ::     @@@-$@           \s
                   .@@@$    @@$ -@           \s
                   :@@@@    @@*  $           \s
                   :@@@@.   @$.              \s
                   ~@@@@   #@!               \s
                    .#*   ~@@-               \s
                          .~~                \s
                                
                """);
    }

    public static void askPeople() {
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
      
                예) 3명인 경우 -> 3
                """);
        System.out.print("참가자 수를 입력해 주세요. (최대 4인) : ");
    }

    public static void showRound(int round) {
        switch (round) {
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
        System.out.print(player.getName() + " 투구 결과 : ");
    }

    public static void askSpare(Player player) {
        System.out.print(player.getName() + " 스페어 결과 : ");
    }

    public static void askBonus(Player player) {
        System.out.print(player.getName() + " 보너스 결과 : ");
    }


    /*System.out.println(ANSI_RED + "이 텍스트는 빨간색입니다." + ANSI_RESET);
    System.out.println(ANSI_GREEN + "이 텍스트는 녹색입니다." + ANSI_RESET);
    System.out.println(ANSI_BLUE + "이 텍스트는 파란색입니다." + ANSI_RESET);
    System.out.println(ANSI_PURPLE + "이 텍스트는 보라색입니다." + ANSI_RESET);
    System.out.println(ANSI_YELLOW + "이 텍스트는 노란색입니다." + ANSI_RESET);
    System.out.println(ANSI_CYAN + "이 텍스트는 청록색입니다." + ANSI_RESET);
    System.out.println(ANSI_BLACK + "이 텍스트는 검은색입니다." + ANSI_RESET);
    System.out.println(ANSI_WHITE + "이 텍스트는 흰색입니다." + ANSI_RESET);*/
}
