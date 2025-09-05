package FactoryMethodPattern;

public class StandardShipment implements Shipment{

    @Override
    public String getInfo() {
        return "Standard Delivery";
        
    }

    @Override
    public double getCost() {
        return 50.0;
        
    }
    
}
