package Model;

public class Reptil extends Satwa implements Perawatan {

    private boolean berbisa;
    private double panjangTubuh;

    public Reptil(int id, String nama, String jenis, boolean berbisa, double panjangTubuh) {
        super(id, nama, jenis);
        this.berbisa = berbisa;
        this.panjangTubuh = panjangTubuh;
    }

    public boolean isBerbisa() {
        return berbisa;
    }

    public void setBerbisa(boolean berbisa) {
        this.berbisa = berbisa;
    }

    public double getPanjangTubuh() {
        return panjangTubuh;
    }

    public void setPanjangTubuh(double panjangTubuh) {
        this.panjangTubuh = panjangTubuh;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println(">> ID Satwa: " + getId());
        System.out.println(">> Nama: " + getNama());
        System.out.println(">> Jenis: " + getJenis());
        cetakStatus();
        System.out.println(">> Berbisa: " + (berbisa ? "Ya" : "Tidak"));
        System.out.println(">> Panjang Tubuh: " + panjangTubuh + " cm");
    }

    @Override
    public String jenisPerawatan() {
        return "Pengaturan suhu kandang dan pemeriksaan kondisi kulit";
    }
}