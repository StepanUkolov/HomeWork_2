package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader consolReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = consolReader.readLine();                          //Считываем с консоли имя файла
        consolReader.close();       //Закрываем поток для чтения

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

        StringBuilder stringBuilder = new StringBuilder();
        while (fileReader.ready()) {
            stringBuilder.append((char) fileReader.read());                 //Записываем содержимое файла в stringBuilder
        }
        fileReader.close();         //Закрываем поток для чтения

        Map<String, Integer> words = new TreeMap<String, Integer>();
        Pattern pattern = Pattern.compile("\\w+");                          //Регулярное выражение для поиска слов
        Matcher matcher = pattern.matcher(stringBuilder.toString().toLowerCase());   //Приводим в нижний регистр

        while (matcher.find()) {    //Ищем совпадения

            if (words.containsKey(matcher.group())) {                       //Если слово есть в списке
                words.put(matcher.group(), words.get(matcher.group()) + 1); //То увеличиваем значение на 1
            } else {
                words.put(matcher.group(), 1);                              //Иначе добавляем в список со значением 1
            }
        }

        int maxValue = 0;                                                   //Максимальное значение

        for (Map.Entry<String, Integer> pair : words.entrySet()) {          //Выводим список
            System.out.println(pair.getValue() + " - " + pair.getKey());
            if (maxValue < pair.getValue()) {                               //И ищем максимальное значение
                maxValue = pair.getValue();
            }
        }

        System.out.println("\nСамое частое: ");

        for (Map.Entry<String, Integer> pair : words.entrySet()) {          //Проходим второй раз
            if (maxValue == pair.getValue()) {
                System.out.println(pair.getValue() + " - " + pair.getKey());        //Выводим максимальное значение
            }
        }

    }
}
