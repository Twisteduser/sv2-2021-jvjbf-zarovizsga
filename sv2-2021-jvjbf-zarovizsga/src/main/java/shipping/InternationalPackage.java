package shipping;

public class InternationalPackage implements Transportable {
    public int weight;
    public boolean breakable;
    public String destination;
    public int distance;

    public InternationalPackage(int weight, boolean breakable, String destination, int distance) {
        this.weight = weight;
        this.breakable = breakable;
        this.destination = destination;
        this.distance = distance;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public int calculateShippingPrice() {
        if (isBreakable()) {
            return 2400 + (distance * 10);
        }
        return 1200 + (distance * 10);
    }

    @Override
    public String getDestinationCountry() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }
}
