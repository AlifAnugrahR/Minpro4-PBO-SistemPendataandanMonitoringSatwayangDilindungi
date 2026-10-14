package Model;

public class Mamalia extends Satwa implements Perawatan {

    private String habitat;
    private double beratBadan;

    public Mamalia(int id, String nama, String jenis, String habitat, double beratBadan) {
        super(id, nama, jenis);
        this.habitat = habitat;
        this.beratBadan = beratBadan;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public double getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(double beratBadan) {
        this.beratBadan = beratBadan;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println(">> ID Satwa: " + getId());
        System.out.println(">> Nama: " + getNama());
        System.out.println(">> Jenis: " + getJenis());
        cetakStatus();
        System.out.println(">> Habitat: " + habitat);
        System.out.println(">> Berat Badan: " + beratBadan + " kg");
    }

    @Override
    public String jenisPerawatan() {
        return "Pemberian pakan harian dan pemeriksaan kesehatan rutin";
    }
}