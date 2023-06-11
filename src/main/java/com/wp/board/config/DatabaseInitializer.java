package com.wp.board.config;

import java.io.*;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void executeSqlScript(Connection connection, String scriptPath) {

        try (InputStream scriptStream = DatabaseInitializer.class.getClassLoader().getResourceAsStream(scriptPath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(scriptStream));
             Statement statement = connection.createStatement()) {

            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("--") || line.trim().isEmpty()) {
                    continue;
                }

                sql.append(line).append("\n");

                if (line.trim().endsWith(";")) {
                    statement.execute(sql.toString());
                    sql.setLength(0);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading SQL script from classpath: " + scriptPath, e);
        } catch (Exception e) {
            throw new RuntimeException("Error executing SQL script from classpath: " + scriptPath, e);
        }
    }
}
