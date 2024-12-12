import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        int priceComparison = Double.compare(product2.getDiscountPrice(), product1.getDiscountPrice());
        if (priceComparison == 0) {
            return product1.getName().compareTo(product2.getName());
        }

        return priceComparison;
    }
}

