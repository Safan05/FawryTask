import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Product Cheese = new ExpirableShippableProduct(5,"Cheese", 250, LocalDate.now().plusDays(10),20 ,10);
        Product ScratchCard = new Product(2,"ScratchCard",20){};
        Product Tv = new ShippableProduct(1,"Tv", 120, 2, 20);
        Customer Abdallah = new Customer(500);
        Abdallah.getCart().addToCart(Cheese, 3);
        Abdallah.getCart().addToCart(ScratchCard, 1);
        Abdallah.getCart().addToCart(Tv, 1);
        Abdallah.checkout();
    }
    
}
