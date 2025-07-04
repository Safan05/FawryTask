public class Customer {
   // private String Name;
    private int balance;
    private Cart cart;
    public Customer(int balance){
  //      this.Name=Name;
        this.balance=balance;
        cart=new Cart();
    }
    public Cart getCart(){
        return cart;
    }
    public void checkout(){
        if(cart.isEmpty()){
            throw new RuntimeException("Your cart is empty");
        }
        if(!isBalanceSufficient()){
            throw new RuntimeException("Balance is not sufficient");
        }
        cart.printReciept();
        balance-=cart.totalCost();
        cart.clear();
        System.out.println("Your Availabe balance is: "+ balance);
    }
    private boolean isBalanceSufficient(){
        return this.balance>cart.totalCost();
    }

}
