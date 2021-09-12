package ru.philosophyit.internship.javabase.files;

import java.io.File;
import java.util.Objects;


public class Main {
    static String counter = "--";

    public static void main(String[] args)
    {
        File root = new File("C:\\Users\\i_ver\\IdeaProjects\\Vertola_VN_Java_2021_Liga\\src\\main\\java\\ru\\philosophyit\\internship\\javabase");
        treeOfFiles(root);
    }

    static void treeOfFiles(File dir){

            for(File file : Objects.requireNonNull(dir.listFiles())) {
                if(!file.isDirectory()) {
                    counter = counter.substring(0, counter.length() - 1);
                    counter += "*";
                }
                printFileName(file);
                if(file.isDirectory()) {
                    counter += "--";
                    treeOfFiles(file);
                }
            }

        counter = counter.substring(0, counter.length() - 2); //Also can use chop
    }

    static void printFileName(File file){
        System.out.println(counter + " " + file.getName());
    }
}
