import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {

    // Define the Book class
    static class Book {
        private String title;
        private String author;
        private String isbn;
        private double price;

        public Book(String title, String author, String isbn, double price) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.price = price;
        }

        // Getters and Setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Price: $" + price;
        }
    }

    // Define the Library class
    static class Library {
        private ArrayList<Book> books;

        public Library() {
            books = new ArrayList<>();
        }

        // Add a new book
        public void addBook(Book book) {
            books.add(book);
        }

        // Remove a book by ISBN
        public void removeBook(String isbn) {
            boolean removed = books.removeIf(book -> book.getIsbn().equals(isbn));
            if (removed) {
                System.out.println("Book with ISBN " + isbn + " has been removed.");
            } else {
                System.out.println("No book found with ISBN " + isbn);
            }
        }

        // Display all books
        public void displayBooks() {
            if (books.isEmpty()) {
                System.out.println("No books in the library.");
            } else {
                for (Book book : books) {
                    System.out.println(book);
                }
            }
        }
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Adding some books initially
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 10.99));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084", 7.99));

        while (true) {
            System.out.println("\nLibrary System Menu:");
            System.out.println("1. Add a Book");
            System.out.println("2. Remove a Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Add a book
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter book price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline

                    Book newBook = new Book(title, author, isbn, price);
                    library.addBook(newBook);
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    // Remove a book
                    System.out.print("Enter ISBN of the book to remove: ");
                    String isbnToRemove = scanner.nextLine();
                    library.removeBook(isbnToRemove);
                    break;

                case 3:
                    // Display all books
                    System.out.println("Books in the library:");
                    library.displayBooks();
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}
