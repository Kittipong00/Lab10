import java.util.*;
import DataModels.*;
import DataModels.Order;
import DecoratorPattern.*;
import FactoryMethodPattern.*;
import ObserverPattern.*;
import StrategyPattern.*;

public class TestRunner {
    public static void main(String[] args) {
        // ... 1. Setup ...
        Product laptop =  new Product("P001", "Laptop", 30000.0);
        Product mouse = new Product("P002", "Mouse", 800.0)
        Order myOrder = new Order("ORD-001", List.of(laptop,mouse), "customer@email.com");

        OrderCalculator calculator = new OrderCalculator();
        ShipmentFactory shipmentFactory = new ShipmentFactory();

        OrderProcessor orderProcessor = new OrderProcessor();
        InventoryService inventory = new InventoryService();
        EmailService emailer = new EmailService();
        orderProcessor.register(inventory);
        orderProcessor.register(emailer);

        System.out.println("\n--- 2. Testing Strategy Pattern (Discounts) ---");
        double originalPrice = myOrder.getTotalPrice();
        System.out.println("Original Price: "+ originalPrice);

        DiscountStrategy tenPercentOff = new PercentageDiscount(10);
        double priceAfterPercentage = calculator.calculateFinalPrice(myOrder, tenPercentOff);
        System.out.println("Price with 10% dicount: "+ priceAfterPercentage);

        DiscountStrategy fiveHundredOff = new FixedDiscount(500);
        double priceAfterFixed = calculator.calculateFinalPrice(myOrder,fiveHundredOff);
        System.out.println("Price with 10% dicount: "+ priceAfterFixed);

        System.out.println("\n--- 3. Testing Factory and Decorator Pattern (Shipment) ---");
        //สร้างการจัดส่งแบบมาตรฐาน
        Shipment standardShipment = shipmentFactory.createShipment("STANDARD");
        System.out.println("Base Shipment: " + standardShipment.getInfo() + ", Cost: " + standardShipment.getCost());

        // "ห่อ" ด้วยบริการห่อของขวัญ
        Shipment giftWrapped = new GiftWrapDecorator(standardShipment);
        System.out.println("Deprecated: " + giftWrapped.getInfo() + ", Cost: " + giftWrapped.getCost());

        // "ห่อ" ทับด้วยบริการประกันสินค้า
        Shipment fullyLoaded  = new InsuranceDecorator(giftWrapped, myOrder);
        System.out.println("Fully Deprecated: " + fullyLoaded.getInfo() + ", Cost: " + fullyLoaded.getCost());

        System.out.println("\n--- 4. Printing Final Summary ---");
        double finalPrice = priceAfterPercentage; // สมมติว่าใช้ส่วนลด 10%
        double totalCost = finalPrice + fullyLoaded.getCost();
        System.out.println("Final price after discount: " + finalPrice);
        System.out.println("Final shipment cost: " + fullyLoaded.getCost());
        System.out.println("TOTAL TO PAY: " + totalCost);
        
        // --- 5. Testing Observer Pattern (Processing Order) ---
        orderProcessor.processOrder(myOrder);

    }
}

