package labshopmessagingtest.infra;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import labshopmessagingtest.domain.Order;
import labshopmessagingtest.domain.OrderCommand;
import labshopmessagingtest.domain.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    private String apiUrl;

    @Value("${api.url.inventory}")
    private String apiUrl;

    @GetMapping("/order/validateInventory/{id}")
    public ResponseEntity<String> inventoryStockCheck(
        @PathVariable(value = "id") Long id
    ) {
        String orderPlacedUrl = apiUrl + "/orderPlaceds/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> orderPlacedEntity = restTemplate.exchange(
            orderPlacedUrl,
            HttpMethod.GET,
            entity,
            String.class
        );

        return orderPlacedEntity;
    }
}
