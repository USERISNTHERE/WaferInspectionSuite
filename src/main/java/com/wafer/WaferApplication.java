package com.wafer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WaferApplication {
    public static void main(String[] args) {
        SpringApplication.run(WaferApplication.class, args);
        System.out.println("\n🚀 Industrial Wafer Inspection Server live at http://localhost:8080\n");
    }
}
