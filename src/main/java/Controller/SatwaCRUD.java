
package Controller;

import DAO.SatwaDAO;
import DAO.SatwaDAOImpl;
import Model.Mamalia;
import Model.Perawatan;
import Model.Reptil;
import Model.Satwa;
import java.util.ArrayList;
import java.util.Scanner;

public class SatwaCRUD {

    private final SatwaDAO satwaDAO;

    public SatwaCRUD() {
        satwaDAO = new SatwaDAOImpl();
    }

    public void tambahSatwa(Scanner input) {

        int id = SatwaCek.cekId(input);

        while (satwaDAO.idSudahAda(id)) {
            System.out.println(">> Woopss, ID itu sudah dipakai Bosku, coba ID lain!");
            id = SatwaCek.cekId(input);
        }

        String nama = SatwaCek.cekString(input, "Masukkan nama satwa Boss: ");
        String jenis = SatwaCek.cekString(input, "Masukkan jenis satwa Boss (Mamalia/Reptil): ");

        while (!jenis.equalsIgnoreCase("Mamalia") && !jenis.equalsIgnoreCase("Reptil")) {
            System.out.println(">> Woopss, jenis harus Mamalia atau Reptil Bosku!");
            jenis = SatwaCek.cekString(input, "Masukkan jenis satwa Boss (Mamalia/Reptil): ");
        }

        Satwa satwaBaru;

        if (jenis.equalsIgnoreCase("Mamalia")) {
            String habitat = SatwaCek.cekString(input, "Masukkan habitat Boss: ");
            System.out.print("Masukkan berat badan (kg): ");
            double beratBadan = input.nextDouble();
            input.nextLine();

            satwaBaru = new Mamalia(id, nama, "Mamalia", habitat, beratBadan);
        } else {
            System.out.print(">> Apakah berbisa Boss? (ya/tidak): ");
            boolean berbisa = SatwaCek.cekBoolean(input);

            System.out.print("Masukkan panjang tubuh (cm): ");
            double panjangTubuh = input.nextDouble();
            input.nextLine();

            satwaBaru = new Reptil(id, nama, "Reptil", berbisa, panjangTubuh);
        }

        satwaDAO.tambah(satwaBaru);

        System.out.println(">> Mantap Boss, proses penambahan satwa selesai!");

        satwaBaru.tampilkanInfo();
    }

    public void tampilkanSatwa() {

        ArrayList<Satwa> daftarSatwa = satwaDAO.tampilkanSemua();

        if (daftarSatwa.isEmpty()) {
            System.out.println(">> Woopss, data satwa masih kosong Bosku!");
            return;
        }

        System.out.println("\n>> ===== DAFTAR SATWA BOSKU (dari database) =====");

        for (Satwa satwa : daftarSatwa) {
            satwa.tampilkanInfo();
            System.out.println(">> -------------------------");
        }
    }

    public void updateSatwa(Scanner input) {

        int id = SatwaCek.cekId(input);

        if (!satwaDAO.idSudahAda(id)) {
            System.out.println(">> Woopss, ID satwa tidak ditemukan Bosku!");
            return;
        }

        String nama = SatwaCek.cekString(input, "Masukkan nama baru Boss: ");
        satwaDAO.updateNama(id, nama);

        System.out.println(">> Mantap Boss, proses update satwa selesai!");
    }

    public void hapusSatwa(Scanner input) {

        int id = SatwaCek.cekId(input);

        boolean berhasil = satwaDAO.hapus(id);

        if (berhasil) {
            System.out.println(">> Siap Boss, data satwa berhasil dihapus dari database!");
        } else {
            System.out.println(">> Woopss, ID satwa tidak ditemukan Bosku!");
        }
    }

    public void tampilkanPerawatan() {

        ArrayList<Satwa> daftarSatwa = satwaDAO.tampilkanSemua();

        if (daftarSatwa.isEmpty()) {
            System.out.println(">> Woopss, data satwa masih kosong Bosku!");
            return;
        }

        System.out.println("\n>> ===== INFO PERAWATAN SATWA =====");

        for (Satwa satwa : daftarSatwa) {

            System.out.println(">> Nama: " + satwa.getNama());

            if (satwa instanceof Perawatan) {
                Perawatan p = (Perawatan) satwa;
                System.out.println(">> Perawatan: " + p.jenisPerawatan());
            }

            System.out.println(">> -------------------------");
        }
    }
}
