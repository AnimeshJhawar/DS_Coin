package DSCoinPackage;

public class TransactionQueue {

  public Transaction firstTransaction;
  public Transaction lastTransaction;
  public int numTransactions;
  
  public void AddTransactions (Transaction transaction) {
    if(firstTransaction ==null)
    {
      firstTransaction = transaction;
    }
    else {
      lastTransaction.next = transaction;
    }
    lastTransaction = transaction;
    numTransactions++;
    }
  
  public Transaction RemoveTransaction () throws EmptyQueueException {
    if(numTransactions==0)
    {
      throw new EmptyQueueException();
    }
    numTransactions -= 1;
    Transaction rem = firstTransaction;
    firstTransaction = firstTransaction.next;
    rem.next = null;
    return rem;   
    }

  public int size() {
    return numTransactions;
  }
}
