package com.adfirstproject.dbproject;

import com.adfirstproject.models.EmployeesInfoContainer;
import com.adfirstproject.models.ProductInfoContainer;
import com.adfirstproject.repository.WarehouseInfoContainerDBDataSource;
import com.adfirstproject.repository.WarehouseRepository;
import com.adfirstproject.util.JsonURLRetrieve;
import com.adfirstproject.util.MessagesConfigurator;
import com.adfirstproject.util.RetrievedJsonToObject;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    JsonURLRetrieve jsonURLRetrieve = new JsonURLRetrieve();
    RetrievedJsonToObject retrievedJsonToObject = new RetrievedJsonToObject(jsonURLRetrieve);
    WarehouseInfoContainerDBDataSource slowDataSource = new WarehouseInfoContainerDBDataSource();
    WarehouseRepository repository = new WarehouseRepository(slowDataSource);
    private Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        Main main = new Main();
        main.runProgram();
    }

    private int userIntegerInput() {
        int input;
        try {
            input = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Incorrect value selected. Only numbers allowed.");
            scanner.nextLine();
            System.out.print("Which is your selection: ");
            input = userIntegerInput();
        }
        return input;
    }

    private void executeOptionMenu(int input) {
        switch (input) {
            case 1:
                System.out.println("\nAdd new product.\n");
                System.out.print("Select a product name: ");
                String productName = userStringInput();
                System.out.print("Select a product description (less than 500 characters): ");
                String productDescription = userStringInput();
                System.out.print("Select a product stock: ");
                int productStock = userIntegerInput();
                System.out.print("Select a product price: ");
                double productPrice = userDoubleInput();
                ProductInfoContainer newProduct = new ProductInfoContainer(productName, productDescription, productStock, productPrice);
                repository.addNewProduct(newProduct);
                break;
            case 2:
                System.out.println("\nAdd new employee.\n");
                System.out.print("Select an employee name: ");
                String employeeName = userStringInput();
                System.out.print("Select an employee surname: ");
                String employeeSurname = userStringInput();
                System.out.print("Select an employee email: ");
                String employeeMail = userStringInput();
                EmployeesInfoContainer newEmployee = new EmployeesInfoContainer(employeeName,employeeSurname,employeeMail);
                repository.addNewEmployee(newEmployee);
                break;
            case 3:
                break;
            case 4:
                System.out.println("\nPopulate product DB.\n");
                List<ProductInfoContainer> productInfoContainerList = retrievedJsonToObject.productListGenerator(retrievedJsonToObject.parseJsonToStringBuffer());
                repository.populateTableProduct(productInfoContainerList);
                break;
            case 5:
                System.out.println("\nShow all products.\n");
                List<ProductInfoContainer> productList = repository.getAllProducts();
                for (ProductInfoContainer product : productList) {
                    System.out.println(String.format("%d.- %s \n\t\tDescription: %s\n\t\tStock: %d\n\t\tPrice: %.2f €",
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getQuantity(),
                            product.getPrice()));
                }
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 0:
                System.out.println("Exiting program...");
                scanner.close();
                break;
            default:
                break;
        }
    }

    private double userDoubleInput() {
        double input;
        try {
            input = scanner.nextDouble();
            if (input < 0) {
                throw new IllegalArgumentException("Negative values are not allowed. Please choose a positive number.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect value selected. Please, choose an integer or decimal number.");
            scanner.nextLine();
            input = userDoubleInput();
        } catch (IllegalArgumentException e) {
            scanner.nextLine();
            input = userDoubleInput();
        }
        return input;
    }

    private String userStringInput() {
        return scanner.nextLine();
    }

    private void showWelcome() {
        System.out.println(MessagesConfigurator.HEADER);
        System.out.println("\nWelcome to Northwind Data Base.\n");
    }

    private void showMenu() {
        System.out.println("Select one of the options below:\n" +
                "1.- Add new product.\n" +
                "2.- Add new employee.\n" +
                "3.- Create new order.\n" +
                "4.- Populate product DB.\n" +
                "5.- Show all products.\n" +
                "6.- Show all employees.\n" +
                "7.- Show all orders.\n" +
                "8.- Show products by price.\n" +
                "9.- Check new favourite products.\n" +
                "0.- Exit program.\n");
        System.out.print("Which is your selection: ");
    }

    private void runProgram() {
        showWelcome();
        int userSelection;
        do {
            showMenu();
            userSelection = userIntegerInput();
            scanner.nextLine();
            executeOptionMenu(userSelection);

        } while (userSelection != 0);
    }


}
