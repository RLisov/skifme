package com.shaq.skifme.utils;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Convert {



    public static final String calcMd5(String s,String p) {
        try {

            //salt hash string
            final MessageDigest saltDigest = MessageDigest.getInstance("md5");
            saltDigest.update(s.getBytes());
            final byte[] saltBytes = saltDigest.digest();
            final StringBuilder saltString = new StringBuilder();
            for (int i = 0; i < saltBytes.length; i++) {
                saltString.append(String.format("%02X", saltBytes[i]));
            }

            //pass hash string
            final MessageDigest passDigest = MessageDigest.getInstance("md5");
            passDigest.update(p.getBytes());
            final byte[] passBytes = passDigest.digest();
            final StringBuilder passString = new StringBuilder();
            for (int i = 0; i < passBytes.length; i++) {
                passString.append(String.format("%02X", passBytes[i]));
            }


            String hexSum = passString.toString().toLowerCase() + saltString.toString().toLowerCase();
            Log.e("hashSum", hexSum);

            //Generate resultHash
            final MessageDigest resultHashDigest = MessageDigest.getInstance("md5");
            resultHashDigest.update(hexSum.getBytes());
            final byte[] bytes = resultHashDigest.digest();
            final StringBuilder resultString = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                resultString.append(String.format("%02X", bytes[i]));
            }

            Log.e("hash",resultString.toString().toLowerCase());
            return resultString.toString().toLowerCase();

        }


        catch (Exception exc) {
            exc.printStackTrace();
        }
        return "";
    }
}