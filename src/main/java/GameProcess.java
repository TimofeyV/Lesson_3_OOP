import java.util.Scanner;

public class GameProcess {

    public void gameRun(){
        Game game = choiseOfGames();
        chooseMainOption(game);
        do {
            chooseGameOptions(game);
        }
        while (!game.getGameStatus().equals(GameStatus.WINNER)
                &&!game.getGameStatus().equals(GameStatus.LOSE));
    }

    private void chooseGameOptions(Game game) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chose game option:\n" +
                "1 - Input your answer\n" +
                "2 - Show your answers\n" +
                "3 - Restart game\n" +
                "4 - Restart game with new word\n" +
                "5 - Show game status\n"+
                "6 - End game");
        int choise = scanner.nextInt();
        if(choise == 1){
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Input word");
            String scannerWord = scanner1.nextLine();
            Answer answer = game.inputValue(scannerWord);
            System.out.println("bulls = " + answer.getBull() + " cows = " + answer.getCow());
            game.addLog(answer.toString());
        }
        else if(choise == 2){
            System.out.println(game.getGameLog());
            chooseGameOptions(game);
        }
        else if(choise == 3){
            game.addLog("User take option restart");
            System.out.println("Input max try count");
            int maxTry = scanner.nextInt();
            game.setMaxTry(maxTry);
            chooseGameOptions(game);
        }
        else if(choise == 4){
            game.addLog("User restarted all program");
            String buf = game.getGameLog();
            game = choiseOfGames();
            game.addLogFirst(buf);
            chooseMainOption(game);
            chooseGameOptions(game);
        }
        else if(choise == 5){
            System.out.println("Game Status - " + game.getGameStatus());
        }
        else if(choise == 6){
            game.exit();
        }

    }

    public Game choiseOfGames() {
        System.out.println("Chose type of Game:\n" +
                "1 - game with numbers.\n" +
                "2 - game with RussianChars.\n" +
                "3 - game with EnglishChars.");
        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();
        if (choise == 1) {
            Game game = new GameNumber();
            System.out.println("Your choise is a game with numbers");
            game.addLog("You chose game with numbers");
            return game;
        }
        else if (choise == 2) {
            Game game = new RuGame();
            System.out.println("Your choise is a game with Ru Chars");
            game.addLog("You chose game with Ru chars");
            return game;
        }
        else if (choise == 3) {
            Game game = new EngGame();
            System.out.println("Your choise is a game with Eng chars");
            game.addLog("You chose game with Ru chars");
            return game;
        } else {
            System.out.println("Wrong choise");
            return choiseOfGames();
        }
    }
    public Game choiseOfGames(Game game){
        return choiseOfGames();
    }
    public void chooseMainOption(Game game){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose what are you want:\n" +
                "1 - Start game\n" +
                "2 - Show your answer\n");
        int choise = scanner.nextInt();
        if (choise == 1){
            System.out.println("Input word's length");
            int wordLength = scanner.nextInt();
            System.out.println("Input max try count");
            int maxTry = scanner.nextInt();
            game.addLog("wordLength = " + wordLength + " ,maxTry = " + maxTry);
            game.start(wordLength,maxTry);
        }
        else if(choise == 2){
            System.out.println(game.getGameLog());
            chooseMainOption(game);
        }


    }
}