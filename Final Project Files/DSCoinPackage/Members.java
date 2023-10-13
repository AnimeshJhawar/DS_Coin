package DSCoinPackage;

import java.util.*;
import HelperClasses.Pair;

public class Members
 {

  public String UID;
  public List<Pair<String, TransactionBlock>> mycoins;
  public Transaction[] in_process_trans;

  public void initiateCoinsend(String destUID, DSCoin_Honest DSobj) {
    Transaction tobj = new Transaction();
    tobj.coinID = mycoins.get(0).get_first();
    tobj.coinsrc_block = mycoins.get(0).get_second();
    mycoins.remove(0);
    tobj.Source = this;
    Members dest = new Members();
    for(int i=0; i<DSobj.memberlist.length;i++)
    {
      if(DSobj.memberlist[i].UID.equals(destUID))
      {
        dest = DSobj.memberlist[i];
        break; 
      }
    }
    tobj.Destination = dest; 
   for(int i=0;;i++)
    {
      if(in_process_trans[i]==null)
      {
        in_process_trans[i] = tobj;
        break;
      }
    }
    DSobj.pendingTransactions.AddTransactions(tobj);
  }


  public Pair<List<Pair<String, String>>, List<Pair<String, String>>> finalizeCoinsend (Transaction tobj, DSCoin_Honest DSObj) throws MissingTransactionException {
   
    return null;
  }

  public void MineCoin(DSCoin_Honest DSObj) {
  Transaction minerRewardTransaction = new Transaction();
  minerRewardTransaction.coinID = String.valueOf(Integer.parseInt(DSObj.latestCoinID) + 1);
  DSObj.latestCoinID = minerRewardTransaction.coinID;
  minerRewardTransaction.Source = null;
  Members des = new Members();
  des.UID = "miner";
  minerRewardTransaction.Destination = des; 
  minerRewardTransaction.coinsrc_block = null;
  }  

  public void MineCoin(DSCoin_Malicious DSObj) {
  Transaction minerRewardTransaction = new Transaction();
  minerRewardTransaction.coinID = String.valueOf(Integer.parseInt(DSObj.latestCoinID) + 1);
  DSObj.latestCoinID = minerRewardTransaction.coinID;
  minerRewardTransaction.Source = null;
  Members des = new Members();
  des.UID = "miner";
  minerRewardTransaction.Destination = des; 
  minerRewardTransaction.coinsrc_block = null;
  }  
}
