package DSCoinPackage;

import HelperClasses.CRF;

public class BlockChain_Honest {

  public int tr_count;
  public static final String start_string = "DSCoin";
  public TransactionBlock lastBlock;

  public void InsertBlock_Honest (TransactionBlock newBlock) {
    CRF obj = new CRF(64);
    String check_nonce;
    long z = 1000000001;
    for(;;z++)
  {
    check_nonce = obj.Fn(lastBlock.dgst + "#" + newBlock.trsummary + "#" + String.valueOf(z));
    if(check_nonce.substring(0,4).equals("0000"))
    {
      break;
    }
  }
    newBlock.nonce = String.valueOf(z);
    newBlock.dgst = check_nonce;
    newBlock.previous = lastBlock;
    lastBlock = newBlock;
  }
}
