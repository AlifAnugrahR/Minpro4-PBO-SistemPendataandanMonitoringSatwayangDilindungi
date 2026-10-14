
package DAO;

import Config.Koneksi;
import Model.Mamalia;
import Model.Reptil;
import Model.Satwa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SatwaDAOImpl implements SatwaDAO {

    private Satwa buatSatwaDariResultSet(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String nama = rs.getString("nama");
        String jenis = rs.getString("jenis");

        if (jenis.equalsIgnoreCase("Mamalia")) {
            String habitat = rs.getString("habitat");
            double beratBadan = rs.getDouble("berat_badan");

            return new Mamalia(id, nama, jenis, habitat, beratBadan);
        } else {
            boolean berbisa = rs.getBoolean("berbisa");
            double panjangTubuh = rs.getDouble("panjang_tubuh");

            return new Reptil(id, nama, jenis, berbisa, panjangTubuh);
        }
    }

    @Override
    public void tambah(Satwa satwa) {

        String sql = "INSERT INTO satwa "
                + "(id, nama, jenis, habitat, berbisa, berat_badan, panjang_tubuh) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, satwa.getId());
            ps.setString(2, satwa.getNama());
            ps.setString(3, satwa.getJenis());

            if (satwa instanceof Mamalia) {
                Mamalia m = (Mamalia) satwa;

                ps.setString(4, m.getHabitat());
                ps.setNull(5, java.sql.Types.BOOLEAN);
                ps.setDouble(6, m.getBeratBadan());
                ps.setNull(7, java.sql.Types.DOUBLE);

            } else if (satwa instanceof Reptil) {
                Reptil r = (Reptil) satwa;

                ps.setNull(4, java.sql.Types.VARCHAR);
                ps.setBoolean(5, r.isBerbisa());
                ps.setNull(6, java.sql.Types.DOUBLE);
                ps.setDouble(7, r.getPanjangTubuh());
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(">> Woopss, gagal menambah data ke database Bosku: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Satwa> tampilkanSemua() {

        ArrayList<Satwa> daftarSatwa = new ArrayList<>();
        String sql = "SELECT * FROM satwa";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                daftarSatwa.add(buatSatwaDariResultSet(rs));
            }

        } catch (SQLException e) {
            System.out.println(">> Woopss, gagal mengambil data dari database Bosku: " + e.getMessage());
        }

        return daftarSatwa;
    }

    @Override
    public boolean idSudahAda(int id) {

        String sql = "SELECT id FROM satwa WHERE id = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println(">> Woopss, gagal mengecek ID Bosku: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void updateNama(int id, String namaBaru) {

        String sql = "UPDATE satwa SET nama = ? WHERE id = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, namaBaru);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(">> Woopss, gagal update data Bosku: " + e.getMessage());
        }
    }

    @Override
    public boolean hapus(int id) {

        String sql = "DELETE FROM satwa WHERE id = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int baris = ps.executeUpdate();
            return baris > 0;

        } catch (SQLException e) {
            System.out.println(">> Woopss, gagal menghapus data Bosku: " + e.getMessage());
            return false;
        }
    }
}
