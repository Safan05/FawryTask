
import java.time.LocalDate;

interface ShippableProductInterface {
    double getShippingFees();
    double getWeight();
}

// Implementation of a basic product
abstract class Product {
    private int quantity;
    private String name;
    private double price;

    public Product(int quantity, String name, double price) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public void decreaseQuantity(int q) {
        if (quantity < q) {
            throw new RuntimeException("Not enough quantity available for " + name);
        }
        quantity -= q;
    }
    public boolean isAvailable(int q) {
        return quantity >= q;
    }
}

// Implementation of shippable product with weight
class ShippableProduct extends Product implements ShippableProductInterface {
    private double weight;
    private double ShippingFees;

    public ShippableProduct(int quantity, String name, double price, double weight,double fees) {
        super(quantity, name, price);
        this.weight = weight;
        this.ShippingFees = fees;
    }

    @Override
    public double getWeight() {
        return weight;
    }
    @Override
    public double getShippingFees(){
        return ShippingFees;
    }
}

// Implementation of expirable product with expiration date
class ExpirableProduct extends Product {
    private LocalDate expirationDate;

    public ExpirableProduct(int quantity, String name, double price, LocalDate expirationDate) {
        super(quantity, name, price);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    public boolean isAvailable(int q) {
        // Assuming a simple check for availability based on expiration date
        return super.isAvailable(q) && !LocalDate.now().isAfter(expirationDate);
    }
}

// Implementation of a expirable and shippable product
class ExpirableShippableProduct extends ExpirableProduct implements ShippableProductInterface {
    private double weight;
    private double ShippingFees;

    public ExpirableShippableProduct(int quantity, String name, double price, LocalDate expirationDate, double weight,double fees) {
        super(quantity, name, price, expirationDate);
        this.weight = weight;
        this.ShippingFees = fees;
    }

    @Override
    public double getWeight() {
        return weight;
    }
        @Override
    public double getShippingFees(){
        return ShippingFees;
    }
}