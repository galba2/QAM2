package sample;

import java.sql.SQLException;

public class MainOnly {

    public static void main(String[] args) {

        try {
            Main.main(args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}

