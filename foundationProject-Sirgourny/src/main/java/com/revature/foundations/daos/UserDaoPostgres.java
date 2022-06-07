package com.revature.foundations.daos;

import com.revature.foundations.models.User;
import com.revature.foundations.utils.exceptions.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoPostgres implements UserDAO{
    final static String loc = "tryagain4.app_users";
    @Override
    public User createUser(User user) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "insert into "+loc+" values (default, ?, ?, ?, ?, ?);";
            System.out.println("Trying to execute: "+sql); // just for debugging for now
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());

            ps.execute();

            // getting the generated primary key value
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("id");

            user.setUser_id(generatedId); //
            return user;
        } catch (SQLException exception) {
            exception.printStackTrace();
           // System.err.println("An Error occurred! Check credentials for your SQL database.");
         /*   throw new RuntimeException(e);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException();
            */
        }
        return null;


    }
        @Override
        public User getUserById(int id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from "+loc+" where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);  // sets first ? in sql String to the Integer (int) id from input
                ResultSet rs = ps.executeQuery(); // JDBC actually interacts wih the DB

            //Get First Record
            if (rs.next()) {

                // rs contains the mock data record
                //if (rs == null) return null;  // The idea of this is to stop if there's no record returned

                // Now, we have a record from the database
                User user = new User();
                // Creating a Java User object to store the table's data
                user.setUser_id(id);
                user.setFirstname(rs.getString("first_name"));  // username is the column name in the SQL table
                user.setLastname(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.err.println("An Error occurred! Check credentials for your SQL database.");
            throw new RuntimeException();
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String sql = "select * from " + loc + " where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);  // sets first ? in sql String to the Integer (int) id from input
            ResultSet rs = ps.executeQuery(); // JDBC actually interacts wih the DB

            //Get First Record
            if(rs.next()) {
                // rs contains the mock data record
                //if (rs == null) return null;  // The idea of this is to stop if there's no record returned

                // Now, we have a record from the database
                User user = new User();
                // Creating a Java User object to store the table's data
                user.setFirstname(rs.getString("first_name"));  // username is the column name in the SQL table
                user.setLastname(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }



        } catch(SQLException e){
                e.printStackTrace();
                System.err.println("An Error occurred! Check credentials for your SQL database.");
                throw new RuntimeException(e);
            } catch(RuntimeException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch(Throwable t){
                t.printStackTrace();
                throw new RuntimeException();
            }
           return null;
        }



    @Override
    public List<User> getAllUsers() {
        try {Connection conn = ConnectionFactory.getInstance().getConnection();
            String sql = "select * from "+loc+";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); // JDBC actually interacts wih the DB

            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("id"));
                user.setFirstname(rs.getString("first_name"));
                user.setLastname(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            return users;

        } catch (SQLException exception) {
            exception.printStackTrace();
        /*
        catch (SQLException e) {

            e.printStackTrace();
            System.err.println("An Error occurred! Check credentials for your SQL database.");
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException();
            */
        }
        return null;
    }



    @Override
    public User updateUser(User user) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){;
            String sql = "update "+loc+" set first_name = ?, last_name = ? email = ?, username = ?, password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());

            ps.execute();

        return user;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUserById(int id) {
        try(Connection conn = ConnectionFactory.getConnection()){
            String sql = "delete from "+loc+" where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
}
