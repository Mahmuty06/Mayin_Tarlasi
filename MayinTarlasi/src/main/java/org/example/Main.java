package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Mayın tarlası oyununa hoşgeldiniz !");
        Scanner scanner = new Scanner(System.in);

        //Değerlendirme Formu 7 : Dizi (matris) Satır Boyutu Kullanıcıdan Alınmıştır.
        System.out.println("Mayın tarlasının satır sayısını 2 ile 100 arasında giriniz.");
        int rowNumber = scanner.nextInt();
        while (rowNumber < 2 || rowNumber >= 100) {
            System.out.println("Satır numarası 2 ile 100 arasında olmalıdır!. Lütfen tekrar giriniz!");
            rowNumber = scanner.nextInt();

        }
        //Değerlendirme Formu 7 : Dizi (matris) Sütun Boyutu Kullanıcıdan Alınmıştır.
        System.out.println("Mayın tarlasının sütun sayısını 2 ile 100 arasında giriniz.");
        int colNumber = scanner.nextInt();

        while (colNumber < 2 || colNumber >= 100) {
            System.out.println("Sütun numarası 2 ile 100 arasında olmalıdır!. Lütfen tekrar giriniz!");
            colNumber = scanner.nextInt();
        }

        MineSweeper mine = new MineSweeper(rowNumber, colNumber);
        mine.run();
    }
}