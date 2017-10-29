package orthober.jeff.STSCampLogic.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orthober.jeff.STSCampLogic.model.Search;

/**
 * Controller
 * @author jeffryorthober
 *
 */
@RestController
public class SearchController {

    //This classed use to verify JSON consumption of search objects

    @RequestMapping(value = "/search")
    public void campsite(@RequestBody Search search) {
    		System.out.println("Just got a request for /search.");
    		System.out.println(search.getStartDate());
    		System.out.println(search.getEndDate());
    }
}

