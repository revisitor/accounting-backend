package ru.mtrefelov.accounting.service;

import java.util.Optional;

import ru.mtrefelov.accounting.core.Account;
import ru.mtrefelov.accounting.persistence.*;

public class AccountService {
  private final AccountRepository accountRepository;
  private final AccountAdapter adapter;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
    this.adapter = new AccountAdapter();
  }

  public Optional<Account> getAccountById(Integer id) {
    Optional<AccountEntity> account = accountRepository.findById(id);
    return account.map(adapter::entityToModel);
  }

  public Account createAccount(Account account) {
    AccountEntity created = accountRepository.save(adapter.modelToEntity(account));
    return adapter.entityToModel(created);
  }

  public void updateAccount(Account account) {
    AccountEntity entity = adapter.modelToEntity(account);
    accountRepository.updateOne(entity.getId(), entity.getName());
  }

  public Integer deleteAccount(Integer id) {
    accountRepository.deleteById(id);
    return id;
  }
}
