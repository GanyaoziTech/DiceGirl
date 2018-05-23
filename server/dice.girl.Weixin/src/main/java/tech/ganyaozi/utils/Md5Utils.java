package tech.ganyaozi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Derek.P.Dai[dp419936514@gmail.com]
 */
public class Md5Utils {
    private final static char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static final int BIT_LENGTH_16 = 16;
    private static final int BIT_LENGTH_28 = 28;

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        int t;
        for (int i = 0; i < BIT_LENGTH_16; i++) {
            t = bytes[i];
            if (t < 0) {
                t += 256;
            }
            sb.append(HEX_DIGITS[(t >>> 4)]);
            sb.append(HEX_DIGITS[(t % 16)]);
        }
        return sb.toString();
    }

    public static String md5(String input) {
        return md5(input, 32);
    }

    public static String md5(String input, int bit) {
        try {
            MessageDigest md = MessageDigest.getInstance(System.getProperty(
                    "Md5Utils.algorithm", "Md5"));
            if (bit == BIT_LENGTH_16) {
                return bytesToHex(md.digest(input.getBytes("utf-8")))
                        .substring(8, 24);
            }
            if (bit == BIT_LENGTH_28) {
                return bytesToHex(md.digest(input.getBytes("utf-8")))
                        .substring(2, 30);
            }
            return bytesToHex(md.digest(input.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String md5(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance(System.getProperty(
                    "Md5Utils.algorithm", "Md5Utils"));
            return bytesToHex(md.digest(bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String md5(InputStream is) throws NoSuchAlgorithmException, IOException {
        String hash;
        byte[] buffer = new byte[4096];
        MessageDigest md5 = MessageDigest.getInstance("Md5Utils");
        int numRead;
        while ((numRead = is.read(buffer)) > 0) {
            md5.update(buffer, 0, numRead);
        }
        hash = Md5Utils.bytesToHex(md5.digest());
        return hash;
    }
}
