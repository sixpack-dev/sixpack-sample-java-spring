package dev.sixpack.sample.spring;

import dev.sixpack.generator.ItemName;
import dev.sixpack.generator.Orchestrator;

import java.util.Map;

@ItemName("MyEntity3")
public class MyOrchestrator1 extends Orchestrator {
    public record MyInputForEntity(String id) {
    }

    // Instead of defining the output type a String,Object map can be used
    public Map<String, Object> generate(MyInputForEntity input) {
        var id = input.id;
        if (id == null) {
            // obtaining data from a generator with Sixpack platform as proxy
            var entity1 = obtain("MySystem", "MyEntity1", input);
            id = (String) entity1.get("id");
        }
        // using output of previous step as input to next step again with sixpack platform as proxy
        var inputForEntity3 = new MyInputForEntity(id);
        var entity2 = obtain("MySystem", "MyEntity2", inputForEntity3);
        return entity2;
    }
}
