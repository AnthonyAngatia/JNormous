package com.example.aboutmonoflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public void demo(){
        // Examples of how a publisher can be created
        Flux<Integer> f1 = Flux.just(1, 2, 3);
        var f2 = Flux.fromStream(Stream.of(1,2,3));


        f1.doOnNext(System.out::println).subscribe();
        f1.subscribe(x->{
            System.out.println("Second one");
            System.out.println(x);
        });


    }
}
