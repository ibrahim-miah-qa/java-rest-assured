package service_test;

import com.globalids.Function;
import com.globalids.Topic;
import com.globalids.service.AppService;
import org.junit.Test;

public class AppServiceTest {
	AppService appService = new AppService();
	
	@Test
	public void app_service_test_create_app() {
		Function function = Function.add;
		appService.execute(function, "app add -d src/test/resources/data.json".split(" "));
	}
}
