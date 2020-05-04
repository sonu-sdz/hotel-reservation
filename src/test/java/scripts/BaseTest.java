package scripts;

import com.seera.base.BaseAPI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    BaseAPI baseAPI;
    @BeforeEach
    public void beforeTest(){
        baseAPI = new BaseAPI();
        baseAPI.setBaseURI("https://www.tajawal.ae");
    }

    @AfterEach
    public void afterEachTest(){

    }

}
