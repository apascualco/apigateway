package com.apascualco.blog.endpoints;

import com.apascualco.blog.domain.HelloWorld;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(value = "hello-world", tags = "hello", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloWorldController {

    @RequestMapping(
            value ="/hello",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Hello World")
    })
    public ResponseEntity<HelloWorld> hello(){

        return ResponseEntity.ok().body(new HelloWorld("Hello World"));
    }

}
