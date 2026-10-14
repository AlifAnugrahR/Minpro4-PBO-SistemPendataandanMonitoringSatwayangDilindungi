package Controller;

import java.util.Scanner;

public class SatwaCek {

    public static int cekId(Scanner input) {
        while (true) {
            try {
                System.out.print(">> Masukkan ID Boss: ");
                int id = Integer.parseInt(input.nextLine());

                if (id > 0) {
                    return id;
                }

                System.out.println(">> Woopss, ID harus lebih dari 0 Bosku!");
            } catch (NumberFormatException e) {
                System.out.println(">> Woopss, ID harus berupa angka Bosku!");
            }
        }
    }

    public static double cekDouble(Scanner input, String pesan) {
        while (true) {
            try {
                System.out.print(">> " + pesan);
                double angka = Double.parseDouble(input.nextLine());

                if (angka > 0) {
                    return angka;
                }

                System.out.println(">> Angka harus lebih dari 0 Bosku!");
            } catch (NumberFormatException e) {
                System.out.println(">> Woopss, input harus berupa angka Bosku!");
            }
        }
    }

    public static String cekString(Scanner input, String pesan) {
        while (true) {
            System.out.print(">> " + pesan);
            String data = input.nextLine().trim();

            if (!data.isEmpty()) {
                return data;
            }

            System.out.println(">> Woopss, input tidak boleh kosong Bosku!");
        }
    }

    public static boolean cekBoolean(Scanner input) {
        while (true) {
            String data = input.nextLine();

            if (data.equalsIgnoreCase("ya")) {
                return true;
            }

            if (data.equalsIgnoreCase("tidak")) {
                return false;
            }

            System.out.println(">> Woopss, jawab 'ya' atau 'tidak' saja Bosku!");
            System.out.print(">> Masukkan pilihan Boss: ");
        }
    }
}