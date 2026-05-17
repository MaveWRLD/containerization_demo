package org.mave.containerization_demo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HelloWorldService {

    private final HelloWorldRepository helloWorldRepository;

    public String getHelloWorld() {
        return helloWorldRepository.getHelloWorld();
    }
}
