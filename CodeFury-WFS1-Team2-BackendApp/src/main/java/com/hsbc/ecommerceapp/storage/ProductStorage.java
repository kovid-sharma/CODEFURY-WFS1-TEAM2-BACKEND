package com.hsbc.ecommerceapp.storage;

import com.hsbc.ecommerceapp.exceptions.ProductNotFoundException;
import com.hsbc.ecommerceapp.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductStorage {
    String sql = "INSERT INTO products (productId, productName, productDescription, price, isActive) VALUES (?, ?, ?, ?, ?)";


    public void addProduct(Product product) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductDescription());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setBoolean(5, product.isActive());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE products SET productName = ?,, productDescription = ?, price = ?, isActive = ? WHERE productId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(1, product.getProductDescription());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setBoolean(3, product.isActive());
            preparedStatement.setString(4, product.getProductId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0)
                throw new ProductNotFoundException("Product not found!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteProduct(String productId) {
        String sql = "DELETE FROM products WHERE productId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, productId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0)
                throw new ProductNotFoundException("Product not found with ID: " + productId);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Product getProductById(String productId) {
        String sql = "SELECT * FROM products WHERE productId = ?";
        Product product = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = new Product(
                        resultSet.getString("productId"),
                        resultSet.getString("productName"),
                        resultSet.getString("productDescription"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("isActive")
                );
            } else {
                throw new ProductNotFoundException("Product not found!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return product;
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("productId"),
                        resultSet.getString("productName"),
                        resultSet.getString("productDescription"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("isActive")
                );
                products.add(product);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return products;
    }
}
