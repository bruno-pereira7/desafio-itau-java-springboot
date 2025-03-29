package desafio.itau.springboot.controller;

@RestController
@RequestMapping("/transacao")
public class TransactionController{

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService){
    this.transactionService = transactionService;
  }

  @PostMapping
  public ResponseEntity<Void>createTransaction(@Valid @RequestBody TransactionRequest request){
    if(request.getDataHora().isAfter(OffsetDateTime.now())){
      return ResponseEntity.unprocessableEntity().build();
    }
    transactionService.addTransaction(new Transaction(request.getValor(), request.getDataHora()));:
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping
  public ResponseEntity<Void> clearTransactions(){
    transactionService.clearTransactions();
    return ResponseEntity.ok().build();
  }
}