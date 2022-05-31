package ru.mtrefelov.accounting.service;

import ru.mtrefelov.accounting.core.Account;
import ru.mtrefelov.accounting.persistence.AccountEntity;

public class AccountAdapter implements Adapter<AccountEntity, Account> {
  @Override
  public Account entityToModel(AccountEntity entity) {
    Account model = new Account(entity.getName());
    return model;
  }

  @Override
  public AccountEntity modelToEntity(Account model) {
    AccountEntity entity = new AccountEntity();
    entity.setId(model.getId());
    entity.setName(model.getName());
    return entity;
  }
}
