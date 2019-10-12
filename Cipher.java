/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cipher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tuesday
 */
class InvalidCipherException extends Exception {

    public InvalidCipherException(String message) {
        super(message);
    }
}

public class Cipher {

    ////////////////////////////////////////////////////////////////////////////
    public static String generateCaesar(String input, boolean encryption, int shift, String alphabet) {

//        if (shift < 1 || shift > alphabet.length() - 1) {
//            System.out.print("Unecessary shift:" + shift);
//            shift = shift % alphabet.length();
//        }
//        if (input == null && input.length() == 0) {
//            System.out.println("error input empty");
//            return null;
//        }
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < input.length(); x++) {
            char temp = input.charAt(x);
            for (int y = 0; y < alphabet.length(); y++) {
                if (temp == alphabet.charAt(y)) {
                    if (encryption) {
                        int position = (y + shift) % alphabet.length();
                        builder.append(alphabet.substring(position, position + 1));
                    } else {
                        int position = (y - shift + alphabet.length()) % alphabet.length();
                        builder.append(alphabet.substring(position, position + 1));
                    }
                    break;

                }

            }

        }

        return builder.toString();
    }

    public static String generateAtbash(String input, String alphabet) {
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < input.length(); x++) {
            for (int y = 0; y < alphabet.length(); y++) {
                if (input.charAt(x) == alphabet.charAt(y)) {
                    int position = alphabet.length() - 1 - y;
                    builder.append(alphabet.substring(position, position + 1));
                    break;
                }

            }
        }

        return builder.toString();
    }

    public static String generateVigenere(String input, String key, String alphabet, boolean isEncode, boolean isAutokey) {
        StringBuilder builder = new StringBuilder();
        int y = 0;
        int z = 0;
        int keyLength = key.length();
        int alphaLength = alphabet.length();
        if (isEncode) {
            for (int x = 0; x < input.length(); x++) {
                int temp3;
                if (!isAutokey) {
                    temp3 = alphabet.indexOf(key.substring(y % keyLength, (y % keyLength) + 1));
                } else {
                    if (y >= keyLength) {
                        temp3 = alphabet.indexOf(input.substring(z, z + 1));
                        z++;
                    } else {
                        temp3 = alphabet.indexOf(key.substring(y % keyLength, (y % keyLength) + 1));
                    }
                }
                int temp4 = ((alphabet.indexOf(input.substring(x, x + 1)) + temp3));
                if (temp4 < 0) {
                    temp4 += alphaLength;
                }
                if (temp4 > alphaLength) {
                    temp4 -= alphaLength;
                }
                builder.append(alphabet.substring(temp4 % alphaLength, temp4 % alphaLength + 1));

                y++;
                //}
            }
        } else {
            for (int x = 0; x < input.length(); x++) {
                if (alphabet.indexOf(input.substring(x, x + 1)) != - 1) {
                    //int temp1 = 0;
                    //int temp2 = ;
                    int temp3;
                    if (!isAutokey) {
                        temp3 = alphabet.indexOf(key.substring(y % key.length(), (y % key.length()) + 1));

                    } else {
                        if (y >= key.length()) {
                            temp3 = alphabet.indexOf(input.substring(z, z + 1));//had output why?
                            //System.out.println(temp3);
                            z++;
                        } else {
                            temp3 = alphabet.indexOf(key.substring(y % key.length(), (y % key.length()) + 1));
                        }
                    }
                    int temp4 = ((alphabet.indexOf(input.substring(x, x + 1)) - temp3));

                    if (temp4 < 0) {
                        temp4 += alphabet.length();
                    }
                    if (temp4 > alphabet.length()) {
                        temp4 -= alphabet.length();
                    }
                    ////System.out.println("temp1:" + temp1 + "  (input)temp2:" + temp2 + "  (key)temp3:" + temp3 + "  temp4:" + temp4);
                    builder.append(alphabet.substring(temp4 % alphabet.length(), temp4 % alphabet.length() + 1));
                    y++;

                }
            }
        }
        return builder.toString();
    }
}
