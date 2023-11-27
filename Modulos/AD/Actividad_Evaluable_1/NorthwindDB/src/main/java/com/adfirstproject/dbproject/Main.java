package com.adfirstproject.dbproject;

import com.adfirstproject.models.EmployeesInfoContainer;
import com.adfirstproject.models.OrderInfoContainer;
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
                addNewProduct();
                break;
            case 2:
                System.out.println("\nAdd new employee.\n");
                addNewEmployee();
                break;
            case 3:
                System.out.println("\nCreate order.\n");
                showAllProducts();
                createOrder();
                break;
            case 4:
                System.out.println("\nPopulate product DB.\n");
                populateProductDB();
                break;
            case 5:
                System.out.println("\nShow all products.\n");
                showAllProducts();
                break;
            case 6:
                System.out.println("\nShow all employees.\n");
                showAllEmployees();
                break;
            case 7:
                System.out.println("\nShow all orders.\n");
                showAllOrders();
                break;
            case 8:
                System.out.println("\nShow products by price.\n");
                showProductsByPrice();
                break;
            case 9:
                System.out.println("\nCheck for new favourite products.\n");
                addNewFavProduct();
                break;
            case 0:
                System.out.println("Exiting program...");
                scanner.close();
                break;
            default:
                System.out.println("\nThe input does not match any option in the menu. Please, choose a correct one.");
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
        System.out.println("\n\nSelect one of the options below:\n" +
                "1.- Add new product.\n" +
                "2.- Add new employee.\n" +
                "3.- Create new order.\n" +
                "4.- Populate product DB.\n" +
                "5.- Show all products.\n" +
                "6.- Show all employees.\n" +
                "7.- Show all orders.\n" +
                "8.- Show products by price.\n" +
                "9.- Check for new favourite products.\n" +
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

    public void addNewProduct() {
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
    }

    public void addNewEmployee() {
        System.out.print("Select an employee name: ");
        String employeeName = userStringInput();
        System.out.print("Select an employee surname: ");
        String employeeSurname = userStringInput();
        System.out.print("Select an employee email: ");
        String employeeMail = userStringInput();
        EmployeesInfoContainer newEmployee = new EmployeesInfoContainer(employeeName, employeeSurname, employeeMail);
        repository.addNewEmployee(newEmployee);
    }

    public void createOrder() {
        System.out.print("Select a product ID from the list above to add to your order: ");
        int productIdSelected = userIntegerInput();
        ProductInfoContainer productRetrieved = repository.getSingleProduct(productIdSelected);
        System.out.print(String.format("How many %s do you want to order: ", productRetrieved.getName()));
        int productsOrdered = userIntegerInput();
        OrderInfoContainer order = new OrderInfoContainer(
                productRetrieved.getId(),
                productRetrieved.getDescription(),
                productsOrdered * productRetrieved.getPrice());
        repository.addNewOrder(order);
    }

    public void populateProductDB() {
        List<ProductInfoContainer> productInfoContainerList = retrievedJsonToObject.productListGenerator(retrievedJsonToObject.parseJsonToStringBuffer());
        repository.populateTableProduct(productInfoContainerList);
    }

    public void showAllProducts() {
        List<ProductInfoContainer> productList = repository.getAllProducts();
        printProductList(productList);

    }

    public void showAllEmployees() {
        List<EmployeesInfoContainer> employeesList = repository.getAllEmployees();
        for (EmployeesInfoContainer employee : employeesList) {
            System.out.println(String.format("%d.- %s %s\n\t\tEmail: %s",
                    employee.getId(),
                    employee.getName(),
                    employee.getSurname(),
                    employee.getEmail()));
        }
    }

    public void showAllOrders(){
        List<OrderInfoContainer> orderList = repository.getAllOrders();
        for (OrderInfoContainer order : orderList) {
            System.out.println(String.format("%d.- Product no.: %d - %s\n\t\tTotal Cost: %s €",
                    order.getId(),
                    order.getId_product(),
                    order.getDescription(),
                    order.getTotalCost()));
        }
    }

    public void showProductsByPrice(){
        System.out.print("Please, choose a maximum price to retrieve the products (600€ by default): ");
        double maxProductPrice = userDoubleInput();
        if(maxProductPrice == 0){
            maxProductPrice = 600;
        }
        List<ProductInfoContainer> productList = repository.getAllProducts(maxProductPrice);
        printProductList(productList);
    }

    public void addNewFavProduct(){
        System.out.print("Please, choose a max price to add products to favourite list (1000€ or greater by default): ");
        double minProductPrice = userDoubleInput();
        if(minProductPrice == 0){
            minProductPrice = 1000;
        }
        repository.addProductToFavList(minProductPrice);
    }

    private void printProductList(List<ProductInfoContainer> productList) {
        for (ProductInfoContainer product : productList) {
            System.out.println(String.format("%d.- %s \n\t\tDescription: %s\n\t\tStock: %d\n\t\tPrice: %.2f €",
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getQuantity(),
                    product.getPrice()));
        }
    }

}
