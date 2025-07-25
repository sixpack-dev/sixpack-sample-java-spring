package dev.sixpack.sample.spring;

import dev.sixpack.generator.Generator;
import dev.sixpack.generator.annotation.ItemMetadata;

@ItemMetadata(
    name = "MyEntity1",
    reportIssueUrl = "https://www.example.com/report-issue"
)
public class MyGenerator1 extends Generator {
    // Types definition is are here inside the class but can be anywhere in the project
    // This represents the input type listing all the fields that the user can fill in the UI
    public record MyInputForEntity1(String name) {
    }

    // This represents the output type returned by the generator
    public record MyEntity1Reference(String id) {
    }

    // The method generate takes an input and produces an output and can do anything
    public MyEntity1Reference generate(MyInputForEntity1 input) {
        return new MyEntity1Reference(String.valueOf((int) Math.floor(Math.random() * 1000)));
    }
}
