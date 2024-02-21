
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Database for Coffee drinks");
                System.out.println("Choose an option:");
                System.out.println("1. View all available coffee drinks:");
                System.out.println("2. Add coffee drink");
                System.out.println("3. Update coffee drink");
                System.out.println("4. Delete coffee drink");
                System.out.println("5. Exit");
                System.out.print("Type number operation: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        viewAllCoffeeDrinks(connection);
                        break;
                    case 2:
                        addCoffeeDrinks(connection, scanner);
                        break;
                    case 3:
                        updateCoffeeDrinks(connection, scanner);
                        break;
                    case 4:
                        deleteCoffeeDrink(connection, scanner);
                        break;
                    case 5:
                        connection.close();
                        System.out.println("Work is done");
                        return;
                    default:
                        System.out.println("Non existent number operation was chosen. Choose again.");
                        break;                }
            }        } catch (SQLException e) {
            e.printStackTrace();
        }    }
    public static void viewAllCoffeeDrinks(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM COFFEE_DRINKS");
        ResultSet r = statement.executeQuery();
        while (r.next()) {
            System.out.println("Id: " + r.getInt("id"));
            System.out.println("Coffee type: " + r.getString("coffeeType"));
            System.out.println("Price: " + r.getInt("price"));
            System.out.println("Available sizes: " + r.getString("size"));
            System.out.println("Ingredients " + r.getString("ingredients"));
            System.out.println("");
        }    }
    public static void addCoffeeDrinks(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Type coffee drink name: ");
        String coffeeType = scanner.nextLine();
        System.out.print("Type coffee price: ");
        int coffeePrice = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Type all available sizes: ");
        String coffeeSizes = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Type all ingredients: ");
        String coffeeIngredients = scanner.nextLine();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO COFFEE_DRINKS (coffeeType, price, size, ingredients) VALUES (?, ?, ?, ?)");
        statement.setString(1, coffeeType);
        statement.setInt(2, coffeePrice);
        statement.setString(3, coffeeSizes);
        statement.setString(4, coffeeIngredients);
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Coffee drink was added");
        } else {
            System.out.println("Error occurred while adding new coffee drink type.");
        }
    }
    public static void updateCoffeeDrinks(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Type new coffee drink type id: ");
        int id = scanner.nextInt();
        System.out.print("Type coffee drink name: ");
        String coffeeType = scanner.nextLine();
        System.out.print("Type coffee price: ");
        int coffeePrice = scanner.nextInt();
        System.out.print("Type all available sizes: ");
        String coffeeSizes = scanner.nextLine();
        String coffeeIngredients = scanner.nextLine();
        System.out.print("Type all ingredients: ");
        PreparedStatement statement = connection.prepareStatement("UPDATE COFFEE_DRINKS SET coffeeType=?, price=?, size=?, ingredients=? WHERE id=?");
        statement.setString(1, coffeeType);
        statement.setInt(2, coffeePrice);
        statement.setString(3, coffeeSizes);
        statement.setString(4, coffeeIngredients);
        statement.setInt(5, id);
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Coffee drink type was updated.");
        } else {
            System.out.println("Error occurred while updating coffee drink type.");
        }
    }
    public static void deleteCoffeeDrink(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Type coffee drink type id:  ");
        int id = scanner.nextInt();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM COFFEE_DRINKS WHERE id=?");
        statement.setInt(1, id);
        int rowsAffected = statement.executeUpdate();
        System.out.println("Coffee drink type was successfully deleted.");
        if (rowsAffected > 0) {
            System.out.println("Coffee drink type was successfully deleted.");
        } else {
            System.out.println("Error occurred while deleting coffee drink type .");
        }
    }
}