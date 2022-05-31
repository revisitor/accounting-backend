package ru.mtrefelov.accounting.rest;

import java.net.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.mtrefelov.accounting.core.Transaction;
import ru.mtrefelov.accounting.service.TransactionService;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {
  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  // @GetMapping
  // public Set<Transaction> getTransactionsByAccountId() {

  // }

  @GetMapping("{id}")
  public Transaction getTransaction(@PathVariable Long id) {
    return transactionService.getTransactionById(id).orElseThrow();
  }

  @PostMapping
  public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) throws URISyntaxException {
    Transaction created = transactionService.createTransaction(transaction);
    URI uri = new URI("/api/transactions/" + created.getId());
    return ResponseEntity.created(uri).body(created);
  }

  @PutMapping("{id}")
  public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
    transactionService.updateTransaction(transaction);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Transaction> deleteTransaction(@PathVariable Long id) {
    transactionService.deleteTransaction(id);
    return ResponseEntity.noContent().build();
  }
}
