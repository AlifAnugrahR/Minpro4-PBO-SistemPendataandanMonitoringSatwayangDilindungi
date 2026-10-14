package Model;

public abstract class Satwa {

    private final int id;
    protected String nama;
    protected String jenis;

    public Satwa(int id, String nama, String jenis) {
        this.id = id;
        setNama(nama);
        setJenis(jenis);
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    
    public abstract void tampilkanInfo();

    public final void cetakStatus() {
        System.out.println(">> Status: Satwa Dilindungi");
    }

    public void tampilkanRingkas() {
        System.out.println(">> [" + id + "] " + nama + " - " + jenis);
    }

    public void tampilkanRingkas(String catatan) {
        System.out.println(">> [" + id + "] " + nama + " - " + jenis + " (" + catatan + ")");
    }
}