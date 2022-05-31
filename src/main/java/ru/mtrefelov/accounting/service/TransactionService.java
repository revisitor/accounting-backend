package ru.mtrefelov.accounting.service;

import java.util.Optional;

import ru.mtrefelov.accounting.core.Transaction;
import ru.mtrefelov.accounting.persistence.*;

public class TransactionService {
  private final TransactionRepository transactionRepository;
  private final TransactionAdapter adapter;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
    this.adapter = new TransactionAdapter();
  }

  public Optional<Transaction> getTransactionById(Long id) {
    Optional<TransactionEntity> transaction = transactionRepository.findById(id);
    return transaction.map(adapter::entityToModel);
  }

  public Transaction createTransaction(Transaction transaction) {
    TransactionEntity created = transactionRepository.save(adapter.modelToEntity(transaction));
    return adapter.entityToModel(created);
  }

  public void updateTransaction(Transaction transaction) {
    TransactionEntity entity = adapter.modelToEntity(transaction);
    transactionRepository.updateOne(
        entity.getId(),
        entity.getTotal(),
        entity.getType(),
        entity.getCategory(),
        entity.getExecutedAt());
  }

  public Long deleteTransaction(Long id) {
    transactionRepository.deleteById(id);
    return id;
  }
}
