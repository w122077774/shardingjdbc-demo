package shard.jdbc.shardingjdbc.util;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @Author: wangjunjie 2019/4/1 10:03
 * @Description:
 * @Version: 1.0.0
 * @Modified By: xxx 2019/4/1 10:03
 */
public class JasyptUtil {

    /**
     * 加密方法
     * @param salt 盐值
     * @param targetString 待加密字符串
     * @return 密文
     */
    public static String encrypt(String salt, String targetString) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(salt);
        return encryptor.encrypt(targetString);
    }

    /**
     * 解密方法
     * @param salt 盐值
     * @param targetString 待解密字符串
     * @return 明文
     */
    public static String decrypt(String salt,String targetString) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(salt);
        return encryptor.decrypt(targetString);
    }

    public static void main(String[] args) {
        String salt = "wjj";
        String password = "w921211d";
        // 进行加密操作
        String encryptString1 = encrypt(salt, password);
        // 进行解密操作
        String decryptString1 = decrypt(salt, encryptString1);
        // 输出明文和密文
        System.out.println("encryptString1="+encryptString1);
        System.out.println("decryptString1="+decryptString1);
    }
}
