package com.wp.board.listener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import io.github.cdimascio.dotenv.Dotenv;

import com.wp.board.config.DatabaseInitializer;
import com.wp.board.dao.PostDAO;
import com.wp.board.dao.UserDAO;
import com.wp.board.dao.impl.PostDAOImpl;
import com.wp.board.dao.impl.UserDAOImpl;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            Dotenv dotenv = Dotenv.configure().load();

            String jdbcDriver = dotenv.get("jdbc.driver");
            String jdbcUrl = dotenv.get("jdbc.url");
            String jdbcUsername = dotenv.get("jdbc.username");
            String jdbcPassword = dotenv.get("jdbc.password");

            Class.forName(jdbcDriver);

            Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            sce.getServletContext().setAttribute("connection", connection);

            initializeDatabase(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);

            UserDAO userDAO = new UserDAOImpl(connection);
            PostDAO postDAO = new PostDAOImpl(connection);

            sce.getServletContext().setAttribute("user_dao", userDAO);
            sce.getServletContext().setAttribute("post_dao", postDAO);

            System.out.println("DB Connection established.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize DB connection", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Connection conn = (Connection) sce.getServletContext().getAttribute("connection");
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("DB Connection closed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeDatabase(
            String jdbcDriver,
            String jdbcUrl,
            String jdbcUsername,
            String jdbcPassword
    ) {
        try {
            Class.forName(jdbcDriver);

            try (Connection connection = DriverManager.getConnection(
                    jdbcUrl, jdbcUsername, jdbcPassword)) {

                DatabaseInitializer.executeSqlScript(connection, "schema.sql");
                DatabaseInitializer.executeSqlScript(connection, "data.sql");

                System.out.println("Database initialized successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Database connection failed.", e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("JDBC Driver not found.", e);
        }
    }
}
