package com.occn.ai.common.utils;

import com.occn.ai.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RsaUtils {

    public static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMi6iU/goMNpF2xlqaLSLI/VAN4zhwK64+DZA7nOEazqtQaLNqQw9EUfiUeSZLdwubBMs11y2/te48qxymwEijPeajB4KSFkrnsk6FVXV2WOkwVt8cNk4YOP3jBd0jG9KNxm6E0i7rM6+XTCAxTvx36vciD6B/1aD64FcaCB7IGvAgMBAAECgYACAS7m39DrMqz7taY0jJHKW++2yF/0TIaDE9fALskpZ2yoZJKGoECIQjDeSJ194X/RPWrbJ47/2yo+N3lUi9UPfATV2Ck5aUEL2F0tnCBJNoNeKBAEDCxGGv5DOnBUyFI+B5S5lRL+vkqxDd8HFUQhUu47LlL/V/oRfo+xFz5AOQJBANrpkqbjWAeHhHSphaLjriHZ0ew8QkB3ou2ZByc+HZaOMpu408r2neK5rNMyEOUbjI22iZic/7tmfcpHt2+kbEkCQQDqvFGMA6fuFekIoiuy7Z54bQ72ZXk+4qFcTtpR71KsXR4H6GslSVDhkGptH0LmaHByFxL1ZNzGM0V/WDNpw043AkEAyONhZe7pAHfXEGvkq7c+O+g0oRInRhvDk0BD9TomUtwJCygQi4fHGaGEPIcMRAME96MUH82rTGDgfHlVl4kOqQJACwcz8ZcOYbIn058C3JIrWs8M3+iejKTLHfIacbUDtlNdoNN7jTHnmeFWDrBkTTzk44dP/bVyZeFsx3HWYIarewJBAIiMJEEvwspg/RSGc270kxmAV1zpZuZoUEffRwbLkG1o3zXOOwXG9T88AIJKMAsIdO1WN+MlkDcRai6fcz2Mixo=";
    public  static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIuolP4KDDaRdsZami0iyP1QDeM4cCuuPg2QO5zhGs6rUGizakMPRFH4lHkmS3cLmwTLNdctv7XuPKscpsBIoz3moweCkhZK57JOhVV1dljpMFbfHDZOGDj94wXdIxvSjcZuhNIu6zOvl0wgMU78d+r3Ig+gf9Wg+uBXGggeyBrwIDAQAB";

    private static final Logger logger = LoggerFactory.getLogger(RsaUtils.class);

    /**
     * RSA 密码解密
     * @param encryptedText 加密的字符串
     * @return 解密后的字符串
     */
    public static String decryptRSA(String encryptedText) {
        try {
            // 将Base64编码的私钥字符串解码为字节数组
            byte[] privateKeyBytes = Base64.getDecoder().decode(PRIVATE_KEY);
            // 根据私钥字节数组生成PrivateKey对象
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            // 使用Cipher进行解密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            // 将Base64编码的密文解码为字节数组
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            // 执行解密操作
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            // 将解密后的字节数组转为字符串
            return new String(decryptedBytes);
        } catch (Exception e) {
            logger.error("RSA 密码解密失败！", e);
            throw new BusinessException();
        }

    }

}
