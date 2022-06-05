package com.revature.foundations;

import com.revature.foundations.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class app {
    public static void main(String[] args) throws SQLException {
        Connection conn = ConnectionFactory.getInstance().getConnection();
    }
}
