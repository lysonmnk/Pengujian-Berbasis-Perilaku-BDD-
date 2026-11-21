# Praktikum Pengujian Perangkat Lunak - Pengujian Berbasis Perilaku (BDD) dengan Katalon Studio

[![Katalon Studio](https://img.shields.io/badge/Katalon%20Studio-11.x-brightgreen.svg)](https://katalon.com/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.x-green.svg)](https://cucumber.io/)
[![Groovy](https://img.shields.io/badge/Language-Groovy-blue.svg)](https://groovy-lang.org/)
[![Status](https://img.shields.io/badge/Test%20Status-PASSED%20(3%2F3)-success.svg)]()

Repositori ini berisi seluruh artefak pengujian otomatis berbasis perilaku (**Behavior-Driven Development / BDD**) menggunakan **Katalon Studio**, **Cucumber 7**, dan bahasa pemrograman **Groovy** untuk pengujian web UI pada aplikasi demo **CURA Healthcare Service**.

---

## 👤 Identitas Praktikan
- **Nama:** Lyson P.E. Manik
- **NIM:** 11S24024
- **Program Studi:** S1 Informatika
- **Fakultas:** Fakultas Informatika dan Teknik Elektro
- **Institusi:** Institut Teknologi Del, Sitoluama, Laguboti
- **Mata Kuliah:** Pengujian Perangkat Lunak
- **Link Video Demonstrasi:** 🎬 [https://youtu.be/DIhWg0uYcSY](https://youtu.be/DIhWg0uYcSY)

---

## 📌 Ringkasan Proyek
Pengujian dilakukan terhadap alur autentikasi/login aplikasi layanan kesehatan **CURA Healthcare Service** ([https://katalon-demo-cura.herokuapp.com/](https://katalon-demo-cura.herokuapp.com/)) dengan skenario pengujian kredensial valid dan tidak valid.

### 🧪 Matriks Kasus Uji
| ID Kasus Uji | Skenario | Username | Password | Expected Result | Status Eksekusi |
| :--- | :--- | :--- | :--- | :---: | :---: |
| **BDD-LOGIN-01** | Login dengan kredensial valid | `John Doe` | `ThisIsNotAPassword` | `SUCCESS` | **PASSED** ✅ |
| **BDD-LOGIN-02** | Login dengan username tidak terdaftar | `Jane Doe` | `ThisIsNotAPassword` | `FAILURE` | **PASSED** ✅ |
| **BDD-LOGIN-03** | Login dengan password salah | `John Doe` | `WrongPassword123` | `FAILURE` | **PASSED** ✅ |

---

## 📂 Struktur Direktori Repositori

```text
.
├── 11S24024_Lyson P.E. Manik_Laporan p8.docx  # Laporan praktikum resmi (Word format)
├── 11S24024_Lyson P.E. Manik_Laporan p8.pdf   # Laporan praktikum resmi (PDF format)
├── LAPORAN_PRAKTIKUM.md                       # Laporan praktikum lengkap (Markdown format)
├── README.md                                  # Dokumentasi repositori
├── Template_Excel_BDD_Katalon.xlsx            # File template data uji (LoginData)
├── Hasil_Pengujian_BDD_Kelompok01.xlsx        # File rekapan hasil pengujian dengan formula metrik
├── 20261120_232645.csv                        # Salinan mentah (raw export) laporan CSV eksekusi
└── BDD_Katalon_Excel/                         # Proyek Katalon Studio
    ├── BDD_Katalon_Excel.prj                  # File project definition Katalon
    ├── build.gradle                           # Konfigurasi dependensi Gradle
    ├── console.properties                     # Konfigurasi console mode
    ├── Data Files/
    │   └── LoginData.dat                      # Test Data konfigurasi Excel LoginData
    ├── Include/
    │   ├── features/
    │   │   └── Login.feature                  # Feature file Gherkin (Scenario Outline & Examples)
    │   └── scripts/groovy/
    │       └── LoginSteps.groovy              # Step Definition Cucumber (@Given, @When, @Then)
    ├── Object Repository/
    │   └── CURA/
    │       ├── a_Make Appointment.rs          # Test Object tombol 'Make Appointment'
    │       ├── input_userName.rs              # Test Object input field username
    │       ├── input_password.rs              # Test Object input field password
    │       └── button_Login.rs                # Test Object tombol submit login
    ├── Scripts/
    │   ├── TC_BDD_Login/
    │   │   └── Script1795166661698.groovy     # Skrip runner feature file
    │   └── TC_Login_From_Excel/
    │       └── Script1795189568782.groovy     # Skrip test case data-driven dengan data binding
    ├── Test Cases/
    │   ├── TC_BDD_Login.tc                    # Test Case pemanggil Login.feature
    │   └── TC_Login_From_Excel.tc             # Test Case data binding dari Excel
    ├── Test Suites/
    │   ├── TS_BDD_Login.ts                    # Test Suite konfigurasi eksekusi pengujian
    │   └── TS_BDD_Login.groovy                # Test Suite Groovy listener
    └── Reports/                               # Laporan eksekusi pengujian (HTML, XML, Cucumber report)
        ├── 20261120_232645/                   # Laporan eksekusi resmi TS_BDD_Login
        └── Self-healing/                      # Laporan dan bukti self-healing locator
```

---

## 🚀 Cara Menjalankan Pengujian di Katalon Studio

### 1. Prasyarat Sistem
- **Katalon Studio:** Versi 11.x (Free / Enterprise)
- **Browser:** Google Chrome (versi terbaru)
- **Koneksi Internet:** Untuk mengakses aplikasi demo CURA

### 2. Membuka Proyek
1. Buka **Katalon Studio**.
2. Pilih **File > Open Project**.
3. Navigasikan ke direktori `BDD_Katalon_Excel` dan pilih berkas `BDD_Katalon_Excel.prj`.
4. Tunggu beberapa saat hingga Katalon mengindeks dan memuat seluruh elemen *Test Explorer*.

### 3. Menjalankan Pengujian BDD via Test Suite
1. Pada panel *Test Explorer*, buka folder **Test Suites > TS_BDD_Login**.
2. Klik panah drop-down di samping tombol **Run** pada toolbar atas, lalu pilih browser **Chrome**.
3. Katalon akan secara otomatis membuka browser Chrome, mengakses aplikasi CURA, menjalankan ketiga skenario pengujian pada `Login.feature`, dan memverifikasi hasilnya.
4. Setelah selesai, periksa hasil pada tab **Log Viewer** dan tab **Result**.

---

## 📊 Hasil dan Rekapitulasi Pengujian

Eksekusi test suite menghasilkan kelulusan **100%**:
- **Total Kasus Uji:** 3
- **Passed:** 3
- **Failed:** 0
- **Duration:** 3 menit 9.139 detik

Data eksekusi telah direkapitulasi secara otomatis pada berkas [Hasil_Pengujian_BDD_Kelompok01.xlsx](Hasil_Pengujian_BDD_Kelompok01.xlsx) (sheet `ExecutionResults`) dan laporan mentah tersimpan pada berkas [20261120_232645.csv](20261120_232645.csv).

Untuk pembahasan mendalam, analisis kasus PASSED/FAILED, self-healing locator, serta jawaban lengkap 6 pertanyaan analisis, silakan baca [LAPORAN_PRAKTIKUM.md](LAPORAN_PRAKTIKUM.md) atau dokumen [11S24024_Lyson P.E. Manik_Laporan p8.pdf](11S24024_Lyson%20P.E.%20Manik_Laporan%20p8.pdf).

---

## 📚 Referensi
1. [Katalon Studio Installation Guide](https://docs.katalon.com/katalon-studio/get-started/install-katalon-studio)
2. [Katalon BDD Testing Framework](https://docs.katalon.com/katalon-studio/bdd-testing/bdd-testing-framework-cucumber-integration)
3. [Cucumber Documentation & Gherkin Reference](https://cucumber.io/docs/gherkin/reference/)
4. [CURA Healthcare Service Application Demo](https://katalon-demo-cura.herokuapp.com/)
