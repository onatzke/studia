import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShoppingCartTest {
    private ShoppingCart cart;
    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;
    private Product product5;
    private Product[] products;

    @Before
    public void setUp() {
        cart = new ShoppingCart();
        product1 = new Product("0021", "Ładowarka", 55.00);
        product2 = new Product("0022", "Słuchawki", 250.90);
        product3 = new Product("0023", "Myszka", 35.50);
        product4 = new Product("0024", "Kabel USB", 15.00);
        product5 = new Product("0025", "Przejściowka", 15.00);
    }

    @Test
    public void testAddProduct() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        assertArrayEquals(new Product[]{product1, product2}, cart.getProducts());
    }

    @Test
    public void testRemoveProduct() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);


        cart.removeProduct(product2);
        assertArrayEquals(new Product[]{product1, product3}, cart.getProducts());
    }

    @Test
    public void testGetProducts() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        Product[] actualProducts = cart.getProducts();
        assertEquals(2, actualProducts.length);
        assertEquals(product1, actualProducts[0]);
        assertEquals(product2, actualProducts[1]);
    }

    @Test
    public void testAddPromotion() {
        Promotion promo = new DiscountOnTotalPrice(100, 0.1);
        cart.addPromotion(promo);
        assertArrayEquals(new Promotion[]{promo}, cart.getPromotions());
    }

    @Test
    public void testRemovePromotion() {
        Promotion promo1 = new DiscountOnTotalPrice(100, 0.1);
        cart.addPromotion(promo1);
        cart.removePromotion(promo1);
        assertArrayEquals(new Promotion[]{}, cart.getPromotions());
    }

    @Test
    public void testApplyPromotions() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        Promotion promo = new DiscountOnTotalPrice(100, 0.1);
        cart.addPromotion(promo);
        cart.applyPromotions();
        assertEquals(49.50, product1.getDiscountPrice(), 0.01);
        assertEquals(225.81, product2.getDiscountPrice(), 0.01);
    }

    @Test
    public void testCalculateTotalPrice() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.applyPromotions();
        assertEquals(305.90, cart.calculateTotalPrice(), 0.01);

        Promotion promo = new DiscountOnTotalPrice(100, 0.1);
        cart.addPromotion(promo);
        cart.applyPromotions();
        assertEquals(275.31, cart.calculateTotalPrice(), 0.01);
    }

    @Test
    public void testFindCheapest() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);
        assertEquals(product3, cart.findCheapest());
    }

    @Test
    public void testFindMostExpensive() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);
        assertEquals(product2, cart.findMostExpensive());
    }

    @Test
    public void testFindMultipleCheapest() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);
        Product[] cheapestTwo = cart.findMultipleCheapest(2);
        assertArrayEquals(new Product[]{product3, product1}, cheapestTwo);
    }

    @Test
    public void testFindMultipleMostExpensive() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);
        Product[] costliestTwo = cart.findMultipleMostExpensive(2);
        assertArrayEquals(new Product[]{product2, product1}, costliestTwo);
    }

    @Test
    public void testSortProducts() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);
        cart.sortProducts();
        assertArrayEquals(new Product[]{product2, product1, product3}, cart.getProducts());
    }

    @Test
    public void testSortProductsByPriceAndName() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);
        cart.addProduct(product4);
        cart.addProduct(product5);
        cart.sortProducts();
        assertArrayEquals(
                new Product[]{product2, product1, product3, product4, product5},
                cart.getProducts()
        );
    }
}
