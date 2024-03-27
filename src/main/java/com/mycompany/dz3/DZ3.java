/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.dz3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NicSt
 */
public class DZ3 {

    public static void main(String[] args) {
        System.out.println("Практическое задание - 1.10, Студент - Стрыгин Никита Владиславович, Группа - РИБО-03-22, Вариант - 1");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> fileNames = new ArrayList<>();

        try {
            System.out.println("Введите количество файлов для склейки: ");
            int numFiles = Integer.parseInt(reader.readLine());

            for (int i = 0; i < numFiles; i++) {
                System.out.println("Введите путь к файлу " + (i + 1) + ": ");
                String filePath = reader.readLine();
                boolean isExistWorkDir = FileUtils.checkWorkDirectory(filePath);
                if (isExistWorkDir){
                    fileNames.add(filePath);
                }
                else{
                    try{
                        File f = new File(filePath);
                        f.createNewFile();
                        System.out.println("Изначально такого файла не было, но мы его создали, теперь давайте его наполним");
                        System.out.println("Введите данные, которыми хотите наполнить файл:");
                        String data = reader.readLine();
                        FileUtils.writestringToFile(filePath, data);
                    }
                    catch(IOException ex){
                        System.out.println("При записи данных в файл " + filePath + " произошла ошибка, возможно путь к файлу введен не в том формате");
                        break;
                    }        
                    fileNames.add(filePath);
                }
            }

            String outputFilePath = getOutputFilePath(fileNames.get(0));
            toUniteFiles(fileNames, outputFilePath);

            System.out.println("Файл успешно склеен. Содержимое результирующего файла:");
            UnitedFileContent(outputFilePath);

        } catch (IOException | NumberFormatException e) {
            System.out.println("Ошибка ввода-вывода или некорректное число файлов. Попробуйте снова.");
        }
    }

    private static String getOutputFilePath(String firstFilePath) {
        File firstFile = new File(firstFilePath);
        String parentDir = firstFile.getParent();
        return parentDir + File.separator + "result.txt";
    }

    private static void toUniteFiles(List<String> fileNames, String outputFilePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (String fileName : fileNames) {
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }
        }
    }

    private static void UnitedFileContent(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    
    }
}
