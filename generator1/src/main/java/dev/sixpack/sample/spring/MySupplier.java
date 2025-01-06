package dev.sixpack.sample.spring;

import dev.sixpack.generator.Supplier;
import dev.sixpack.generator.SupplierName;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
@SupplierName("MySystem")
public class MySupplier extends Supplier {

    @PostConstruct
    public void init() {
        // You can load the literals from a configuration file using Spring's @Value
        new MySupplier()
                .withSixPackUrl("localhost:7233")
                .withAccount("MyAccount")
                .withEnvironment("STAGE")
                .withClientCertificatePath("certs/generator.cert.pem") // download this file from the settings page
                .withClientKeyPath("certs/generator.pkey.pem") // download this file from the settings page
                .withAuthToken("MyAuthToken")
                .withGenerators( // register all generators you want to include, create the objects
                        new MyGenerator1(),
                        new MyGenerator2())
                .withOrchestrators( // register all orchestrators, just give the classes, an instance will be created per request
                        MyOrchestrator1.class
                )
                .bootstrap();
    }
}
