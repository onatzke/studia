import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PromotionTest {
    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;
    private Product productMug;
    private Product[] products;

    @Before
    public void setUp() {
        product1 = new Product("0021", "Ładowarka", 55.00);
        product2 = new Product("0022", "Słuchawki", 250.90);
        product3 = new Product("0023", "Myszka", 35.50);
        product4 = new Product("0024", "Kabel USB", 15.00);
        productMug = new Product("0000", "Kubek", 00.0);
        products = new Product[]{product1, product2, product3};
    }

    @Test
    public void testDiscountOnTotalPrice() {
        Promotion promotion = new DiscountOnTotalPrice(300.0, 0.05);
        Product[] result = promotion.apply(products);
        assertEquals(52.25, result[0].getDiscountPrice(), 0.01);
        assertEquals(238.36, result[1].getDiscountPrice(), 0.01);
        assertEquals(33.73, result[2].getDiscountPrice(), 0.01);
    }

    @Test
    public void testDiscountOnTotalPriceBelowThreshold() {
        Promotion promotion = new DiscountOnTotalPrice(400.0, 0.1);
        Product[] result = promotion.apply(products);
        assertEquals(55.00, result[0].getDiscountPrice(), 0.01);
        assertEquals(250.90, result[1].getDiscountPrice(), 0.01);
        assertEquals(35.50, result[2].getDiscountPrice(), 0.01);
    }

    @Test
    public void testFreeCheapestOfThreeProducts() {
        Promotion promotion = new FreeCheapestOfThreeProducts();
        Product[] result = promotion.apply(products);
        assertEquals(55.00, result[0].getDiscountPrice(), 0.01);
        assertEquals(250.90, result[1].getDiscountPrice(), 0.01);
        assertEquals(0.0, result[2].getDiscountPrice(), 0.01);
    }

    @Test
    public void testFreeCheapestOfThreeProductsLessThanThree() {
        Product[] lessThanThree = new Product[]{product1, product2};
        Promotion promotion = new FreeCheapestOfThreeProducts();
        Product[] result = promotion.apply(lessThanThree);
        assertEquals(55.00, result[0].getDiscountPrice(), 0.01);
        assertEquals(250.90, result[1].getDiscountPrice(), 0.01);
    }

    @Test
    public void testFreeGiftAboveThreshold() {
        Promotion promotion = new FreeGift(200.0, "0000", "Kubek", 00.0);
        Product[] productsWithThreshold = {product1, product2}; // Suma > 200
        Product[] result = promotion.apply(productsWithThreshold);

        assertEquals(3, result.length);
        assertEquals(55.00, result[0].getDiscountPrice(), 0.01);
        assertEquals(250.90, result[1].getDiscountPrice(), 0.01);
        assertEquals("0000", result[2].getCode());
        assertEquals(0.0, result[2].getDiscountPrice(), 0.01);
    }


    @Test
    public void testDiscountItem() {
        Promotion promotion = new DiscountItem("0021", 0.2);
        Product[] result = promotion.apply(products);
        assertEquals(44.00, result[0].getDiscountPrice(), 0.01);
        assertEquals(250.90, result[1].getDiscountPrice(), 0.01);
        assertEquals(35.50, result[2].getDiscountPrice(), 0.01);
    }

    @Test
    public void testDiscountItemNonExistentProduct() {
        Promotion promotion = new DiscountItem("0028", 0.2);
        Product[] result = promotion.apply(products);
        assertEquals(55.00, result[0].getDiscountPrice(), 0.01);
        assertEquals(250.90, result[1].getDiscountPrice(), 0.01);
        assertEquals(35.50, result[2].getDiscountPrice(), 0.01);
    }
}
