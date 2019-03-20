package modelLayer;

import java.util.*;

import uiLayer.PrintReceipt;

public class OrderLine {
    // instance variables

    private LinkedList<Product> products;
    private OrderContainer odrCon;
    private ProductContainer prodCon;
    private String output;
    private PrintReceipt print;

    public OrderLine() {
        // Initialize instance variables
        products = new LinkedList<Product>();
        odrCon = OrderContainer.getInstance();
        prodCon = ProductContainer.getInstance();
        output = new String();
    }

    //Get method

    //Set methods
    public void addProduct(int p, int a){
        for(int i=1;i<=a;i++){
            products.add(prodCon.getProduct(p));
        }        
    }

    public void addProduct(int p){
        products.add(prodCon.getProduct(p));
    }    

    //Other methods
    public void finishOrder(int id, int disc){
        Order o = odrCon.getOrder(id);
        double total = 0;
        if(disc > 20)
            disc = 20;
        output = "## KVITTERING ##\n";
        for(Product p : products){
            o.addProduct(p);
            output = output + (p.getName() + ": " + p.getPrice() + " kr\n" );
            total = total + p.getPrice();
        }   

        output = output + ("\nTotal: " + (double)total+ " kr\n" );
        output = output + ("\nRabat: " + (double)disc + "%\n");
        total = total * ((100d-(double)disc)/100d);
        output = output + ("Total inkl. rabat: " + (double)total+ " kr\n");
        output = output + ("###########\n");
        print = new PrintReceipt(output);
        print.setSize(450, 525);
        print.setVisible(true);        
        products.clear();
    }
    public void finishOrder(int id){
        Order o = odrCon.getOrder(id);
        double total = 0;
        output = "## KVITTERING ##\n";
        for(Product p : products){
            o.addProduct(p);
            output = output + (p.getName() + ": " + p.getPrice() + " kr\n" );
            total = total + p.getPrice();
        }   
        output = output + ("\nTotal: " + total) + " kr\n";
        output = output + ("###########\n");
        print = new PrintReceipt(output);
        print.setVisible(true);
        print.setSize(450, 525);
        products.clear();
    }

    public void finishOrder(){
        double total = 0;
        output = "## KVITTERING ##\n";
        for(Product p : products){
        	output = output + (p.getName()+ ": " + p.getPrice() + " kr\n");
            total = total + p.getPrice();
        }    
        output = output + ("\nTotal: " + total + " kr\n");
        output = output + ("###########\n");
        print = new PrintReceipt(output);
        print.setSize(450, 525);
        print.setVisible(true);
        products.clear();

    }
    
    public int size(){
        return products.size();
    }
    
}