package org.mave.containerization_demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class HelloWorldRepository {

    private final HelloWorldModel helloWorldModel;

    public String getHelloWorld() {
        helloWorldModel.setMessage("Hello World V4!");
        return helloWorldModel.getMessage();
    }

}
