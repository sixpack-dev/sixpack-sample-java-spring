package dev.sixpack.sample.spring;

import dev.sixpack.generator.Generator;
import dev.sixpack.generator.annotation.ItemMetadata;

@ItemMetadata(
    name = "MyEntity2",
    reportIssueUrl = "https://www.example.com/report-issue"
)
public class MyGenerator2 extends Generator {
    // Shows available types on input, they need to be nullable
    public record MyInputForEntity2(String name, // will become a text input
                                    Integer age, // will become a number input
                                    Gender gender, // will become a 3 state switch
                                    Boolean drivingLicense // will become a select
    ) {
    }

    public enum Gender {
        MALE,
        FEMALE,
        NON_BINARY
    }

    // The output can even copy input parameters as well to allow giving their generated value
    public record MyEntity2Reference(String id,
                                     String name,
                                     Integer age,
                                     Gender gender,
                                     Boolean drivingLicense
    ) {
    }

    // Handling null inputs to generate random values
    public MyEntity2Reference generate(MyInputForEntity2 input) {
        var id = getRandomNumberString(0, 1000);
        var name = input.name != null ? input.name : "randomName" + "_" + Math.random();
        var age = input.age != null ? input.age : getRandomNumber(1, 100);
        var gender = input.gender != null ? input.gender : Gender.values()[getRandomNumber(0, Gender.values().length)];
        var drivingLicense = input.drivingLicense != null ? input.drivingLicense : true;
        return new MyEntity2Reference(id, name, age, gender, drivingLicense);
    }

    private static String getRandomNumberString(int a, int b) {
        return String.valueOf(getRandomNumber(a, b));
    }

    private static int getRandomNumber(int a, int b) {
        return (int) (Math.floor(Math.random() * (b - a) + a));
    }


}
