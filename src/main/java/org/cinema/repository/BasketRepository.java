package org.cinema.repository;

import org.cinema.model.Basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketRepository {
    private final Connection CONNECTION;

    public BasketRepository(Connection CONNECTION) {
        this.CONNECTION = CONNECTION;
    }

    public void createTable() throws SQLException{
        String createTable ="CREATE TABLE IF NOT EXISTS Basket (id serial primary key ," +
                " CONSTRAINT username FOREIGN KEY(username) REFERENCES User (username)," +
                " CONSTRAINT idTicket FOREIGN KEY(id) REFERENCES TicketTable(id),filmName varchar(50)," +
                " numberTicket Integer,priceAll Integer)";
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(createTable);
        preparedStatement.execute();
    }
  
    //::::>
    public int importTicket(Basket basket) throws SQLException {
        String importBasket = "INSERT INTO Basket (username,idTicket,filmName,numberTicket,priceAll) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(importBasket);
        preparedStatement.setString(1,basket.getUsername());
        preparedStatement.setInt(2,basket.getIdTicket());
        preparedStatement.setString(3,basket.getFilmName());
        preparedStatement.setInt(4,basket.getNumber());
        preparedStatement.setInt(5,basket.getPriceAll());
        return preparedStatement.executeUpdate();
    }

    

    //::::>
    public void cancelTicket(Integer id) throws SQLException {
        String cancel = "DELETE FROM Baske WHERE idTicke = ? ";
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(cancel);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }

    //::::>
    public void viewMyBasket(String username) throws SQLException {
        String finduser = " SELECT * FROM Basket WHERE username = ? ";
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(finduser);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println("id=" + resultSet.getInt("id") + "  |filmName=" + resultSet.getString("filmName") +
                                   "   |numberTicket=" + resultSet.getInt("numberTicket") + "   |priceAll=" + resultSet.getInt("priceall"));

        System.out.println("That's all :)");
    }







}
