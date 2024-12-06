package controller;

import model.PlayerRecord;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameRecordManager {
    private static final String RECORDS_FILE = "records/game_records.csv";
    private static final String CSV_HEADER = "PlayerName,Points,DateTime";

    public void savePlayerRecord(PlayerRecord record) {
        try {
            Path path = Paths.get(RECORDS_FILE);
            if (!Files.exists(path)) {
                Files.createFile(path);
                try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                    writer.write(CSV_HEADER);
                    writer.newLine();
                }
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.write(record.toCSVString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar el registro: " + e.getMessage());
        }
    }

    public List<PlayerRecord> getAllRecords() {
        List<PlayerRecord> records = new ArrayList<>();
        
        try {
            Path path = Paths.get(RECORDS_FILE);
            if (!Files.exists(path)) {
                return records;
            }

            List<String> lines = Files.readAllLines(path);
            
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts.length == 3) {
                    String playerName = parts[0];
                    int points = Integer.parseInt(parts[1]);
                    @SuppressWarnings("unused")
                    LocalDateTime dateTime = LocalDateTime.parse(parts[2]);
                    
                    PlayerRecord record = new PlayerRecord(playerName, points);
                    records.add(record);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer los registros: " + e.getMessage());
        }
        
        return records;
    }

    public List<PlayerRecord> getTopRecords(int limit) {
        List<PlayerRecord> allRecords = getAllRecords();
        
        return allRecords.stream()
            .sorted((r1, r2) -> Integer.compare(r2.getPointsEarned(), r1.getPointsEarned()))
            .limit(limit)
            .toList();
    }
}