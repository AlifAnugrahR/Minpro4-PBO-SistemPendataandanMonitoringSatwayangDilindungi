package Config;

import java.sql.Connection;

public class TestKoneksi {

    public static void main(String[] args) {
        System.out.println("Yoow Tes Koneksi menn");

        Connection conn = Koneksi.getConnection();

        if (conn != null) {
            System.out.println("MANTAPPP Koneksi Berhasil BOSKU");
        } else {
            System.out.println("HADUH Koneksi Gagal BOSKUU");
        }
    }
}
