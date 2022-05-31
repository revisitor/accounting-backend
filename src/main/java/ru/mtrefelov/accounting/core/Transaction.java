package ru.mtrefelov.accounting.core;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
  private final long id;

  private final BigDecimal total;

  private final String category;

  private final LocalDate executedAt;

  private final TransactionType type;

  private Transaction(TransactionBuilder builder) {
    id = builder.id;
    total = Objects.requireNonNull(builder.total, "Transaction total is null");
    type = Objects.requireNonNull(builder.type, "Transaction type is null");
    category = Objects.requireNonNullElse(builder.category, "");
    executedAt = Objects.requireNonNullElse(builder.executedAt, LocalDate.now());
  }

  public long getId() {
    return id;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public String getCategory() {
    return category;
  }

  public LocalDate getExecutedAt() {
    return executedAt;
  }

  public TransactionType getType() {
    return type;
  }

  public static TransactionBuilder builder(BigDecimal total, TransactionType type) {
    return new TransactionBuilder(total, type);
  }

  public static class TransactionBuilder {
    private long id;

    private BigDecimal total;

    private String category;

    private LocalDate executedAt;

    private TransactionType type;

    public TransactionBuilder(BigDecimal total, TransactionType type) {
      this.total = total;
      this.type = type;
    }

    public TransactionBuilder id(long id) {
      this.id = id;
      return this;
    }

    public TransactionBuilder category(String category) {
      this.category = category;
      return this;
    }

    public TransactionBuilder executedAt(LocalDate executedAt) {
      this.executedAt = executedAt;
      return this;
    }

    public Transaction build() {
      return new Transaction(this);
    }
  }
}
