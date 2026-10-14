package View;

import Controller.SatwaCRUD;
import java.util.Scanner;

public class Menu {

    public static void tampilkan() {

        System.out.println("\n=======================================");
        System.out.println("SISTEM PENDATAAN DAN MONITORING SATWA");
        System.out.println("=======================================");
        System.out.println("1. Tambah Satwa");
        System.out.println("2. Tampilkan Satwa");
        System.out.println("3. Update Satwa");
        System.out.println("4. Hapus Satwa");
        System.out.println("5. Lihat Perawatan Satwa");
        System.out.println("6. Keluar");
        System.out.println("=======================================");
        System.out.print("Pilih menu Boss: ");
    }

    public static void jalankan(Scanner input, SatwaCRUD crud) {

        while (true) {

            tampilkan();

            String pilihan = input.nextLine();

            switch (pilihan) {

                case "1":
                    crud.tambahSatwa(input);
                    break;

                case "2":
                    crud.tampilkanSatwa();
                    break;

                case "3":
                    crud.updateSatwa(input);
                    break;

                case "4":
                    crud.hapusSatwa(input);
                    break;

                case "5":
                    crud.tampilkanPerawatan();
                    break;

                case "6":
                    System.out.println(">> Siap Boss, program selesai. Sampai jumpa Bosku!");
                    return;

                default:
                    System.out.println(">> Woopss, menu itu tidak ada Bosku! Pilih 1 sampai 6 ya.");
            }
        }
    }
}