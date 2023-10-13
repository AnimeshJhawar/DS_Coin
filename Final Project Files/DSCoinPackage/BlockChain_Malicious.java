package DSCoinPackage;

import HelperClasses.*;

public class BlockChain_Malicious {

  public int tr_count;
  public static final String start_string = "DSCoin";
  public TransactionBlock[] lastBlocksList;

  public static boolean checkTransactionBlock (TransactionBlock tB) {
    if(!(tB.dgst.substring(0,4).equals("0000")))
    {
      return false;
    }
    
    CRF obj = new CRF(64);
    
    if(tB.previous.dgst == null)
    {
     tB.previous.dgst = start_string; 
    }

    if(tB.dgst != obj.Fn(tB.previous.dgst+ "#" + tB.trsummary+ "#" + tB.nonce))
    {
     return false; 
    }
    MerkleTree ok = new MerkleTree(); 
    if(tB.trsummary != ok.Build(tB.trarray)) 
    { 
     return false;
    }
    for(int i=0; tB.trarray[i]!=null;i++)
    {
      if(!tB.checkTransaction(tB.trarray[i]))
      {
        return false;
      }
    }
    return true;
  }

  public TransactionBlock FindLongestValidChain () {
  
  int[] novalidblock = new int[lastBlocksList.length]; 
  TransactionBlock[] validblock = new TransactionBlock[lastBlocksList.length];

    for(int i=0; i<lastBlocksList.length;i++)
    {
      TransactionBlock lastBlock = lastBlocksList[i];
      int validcount=0;
      TransactionBlock checkBlock = lastBlock;
      while(checkBlock !=null)
      {
        if(checkTransactionBlock(checkBlock))
        {
          validcount+=1;
        }
        else
        {
          lastBlock = checkBlock.previous;
          validcount=0;
        }
        checkBlock = checkBlock.previous;
      }
      novalidblock[i] = validcount;
      validblock[i] = lastBlock; 
    }
    int longest = 0;
    for ( int i = 0; i < novalidblock.length; i++ )
    {
        if ( novalidblock[i] > novalidblock[longest] ) 
          longest = i;
    }
    return validblock[longest];
  }

  public void InsertBlock_Malicious (TransactionBlock newBlock) {
    TransactionBlock lastBlock = FindLongestValidChain();
    CRF obj = new CRF(64);
    String check_nonce;
    long s = 1000000001;
    for(;;s++)
    {
      check_nonce = obj.Fn(lastBlock.dgst + "#" + newBlock.trsummary + "#" + String.valueOf(s));
      if(check_nonce.substring(0,4).equals("0000"))
      {
        break;
      }
    }
      newBlock.nonce = String.valueOf(s);
      newBlock.dgst = check_nonce;
      newBlock.previous = lastBlock;
      lastBlock = newBlock;
      for(int i=0; ;i++)
      {
        if(lastBlocksList[i] == null)
        {
          lastBlocksList[i] = newBlock;
        }
      }
    }
}
