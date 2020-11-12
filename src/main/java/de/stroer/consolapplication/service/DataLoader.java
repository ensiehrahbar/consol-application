package de.stroer.consolapplication.service;

import de.stroer.consolapplication.model.Customer;
import de.stroer.consolapplication.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private CustomerRepository repository;

    public DataLoader(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("Loading data...");
        repository.save(new Customer("GB33BUKB20201585555555", "Santideva", "Samini"));
        repository.save(new Customer("GB33BUKB20201565555555", "Marie", "Kondo"));
        repository.save(new Customer("GB33BUKB20201575555555", "Martin", "Fowler"));
    }
}