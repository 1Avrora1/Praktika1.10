/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dz3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author NicSt
 */
public class FileUtils {
    public static boolean checkWorkDirectory(String path){
        File workDir = new File(path);
        if(workDir.exists()){
            return true;
        }
        else{
            return false;
        }
    }
    public static void writestringToFile(String path, String data) throws IOException{
        FileWriter fw = new FileWriter(path);
        fw.write(data);
        fw.close();
    }
}
