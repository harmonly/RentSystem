import com.example.rentsystem.service.UserService;
import com.example.rentsystem.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void test() {
        UserService service = new UserServiceImpl();
        service.getUsers().forEach(System.out::println);
    }
}
