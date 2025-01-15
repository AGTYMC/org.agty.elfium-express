package org.agty.elfiumexpress;

import org.agty.elfiumexpress.storage.service.StorageService;
import org.agty.elfiumexpress.storage.types.FileMime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
            FileMime.init();
        };
    }
}
