public class SignatureKeys {
    public String sk;
    public String vk;
    public SignatureKeys(String _sk, String _vk) {
        sk = _sk;
        vk = _vk;
    }
    public String get_sk() {
        return sk;
    }
    public String get_vk() {
        return vk;
    }
}
