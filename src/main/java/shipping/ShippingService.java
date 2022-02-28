package shipping;

import java.util.*;

public class ShippingService {

    private List<Transportable> packages = new ArrayList<>();

    public List<Transportable> getPackages() {
        return packages;
    }

    public void addPackage(Transportable transportable) {
        packages.add(transportable);
    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int weight) {
        return packages.stream().filter(t -> t.isBreakable() == breakable).filter(t -> t.getWeight() >= weight).toList();
    }

    public Map<String, Integer> collectTransportableByCountry() {
        Map<String, Integer> transports = new HashMap<>();
        for (Transportable tr: packages) {
            transports.put(tr.getDestinationCountry(), getNumberOfDestinations(tr.getDestinationCountry()));
        }
        return transports;
    }

    private int getNumberOfDestinations(String country) {
        int numberOfDestinations = 0;
        for (Transportable tr: packages) {
            if (country.equals(tr.getDestinationCountry())) {
                numberOfDestinations ++;
            }
        }
        return numberOfDestinations;
    }

    public List<Transportable> sortInternationalPackagesByDistance() {
        return packages.stream().filter(t -> !"Hungary".equals(t.getDestinationCountry()))
                .map(t -> (InternationalPackage) t)
                .sorted(Comparator.comparingInt(InternationalPackage::getDistance))
                .map(i -> (Transportable) i)
                .toList();
    }
}
