package com.hsbc.ecommerceapp.storage;

import com.hsbc.ecommerceapp.exceptions.SubscriptionNotFoundException;
import com.hsbc.ecommerceapp.model.Subscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionStorage {
    public void addSubscription(Subscription subscription) {
        String sql = "INSERT INTO subscriptions (subscriptionId, productId, userId, startDate, endDate, isActive) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, subscription.getSubscriptionId());
            preparedStatement.setString(2, subscription.getProductId());
            preparedStatement.setString(3, subscription.getCustomerId());
            preparedStatement.setDate(4, Date.valueOf(subscription.getStartDate()));
            preparedStatement.setDate(5, Date.valueOf(subscription.getEndDate()));
            preparedStatement.setBoolean(6, subscription.isActive());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateSubscription(Subscription subscription) {
        String sql = "UPDATE subscriptions SET productId = ?, customerId = ?, startDate = ?, endDate = ?, isActive = ? WHERE subscriptionId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, subscription.getProductId());
            preparedStatement.setString(2, subscription.getCustomerId());
            preparedStatement.setDate(3, Date.valueOf(subscription.getStartDate()));
            preparedStatement.setDate(4, Date.valueOf(subscription.getEndDate()));
            preparedStatement.setBoolean(5, subscription.isActive());
            preparedStatement.setString(6, subscription.getSubscriptionId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0)
                throw new SubscriptionNotFoundException("Subscription not found!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cancelSubscription(String subscriptionId) {
//        Subscription subscription = subscriptionMap.get(subscriptionId);
//        if(subscription != null)
//            subscription.setActive(false);

        String sql = "DELETE FROM subscriptions WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, subscriptionId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SubscriptionNotFoundException("Subscription not found!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Subscription getSubscriptionById(String subscriptionId) {
        String sql = "SELECT * FROM subscriptions WHERE subscriptionId = ?";
        Subscription subscription = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, subscriptionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                subscription = new Subscription(
                        resultSet.getString("subscriptionId"),
                        resultSet.getString("productId"),
                        resultSet.getString("customerId"),
                        resultSet.getString("type"),
                        resultSet.getDate("startDate").toLocalDate(),
                        resultSet.getDate("endDate").toLocalDate(),
                        resultSet.getBoolean("isActive")
                );
            } else {
                throw new SubscriptionNotFoundException("Subscription not found!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return subscription;
    }

    public List<Subscription> getAllSubscriptions() {
        String sql = "SELECT * FROM subscriptions";
        List<Subscription> subscriptions = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Subscription subscription = new Subscription(
                        resultSet.getString("subscriptionId"),
                        resultSet.getString("productId"),
                        resultSet.getString("customerId"),
                        resultSet.getString("type"),
                        resultSet.getDate("startDate").toLocalDate(),
                        resultSet.getDate("endDate").toLocalDate(),
                        resultSet.getBoolean("isActive")
                );
                subscriptions.add(subscription);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return subscriptions;
    }
}
