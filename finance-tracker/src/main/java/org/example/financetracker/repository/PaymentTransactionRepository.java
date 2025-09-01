package org.example.financetracker.repository;


import org.example.financetracker.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
    @Query("SELECT pt FROM PaymentTransaction pt LEFT JOIN FETCH pt.id")
    Optional<PaymentTransaction> findByIdWithRefunds(Long id);
}
