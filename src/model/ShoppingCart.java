package model;

import services.MyFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private int cartId;
    private List<Child> childList;
    private double vat;
    private double subtotal;
    private double total;

    public ShoppingCart() {
        int id = 0;
        try{
            ArrayList<String[]> cs = MyFile.ReadFile("orders.csv");
            id = cs.size();
        }catch (IOException e){

        }



        cartId = id;
        this.childList = new ArrayList<Child>();
        vat = 0;
        total = 0;
        subtotal = 0;
    }

    public ShoppingCart(int cartId, List<Child> childList, double vat, double subtotal, double total) {
        this.cartId = cartId;
        this.childList = childList;
        this.vat = vat;
        this.subtotal = subtotal;
        this.total = total;
    }

    public void addChild(Child child) {
        this.childList.add(child);
        subtotal = calculateSubtotal();
        total =  calculateTotal();

    }

    public void removeChild(Child child) {
        this.childList.remove(child);
        subtotal = calculateSubtotal();
        total =  calculateTotal();

    }

    public void removeChild(int index) {
        this.childList.remove(index);
        subtotal = calculateSubtotal();
        total =  calculateTotal();

    }

    public void clearCart() {
        this.childList.clear();
        subtotal = calculateSubtotal();
        total =  calculateTotal();

    }

    public int getChildCount() {
        return childList.size();
    }

    public double calculateTotal() {
        System.out.println(this.calculateSubtotal());
        System.out.println((1+vat));
        return this.calculateSubtotal() * (1 + vat);
    }

    public double calculateSubtotal() {
        double sum = 0.0;
        for (Child child : this.childList) {
            sum += child.getPrice();
        }
        return sum;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartId=" + cartId +
                ", childList=" + childList +
                ", vat=" + vat +
                ", subtotal=" + subtotal +
                ", total=" + total +
                '}';
    }
}
