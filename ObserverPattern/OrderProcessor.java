package ObserverPattern;
import java.util.*;
import DataModels.Order;
/**
 *  Subject (Publisher) ที่คอยแจ้งข่าวสาร
 */
public class OrderProcessor {
    ArrayList<OrderObserver> channels = new ArrayList<>();

    public void register(OrderObserver observer){
        channels.add(observer);
    }
    void unregister(OrderObserver observer){
        channels.remove(observer);
    }
    void notifyObserver(Order order){
        for (OrderObserver o : channels) {
            o.update(order); 
        }
    }
    public void processOrder(Order order){
        System.out.println("\nProcessing order"+ order.orderId()+"...");
        //...ตรรกะการประมวลผลคำสั่งอื่นๆ ...
        System.out.println("Order process successfully.");
        // แจ้งเตือนผู้ติดตามทั้งหมด
        notifyObserver(order);
    }
}
