package lxc.nsu.edu.com.ssft.chapter02.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 123 on 2017/7/3.
 */

public class MD5Utils {
    /**
     * md5 加密算法
     * @param text
     * @return
     */
    public static String encode(String text){
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b:result){
                int number=b&0xff;
                String hex=Integer.toHexString(number);
                if (hex.length()==1){
                    sb.append("0"+hex);
                }else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            //can't reach
            return "";
        }
    }
}
