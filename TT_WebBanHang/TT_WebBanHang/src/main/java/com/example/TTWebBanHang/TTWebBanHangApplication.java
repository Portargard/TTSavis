package com.example.TTWebBanHang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class TTWebBanHangApplication {

    public static void main(String[] args) {
        SpringApplication.run(TTWebBanHangApplication.class, args);
        List<String> strings = Arrays.asList("args", "", "code", "learn", "...");
        List<String> filter = strings.stream().collect(Collectors.toList());
        System.out.println(filter);
    }


}
