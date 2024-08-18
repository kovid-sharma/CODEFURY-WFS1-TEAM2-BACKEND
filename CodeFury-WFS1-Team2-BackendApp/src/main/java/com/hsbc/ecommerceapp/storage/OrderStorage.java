package com.hsbc.ecommerceapp.storage;

import com.hsbc.ecommerceapp.exceptions.OrderNotFoundException;
import com.hsbc.ecommerceapp.model.Order;
import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.service.CustomerService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderStorage {
    // method to add new order
    public void addOrder(String customerId, Order order) {
        String sql = "INSERT INTO orders (order_id, customerId, total_amount, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, order.getOrderId());
            preparedStatement.setString(2, customerId);
            preparedStatement.setDouble(3, order.getTotalAmount());
            preparedStatement.setString(4, order.getStatus());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // method to update an order
    public void updateOrder(String customerId, Order order) throws OrderNotFoundException {
        String sql = "UPDATE orders SET user_id = ?, total_amount = ?, status = ? WHERE order_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, customerId);
            preparedStatement.setDouble(2, order.getTotalAmount());
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setString(4, order.getOrderId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new OrderNotFoundException("Order not found!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // method to delete an order
    public void deleteOrder(String orderId) throws OrderNotFoundException {
        String sql = "DELETE FROM orders WHERE order_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, orderId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new OrderNotFoundException("Order not found!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // method to get an order by id
    public Order getOrderById(String orderId) throws OrderNotFoundException {
        String sql = "SELECT * FROM orders WHERE id = ?";
        Order order = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                order = new Order(
                        resultSet.getString("order_id"),
                        resultSet.getString("user_id"),
                        resultSet.getDouble("total_amount"),
                        resultSet.getString("status")
                );
            } else {
                throw new OrderNotFoundException("Order not found with ID: " + orderId);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return order;
    }

    // method to get the order of a customer by id
    public List<Order> getOrderByCustomerId(String customerId) {
        List<Order> orderList = new ArrayList<Order>();
        String sql = "SELECT * FROM orders WHERE user_id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customerId);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    Order order = new Order(
                            resultSet.getString("order_id"),
                            resultSet.getString("user_id"),
                            resultSet.getDouble("total_amount"),
                            resultSet.getString("status")
                    );
                    orderList.add(order);
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return orderList;
    }
}
