package shipping;

public class InternationalPackage implements Transportable{

    private int weight;
    private boolean isBreakable;
    private String destinationCountry;
    private int distance;
    private final int SHIPPING_COST = 1200;
    private final int COST_PER_KM = 10;

    public InternationalPackage(int weight, boolean isBreakable, String destinationCountry, int distance) {
        this.weight = weight;
        this.isBreakable = isBreakable;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return isBreakable;
    }

    @Override
    public int calculateShippingPrice() {
        int cost = distance * COST_PER_KM;
        return isBreakable() ? SHIPPING_COST * 2 + cost : SHIPPING_COST + cost;
    }

    @Override
    public String getDestinationCountry() {
        return destinationCountry;
    }

    public int getDistance() {
        return distance;
    }
}
