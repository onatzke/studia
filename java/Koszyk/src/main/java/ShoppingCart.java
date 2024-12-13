import java.util.Arrays;


public class ShoppingCart {
    private Product[] products;
    private Promotion[] promotions;
    private int productCount;
    private int promotionCount;

    public ShoppingCart() {
        this.products = new Product[10];
        this.promotions = new Promotion[10];
        this.productCount = 0;
        this.promotionCount = 0;
    }

    public void addProduct(Product product) {
        products[productCount++] = product;
    }

    public void removeProduct(Product product) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].equals(product)) { // Compare objects
                System.arraycopy(products, i + 1, products, i, productCount - i - 1);
                products[--productCount] = null; // Nullify the last element
                break;
            }
        }
    }

    public Product[] getProducts() {
        return Arrays.copyOf(products, productCount);
    }

    public void addPromotion(Promotion promotion) {
        promotions[promotionCount++] = promotion;
    }

    public void removePromotion(Promotion promotion) {
        for (int i = 0; i < promotionCount; i++) {
            if (promotions[i].equals(promotion)) {
                System.arraycopy(promotions, i + 1, promotions, i, promotionCount - i - 1);
                promotions[--promotionCount] = null;
                break;
            }
        }
    }

    public void applyPromotions() {
        for (int i = 0; i < promotionCount; i++) {
            promotions[i].apply(getProducts());
        }
    }

    public Promotion[] getPromotions() {
        return Arrays.copyOf(promotions, promotionCount);
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (int i = 0; i < productCount; i++) {
            total += products[i].getDiscountPrice();
        }
        return total;
    }

    public Product findCheapest() {
        if (productCount == 0) return null;

        Product cheapest = products[0];
        for (int i = 1; i < productCount; i++) {
            if (products[i].getPrice() < cheapest.getPrice()) {
                cheapest = products[i];
            }
        }
        return cheapest;
    }

    public Product findMostExpensive() {
        if (productCount == 0) return null;

        Product costliest = products[0];
        for (int i = 1; i < productCount; i++) {
            if (products[i].getPrice() > costliest.getPrice()) {
                costliest = products[i];
            }
        }
        return costliest;
    }

    public Product[] findMultipleCheapest(int n) {
        Product[] sorted = Arrays.copyOf(getProducts(), productCount);
        Arrays.sort(sorted, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        return Arrays.copyOf(sorted, Math.min(n, sorted.length));
    }

    public Product[] findMultipleMostExpensive(int n) {
        Product[] sorted = Arrays.copyOf(getProducts(), productCount);
        Arrays.sort(sorted, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        return Arrays.copyOf(sorted, Math.min(n, sorted.length));
    }
    
    public void sortProducts() {
        Arrays.sort(products, 0, productCount, new ProductComparator());
    }

}
