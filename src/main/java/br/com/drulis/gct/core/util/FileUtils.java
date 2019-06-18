package br.com.drulis.gct.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
/**
 * @author Victor Drulis Oliveira
 * @since 18 de jun de 2019
 * @contact victordrulis@gmail.com
 *
 */
public abstract class FileUtils {
    
    public FileUtils() {};
    
    public static void criarArquivo() throws IOException {
        String path = "C:\\Users\\vdrul\\Desktop\\log_" + Calendar.DAY_OF_YEAR + ".txt";
        System.out.println(path);
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        String linha = "UOOOOOOOOOOOOOOWWW!!";
        buffWrite.append(linha + "\n");
        buffWrite.close();
    }
    
    public static void leitor(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        
        while (true) {
            if (linha != null) 
                System.out.println(linha);
            else
                break;
            linha = buffRead.readLine();
        }
        
        buffRead.close();
    }
}
