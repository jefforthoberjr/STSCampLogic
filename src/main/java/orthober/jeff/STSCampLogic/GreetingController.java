package orthober.jeff.STSCampLogic;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller
 * @author jeffryorthober
 *
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    
    //A long variable which can be read and written atomically
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = RequestMethod.GET, value = "/greeting")
    public Greeting greeting(
    		@RequestParam(value="name", required=false, defaultValue="World") String name) {
    		System.out.println("Just got a GET request for /greeting.");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
