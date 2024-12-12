import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PromotionTest {
    private Product product1; // Ładowarka
    private Product product2; // Słuchawki
    private Product product3; // Myszka
    private Product product4; // Kabel USB
    private Product[] products;

    @Before
    public void setUp() {
        product1 = new Product("0021", "Ładowarka", 55.00);
        product2 = new Product("0022", "Słuchawki", 250.90);
        product3 = new Product("0023", "Myszka", 35.50);
        product4 = new Product("0024", "Kabel USB", 15.00);
        products = new Product[]{product1, product2, product3};
    }

    @Test
    public void testDiscountOnTotalPrice() {
        Promotion promotion = new DiscountOnTotalPrice(300.0, 0.05);
        promotion.apply(products);

        assertEquals(52.25, product1.getDiscountPrice(), 0.01);
        assertEquals(238.36, product2.getDiscountPrice(), 0.01);
        assertEquals(33.73, product3.getDiscountPrice(), 0.01);
    }

    @Test
    public void testDiscountOnTotalPriceBelowThreshold() {
        Promotion promotion = new DiscountOnTotalPrice(400.0, 0.1);
        promotion.apply(products);

        assertEquals(55.00, product1.getDiscountPrice(), 0.01);
        assertEquals(250.90, product2.getDiscountPrice(), 0.01);
        assertEquals(35.50, product3.getDiscountPrice(), 0.01);
    }

    @Test
    public void testFreeCheapestOfThreeProducts() {
        Promotion promotion = new FreeCheapestOfThreeProducts();
        promotion.apply(products);

        assertEquals(55.00, product1.getDiscountPrice(), 0.01);
        assertEquals(250.90, product2.getDiscountPrice(), 0.01);
        assertEquals(0.0, product3.getDiscountPrice(), 0.01);
    }

    @Test
    public void testFreeCheapestOfThreeProductsLessThanThree() {
        Promotion promotion = new FreeCheapestOfThreeProducts();
        Product[] lessThanThree = new Product[]{product1, product2};
        promotion.apply(lessThanThree);

        assertEquals(55.00, product1.getDiscountPrice(), 0.01);
        assertEquals(250.90, product2.getDiscountPrice(), 0.01);
    }

    @Test
    public void testFreeGiftPromotionAboveThreshold() {
        Promotion promotion = new FreeGiftPromotion(200.0);
        promotion.apply(products);

        assertEquals(55.00, product1.getDiscountPrice(), 0.01);
        assertEquals(250.90, product2.getDiscountPrice(), 0.01);
        assertEquals(35.50, product3.getDiscountPrice(), 0.01);
    }

    @Test
    public void testFreeGiftPromotionBelowThreshold() {
        Promotion promotion = new FreeGiftPromotion(200.0);
        promotion.apply(products);

        assertEquals(55.00, product1.getDiscountPrice(), 0.01);
        assertEquals(35.50, product3.getDiscountPrice(), 0.01);
        assertEquals(15.00, product4.getDiscountPrice(), 0.01);

    }

    @Test
    public void testDiscountCoupon() {
        Promotion promotion = new DiscountItem("0021", 0.2); // 20% zniżki na produkt "Ładowarka"
        promotion.apply(products);

        assertEquals(44.00, product1.getDiscountPrice(), 0.01);
        assertEquals(250.90, product2.getDiscountPrice(), 0.01);
        assertEquals(35.50, product3.getDiscountPrice(), 0.01);
    }

    @Test
    public void testDiscountCouponNonExistentProduct() {
        Promotion promotion = new DiscountItem("0028", 0.2);
        promotion.apply(products);

        assertEquals(55.00, product1.getDiscountPrice(), 0.01);
        assertEquals(250.90, product2.getDiscountPrice(), 0.01);
        assertEquals(35.50, product3.getDiscountPrice(), 0.01);
    }


}
