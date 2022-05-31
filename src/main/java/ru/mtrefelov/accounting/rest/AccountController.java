package ru.mtrefelov.accounting.rest;

import java.net.*;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.mtrefelov.accounting.core.Account;
import ru.mtrefelov.accounting.service.AccountService;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping
  public Set<Account> getAccounts() {
    return null;
  }

  @PostMapping
  public ResponseEntity<Account> createAccount(@RequestBody Account account) throws URISyntaxException {
    Account created = accountService.createAccount(account);
    URI uri = new URI("/api/clients/" + created.getId());
    return ResponseEntity.created(uri).body(created);
  }

  @PutMapping("{id}")
  public ResponseEntity<Account> updateAccount(@PathVariable Integer id, @RequestBody Account account) {
    accountService.updateAccount(account);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Account> deleteAccount(@PathVariable Integer id) {
    accountService.deleteAccount(id);
    return ResponseEntity.noContent().build();
  }
}
