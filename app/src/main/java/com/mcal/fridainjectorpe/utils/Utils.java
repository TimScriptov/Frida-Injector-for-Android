package com.mcal.fridainjectorpe.utils;

import android.content.Context;
import android.content.res.AssetManager;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Utils {
    public static void extractAsset(@NotNull Context context, String assetName, File dest) throws IOException {
        AssetManager assetManager = context.getAssets();
        InputStream in = assetManager.open(assetName);
        OutputStream out = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        in.close();
        out.flush();
        out.close();
    }

    public static void writeToFile(File dest, String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    new FileOutputStream(dest));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    public static String readFromFile(File src) {
        try {
            InputStream inputStream = new FileInputStream(src);
            return readFromFile(new FileInputStream(src));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @NotNull
    public static String readFromFile(InputStream is) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String content;
        StringBuilder stringBuilder = new StringBuilder();
        while ((content = bufferedReader.readLine()) != null) {
            stringBuilder.append(content).append("\n");
        }
        is.close();
        return stringBuilder.toString();
    }
}