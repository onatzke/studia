import java.util.Arrays;

public interface Promotion {
    Product[] apply(Product[] products);
}

class DiscountOnTotalPrice implements Promotion {
    private final double threshold;
    private final double discountRate;

    public DiscountOnTotalPrice(double threshold, double discountRate) {
        this.threshold = threshold;
        this.discountRate = discountRate;
    }

    @Override
    public Product[] apply(Product[] products) {
        Product[] newProducts = Arrays.copyOf(products, products.length);
        double total = 0;
        for (Product product : newProducts) {
            total += product.getPrice();
        }

        if (total > threshold) {
            for (Product product : newProducts) {
                product.setDiscountPrice(product.getPrice() * (1 - discountRate));
            }
        }
        return newProducts;
    }
}

class FreeCheapestOfThreeProducts implements Promotion {
    @Override
    public Product[] apply(Product[] products) {
        Product[] newProducts = Arrays.copyOf(products, products.length);
        if (newProducts.length >= 3) {
            Product cheapestProduct = newProducts[0];
            for (Product product : newProducts) {
                if (product.getPrice() < cheapestProduct.getPrice()) {
                    cheapestProduct = product;
                }
            }
            cheapestProduct.setDiscountPrice(0);
        }
        return newProducts;
    }
}

class FreeGift implements Promotion {
    private final double threshold;
    private final String giftCode;
    private final String giftName;
    private final double giftPrice;

    public FreeGift(double threshold, String giftCode, String giftName, double giftPrice) {
        this.threshold = threshold;
        this.giftCode = giftCode;
        this.giftName = giftName;
        this.giftPrice = giftPrice;
    }

    @Override
    public Product[] apply(Product[] products) {
        double total = 0;
        for (Product product : products) {
            if (product != null) {
                total += product.getPrice();
            }
        }


        if (total >= threshold) {
            Product gift = new Product(giftCode, giftName, giftPrice);
            gift.setDiscountPrice(0.0);

            Product[] newArray = Arrays.copyOf(products, products.length + 1);
            newArray[newArray.length - 1] = gift;
            return newArray;
        } else {
            return products;
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
    public Product[] apply(Product[] products) {
        Product[] newProducts = Arrays.copyOf(products, products.length);
        for (Product product : newProducts) {
            if (product != null && product.getCode().equals(productCode)) {
                product.setDiscountPrice(product.getPrice() * (1 - discountRate));
            }
        }
        return newProducts;
    }
}

