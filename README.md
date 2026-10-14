<div align="center">

# Mini Project 4 PBO
## Sistem Pendataan dan Monitoring Satwa yang Dilindungi

</div>

---

## 1. Latar Belakang

Satwa dilindungi di Indonesia, seperti Orangutan dan Komodo, perlu didata dan dipantau agar keberadaannya tetap terjaga. Pencatatan yang dilakukan secara manual rentan salah atau tercatat ganda. Oleh karena itu, dibuat program sederhana berbasis Java untuk membantu mencatat dan memantau data satwa dilindungi.

Program ini merupakan pengembangan bertahap: Mini Project 1 (dasar), Mini Project 2 (encapsulation, inheritance, polymorphism, MVC), Mini Project 3 (abstraction, overloading, interface), dan sekarang **Mini Project 4** yang menghubungkan program ke **database MySQL** menggunakan **JDBC**, `PreparedStatement`, dan pola **DAO**, sehingga data tidak lagi hilang setiap program ditutup.

## 2. Deskripsi Program

Program ini merupakan aplikasi berbasis konsol (Command Line Interface) yang digunakan untuk mengelola data satwa dilindungi. Data disimpan secara permanen di database MySQL, bukan lagi di memori (ArrayList) seperti Mini Project 3.

Data yang dikelola meliputi ID, nama, jenis (Mamalia/Reptil), serta data khusus tiap jenis: habitat dan berat badan untuk Mamalia, status berbisa dan panjang tubuh untuk Reptil.

## 3. Fitur Utama

- **Tambah Satwa** – menambahkan data satwa baru langsung ke database.
- **Tampilkan Satwa** – menampilkan seluruh data satwa yang diambil langsung dari database.
- **Update Satwa** – mengubah nama satwa berdasarkan ID, tersimpan permanen di database.
- **Hapus Satwa** – menghapus data satwa dari database berdasarkan ID.
- **Lihat Perawatan Satwa** – menampilkan info perawatan tiap satwa (memanfaatkan interface `Perawatan`), data diambil dari database.

## 4. Teknologi yang Digunakan

| Teknologi | Keterangan |
|---|---|
| Java | Bahasa pemrograman utama |
| NetBeans + Maven | IDE dan tool build project |
| MySQL (Laragon) | Database untuk menyimpan data satwa |
| JDBC (`mysql-connector-j`) | Penghubung Java ke database MySQL |
| `PreparedStatement` | Menjalankan query database dengan aman (anti SQL Injection) |
| Pola DAO (Data Access Object) | Memisahkan logika akses database dari logika program |

---

## 5. Struktur Package

```
Source Packages
├── com.mycompany.minpro4pbosatwayangdilindungi
│   └── Minpro4PBOSatwaYangDilindungi.java   (class main)
├── Config
│   ├── Koneksi.java           (koneksi JDBC ke MySQL)
│   └── TestKoneksi.java       (class untuk menguji koneksi database)
├── DAO
│   ├── SatwaDAO.java          (interface DAO)
│   └── SatwaDAOImpl.java      (implementasi, pakai PreparedStatement)
├── Controller
│   ├── SatwaCRUD.java         (mengatur alur CRUD, memanggil DAO)
│   └── SatwaCek.java          (validasi input)
├── Model
│   ├── Satwa.java             (abstract class)
│   ├── Perawatan.java         (interface)
│   ├── Mamalia.java
│   └── Reptil.java
└── View
    └── Menu.java              (tampilan menu)

sql/
└── satwa.sql                  (pembuatan database, tabel, dan dummy data)
```

---
**Gambar MVC**

<img width="400" height="362" alt="image" src="https://github.com/user-attachments/assets/083736a6-1600-446c-bd88-f2d145338b05" />


Diatas ini adalah bentuk dari Model, View, Controller yang sudah diimplementasikan ke program saya

---


**Struktur Package di NetBeans**

### Fungsi Tiap Package

| Package | Tugas |
|---|---|
| **Model** | Menyimpan data dan menerapkan konsep OOP (encapsulation, inheritance, polymorphism, abstraction) |
| **View** | Menampilkan menu dan menerima interaksi pengguna |
| **Controller** | Mengatur alur program dan menghubungkan View dengan DAO |
| **DAO** | Menangani seluruh operasi database (tambah, baca, ubah, hapus) |
| **Config** | Mengatur koneksi database lewat JDBC |
| **Main** | Titik awal program |

---

## 6. Penjelasan Alur Program

Program dimulai dari `main()` di class `Minpro4PBOSatwaYangDilindungi`. Dibuat objek `Scanner` dan objek `SatwaCRUD`. Berbeda dari Mini Project 3, `SatwaCRUD` **tidak lagi membuat ArrayList dummy** — constructor-nya hanya membuat objek `SatwaDAOImpl` yang akan berkomunikasi dengan database setiap kali dibutuhkan.

```java
public SatwaCRUD() {
    satwaDAO = new SatwaDAOImpl();
}
```

Selanjutnya `Menu.jalankan()` menampilkan menu utama berulang dengan `while (true)` sampai pengguna memilih Keluar.

**GAMBAR - Tampilan Menu Utama**


<img width="446" height="277" alt="image" src="https://github.com/user-attachments/assets/841a0cb6-3800-4038-9786-1f601ae4a2ad" />



```java
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
```

Setiap pilihan menu memanggil method di `SatwaCRUD`, yang kemudian memanggil method di `SatwaDAO` untuk benar-benar mengakses database. Jika pilihan tidak sesuai, program menampilkan pesan menu tidak tersedia tanpa menghentikan program.

---

## 7. Dummy Data dari File .sql

Sesuai ketentuan tugas, data awal **tidak dibuat lewat kode Java**. Constructor `SatwaCRUD` di Mini Project 4 ini **tidak lagi berisi `daftarSatwa.add(...)` seperti Mini Project 3**:

```java
// ini adalah Mini Project 3 yang lama dan sudah tidak dipakai lagi
public SatwaCRUD() {
    daftarSatwa = new ArrayList<>();
    daftarSatwa.add(new Mamalia(1, "Orangutan", "Mamalia", "Hutan", 75.5));
    daftarSatwa.add(new Reptil(2, "Komodo", "Reptil", false, 250.0));
}

// ini adaalah Mini Project 4 yang baru dan data dari database)
public SatwaCRUD() {
    satwaDAO = new SatwaDAOImpl();
}
```

Dummy data sekarang sepenuhnya berasal dari file `sql/satwa.sql`, yang dijalankan satu kali lewat phpMyAdmin sebelum program pertama kali dijalankan:

```sql
INSERT INTO satwa (id, nama, jenis, habitat, berbisa, berat_badan, panjang_tubuh) VALUES
(1, 'Orangutan', 'Mamalia', 'Hutan', NULL, 75.5, NULL),
(2, 'Komodo', 'Reptil', NULL, FALSE, NULL, 250.0);
```

**GAMBAR - Tabel satwa di phpMyAdmin Setelah Import satwa.sql, Sebelum Program Dijalankan**

proses membuat database 
<img width="788" height="440" alt="image" src="https://github.com/user-attachments/assets/ec7b7ff2-1563-4beb-8838-da570e36b803" />



Dan ini adalah hasilnya 

<img width="1507" height="765" alt="Screenshot 2026-10-13 213347" src="https://github.com/user-attachments/assets/85b0a872-9c17-45ff-b340-b0472391de03" />


Gambar di atas membuktikan data awal (Orangutan, Komodo) sudah ada di database **sebelum** program Java dijalankan sama sekali


**GAMBAR - Menu Tampilkan Satwa Langsung Menampilkan Data Awal**


<img width="557" height="390" alt="image" src="https://github.com/user-attachments/assets/b53cfd47-f7ce-4119-82a1-96223bf23f5e" />


Gambar di atas menunjukkan saat program baru pertama kali dijalankan dan pengguna langsung memilih menu 2 (Tampilkan Satwa), data Orangutan dan Komodo langsung muncul tanpa perlu input apa pun — bukti data benar-benar diambil dari database, bukan dari kode.

---

## 8. CRUD Terhubung Langsung ke Database

Seluruh proses CRUD ditangani oleh `SatwaCRUD` (Controller) yang memanggil `SatwaDAOImpl` (DAO), tanpa ada data yang disimpan sementara di memori.

### 8.1 Create - `tambahSatwa()` dan `SatwaDAO.tambah()`

**Fungsi:** menambahkan data satwa baru langsung ke tabel `satwa` di database.

**Proses:** pengguna memasukkan ID, nama, jenis, lalu data tambahan sesuai jenis (habitat + berat badan untuk Mamalia, berbisa + panjang tubuh untuk Reptil). Objek dibuat di memori Java, lalu dikirim ke database lewat `INSERT`.

```java
// Controller/SatwaCRUD.java
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
```

```java
// DAO/SatwaDAOImpl.java
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
```

**GAMBAR - Proses Tambah Data di Program**

<img width="645" height="333" alt="image" src="https://github.com/user-attachments/assets/aea91e7e-5b4a-4a56-85af-7da9f44c16cb" />


**GAMBAR - BUKTI: Tabel satwa di phpMyAdmin Setelah Tambah Data, Menampilkan Baris Baru**

<img width="878" height="550" alt="image" src="https://github.com/user-attachments/assets/8a76ce4e-469b-4a92-99b7-576f34568c46" />


Gambar 6 adalah bukti nyata bahwa `ps.executeUpdate()` benar-benar mengubah isi database: setelah menambah data lewat program, baris baru langsung muncul di phpMyAdmin tanpa perlu input ulang secara manual di sana.

### 8.2 Read – `tampilkanSatwa()` dan `SatwaDAO.tampilkanSemua()`

**Fungsi:** menampilkan seluruh data satwa, diambil langsung dari tabel `satwa`.

```java
// Controller/SatwaCRUD.java
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
```

```java
// DAO/SatwaDAOImpl.java
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
```

Method `buatSatwaDariResultSet()` membaca setiap baris `ResultSet` dan mengubahnya jadi objek `Mamalia` atau `Reptil` sesuai nilai kolom `jenis` - inilah yang membuat `tampilkanInfo()` di menu ini bisa memanggil versi override yang tepat (polymorphism).

**GAMBAR - Hasil Tampilkan Satwa di Program (Setelah Ada Data Tambahan)]**


<img width="477" height="537" alt="image" src="https://github.com/user-attachments/assets/d8098ab2-c04e-4f64-a5c7-354996a7f605" />


### 8.3 Update - `updateSatwa()` dan `SatwaDAO.updateNama()`

**Fungsi:** mengubah nama satwa berdasarkan ID, langsung di database.

```java
// Controller/SatwaCRUD.java
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
```

```java
// DAO/SatwaDAOImpl.java
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
```

ID dicek lebih dulu ke database lewat `idSudahAda()`, bukan dicek ke ArrayList seperti Mini Project 3, karena data sepenuhnya ada di database.

**GAMBAR - Proses Update Data di Program**

<img width="547" height="133" alt="image" src="https://github.com/user-attachments/assets/a0145ad7-e8d9-4b84-ac1b-1424c4263cb0" />


**GAMBAR - BUKTI: Tabel satwa di phpMyAdmin Setelah Update, Nama Berubah**

<img width="853" height="555" alt="image" src="https://github.com/user-attachments/assets/1ccb2ce5-e229-480e-b086-d5f0963c2c1f" />


### 8.4 Delete - `hapusSatwa()` dan `SatwaDAO.hapus()`

**Fungsi:** menghapus data satwa dari database berdasarkan ID.

```java
// Controller/SatwaCRUD.java
public void hapusSatwa(Scanner input) {

    int id = SatwaCek.cekId(input);

    boolean berhasil = satwaDAO.hapus(id);

    if (berhasil) {
        System.out.println(">> Siap Boss, data satwa berhasil dihapus dari database!");
    } else {
        System.out.println(">> Woopss, ID satwa tidak ditemukan Bosku!");
    }
}
```

```java
// DAO/SatwaDAOImpl.java
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
```

`ps.executeUpdate()` mengembalikan jumlah baris yang terpengaruh. Jika `0` (ID tidak ditemukan), method mengembalikan `false`, dan `SatwaCRUD` menampilkan pesan ID tidak ditemukan.

**GAMBAR - Proses Hapus Data di Program**

<img width="673" height="100" alt="image" src="https://github.com/user-attachments/assets/f3ba4ae0-de88-43ad-9cbc-3e5cd5fdcc82" />


**GAMBAR - BUKTI: Tabel satwa di phpMyAdmin Setelah Hapus, Baris Hilang**

<img width="833" height="525" alt="image" src="https://github.com/user-attachments/assets/0585c5c7-dea4-4a92-ba42-a89d9a74fff4" />


---

## 9. Input Validation

Validasi input ditangani oleh class `Controller/SatwaCek.java`, bertujuan agar program tidak berhenti tiba-tiba ketika pengguna memasukkan data yang salah.

### 9.1 Validasi ID (harus angka dan lebih dari 0)

```java
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
```

Jika pengguna memasukkan `"abc"`, `Integer.parseInt()` melempar `NumberFormatException`, ditangkap `catch`, program menampilkan pesan error dan meminta input diulang, tanpa menghentikan program.

**GAMBAR - Invalid Input: ID Bukan Angka**

<img width="462" height="112" alt="image" src="https://github.com/user-attachments/assets/33b2678e-f394-44f2-a175-2558a8d72ec7" />


### 9.2 ID Tidak Boleh Sama (dicek ke database)

Berbeda dari Mini Project 3 (cek ke ArrayList), sekarang pengecekan ID dobel dilakukan langsung ke database lewat `SatwaDAO.idSudahAda()`:

```java
// DAO/SatwaDAOImpl.java
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
```

```java
// Controller/SatwaCRUD.java (dipakai di tambahSatwa())
while (satwaDAO.idSudahAda(id)) {
    System.out.println(">> Woopss, ID itu sudah dipakai Bosku, coba ID lain!");
    id = SatwaCek.cekId(input);
}
```

**GAMBAR - Invalid Input: ID Sudah Dipakai (ID 1 Dicoba Lagi)**

<img width="602" height="77" alt="image" src="https://github.com/user-attachments/assets/c32c42fa-3122-4aae-88d5-71d2f1e8686c" />


### 9.3 Validasi Angka Desimal (berat badan / panjang tubuh)

```java
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
```

### 9.4 Validasi String Tidak Boleh Kosong

```java
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
```

`trim()` menghapus spasi di awal/akhir, `isEmpty()` memastikan input tidak kosong setelah di-trim.

**GAMBAR - Invalid Input: Nama Dikosongkan**

<img width="602" height="57" alt="image" src="https://github.com/user-attachments/assets/7e8edd2c-616e-4e08-b1cc-15a95bd775e4" />


### 9.5 Validasi Jenis Satwa (Mamalia/Reptil)

```java
while (!jenis.equalsIgnoreCase("Mamalia") && !jenis.equalsIgnoreCase("Reptil")) {
    System.out.println(">> Woopss, jenis harus Mamalia atau Reptil Bosku!");
    jenis = SatwaCek.cekString(input, "Masukkan jenis satwa Boss (Mamalia/Reptil): ");
}
```

`equalsIgnoreCase()` dipakai supaya "mamalia" atau "MAMALIA" tetap dianggap valid.

**GAMBAR - Invalid Input: Jenis Satwa Salah**

<img width="596" height="55" alt="image" src="https://github.com/user-attachments/assets/70a41e67-f64d-41b2-a067-b38c81388bd6" />


### 9.6 Validasi Pilihan Ya/Tidak (berbisa)

```java
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
```

**GAMBAR - Invalid Input: Jawaban Berbisa Salah**

<img width="537" height="53" alt="image" src="https://github.com/user-attachments/assets/78423a7a-e35d-42bd-93a2-7c5460e13ef1" />


---

## 10. Encapsulation dan Inheritance

### Encapsulation

Seluruh atribut bersifat `private` (atau `protected` untuk atribut warisan), diakses lewat getter/setter.

```java
public abstract class Satwa {
    private final int id;
    protected String nama;
    protected String jenis;

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
}
```

`id` bersifat `private final` dan tidak punya setter, karena tidak boleh berubah setelah data dibuat. Atribut khusus (`habitat`, `beratBadan` di Mamalia; `berbisa`, `panjangTubuh` di Reptil) juga `private`, diakses lewat getter/setter masing-masing — dipakai langsung oleh `SatwaDAOImpl` untuk mengambil nilai sebelum disimpan ke database, contohnya `m.getHabitat()` dan `r.getPanjangTubuh()`.

### Inheritance

```
Satwa (abstract)
├── Mamalia
└── Reptil
```

`Mamalia` dan `Reptil` mewarisi `Satwa` lewat `extends`, memakai `super(id, nama, jenis)` untuk mengisi data umum lewat constructor superclass.

```java
public class Mamalia extends Satwa implements Perawatan {
    private String habitat;
    private double beratBadan;

    public Mamalia(int id, String nama, String jenis, String habitat, double beratBadan) {
        super(id, nama, jenis);
        this.habitat = habitat;
        this.beratBadan = beratBadan;
    }
}
```

**GAMBAR - Struktur Package Model**

<img width="137" height="91" alt="image" src="https://github.com/user-attachments/assets/e439f8b3-7031-4679-b131-3f2ea63c00b5" />

---

## 11. Polymorphism dan Abstraction

### Abstraction

`Satwa` adalah **abstract class** dengan **abstract method** `tampilkanInfo()`:

```java
public abstract class Satwa {
    public abstract void tampilkanInfo();

    public final void cetakStatus() {
        System.out.println(">> Status: Satwa Dilindungi");
    }
}
```

`Satwa` tidak bisa dibuat objeknya langsung — hanya `Mamalia` dan `Reptil` yang bisa. `SatwaDAOImpl.buatSatwaDariResultSet()` memanfaatkan hal ini: method tersebut **wajib** mengembalikan `Mamalia` atau `Reptil` (bukan `Satwa` polos), sesuai nilai kolom `jenis` dari database.

**GAMBAR - Isi Satwa.java Menunjukkan abstract class dan abstract method**

<img width="677" height="632" alt="image" src="https://github.com/user-attachments/assets/8572b1e7-20b9-4a32-acfe-865a70720e24" />


### Polymorphism - Overriding

```java
// Mamalia.java
@Override
public void tampilkanInfo() {
    System.out.println(">> ID Satwa: " + getId());
    System.out.println(">> Nama: " + getNama());
    System.out.println(">> Jenis: " + getJenis());
    cetakStatus();
    System.out.println(">> Habitat: " + habitat);
    System.out.println(">> Berat Badan: " + beratBadan + " kg");
}
```

```java
// Reptil.java
@Override
public void tampilkanInfo() {
    System.out.println(">> ID Satwa: " + getId());
    System.out.println(">> Nama: " + getNama());
    System.out.println(">> Jenis: " + getJenis());
    cetakStatus();
    System.out.println(">> Berbisa: " + (berbisa ? "Ya" : "Tidak"));
    System.out.println(">> Panjang Tubuh: " + panjangTubuh + " cm");
}
```

Dipanggil secara polymorphic di `SatwaCRUD.tampilkanSatwa()`:

```java
for (Satwa satwa : daftarSatwa) {
    satwa.tampilkanInfo();
}
```

**GAMBAR - Hasil Overriding tampilkanInfo() pada Mamalia dan Reptil**

<img width="633" height="382" alt="image" src="https://github.com/user-attachments/assets/41fac3c9-0add-4b96-b152-8ab0f38ea283" />


### Polymorphism - Overloading

Method `tampilkanRingkas()` di `Satwa` punya 2 versi, nama sama, parameter beda:

```java
public void tampilkanRingkas() {
    System.out.println(">> [" + id + "] " + nama + " - " + jenis);
}

public void tampilkanRingkas(String catatan) {
    System.out.println(">> [" + id + "] " + nama + " - " + jenis + " (" + catatan + ")");
}
```

---

## 12. Penjelasan Penerapan JDBC dan PreparedStatement

Koneksi database diatur di `Config/Koneksi.java` memakai `DriverManager`:

```java
public class Koneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/db_minpro4_satwa";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(">> Woopss, gagal konek ke database Bosku: " + e.getMessage());
            return null;
        }
    }
}
```

Koneksi ini diuji lewat class terpisah `Config/TestKoneksi.java`:

```java
public class TestKoneksi {
    public static void main(String[] args) {
        System.out.println("yow Tes Koneksi menn");

        Connection conn = Koneksi.getConnection();

        if (conn != null) {
            System.out.println("MANTAPPP Koneksi Berhasil BOSKU");
        } else {
            System.out.println("HADUH Koneksi Gagal BOSKUU");
        }
    }
}
```

**GAMBAR Hasil Run TestKoneksi.java, Koneksi Berhasil**

<img width="717" height="532" alt="Screenshot 2026-10-14 132825" src="https://github.com/user-attachments/assets/fe51e355-c52e-4ba3-ac0c-ff983333bcca" />


Semua query memakai `PreparedStatement`, sehingga nilai dari input pengguna **tidak pernah digabung langsung ke teks SQL** (mencegah SQL Injection). Tanda `?` adalah parameter yang diisi lewat `ps.setInt()`, `ps.setString()`, dst — bukan dengan menyambung String secara manual, contohnya pada `tambah()` di bagian 8.1.

`try-with-resources` dipakai pada `Connection`, `PreparedStatement`, dan `ResultSet`, sehingga semua koneksi otomatis tertutup setelah selesai dipakai, tanpa perlu `finally` manual. Setiap operasi dibungkus `try-catch` untuk `SQLException`, dan menampilkan pesan error yang jelas ke pengguna tanpa menghentikan program secara tiba-tiba.

---

## 13. Penjelasan Penerapan DAO

Pola DAO (Data Access Object) memisahkan logika akses database dari logika program utama. Ada satu interface (`SatwaDAO`) dan satu implementasi (`SatwaDAOImpl`):

```java
public interface SatwaDAO {
    void tambah(Satwa satwa);
    ArrayList<Satwa> tampilkanSemua();
    boolean idSudahAda(int id);
    void updateNama(int id, String namaBaru);
    boolean hapus(int id);
}
```

`SatwaCRUD` (Controller) **tidak pernah menulis SQL sendiri** - ia hanya memanggil method dari `SatwaDAO`, seperti `satwaDAO.tambah(satwaBaru)` atau `satwaDAO.hapus(id)`. Semua detail `PreparedStatement`, `ResultSet`, dan penanganan `SQLException` tersembunyi rapi di dalam `SatwaDAOImpl`.

Keuntungan pola ini: jika suatu saat database diganti, cukup ubah `SatwaDAOImpl`, tanpa perlu menyentuh `SatwaCRUD`, `Menu`, atau `Model` sama sekali.

---

## 14. Pengembangan dari Mini Project 3

| Perubahan | Keterangan |
|---|---|
| Penyimpanan data | Dari `ArrayList` di memori  database MySQL (data tidak hilang saat program ditutup) |
| Dummy data | Dari ditulis di constructor `SatwaCRUD` sekarang **wajib** lewat file `sql/satwa.sql` |
| `SatwaCRUD` | Tidak lagi membuat `ArrayList`, sekarang membuat `SatwaDAOImpl` dan memanggil method DAO untuk setiap operasi |
| Package baru | `DAO` (interface + implementasi) dan `Config` (koneksi database + test koneksi) |
| Model (`Satwa`, `Mamalia`, `Reptil`, `Perawatan`) | **Tidak ada yang diubah logikanya** - encapsulation, inheritance, abstraction, polymorphism, dan interface tetap sama persis seperti Mini Project 3 |
| `Menu`, `SatwaCek` | **Tidak diubah** |
| Nilai tambah | **Tidak menggunakan ORM.** Pengembangan difokuskan pada penerapan JDBC + `PreparedStatement` + DAO yang benar dan teruji, sesuai ketentuan wajib tugas |

---

## 15. Kesimpulan

Mini Project 4 ini berhasil menghubungkan program Sistem Pendataan dan Monitoring Satwa yang Dilindungi ke database MySQL menggunakan JDBC, `PreparedStatement`, dan pola DAO, tanpa mengubah penerapan konsep OOP (encapsulation, inheritance, polymorphism, abstraction) yang sudah dibangun sejak Mini Project 3. Data kini tersimpan secara permanen di database, diisi awal lewat file `satwa.sql`, dan seluruh operasi CRUD (tambah, tampil, update, hapus) terbukti benar-benar membaca serta menulis ke database — dibuktikan lewat perbandingan langsung tampilan phpMyAdmin sebelum dan sesudah setiap aksi di program.

Sebagai Mini Project terakhir dalam rangkaian pengembangan sistem ini, Mini Project 4 melengkapi seluruh pilar PBO (encapsulation, inheritance, polymorphism, abstraction) dengan kemampuan penyimpanan data permanen lewat database, menjadikan program ini aplikasi pendataan satwa yang utuh dan siap dipakai.
