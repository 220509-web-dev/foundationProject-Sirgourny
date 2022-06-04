package com.revature.foundations;

import com.revature.foundations.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class app {
    public static void main(String[] args) throws SQLException {
        Connection conn = ConnectionUtil.getConnection();
    }
}
