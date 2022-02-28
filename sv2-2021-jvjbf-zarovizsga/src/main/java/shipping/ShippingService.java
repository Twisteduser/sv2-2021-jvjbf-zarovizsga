package shipping;

import java.util.*;
import java.util.stream.Collectors;

public class ShippingService {
    public List<Transportable> packages = new ArrayList<>();

    public void addPackage(Transportable pack) {
        packages.add(pack);
    }

    public List<Transportable> getPackages() {
        return packages;
    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int weight){
        return packages.stream()
                .filter(p->p.isBreakable()==breakable && p.getWeight()>=weight)
                .sorted(Comparator.comparing(Transportable::getWeight))
                .collect(Collectors.toList());
    }

    public Map<String, Integer> collectTransportableByCountry(){
        Map<String,Integer> result = new LinkedHashMap<>();
        for (Transportable t: packages){
            result.compute(t.getDestinationCountry(), (i,j)->j==null ? 1:j+1);
        }
        return result;
    }

    public List<Transportable> sortInternationalPackagesByDistance(){
        return packages.stream()
                .filter(InternationalPackage.class::isInstance)
                .sorted(Comparator.comparing(p->((InternationalPackage) p).getDistance()))
                .collect(Collectors.toList());
    }
}

