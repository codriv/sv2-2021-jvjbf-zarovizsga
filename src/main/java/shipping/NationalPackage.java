package shipping;

public class NationalPackage implements Transportable{

    private int weight;
    private boolean isBreakable;
    private final int SHIPPING_COST = 1000;

    public NationalPackage(int weight, boolean isBreakable) {
        this.weight = weight;
        this.isBreakable = isBreakable;
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
        return isBreakable() ? SHIPPING_COST * 2 : SHIPPING_COST;
    }
}
