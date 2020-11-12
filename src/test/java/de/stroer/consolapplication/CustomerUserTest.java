package de.stroer.consolapplication;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.stroer.consolapplication.model.Customer;
import de.stroer.consolapplication.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CustomerUserTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CustomerRepository mockRepository;

    @BeforeEach
    public void init() {
    Customer customer= new Customer("DE89370400440532013000", "Santideva","Bahari");
        when(mockRepository.findById(1L)).thenReturn(Optional.of(customer));
    }

    @Test
    public void find_login_ok() throws Exception {

        String expected = "{id:1,ibans:\"DE89370400440532013000\",fName:\"Santideva\",lName:\"Bahari\"}";

        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity("/api/v1/customers/1", String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    @Test
    public void find_nologin_401() throws Exception {

        String expected = "{\"status\":401,\"error\":\"Unauthorized\",\"message\":\"Unauthorized\",\"path\":\"/books/1\"}";

        ResponseEntity<String> response = restTemplate
                .getForEntity("api/v1/customers/1", String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    private static void printJSON(Object object) {
        String result;
        try {
            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
