package com.self.down;

import com.self.http.HttpUtils;

import java.io.*;
import java.util.Collections;

/**
 * @author shichen
 * @create 2018/8/31
 * @desc
 */
public class DownloadPicMainTest {

    public static void main(String[] args) throws Exception {

        byte[] responseBytes = HttpUtils.getBinary("https://esscraftsman.51kupai.com/1e0af5b5-0d2f-421d-91b9-7515d50b6d82", Collections.EMPTY_MAP);

        if (null != responseBytes) {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("a.jpg"));
            bufferedOutputStream.write(responseBytes);
            bufferedOutputStream.flush();
        }
    }

}
