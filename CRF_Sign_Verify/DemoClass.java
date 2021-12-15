public class DemoClass {
    
    // Demo usage of the CRF class to get string mappings
    public static void demo_CRF()
    {
        CRF CRF_obj = new CRF(64);  // Specify output length here
        String s = "password";
        String output = CRF_obj.Fn(s);
        String expected_output = "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8".substring(0,CRF_obj.outputsize);

        System.out.println("Fn("+s+") = "+output+"\t | Expected: "+expected_output+"\n");
    }

    public static void demo_signature() {

        // == START
        //This will handled by us, you will not need to generate keys.
        SignatureKeys keys = Signature.keygen();
        String sk = keys.get_sk();
        String vk = keys.get_vk();
        // == END

        String message = "Attack at dawn__________________________________________________";
        String signature;
        try {
            signature = Signature.BoundedMsgSign(message, sk);
        }
        catch(Exception e) {
            e.printStackTrace();
            signature = "Invalid";
        }

        System.out.println("Input Message: \""+message+"\"\t| Signature: "+signature+"\n");

        boolean messageIsUnaltered = false;

        messageIsUnaltered = Signature.BoundedMsgVerify(message, vk, signature);

        if(messageIsUnaltered) {
            System.out.println("Message \""+message+"\" is unaltered. \t| Expected: Message \"Attack at dawn\" is unaltered.");
        }
        else
        {
            System.out.println("Signature is invalid or Message \""+message+"\" is altered. \t| Expected: Message \"Attack at dawn\" is unaltered.");
        }

        String randomSignature = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
        messageIsUnaltered = Signature.BoundedMsgVerify(message, vk, randomSignature);

        if(messageIsUnaltered) {
            System.out.println("Message \""+message+"\" is unaltered. \t| Expected: Signature is invalid or Message \"Attack at dawn\" is altered.");
        }
        else
        {
            System.out.println("Signature is invalid or Message \""+message+"\" is altered. \t| Expected: Signature is invalid or Message \"Attack at dawn\" is altered.");
        }

        String alteredMessage = "Do not attack at dawn___________________________________________";
        messageIsUnaltered = Signature.BoundedMsgVerify(alteredMessage, vk, signature);

        if(messageIsUnaltered) {
            System.out.println("Message \""+alteredMessage+"\" is unaltered. \t| Expected: Signature is invalid or Message \"Do not attack at dawn\" is altered");
        }
        else
        {
            System.out.println("Signature is invalid or Message \""+alteredMessage+"\" is altered. \t| Expected: Signature is invalid or Message \"Do not attack at dawn\" is altered");
        }

    }

    public static void main(String[] args) {
        demo_CRF();
        demo_signature();
    }
}


