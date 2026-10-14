package DAO;

import Model.Satwa;
import java.util.ArrayList;

public interface SatwaDAO {
    void tambah(Satwa satwa);
    ArrayList<Satwa> tampilkanSemua();
    boolean idSudahAda(int id);
    void updateNama(int id, String namaBaru);
    boolean hapus(int id);
}