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
                .withClientCertificatePath("certs/generator.cert") // download this file from the settings page
                .withClientKeyPath("certs/generator.pkey") // download this file from the settings page
                .withGenerators(
                        new MyGenerator1())
                .bootstrap();
    }
}
