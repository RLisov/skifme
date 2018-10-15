package com.shaq.skifme.utils;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Convert {


    public static String calcMd5(String s,String p) {
        try {
            // Create MD5 Hash of salt and password
            MessageDigest saltHash = java.security.MessageDigest.getInstance("MD5");
            saltHash.update(s.getBytes());
            byte saltMessageDigest[] = saltHash.digest();

            MessageDigest passHash = java.security.MessageDigest.getInstance("MD5");
            passHash.update(p.getBytes());
            byte passMessageDigest[] = passHash.digest();


            // Create Hex String for Salt and Pass
            StringBuffer saltHexString = new StringBuffer();
            for (int i=0; i<saltMessageDigest.length; i++)
                saltHexString.append(Integer.toHexString(0xFF & saltMessageDigest[i]));
            Log.e("salt",saltHexString.toString());

            StringBuffer passHexString = new StringBuffer();
            for (int i=0; i<passMessageDigest.length; i++)
                passHexString.append(Integer.toHexString(0xFF & passMessageDigest[i]));

            //Hex salt + Hex pass
            String hexSum = saltHexString.toString() + passHexString.toString();


            //Create MD5 hash for session hash
            MessageDigest hexHash = java.security.MessageDigest.getInstance("MD5");
            hexHash.update(hexSum.getBytes());
            byte HashMessageDigest[] = hexHash.digest();

            //Create Hex string for session hash
            StringBuffer SumHexString = new StringBuffer();
            for (int i=0; i<passMessageDigest.length; i++)
            SumHexString.append(Integer.toHexString(0xFF & HashMessageDigest[i]));
            Log.e("sum",SumHexString.toString());

            return SumHexString.toString();

        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}