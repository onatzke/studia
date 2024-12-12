public interface Promotion {
    void apply(Product[] products);
}

class DiscountOnTotalPrice implements Promotion {
    private final double threshold;
    private final double discountRate;

    public DiscountOnTotalPrice(double threshold, double discountRate) {
        this.threshold = threshold;
        this.discountRate = discountRate;
    }

    @Override
    public void apply(Product[] products) {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }

        if (total > threshold) {
            for (Product product : products) {
                product.setDiscountPrice(product.getPrice() * (1 - discountRate));
            }
        }
    }
}

class FreeCheapestOfThreeProducts implements Promotion {
    @Override
    public void apply(Product[] products) {
        if (products.length >= 3) {
            // Find the cheapest product
            Product cheapestProduct = products[0];
            for (Product product : products) {
                if (product.getPrice() < cheapestProduct.getPrice()) {
                    cheapestProduct = product;
                }
            }

            cheapestProduct.setDiscountPrice(0);
        }
    }
}

class FreeGiftPromotion implements Promotion {
    private final double threshold;

    public FreeGiftPromotion(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public void apply(Product[] products) {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }

    }
}

class DiscountItem implements Promotion {
    private final String productCode;
    private final double discountRate;

    public DiscountItem(String productCode, double discountRate) {
        this.productCode = productCode;
        this.discountRate = discountRate;
    }

    @Override
    public void apply(Product[] products) {
        for (Product product : products) {
            if (product != null && product.getCode().equals(productCode)) {
                product.setDiscountPrice(product.getPrice() * (1 - discountRate));
            }
        }
    }
}

