package ru.mtrefelov.accounting.core;

import java.math.BigDecimal;
import java.util.*;

public class Account {
  private int id;

  private String name;

  private Set<Transaction> transactions = new HashSet<>();

  public Account(String name) {
    this.name = Objects.requireNonNull(name, "Account name is null").trim();
  }

  public void addTransaction(Transaction transaction) {
    transactions.add(transaction);
  }

  public BigDecimal getBalance() {
    BigDecimal income = getTotalIncome();
    BigDecimal expense = getTotalExpense();
    return income.subtract(expense);
  }

  public BigDecimal getTotalIncome() {
    return transactions.stream()
        .filter((transaction) -> transaction.getType() == TransactionType.INCOME)
        .map(Transaction::getTotal)
        .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
  }

  public BigDecimal getTotalExpense() {
    return transactions.stream()
        .filter((transaction) -> transaction.getType() == TransactionType.EXPENSE)
        .map(Transaction::getTotal)
        .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = Objects.requireNonNull(name).trim();
  }

  public Set<Transaction> getTransactions() {
    return new HashSet<>(transactions);
  }

  public void setTransactions(Set<Transaction> transactions) {
    this.transactions = new HashSet<>(transactions);
  }
}
