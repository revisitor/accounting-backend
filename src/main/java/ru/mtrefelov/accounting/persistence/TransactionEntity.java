package ru.mtrefelov.accounting.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

import ru.mtrefelov.accounting.core.TransactionType;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
  @Id
  private Long id;

  @Column(nullable = false, scale = 2)
  private BigDecimal total;

  @Column(nullable = false)
  private String category;

  @Column(name = "executed_at", nullable = false, columnDefinition = "DATE")
  private LocalDate executedAt;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private AccountEntity account;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public LocalDate getExecutedAt() {
    return executedAt;
  }

  public void setExecutedAt(LocalDate executedAt) {
    this.executedAt = executedAt;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public AccountEntity getAccount() {
    return account;
  }

  public void setAccount(AccountEntity account) {
    this.account = account;
  }
}
