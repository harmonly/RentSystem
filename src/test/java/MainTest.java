import com.example.rentsystem.entity.Order;
import com.example.rentsystem.entity.User;
import com.example.rentsystem.mapper.HouseMapper;
import com.example.rentsystem.mapper.UserMapper;
import com.example.rentsystem.service.OrderService;
import com.example.rentsystem.service.UserService;
import com.example.rentsystem.service.impl.OrderServiceImpl;
import com.example.rentsystem.service.impl.UserServiceImpl;
import com.example.rentsystem.utils.SqlUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainTest {

    @Test
    public void test() throws ParseException {
/*        HouseService service = new HouseServiceImpl();
        service.addHouse(House.builder()
                .isRent(0)
                .location("四川省成都市")
                .name("太医有房")
                .ownerId(1)
                .price(998)
                .build());
        service.getHouses().forEach(System.out::println);*/
/*        OrderService service = new OrderServiceImpl();
        service.addOrder(Order.builder()
                .ownerId(1)
                .buyId(1)
                .houseId(1)
                .bTime(new Date())
                .eTime(new Date())
                .build());
        service.getOrders().forEach(System.out::println);*/
/*        SqlSession session = SqlUtil.getSession();
        HouseMapper mapper = session.getMapper(HouseMapper.class);
        mapper.getHouses().forEach(System.out::println);*/
    }
}
