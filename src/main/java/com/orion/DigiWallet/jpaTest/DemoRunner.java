package com.orion.DigiWallet.jpaTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoRunner implements CommandLineRunner {

    private final BaseCRUD baseCRUD;

    @Autowired
    public DemoRunner(BaseCRUD baseCRUD) {
        this.baseCRUD = baseCRUD;
    }

    @Override
    public void run(String... args) {
        baseCRUD.insertDepartment();

       // baseCRUD.moreDataInsert();

        /*
        List<Employee> employees =  baseCRUD.getAllEmployees();
        for (Employee emp : employees) {
            System.out.println("Employee Name: " + emp.getName() +
                    ", Department: " + emp.getDepartment().getName());
        }
        */

    }
}
/*

1. Spring Boot starts
2. Auto-configuration runs
3. DataSource is created
4. JPA EntityManagerFactory is ready
5. Repositories are ready
6. Beans are injected
7. ApplicationContext is fully initialized
-------------------------------------------
        8. CommandLineRunner.run() is executed

*/
/*
Real-life scenario
Applications depend on:

Message brokers (Kafka, RabbitMQ)
Third-party APIs
Cloud services (S3, Azure Blob, GCP Storage)
Internal microservices
Before handling requests, the app must:
Establish connections
Perform handshake / auth
Verify reachability

Why CommandLineRunner
Ensures external systems are reachable once
Prevents runtime surprises
Keeps startup logic centralized
 */