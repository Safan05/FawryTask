import java.util.ArrayList;
import java.util.List;

class Item {
    public int quantity;
    public Product product;
    
    public Item(Product product,int quantity){
        this.quantity = quantity;
        this.product = product;
    }
    public void IncrementQuantity(){
        this.quantity+=1;
    }
    public double getPrice(){
        return this.quantity*this.product.getPrice();
    }
}
public class Cart {
    private List<Item> items = new ArrayList<>();
    double totalPrice=0;
    double totalShipping=0;
    double totalWeight=0;
    // check empty cart
    public boolean isEmpty() {
        return items.isEmpty();
    }
    // clear cart
    public void clear() {
        items.clear();
        totalPrice = 0;
        totalShipping = 0;
        totalWeight = 0;
    }
    // adding item to cart
    public void addToCart(Product product, int quantity) {
        if (!product.isAvailable(quantity)) {
            throw new RuntimeException("Requested quantity of product "+product.getName()+" is not available.");
        }
        product.decreaseQuantity(quantity);
        items.add(new Item(product, quantity));
    }
    // decrement item from cart
    public void decrementProduct(Product product){
        for(int i=0;i<items.size();i++){
            Item item = items.get(i);
            if(item.product==product){
                item.quantity-=1;
                if(item.quantity==0)
                    items.remove(i);
                return;
            }
        }
        throw new RuntimeException("The product "+product.getName()+ " is not in your cart");
    }
    public double totalCost(){
        calcPrices();
        return totalPrice+totalShipping;
    }
    // check products availability and expiration
    public boolean checkCartAvailability() {
        for (Item item : items) {
            if (!item.product.isAvailable(item.quantity)) {
                return false; 
            }
            if (item.product instanceof ExpirableProduct) {
                ExpirableProduct expirable = (ExpirableProduct) item.product;
                if (!expirable.isAvailable(item.quantity)) {
                    return false; 
                }
            }
        }
        return true;
    }

    // to calculate prices
    private void calcPrices(){
        totalPrice= 0;
        totalShipping =0;
        for(int i=0;i<items.size();i++){
            Item item = items.get(i);
            totalPrice+= item.getPrice();
             if(item.product instanceof ShippableProductInterface){
                ShippableProductInterface shippable = (ShippableProductInterface) item.product;
                totalShipping+=shippable.getShippingFees();
            }
        }
    }
    private void printPrices(){
        totalPrice= 0;
        totalShipping =0;
        System.out.println("** Checkout receipt **");
        //double totalPrice=0;
        for(int i=0;i<items.size();i++){
            Item item = items.get(i);
            System.out.println(item.quantity + "x "+item.product.getName()+"     "+item.getPrice());
        }
        calcPrices();
        System.out.println("-------------------");
        System.out.println("Subtotal      "+totalPrice);
        System.out.println("Shipping      "+totalShipping);
        System.out.println("Total         "+totalCost());
    }
    private void printShipment(){
        totalWeight = 0;
        System.out.println("** Shipment notice **");
        for(int i=0;i<items.size();i++){
            Item item = items.get(i);
            if(item.product instanceof ShippableProductInterface){
                ShippableProductInterface shippable = (ShippableProductInterface) item.product;
                System.out.println(item.quantity + "x "+item.product.getName()+"         "+shippable.getWeight()+" kg");
                totalWeight +=shippable.getWeight();
            }
        }
        System.out.println("Total Package Weight :" + totalWeight);
    }
    public void printReciept(){
        printShipment();
        printPrices();
    }
}
