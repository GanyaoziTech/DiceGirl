package tech.ganyaozi.dicegirl.netty.http;

import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.security.SecureRandom;
import java.util.Random;

/**
 */
public class UtilTest {


    @Test
    public void main() throws Exception {
        Random RANDOM = new SecureRandom();
        byte[] salt = new byte[64];
        RANDOM.nextBytes(salt);
        //       String str = new String(salt,"UTF-8");
        String str = new BASE64Encoder().encode(salt);
        System.out.println(str);
    }

}
