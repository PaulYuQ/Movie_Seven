import org.junit.Test;
import service.CollectionService;
import service.impl.CollectionServiceImpl;

public class CollectionImplTest {
    CollectionService collectionService = new CollectionServiceImpl();
    @Test
    public void testDate() {
        System.out.println(collectionService.getAllCollection(3, 0, 6));
    }
}
