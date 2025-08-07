package com.example.fraud;

import com.example.fraud.entity.TransactionEntity;
import com.example.fraud.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void testInsertAndFindTransaction() {
        TransactionEntity tx = TransactionEntity.builder()
                .amount(123.45)
                .type("PAYMENT")
                .location("Loc-42")
                .isFraud(false)
                .timestamp(java.time.LocalDateTime.now())
                .build();

        transactionRepository.save(tx);

        List<TransactionEntity> all = transactionRepository.findAll();
        assertThat(all).isNotEmpty();
    }
}
