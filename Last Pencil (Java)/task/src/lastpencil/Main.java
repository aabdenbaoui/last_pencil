package lastpencil;

import java.util.Scanner;

public class Main {
    

    public static void main(String[] args) {
        starTheGame();
    }
    public static void starTheGame() {
        int pencils;

        pencils = askForPencils();
        String name = getUserName();
        Bot bot = new Bot(pencils);
        Player botPlayer = new Player("Jack", bot);
        Player johnPlayer =  new Player("John");
        displayPencils(pencils);
        if(name.equals("John")){
            johnFirst(johnPlayer, pencils, botPlayer);
        }else if (name.equals("Jack")){
            botFirst(botPlayer, pencils,johnPlayer);
        }
        if(johnPlayer.isWinner){
            System.out.println(johnPlayer.getName() + " won!");
        }else if(botPlayer.isWinner()){
            System.out.println(botPlayer.getName() + " won!");
        }


    }

    private static void botFirst(Player bot,int pencils, Player john) {
        int pencilsLeft = pencils;

        while(true) {
            bot.setPencils(pencilsLeft);
            System.out.println(getBotTake(bot));
            pencilsLeft = bot.pencilsLeft();
            displayPencils(pencilsLeft);
//            System.out.println(getBotTake(bot));
//             pencilsLeft = bot.pencilsLeft();
//            displayPencils(pencilsLeft);
            if (pencilsLeft <= 0) {
                john.setWinner(true);
                bot.setWinner(false);
                break;
            }
//            System.out.println("Pencils:" + pencilsLeft);
            int take = askForTheTake(pencilsLeft);

            pencilsLeft -= take;
            displayPencils(pencilsLeft);

            if (pencilsLeft <= 0) {
                john.setWinner(false);
                bot.setWinner(true);
                break;
            }
            bot.setPencils(pencilsLeft);
        }

    }
    public static int getBotTake(Player player){
        System.out.print("Jack turn:");
        return player.getTake();
    }
    private static void johnFirst(Player john, int pencils, Player bot) {
        int pencilsLeft = pencils;
        while(true) {
            int take = askForTheTake(pencilsLeft);
             pencilsLeft = pencilsLeft - take;
            displayPencils(pencilsLeft);
            if (pencilsLeft <= 0) {
                john.setWinner(false);
                bot.setWinner(true);
                break;
            }
            bot.setPencils(pencilsLeft);
            System.out.println(getBotTake(bot));
            pencilsLeft = bot.pencilsLeft();
            displayPencils(pencilsLeft);
            if (pencilsLeft <= 0) {
                john.setWinner(true);
                bot.setWinner(false);
                break;
            }

        }


    }

    public static int askForTheTake(int pencils){
        Scanner sc = new Scanner(System.in);
        System.out.println("John turn!");
        String takeStr = sc.nextLine();
        while(!isNumeric(takeStr) || (pencils - Integer.parseInt(takeStr)) < 0 || (Integer.parseInt(takeStr) <  1 || Integer.parseInt(takeStr) > 3 )) {
            if (!isNumeric(takeStr) || (Integer.parseInt(takeStr) < 1 || Integer.parseInt(takeStr) > 3 )|| takeStr == null || takeStr.length() == 0 || takeStr.equals("") || takeStr.isBlank()) {
                System.out.println("Possible values: '1', '2' or '3'");
            } else if (pencils - Integer.parseInt(takeStr) < 0) {
                System.out.println("Too many pencils were taken");
            }
            takeStr = sc.nextLine();
        }
        return Integer.parseInt(takeStr);
    }
    public static int askForPencils(){
        Scanner sc = new Scanner(System.in);
        System.out.println("How many pencils would you like to use:");
        String pencilsStr = sc.nextLine();
        while (!isNumeric(pencilsStr) || pencilsStr.equals("0") || pencilsStr == null || pencilsStr.isBlank() || (pencilsStr.length() == 0) || pencilsStr.equals("")) {
            if (!isNumeric(pencilsStr) || pencilsStr == null || pencilsStr.length() == 0 || pencilsStr.equals("") || pencilsStr.isBlank()) {
                System.out.println("The number of pencils should be numeric");
            } else if (Integer.parseInt(pencilsStr) <= 0){
                System.out.println("The number of pencils should be positive");
            }
            pencilsStr = sc.nextLine();
        }
        return Integer.parseInt(pencilsStr);
    }
    public static void displayPencils(int total){
        if(total <= 0){
            return;
        }
        for(int i = 0; i < total; i++){
            System.out.print("|");
        }
        System.out.println();

    }
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
    public static String getUserName(){
        Scanner sc = new Scanner(System.in);
        String name;
        System.out.println("Who will be the first (John, Jack):");
        name = sc.nextLine();
        while(!name.equals("John") && !name.equals("Jack")){
            System.out.println("Choose between 'John' and 'Jack'");
            name = sc.nextLine();
        }
        return name;
    }
}
