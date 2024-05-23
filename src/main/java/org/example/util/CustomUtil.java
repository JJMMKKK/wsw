package org.example.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CustomUtil {

    public String createTemporaryPassword(int length){

        char[] passwordChar = new char[]{
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        };
        StringBuilder tempPassword = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            passwordChar[i] = passwordChar[random.nextInt(passwordChar.length)];
            tempPassword.append(passwordChar[i]);
        }
        return tempPassword.toString();
    }

}
