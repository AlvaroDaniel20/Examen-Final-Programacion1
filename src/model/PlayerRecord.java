package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlayerRecord {
    private String playerName;
    private int pointsEarned;
    private LocalDateTime gameDateTime;

    public PlayerRecord(String playerName, int pointsEarned) {
        this.playerName = playerName;
        this.pointsEarned = pointsEarned;
        this.gameDateTime = LocalDateTime.now();
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public LocalDateTime getGameDateTime() {
        return gameDateTime;
    }

    public String toCSVString() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return String.format("%s,%d,%s", 
            playerName, 
            pointsEarned, 
            gameDateTime.format(formatter)
        );
    }
}
