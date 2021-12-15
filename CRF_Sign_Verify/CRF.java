import HelperClasses.Pair;
import HelperClasses.sha256;
import java.lang.Math;
import java.util.*;
import java.io.*;
import java.util.Random;

public class CRF extends sha256 {

    // Stores the output size of the function Fn()
    public int outputsize;

    CRF(int size) {
        outputsize = size;
        assert outputsize <= 64;
    }

    // Outputs the mapped outputSize characters long string s' for an input string s
    public String Fn(String s) {
        String shasum = encrypt(s);
        return shasum.substring(0,outputsize);
    }

    /*==========================
    |- To be done by students -|
    ==========================*/
    //class practice
    public String FnPowerI( String s, int n)
    {
        String Output=" ";
     for(int i=0;i<n;i++)
     {
       Output = Fn(s);
       s= Output;
     }
     return Output;
    }

  //FindCollDeterministic()
    public Pair<String, String> FindCollDeterministic() {
    try{ 
     String base="0000";
        double x=Math.pow((double)16,(double)outputsize);
        int y= (int)x;
        String array[]=new String[y];
        String val=Fn(base);
        array[Integer.parseInt(val,16)]=base;        
        String convert=" ";
        String element=" ";
        for(int i =1;i<y+2;i++){
            convert=Fn(val);
            element=array[Integer.parseInt(convert,16)];
            if(element!=null){
                Pair<String,String> p =new Pair<String,String>(element,val);
                return p;
            }
            else{
                array[Integer.parseInt(convert,16)]=val;
                val=convert;
            }
        }
    }catch(Exception e)
    {
     System.out.println("Err in Deterministic CRF");       
    }
        return null;
    }
  
  //FindCollRandomized()
    public void FindCollRandomized() { 
        String attempt_filename = "FindCollRandomizedAttempts.txt";
        String outcome_filename = "FindCollRandomizedOutcome.txt";
    try{
     double x=Math.pow((double)16,(double)outputsize);
        int z= (int)x;  
      String[] array=new String[z];
    int y = 1000*(int)Math.pow((double)4,(double)outputsize); 
    FileOutputStream fs = new FileOutputStream(attempt_filename);
    PrintStream ps = new PrintStream(fs);    
    for(int i=1; i<y+2;i++){
        ps.println(RequiredString(outputsize));
    }

    FileInputStream fstream = new FileInputStream(attempt_filename);
    Scanner s = new Scanner(fstream);
        String val=" ";
        String convert=" ";
        String element=" ";
        boolean found=false;
        String output1=" ",output2=" ";   
        for(int i =1;i<y+1;i++){
            val = s.nextLine();
            convert=Fn(val);
            element=array[Integer.parseInt(convert,16)];
            if(element!=null){
                output1=element;
                output2=val;
                found=true;
                break;
            }
            else{
                array[Integer.parseInt(convert,16)]=val;
                val=convert;
            }
         }

    FileOutputStream fs2 = new FileOutputStream(outcome_filename);
    PrintStream ps2 = new PrintStream(fs2);
    if(found)
    {
     ps2.println("FOUND");
     ps2.println(output1);
     ps2.println(output2);   
    } 
    else
    {
    ps2.println("NOT FOUND");
    }
}catch(Exception e)
{
    System.out.println("Err in randomized CRF");
}
}

//random string generator
public static String RequiredString(int n)
{
String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+ "0123456789"+ "abcdefghijklmnopqrstuvxyz";
StringBuilder s = new StringBuilder(n);
int y;
for ( y = 0; y < n; y++) {
int index= (int)(AlphaNumericString.length()* Math.random());
s.append(AlphaNumericString.charAt(index));
}
return s.toString();
}

//


}


