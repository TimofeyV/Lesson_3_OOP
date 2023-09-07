public interface Game {
    void start(Integer sizeWord, Integer maxTry);

    Answer inputValue(String value);

    GameStatus getGameStatus();

    void addLog(String log);
    String getGameLog();
    public void setMaxTry(Integer maxTry);
    void addLogFirst(String log);
    void exit();
}