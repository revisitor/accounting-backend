package ru.mtrefelov.accounting.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static ru.mtrefelov.accounting.core.Transaction.TransactionBuilder;
import ru.mtrefelov.accounting.core.*;
import ru.mtrefelov.accounting.persistence.TransactionEntity;

public class TransactionAdapter implements Adapter<TransactionEntity, Transaction> {
  @Override
  public Transaction entityToModel(TransactionEntity entity) {
    BigDecimal total = entity.getTotal();
    TransactionType type = entity.getType();
    TransactionBuilder builder = Transaction.builder(total, type);

    Long id = entity.getId();
    builder.id(id);

    String category = entity.getCategory();
    builder.category(category);

    LocalDate executedAt = entity.getExecutedAt();
    builder.executedAt(executedAt);

    return builder.build();
  }

  @Override
  public TransactionEntity modelToEntity(Transaction model) {
    TransactionEntity entity = new TransactionEntity();

    Long id = model.getId();
    entity.setId(id);

    String category = model.getCategory();
    entity.setCategory(category);

    BigDecimal total = model.getTotal();
    entity.setTotal(total);

    LocalDate executedAt = model.getExecutedAt();
    entity.setExecutedAt(executedAt);

    TransactionType type = model.getType();
    entity.setType(type);

    return entity;
  }
}
