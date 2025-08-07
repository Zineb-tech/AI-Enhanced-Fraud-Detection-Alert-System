package com.example.fraud.init;

import com.example.fraud.entity.TransactionEntity;
import com.example.fraud.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TransactionRepository transactionRepository;

    @Override
    public void run(String... args) {
        if (transactionRepository.count() == 0) {
            Random rand = new Random();
            IntStream.range(0, 1000).forEach(i -> {
                boolean isFraud = rand.nextDouble() < 0.08; // ~8%
                transactionRepository.save(TransactionEntity.builder()
                    .amount(10 + rand.nextDouble() * 990)
                    .type(rand.nextBoolean() ? "TRANSFER" : "PAYMENT")
                    .location("Loc-" + rand.nextInt(100))
                    .isFraud(isFraud)
                    .timestamp(LocalDateTime.now().minusDays(rand.nextInt(30)))
                    .build());
            });
        }
    }
}
