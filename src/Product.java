import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Custom exception class
class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

// Inventory class to manage products
class Inventory {
    private Map<String, String> products;

    public Inventory() {
        products = new HashMap<>();
        products.put("001", "Laptop");
        products.put("002", "Smartphone");
    }

    // Method to find a product, throws ProductNotFoundException if not found
    public String findProduct(String productId) throws ProductNotFoundException {
        if (!products.containsKey(productId)) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
        }
        return products.get(productId);
    }
}

// Main class to demonstrate exception handling
public class Product {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID to search: ");
        String productId = scanner.nextLine();

        try {
            // Attempt to find a product by its ID
            String product = inventory.findProduct(productId);
            System.out.println("Product found: " + product);
        } catch (ProductNotFoundException e) {
            // Handle the custom exception if the product is not found
            System.out.println(e.getMessage());
        } finally {
            // This block executes regardless of whether an exception is thrown or not
            System.out.println("Search attempt completed.");
        }

        scanner.close();
    }
}
