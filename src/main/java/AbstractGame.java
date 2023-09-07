import java.util.List;
import java.util.Random;

public abstract class AbstractGame implements Game {

    Integer sizeWord;
    Integer maxTry;
    String word;
    GameStatus gameStatus = GameStatus.INIT;
    String gameLog = "";

    public void setMaxTry(Integer maxTry) {
        this.maxTry = maxTry;
    }

    /**
     * @return
     * @apiNote метод предзаполняет слова компьютера
     */
    public String generateWord() {
        List<String> alphavit = generateCharList();
        Random r = new Random();
        String result = "";
        for (int i = 0; i < sizeWord; i++) {
            int index = r.nextInt(alphavit.size());
            result = result.concat(alphavit.get(index));
            alphavit.remove(index);
        }
        return result;
    }

    abstract List<String> generateCharList();


    @Override
    public void start(Integer sizeWord, Integer maxTry) {

        this.sizeWord = sizeWord;
        this.maxTry = maxTry;
        this.word = generateWord();
        gameStatus = GameStatus.START;
        System.out.println("Game start");
        addLog("Game start");
        System.out.println("подсказка: " + word);
    }

    public String getGameLog() {
        return gameLog;
    }

    @Override
    public Answer inputValue(String value) {
        maxTry--;
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == value.charAt(i)) {
                bulls++;
                cows++;
            } else {
                for (int j = 0; j < word.length(); j++) {
                    if (word.charAt(i) == value.charAt(j)) {
                        cows++;
                    }
                }
            }
        }
        if (word.length() == bulls) {
            System.out.println("You won");
            gameStatus = GameStatus.WINNER;
        }
        if (maxTry == 0 && !gameStatus.equals(GameStatus.WINNER)) {
            System.out.println("You've lost");
            gameStatus = GameStatus.LOSE;
        }

        return new Answer(value, bulls, cows);
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public void addLog(String log) {
        this.gameLog = this.gameLog + log + "\n";
    }

    @Override
    public void addLogFirst(String log) {
        this.gameLog = log + this.gameLog + "\n";
    }
    @Override
    public void exit() {
        gameStatus = GameStatus.LOSE;
    }

}