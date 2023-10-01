package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//Degerlendirme Formu 5: Proje MineSweeper sınıfı içerisinde tasarlandı.
public class MineSweeper {

    private int mRowNumber;
    private int mColNumber;

    public MineSweeper(int rowNumber, int colNumber) {
        mRowNumber = rowNumber;
        mColNumber = colNumber;
    }
    // Degerlendirme Formu 6 : Oyunu Başlatma Fonksiyonu.
    public void run() {

        // Mayın tarlası matris olarak tutuluyor
        int[][] board = new int[mRowNumber][mColNumber];

        // Matriste tüm elemanlar 0 olarak ayarlanıyor.
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }

       // Haritada kaç tane mayın olacağı hesaplanıyor
        int mineCount = (int) Math.floor((double) (mRowNumber * mColNumber) / 4);

        Random random = new Random();

        //Degerlendirme Formu 8 : Mayın sayısı 0 olana kadar mayın tarlasına mayın yerleştireceğiz.
        while (mineCount != 0) {

            // Mayın yerleştirmek için rastgele bir satır ve sütun belirleniyor.
            int randomRowNumber = random.nextInt(mRowNumber);
            int randomColNumber = random.nextInt(mColNumber);

            // Rastgele seçtiğimiz noktada zaten bir mayın varsa döngüyü tekrar başlatıyoruz.
            if (board[randomRowNumber][randomColNumber] < 0) {
                continue;
            }

            // Rastgele seçtiğimiz noktaya -10 değerni vererek o noktaya mayın yerleştirmiş oluyoruz.
            board[randomRowNumber][randomColNumber] = -10;

            // Değerlendirme Formu 12 : Girilen noktada mayın yoksa etrafındaki mayın sayısı veya 0 değeri yerine yazılmıştır.

            // Mayın yerleştirdiğimiz noktanın üstündeki noktayı kontrol ediyoruz.
            if (randomRowNumber >= 1) {
                // Mayın yerleştirdiğimiz noktanın üstündeki noktayı 1 arttırıyoruz.
                board[randomRowNumber - 1][randomColNumber] += 1;
            }

            //  Mayın yerleştirdiğimiz noktanın Sağ üst noktayı kontrol ediyoruz.
            if (randomRowNumber >= 1 && randomColNumber < mColNumber - 1) {
                // Mayın yerleştirdiğimiz noktanın Sağ üst noktasını 1 arttırıyoruz.
                board[randomRowNumber - 1][randomColNumber + 1] += 1;
            }
            //  Mayın yerleştirdiğimiz noktanın Sağ noktayı kontrol ediyoruz.
            if (randomColNumber < mColNumber - 1) {
                // Mayın yerleştirdiğimiz noktanın Sağ  noktayı 1 arttırıyoruz.
                board[randomRowNumber][randomColNumber + 1] += 1;
            }

            //  Mayın yerleştirdiğimiz noktanın Sağ Alt noktayı kontrol ediyoruz.
            if (randomRowNumber < mRowNumber - 1 && randomColNumber < mColNumber - 1) {
                // Mayın yerleştirdiğimiz noktanın Sağ Alt noktasını 1 arttırıyoruz.
                board[randomRowNumber + 1][randomColNumber + 1] += 1;

            }

            //  Mayın yerleştirdiğimiz noktanın Alt noktayı kontrol ediyoruz.
            if (randomRowNumber < mRowNumber - 1) {
                // Mayın yerleştirdiğimiz noktanın Alt noktayı 1 arttırıyoruz.
                board[randomRowNumber + 1][randomColNumber] += 1;
            }

            //  Mayın yerleştirdiğimiz noktanın Sol Alt noktayı kontrol ediyoruz.
            if (randomColNumber > 0 && randomRowNumber < mRowNumber - 1) {
                // Mayın yerleştirdiğimiz noktanın Sol Alt noktayı 1 arttırıyoruz.
                board[randomRowNumber + 1][randomColNumber - 1] += 1;
            }

            //  Mayın yerleştirdiğimiz noktanın Sol noktayı kontrol ediyoruz.
            if (randomColNumber > 0) {
                // Mayın yerleştirdiğimiz noktanın Sol noktayı 1 arttırıyoruz.
                board[randomRowNumber][randomColNumber - 1] += 1;
            }

            //  Mayın yerleştirdiğimiz noktanın Sol Üst noktayı kontrol ediyoruz.
            if (randomRowNumber > 0 && randomColNumber > 0) {
                // Mayın yerleştirdiğimiz noktanın Sol Üst noktayı 1 arttırıyoruz.
                board[randomRowNumber - 1][randomColNumber - 1] += 1;
            }

            // Mayın yerleştirdiğimiz için mayın sayısını bir azaltıyoruz.
            mineCount -= 1;
        }

        // Mayınların konumlarını içi içe for döngüsü ile yazdırıyoruz.
        System.out.println("Mayınların konumları");
        for (int[] row : board) {
            for (int node : row) {
                if (node < 0) {
                    System.out.print("* ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.print("\n");
        }

        // Mayınların kapalı konumlarını iç içe for döngüsü ile yazdırıyoruz.
        System.out.println("Mayınların kapalı konumları.");
        for (int[] row : board) {
            for (int node : row) {
                System.out.print("- ");
            }
            System.out.print("\n");
        }

        // Kullanıcnın açtığı alanları tutmak için ikinci bir board tanımlıyoruz.
        boolean[][] secondBoard = new boolean[mRowNumber][mColNumber];

        // Kullanıcının kaç tane alan açtığının sayısını tutmak için değişken tanımlıyoruz.
        int openedNodeCounter = 0;

        // Şuan kullanıcı hiçbir alanı açamadığı için ikinci board da tüm değerleri false yapıyoruz.
        for (boolean[] row : secondBoard) {
            Arrays.fill(row, false);
        }
        // Degerlendirme Formu 9 : Kullanıcıdan İşaretlemek İstediği Satır ve Sütun Bilgisi Alınıyor.
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Satır numarasını giriniz:");
            int rowNumber = scanner.nextInt();

            // Degerlendirme Formu 10 : Kullanıcının seçtiği nokta dizinin sınırları içerisinde olup olmadığı kontrol edilerek
            //değilse uyarı mesajı veriliyor ve tekrar giriş isteniyor.
            while (rowNumber < 0 || rowNumber >= mRowNumber) {
                System.out.println("Satır numarası 0 ile " + (mRowNumber - 1) + " arasında olmalıdır!. Lütfen tekrar giriniz!");
                rowNumber = scanner.nextInt();
            }

            System.out.println("Sütun numarasını giriniz:");
            int colNumber = scanner.nextInt();

            // Degerlendirme Formu 10 : Kullanıcının seçtiği nokta dizinin sınırları içerisinde olup olmadığı kontrol edilerek
            //değilse uyarı mesajı veriliyor ve tekrar giriş isteniyor.
            while (colNumber < 0 || colNumber >= mRowNumber) {
                System.out.println("Sütun numarası 0 ile " + (mColNumber - 1) + " arasında olmalıdır!. Lütfen tekrar giriniz!");
                colNumber = scanner.nextInt();
            }
            // Değerlendirme Formu 13 : Kullanıcı mayına bastığında oyunu kaybedecek şekilde kontrol yapılmıştır.
            if (board[rowNumber][colNumber] < 0) {
                // Degerlendirme Formu 15 : Kullanıcının kaybetme durumunda uygun mesajlar kullanıcıya gösteriyor.
                System.out.println("BOOM! Game Over. Welcome to HELL !!!!");
                break;
            } else {
                int totalMineCount = (int) Math.floor((double) (mRowNumber * mColNumber) / 4);
                secondBoard[rowNumber][colNumber] = true;
                openedNodeCounter++;

                // Degerlendirme Formu 11 : Kullanıcının seçtiği alanları açık şekilde tekrar ekrana yazdırılır.
                for (int i = 0; i < board.length; i++) {
                    int[] row = board[i];
                    for (int j = 0; j < row.length; j++) {
                        if (secondBoard[i][j]) {
                            int node = board[i][j];
                            System.out.print(node + " ");
                        } else {
                            System.out.print("- ");
                        }
                    }
                    System.out.print("\n");
                }
                // Degerlendirme Formu 14 : Tüm noktalar mayınsız bir şekilde seçilirse oyunu kazanmanın kontrolü yapılıyor.
                // Mayın tarlasında kullanıcının açtığı toplam alan ile haritadaki toplam mayın sayısını toplayınca mayın tarlasındaki toplam alana sayısına eşit ise kullanıcı oyunu kazanmıştır.
                if(totalMineCount + openedNodeCounter == mRowNumber* mColNumber) {
                    // Degerlendirme Formu 15 : Kullanıcının kazanma durumunda uygun mesajlar kullanıcıya gösteriyor.
                    System.out.println("OYUNU KAZANDINIZ. HELALL");
                    break;
                }
            }
        }
    }
}
