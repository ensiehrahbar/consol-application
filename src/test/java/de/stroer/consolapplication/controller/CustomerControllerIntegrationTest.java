package de.stroer.consolapplication.controller;

import de.stroer.consolapplication.ConsolApplication;
import de.stroer.consolapplication.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ConsolApplication.class,
                  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return ("http://localhost:" + port+"/api/v1/");
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllCustomers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/customers",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = restTemplate.getForObject(getRootUrl() + "/customers/1", Customer.class);
        System.out.println(customer.getfName());
        assertNotNull(customer);
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
       // customer.setId((long) 2);
        customer.setIban("GB07BARC10201530093459");
        customer.setfName("admin");
        customer.setlName("admin");

        ResponseEntity<Customer> postResponse = restTemplate.postForEntity(getRootUrl() + "/customers", customer, Customer.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateCustomer() {
        int id = 1;
        Customer customer = restTemplate.getForObject(getRootUrl() + "customers/" + id, Customer.class);
        customer.setIban("DE89378800440532013000");
        customer.setfName("Samira");
        customer.setlName("Alex");

        restTemplate.put(getRootUrl() + "/customers/" + id, customer);

        Customer updatedCustomer = restTemplate.getForObject(getRootUrl() + "/customers/" + id, Customer.class);
        assertNotNull(updatedCustomer);
    }

    @Test
    public void testDeleteCustomer() {
        int id = 2;
        Customer customer = restTemplate.getForObject(getRootUrl() + "/customers/" + id, Customer.class);
        assertNotNull(customer);

        restTemplate.delete(getRootUrl() + "/customers/" + id);

        try {
            customer = restTemplate.getForObject(getRootUrl() + "/customers/" + id, Customer.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    @Rollback(false)
    public void testCreateNewCustomerDoplicate() {

    }
}
