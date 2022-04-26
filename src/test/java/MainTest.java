import com.example.rentsystem.entity.Order;
import com.example.rentsystem.service.OrderService;
import com.example.rentsystem.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class MainTest {

    @Test
    public void test() {
/*        HouseService service = new HouseServiceImpl();
        service.addHouse(House.builder()
                .isRent(0)
                .location("四川省成都市")
                .name("太医有房")
                .ownerId(1)
                .price(998)
                .build());
        service.getHouses().forEach(System.out::println);*/
        OrderService service = new OrderServiceImpl();
        service.addOrder(Order.builder()
                .ownerId(1)
                .buyId(1)
                .houseId(1)
                .bTime(new Date())
                .eTime(new Date())
                .build());
        service.getOrders().forEach(System.out::println);
    }
}
