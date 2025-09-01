package org.example.financetracker.repository;

import org.example.financetracker.entity.PaymentRefund;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRefundRepository extends CrudRepository<PaymentRefund, Long> {
}
