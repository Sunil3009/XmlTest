package com.securin.xmltest;

import com.securin.xmltest.Service.CpeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class XmlTestApplication {



    public static void main(String[] args) throws IOException {
        SpringApplication.run(XmlTestApplication.class, args);
    }


}
