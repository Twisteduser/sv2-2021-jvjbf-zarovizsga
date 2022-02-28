package shipping;

public interface Transportable {
    public String hun = "Hungary";

    int getWeight();
    boolean isBreakable();
    int calculateShippingPrice();
    default String getDestinationCountry(){
        return hun;
    };
}
