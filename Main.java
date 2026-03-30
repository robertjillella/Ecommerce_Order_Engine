package com.main;



import java.util.Scanner;

import com.service.CartService;
import com.service.OrderService;
import com.service.PaymentService;
import com.service.ProductService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProductService ps = new ProductService();
        CartService cs = new CartService(ps);
        PaymentService pay = new PaymentService();
        OrderService os = new OrderService(cs, ps, pay);

        while (true) {
            System.out.println("1.Add Product 2.View 3.AddCart 4.ViewCart 5.Order 6.ViewOrders 0.Exit");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> ps.addProduct(sc.next(), sc.next(), sc.nextDouble(), sc.nextInt());
                case 2 -> ps.viewProducts();
                case 3 -> cs.addItem(sc.next(), sc.next(), sc.nextInt());
                case 4 -> cs.viewCart(sc.next());
                case 5 -> os.placeOrder(sc.next());
                case 6 -> os.viewOrders();
                case 0 -> System.exit(0);
            }
        }
    }
}