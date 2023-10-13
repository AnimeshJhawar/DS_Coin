package DSCoinPackage;

import HelperClasses.MerkleTree;
import HelperClasses.CRF;

public class TransactionBlock {

  public Transaction[] trarray;
  public TransactionBlock previous;
  public MerkleTree Tree;
  public String trsummary;
  public String nonce;
  public String dgst;

  TransactionBlock(Transaction[] t) {
    trarray = t.clone();
    trsummary = Tree.Build(t);
    CRF obj = new CRF(64);
    String check_dgst;
    long s = 1000000001;
    for(;;s++)
    {
      check_dgst = obj.Fn(this.previous.dgst + "#" + trsummary + "#" + String.valueOf(s));
      if(check_dgst.substring(0,4).equals("0000"))
      {
       break;
      }
    }
    nonce = String.valueOf(s);
    dgst = check_dgst;
    }

  public boolean checkTransaction (Transaction t) {
    if(t.coinsrc_block ==null)
    {
      return true; // Pg 9 if coinsrc block is null, you can declare that the transaction is valid.
    }
    Transaction tcheck = new Transaction();
    for(int i =0; t.coinsrc_block.trarray[i]!=null;i++)
    {
      tcheck = t.coinsrc_block.trarray[i];
      if(tcheck.coinID == t.coinID && tcheck.Destination.UID == t.Source.UID)
      {
        return false;
      }
    }
    TransactionBlock check = this;
    do
    {
      for(int j=0; check.trarray[j] !=null; j++)
      {
        if(t.coinID == check.trarray[j].coinID)
          return false;
      }
      check = check.previous;
    }while(check!=t.coinsrc_block);
  return true;
  }
}
 