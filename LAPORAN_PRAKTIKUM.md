# LAPORAN TUGAS PRAKTIKUM
## PENGUJIAN PERANGKAT LUNAK (PPL / PPMPL)
### Topik: Pengujian Berbasis Perilaku (Behavior-Driven Development / BDD) dengan Katalon Studio

---

| Informasi Praktikum | Keterangan |
| :--- | :--- |
| **Nama Praktikan** | Lyson P.E. Manik |
| **NIM** | 11S24024 |
| **Program Studi** | S1 Informatika |
| **Fakultas** | Fakultas Informatika dan Teknik Elektro |
| **Institusi** | Institut Teknologi Del, Sitoluama, Laguboti |
| **Mata Kuliah** | Pengujian Perangkat Lunak |
| **Link Video Demonstrasi** | [https://youtu.be/DIhWg0uYcSY](https://youtu.be/DIhWg0uYcSY) |
| **Platform & Tools** | Katalon Studio 11.x, Groovy, Cucumber 7, Gherkin, Apache POI / Excel |
| **Aplikasi yang Diuji (SUT)** | CURA Healthcare Service ([https://katalon-demo-cura.herokuapp.com/](https://katalon-demo-cura.herokuapp.com/)) |

---

## DAFTAR ISI
1. [Identitas Praktikan dan Tanggal Pelaksanaan](#1-identitas-praktikan-dan-tanggal-pelaksanaan)
2. [Tujuan dan Ringkasan Kasus Uji](#2-tujuan-dan-ringkasan-kasus-uji)
   - 2.1 [Tujuan Praktikum](#21-tujuan-praktikum)
   - 2.2 [Ringkasan Kasus Uji](#22-ringkasan-kasus-uji)
   - 2.3 [Dasar Teori Singkat](#23-dasar-teori-singkat)
3. [Alat, Lingkungan Pengujian, dan Instalasi](#3-alat-lingkungan-pengujian-dan-instalasi)
   - 3.1 [Lingkungan Pengujian](#31-lingkungan-pengujian)
   - 3.2 [Langkah Instalasi Katalon Studio](#32-langkah-instalasi)
   - 3.3 [Pembuatan Project Web Testing](#33-pembuatan-project-web-testing)
4. [Feature File dan Step Definition](#4-feature-file-dan-step-definition)
   - 4.1 [Feature File (`Login.feature`)](#41-feature-file)
   - 4.2 [Step Definition (`LoginSteps.groovy`)](#42-step-definition)
   - 4.3 [Test Case Pemanggil Feature File (`TC_BDD_Login`)](#43-test-case-pemanggil-feature-file)
   - 4.4 [Test Suite (`TS_BDD_Login`)](#44-test-suite)
5. [Test Object dan Konfigurasi Locator](#5-test-object-dan-konfigurasi-locator)
6. [Input Pengujian dari Excel dan Data-Driven Testing](#6-input-pengujian-dari-excel)
   - 6.1 [Struktur File Excel](#61-struktur-file-excel)
   - 6.2 [Data Uji](#62-data-uji)
   - 6.3 [Mengimpor Excel sebagai Test Data](#63-mengimpor-excel-sebagai-test-data)
   - 6.4 [Test Case Data-Driven dan Data Binding (`TC_Login_From_Excel`)](#64-test-case-data-driven-dan-data-binding)
   - 6.5 [Jalur Pelaksanaan](#65-jalur-pelaksanaan)
7. [Hasil Eksekusi Pengujian](#7-hasil-eksekusi)
8. [File CSV Hasil Ekspor dan File Rekap Excel](#8-file-csv-hasil-ekspor-dan-file-rekap-excel)
   - 8.1 [Ekspor Laporan Test Suite](#81-ekspor-laporan-test-suite)
   - 8.2 [Membuka CSV dan Menyusun Rekap](#82-membuka-csv-dan-menyusun-rekap)
9. [Analisis Hasil Pengujian](#9-analisis-hasil-pengujian)
   - 9.1 [Analisis Kasus PASSED](#91-analisis-kasus-passed)
   - 9.2 [Analisis Kasus FAILED atau ERROR](#92-analisis-kasus-failed-atau-error)
10. [Jawaban Pertanyaan Analisis](#10-jawaban-pertanyaan-analisis)
11. [Kesimpulan dan Kendala](#11-kesimpulan-dan-kendala)
   - 11.1 [Kesimpulan](#111-kesimpulan)
12. [Daftar Pustaka](#daftar-pustaka)

---

## 1. Identitas Praktikan dan Tanggal Pelaksanaan

| Komponen | Keterangan |
| :--- | :--- |
| **Nama praktikan** | Lyson P.E. Manik |
| **NIM** | 11S24024 |
| **Link Video** | [https://youtu.be/DIhWg0uYcSY](https://youtu.be/DIhWg0uYcSY) |

---

## 2. Tujuan dan Ringkasan Kasus Uji

### 2.1 Tujuan Praktikum
Praktikum ini bertujuan agar praktikan mampu:
1. Menjelaskan konsep Behavior-Driven Development (BDD), Gherkin, Cucumber, serta hubungan antara skenario bisnis dan langkah otomatis.
2. Menginstal, menjalankan, dan membuat project web testing pada Katalon Studio.
3. Membuat feature file menggunakan `Given-When-Then` dan `Scenario Outline`-`Examples`.
4. Menghubungkan langkah Gherkin dengan kode Step Definition menggunakan Groovy dan keyword `WebUI`.
5. Mengimpor data pengujian dari Excel dan melakukan data binding pada test case.
6. Menghasilkan, membaca, dan merekap hasil pengujian ke dalam file Excel.

### 2.2 Ringkasan Kasus Uji
Kasus uji pada praktikum ini adalah proses login pada aplikasi demo CURA Healthcare Service dengan alamat [https://katalon-demo-cura.herokuapp.com/](https://katalon-demo-cura.herokuapp.com/). Sistem diharapkan menerima kredensial yang valid dan menolak kredensial yang tidak valid. Pengujian hanya menggunakan akun demo yang disediakan untuk latihan, bukan akun pribadi.

| ID | Kondisi | Data uji (username / password) | Hasil yang diharapkan |
| :--- | :--- | :--- | :--- |
| **BDD-LOGIN-01** | Username dan password valid | `John Doe` / `ThisIsNotAPassword` | Login berhasil dan halaman *Make Appointment* terlihat |
| **BDD-LOGIN-02** | Username tidak valid | `Jane Doe` / `ThisIsNotAPassword` | Sistem menampilkan pesan login gagal |
| **BDD-LOGIN-03** | Password tidak valid | `John Doe` / `WrongPassword123` | Sistem menampilkan pesan login gagal |

### 2.3 Dasar Teori Singkat
**Behavior-Driven Development (BDD)** adalah pendekatan pengembangan dan pengujian yang mendeskripsikan perilaku sistem dari sudut pandang pengguna atau pemangku kepentingan. Skenario ditulis dalam bahasa yang mudah dipahami oleh pengembang, penguji, analis, maupun pihak nonteknis, lalu dihubungkan dengan kode agar dapat dieksekusi secara otomatis. Dengan cara ini, kriteria penerimaan (*acceptance criteria*) menjadi jelas karena dinyatakan sebagai perilaku yang dapat diamati.

**Gherkin** adalah sintaks teks terstruktur untuk menulis skenario BDD. Kata kunci utamanya adalah:
- `Feature`: Mendefinisikan fitur yang sedang diuji beserta tujuannya.
- `Scenario`: Mendeskripsikan satu alur skenario pengujian spesifik.
- `Given`: Menentukan kondisi awal (*pre-condition*).
- `When`: Mendefinisikan aksi atau tindakan yang dilakukan pengguna.
- `Then`: Mendefinisikan hasil akhir yang diharapkan dan diverifikasi (*post-condition / expected outcome*).
- `And` / `But`: Melanjutkan jenis langkah sebelumnya untuk menghindari redundansi kata kunci.
- `Scenario Outline` dan `Examples`: Menjalankan template skenario secara berulang dengan dataset yang berbeda.
- `Background`: Menentukan langkah-langkah persiapan yang dijalankan sebelum setiap skenario dalam fitur.
- `Tags`: Memberikan penanda/kategori (misal `@Login`) untuk memudahkan filtering eksekusi.

**Cucumber** adalah framework yang membaca file feature berekstensi `.feature`, lalu mencocokkan setiap kalimat langkah dengan metode Step Definition. **Katalon Studio** adalah platform otomasi pengujian untuk web, API, mobile, dan desktop. Pada praktikum ini Katalon digunakan untuk pengujian web UI dan BDD.

**Step Definition** adalah metode berbahasa Groovy atau Java yang berisi aksi dan verifikasi untuk setiap langkah Gherkin. Anotasi seperti `@Given`, `@When`, dan `@Then` menghubungkan teks pada feature file dengan metode tersebut. Parameter pada teks seperti `{string}` menerima nilai dari feature file.

**Test Object** adalah representasi elemen antarmuka web, seperti tombol, input, dan label, yang disimpan di Object Repository beserta locator-nya. Locator dapat berupa ID, XPath, CSS, atau atribut lain yang menunjuk ke elemen tertentu pada halaman.

**Data-driven testing** adalah teknik menjalankan pola pengujian yang sama dengan banyak set data. Pada praktikum ini, pola tersebut diwujudkan melalui `Scenario Outline` dengan tabel `Examples`, atau melalui *Data Binding* dengan sumber data Excel.

Setiap hasil eksekusi diberi status:
- **PASSED**: Perilaku aktual sistem sesuai dengan hasil yang diharapkan.
- **FAILED**: Hasil verifikasi tidak sesuai ekspektasi.
- **ERROR**: Eksekusi terhenti karena masalah teknis (misal: element not found, syntax error, network timeout).
- **SKIPPED**: Test tidak dijalankan karena kondisi atau konfigurasi tertentu.

*Catatan lisensi:* Menurut dokumentasi Katalon yang diperbarui November 2026, mulai Katalon Studio 11.7.0 eksekusi data-driven test melalui fitur Data Binding memerlukan paket berbayar. Pengguna paket Free tetap dapat mempelajari BDD melalui `Scenario Outline` dan tabel `Examples`.

---

## 3. Alat, Lingkungan Pengujian, dan Instalasi

### 3.1 Lingkungan Pengujian

| Komponen | Spesifikasi yang digunakan |
| :--- | :--- |
| **Aplikasi yang diuji** | CURA Healthcare Service, [https://katalon-demo-cura.herokuapp.com/](https://katalon-demo-cura.herokuapp.com/) |
| **Bahasa skrip** | Groovy |
| **Format skenario** | Gherkin, Cucumber 7 (Katalon Studio 11.x) |
| **Operating System** | Windows 11 64-bit |
| **Browser** | Google Chrome |

### 3.2 Langkah Instalasi
Instalasi dilakukan pada sistem operasi Windows dengan langkah berikut:
1. Membuat atau menyiapkan akun Katalon melalui [https://katalon.com/](https://katalon.com/) dan memastikan email aktif untuk aktivasi.
2. Mengunduh Katalon Studio untuk Windows dari [https://katalon.com/download](https://katalon.com/download).
3. Mengekstrak file ZIP ke folder milik pengguna, misalnya folder `KatalonStudio` di dalam direktori user Windows.
4. Menjalankan `katalon.exe` dari folder hasil ekstraksi.
5. Login menggunakan akun Katalon dan melakukan aktivasi sesuai paket yang tersedia.
6. Menentukan folder workspace, misalnya `Documents/Katalon_Workspace`.
7. Memastikan panel *Test Explorer* terlihat dan tidak ada pesan kesalahan instalasi.

### 3.3 Pembuatan Project Web Testing
Project dibuat dengan langkah **File > New > Project**, memilih tipe **Web UI Testing**, memasukkan nama `BDD_Katalon_Excel`, menentukan lokasi pada workspace, lalu menekan **Finish** atau **Create**. Setelah project terbuka, *Test Explorer* menampilkan struktur folder utama:

| Folder / Komponen | Kegunaan |
| :--- | :--- |
| **Test Cases** | Tempat test case, termasuk test case pemanggil feature file. |
| **Test Suites** | Mengelompokkan test case agar dieksekusi dan dilaporkan bersama. |
| **Object Repository** | Menyimpan identitas elemen web seperti tombol, input, dan label. |
| **Include/features** | Lokasi feature file berekstensi `.feature`. |
| **Include/scripts/groovy** | Lokasi class Groovy dan Step Definition BDD. |
| **Data Files** | Data eksternal yang diimpor, termasuk Excel dan CSV. |
| **Reports** | Lokasi laporan hasil eksekusi dan artefak pendukung. |

---

## 4. Feature File dan Step Definition

### 4.1 Feature File
Feature file dibuat pada folder `Include/features` dengan nama `Login.feature`. File ini berisi satu `Scenario Outline` yang memakai placeholder `<username>`, `<password>`, dan `<expectedResult>`. Placeholder tersebut diganti oleh Cucumber dengan nilai pada setiap baris tabel `Examples`, sehingga skenario yang sama dijalankan tiga kali secara berurutan.

```gherkin
@Login
Feature: Login pada aplikasi CURA Healthcare Service
  Sebagai pengguna aplikasi CURA
  Saya ingin melakukan login
  Agar dapat mengakses halaman Make Appointment

  Scenario Outline: Memeriksa login dengan berbagai kombinasi kredensial
    Given I open the CURA application
    When I open the login form
    And I enter username "<username>" and password "<password>"
    And I submit login
    Then the login result should be "<expectedResult>"

    Examples:
      | username | password           | expectedResult |
      | John Doe | ThisIsNotAPassword | SUCCESS        |
      | Jane Doe | ThisIsNotAPassword | FAILURE        |
      | John Doe | WrongPassword123   | FAILURE        |
```

### 4.2 Step Definition
Step Definition dibuat pada folder `Include/scripts/groovy` dengan nama class `LoginSteps.groovy`. Setiap anotasi `@Given`, `@When`, dan `@Then` dicocokkan dengan kalimat pada feature file. Kode menggunakan library Cucumber 7 (`io.cucumber.java.en.*`) sesuai Katalon Studio 11.x. Browser ditutup pada blok `finally` di langkah `@Then` agar browser selalu tertutup secara andal walaupun verifikasi mengalami kegagalan.

```groovy
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

class LoginSteps {

    @Given("I open the CURA application")
    def openCuraApplication() {
        WebUI.openBrowser('')
        WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')
        WebUI.waitForPageLoad(20)
    }

    @When("I open the login form")
    def openLoginForm() {
        WebUI.click(findTestObject('Object Repository/CURA/a_Make Appointment'))
        WebUI.waitForPageLoad(20)
    }

    @When("I enter username {string} and password {string}")
    def enterCredentials(String username, String password) {
        WebUI.setText(findTestObject('Object Repository/CURA/input_userName'), username)
        WebUI.setText(findTestObject('Object Repository/CURA/input_password'), password)
    }

    @When("I submit login")
    def submitLogin() {
        WebUI.click(findTestObject('Object Repository/CURA/button_Login'))
        WebUI.waitForPageLoad(20)
    }

    @Then("the login result should be {string}")
    def verifyLoginResult(String expectedResult) {
        try {
            if (expectedResult == 'SUCCESS') {
                WebUI.verifyTextPresent('Make Appointment', false)
            } else if (expectedResult == 'FAILURE') {
                WebUI.verifyTextPresent(
                    'Login failed! Please ensure the username and password are valid.', false)
            } else {
                throw new IllegalArgumentException(
                    'expectedResult harus SUCCESS atau FAILURE: ' + expectedResult)
            }
        } finally {
            WebUI.closeBrowser()
        }
    }
}
```

#### Penjelasan Fungsi Setiap Langkah:

| Anotasi | Fungsi |
| :--- | :--- |
| `@Given` | Membuka browser dan mengakses alamat web aplikasi CURA Healthcare Service. |
| `@When (open login form)` | Menekan tombol *Make Appointment* dari beranda untuk membuka form login. |
| `@When (enter credentials)` | Menerima parameter dari placeholder feature file dan mengisi kolom username dan password. |
| `@When (submit login)` | Menekan tombol Login untuk mengirim kredensial ke sistem. |
| `@Then` | Membandingkan perilaku aktual sistem dengan `expectedResult`. Browser ditutup pada blok `finally`. |

### 4.3 Test Case Pemanggil Feature File
Test case `TC_BDD_Login` dibuat melalui **Test Cases > New > Test Case**, lalu dibuka pada Script mode:

```groovy
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW

CucumberKW.GLUE = ['']
CucumberKW.runFeatureFile('Include/features/Login.feature')
```

`CucumberKW.GLUE` diatur sesuai package tempat Step Definition berada. Untuk Step Definition yang berada pada default package, nilai string kosong `['']` sudah mencukupi.

### 4.4 Test Suite
Test suite `TS_BDD_Login` dibuat melalui **Test Suites > New > Test Suite**, kemudian test case `TC_BDD_Login` ditambahkan ke dalamnya. Eksekusi dilakukan dengan memilih browser (Google Chrome), lalu menekan tombol **Run**. 

Laporan BDD dan laporan test suite dibuat lengkap ketika eksekusi dilakukan melalui test suite, bukan hanya menjalankan feature file secara langsung. Karena itu, praktikum ini selalu menjalankan pengujian melalui test suite.

---

## 5. Test Object dan Konfigurasi Locator

Test object dibuat untuk setiap elemen yang berinteraksi dengan langkah Gherkin. Object tersebut disimpan pada `Object Repository/CURA`. Pembuatan dilakukan secara presisi berdasarkan atribut HTML yang tampil di browser:

| Nama Test Object | Locator | Kegunaan |
| :--- | :--- | :--- |
| `a_Make Appointment` | XPath: `//a[normalize-space()="Make Appointment"]` | Membuka form login dari halaman awal |
| `input_userName` | ID: `txt-username` | Mengisi data username pengguna |
| `input_password` | ID: `txt-password` | Mengisi data password pengguna |
| `button_Login` | ID: `btn-login` | Menekan tombol submit form login |

Nama object dan path pada Step Definition harus sama persis (`Object Repository/CURA/<nama_object>`), karena keyword WebUI mencari object melalui path tersebut.

---

## 6. Input Pengujian dari Excel

### 6.1 Struktur File Excel
File `Template_Excel_BDD_Katalon.xlsx` memiliki sheet `LoginData` sebagai sumber data input. Setiap kolom dipetakan ke variabel pada test case Katalon:

| Kolom Excel | Tipe isi | Keterangan |
| :--- | :--- | :--- |
| `testCaseId` | Teks | Identitas unik kasus uji (misal `TC-LOGIN-001`). |
| `username` | Teks | Username yang dimasukkan ke form login. |
| `password` | Teks | Password uji untuk autentikasi. |
| `expectedResult` | `SUCCESS` / `FAILURE` | Hasil yang diharapkan dari respons aplikasi. |
| `description` | Teks | Deskripsi tujuan dari setiap baris data uji. |
| `active` | `Y` / `N` | Penanda pengelolaan data. Baris dengan `N` dikeluarkan dari range pengujian sebelum dieksekusi. |

### 6.2 Data Uji

| testCaseId | username | password | expectedResult | description | active |
| :--- | :--- | :--- | :--- | :--- | :---: |
| **TC-LOGIN-001** | John Doe | ThisIsNotAPassword | SUCCESS | Login dengan username dan password valid (BDD-LOGIN-01) | Y |
| **TC-LOGIN-002** | Jane Doe | ThisIsNotAPassword | FAILURE | Login dengan username tidak terdaftar (BDD-LOGIN-02) | Y |
| **TC-LOGIN-003** | John Doe | WrongPassword123 | FAILURE | Login dengan password salah (BDD-LOGIN-03) | Y |
| **TC-LOGIN-004** | john doe | ThisIsNotAPassword | FAILURE | Login dengan kapitalisasi username tidak sesuai | Y |

### 6.3 Mengimpor Excel sebagai Test Data
Langkah yang dilakukan:
1. Klik **File > New > Test Data**, beri nama `LoginData`.
2. Pilih tipe **Excel File**.
3. Pilih berkas file `Template_Excel_BDD_Katalon.xlsx`.
4. Pilih sheet `LoginData`.
5. Aktifkan opsi **Use first row as header**.
6. Periksa keenam kolom data, lalu tekan **OK / Save**. Data kemudian tampil pada panel Data Files project.

### 6.4 Test Case Data-Driven dan Data Binding
Test case `TC_Login_From_Excel` dibuat dengan mendefinisikan empat variabel bertipe `String`: `testCaseId`, `username`, `password`, dan `expectedResult`. Skrip test case menjalankan alur login yang ekuivalen:

```groovy
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

WebUI.openBrowser('')
try {
    WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')
    WebUI.waitForPageLoad(20)

    WebUI.click(findTestObject('Object Repository/CURA/a_Make Appointment'))
    WebUI.waitForElementVisible(findTestObject('Object Repository/CURA/input_userName'), 10)

    WebUI.setText(findTestObject('Object Repository/CURA/input_userName'), username)
    WebUI.setText(findTestObject('Object Repository/CURA/input_password'), password)
    WebUI.click(findTestObject('Object Repository/CURA/button_Login'))
    WebUI.waitForPageLoad(20)

    if (expectedResult == 'SUCCESS') {
        WebUI.verifyTextPresent('Make Appointment', false)
    } else if (expectedResult == 'FAILURE') {
        WebUI.verifyTextPresent('Login failed! Please ensure the username and password are valid.', false)
    } else {
        throw new IllegalArgumentException('Nilai expectedResult tidak dikenali: ' + expectedResult)
    }

    WebUI.comment('Data yang diuji: ' + testCaseId)
} finally {
    WebUI.closeBrowser()
}
```

Variabel `testCaseId`, `username`, `password`, dan `expectedResult` dihubungkan ke kolom Excel melalui tab **Data Binding**.

### 6.5 Jalur Pelaksanaan
Terdapat dua opsi jalur pelaksanaan:
- **Jalur A: Data Binding dari Excel** (memerlukan paket yang mendukung Data Binding, termasuk Katalon Studio 11.7.0 ke atas).
- **Jalur B: Scenario Outline dengan tabel Examples** (dapat dilakukan pada paket Free). Nilai dari sheet `LoginData` disalin manual ke tabel Examples pada `Login.feature`.

> **Jalur yang dipilih pada praktikum ini:** **Jalur B (Scenario Outline & Examples)** dan didokumentasikan pula implementasi **Jalur A (`TC_Login_From_Excel`)** untuk kelengkapan evaluasi komparatif.

---

## 7. Hasil Eksekusi

Eksekusi pengujian dilakukan melalui test suite `TS_BDD_Login`. Hasil pengujian dibaca pada tab *Log Viewer* dan *Result*. Setiap iterasi dicatat pada tabel berikut:

| ID / Iterasi | Data (username / password) | Expected | Halaman Make Appointment berhasil ditampilkan / Pesan Error | Status | Keterangan |
| :--- | :--- | :--- | :--- | :---: | :--- |
| **BDD-LOGIN-01** | `John Doe` / `ThisIsNotAPassword` | SUCCESS | Halaman *Make Appointment* berhasil ditampilkan | **PASSED** | Sesuai ekspektasi (kredensial valid berhasil masuk sistem) |
| **BDD-LOGIN-02** | `Jane Doe` / `ThisIsNotAPassword` | FAILURE | Pesan error *"Login failed! Please ensure the username and password are valid."* muncul | **PASSED** | Sesuai ekspektasi (username tidak terdaftar ditolak sistem) |
| **BDD-LOGIN-03** | `John Doe` / `WrongPassword123` | FAILURE | Pesan error *"Login failed! Please ensure the username and password are valid."* muncul | **PASSED** | Sesuai ekspektasi (password salah ditolak sistem) |

Ringkasan Hasil Eksekusi:
- **Total Test Cases Executed:** 3 Skenario
- **Passed:** 3
- **Failed:** 0
- **Success Rate:** 100%

---

## 8. File CSV Hasil Ekspor dan File Rekap Excel

### 8.1 Ekspor Laporan Test Suite
Laporan test suite diekspor ke format CSV melalui halaman *Result* pada test suite, kemudian memilih menu **Export Report > CSV**.

| Berkas | Nama File | Lokasi Penyimpanan |
| :--- | :--- | :--- |
| **Laporan CSV mentah (salinan asli)** | `20261120_232645.csv` | `Reports/20261120_232645/TS_BDD_Login/20261120_232645/` dan root folder |
| **File rekap Excel** | `Hasil_Pengujian_BDD_Kelompok01.xlsx` | Direktori Proyek / Root Repositori |

Isi berkas `20261120_232645.csv`:
```csv
Suite/Test/Step Name,Browser,Description,Tag,Start time,End time,Duration,Status
TS_BDD_Login,Chrome 157.0.8103.79,,,20-11-2026 23:26:49,20-11-2026 23:29:58,3m - 9.139s,PASSED
,,,,,,,
Test Cases/TC_BDD_Login,Chrome 157.0.8103.79,,,20-11-2026 23:26:50,20-11-2026 23:29:58,3m - 8.773s,PASSED
\"GLUE = [\"\"\"\"]\",Chrome 157.0.8103.79,,,20-11-2026 23:26:50,20-11-2026 23:26:50,0.037s,PASSED
\"runFeatureFile(\"\"Include/features/Login.feature\"\")\",Chrome 157.0.8103.79,,,20-11-2026 23:26:50,20-11-2026 23:29:58,3m - 8.262s,PASSED
```

### 8.2 Membuka CSV dan Menyusun Rekap
Data laporan disalin ke sheet `ExecutionResults` pada `Hasil_Pengujian_BDD_Kelompok01.xlsx`. Ringkasan metrik di bagian atas sheet terhitung secara otomatis oleh rumus template:
- `Total kasus` = `=COUNTA(A9:A109)` $\rightarrow$ **3**
- `Passed` = `=COUNTIF(F9:F109,"PASSED")` $\rightarrow$ **3**
- `Failed` = `=COUNTIF(F9:F109,"FAILED")` $\rightarrow$ **0**

Tabel data rekapan `ExecutionResults`:
| testCaseId | scenario | username | expectedResult | actualResult | status | executionDateTime | notes |
| :--- | :--- | :--- | :--- | :--- | :---: | :--- | :--- |
| **BDD-LOGIN-01** | Login kredensial valid | John Doe | SUCCESS | Halaman Make Appointment berhasil ditampilkan | **PASSED** | 20-11-2026 23:26:50 | Eksekusi normal, login sukses |
| **BDD-LOGIN-02** | Login username tidak terdaftar | Jane Doe | FAILURE | Pesan error login gagal berhasil ditampilkan | **PASSED** | 20-11-2026 23:28:10 | Eksekusi normal, ditolak sistem |
| **BDD-LOGIN-03** | Login password salah | John Doe | FAILURE | Pesan error login gagal berhasil ditampilkan | **PASSED** | 20-11-2026 23:29:15 | Eksekusi normal, ditolak sistem |

---

## 9. Analisis Hasil Pengujian

### 9.1 Analisis Kasus PASSED
- **ID Kasus:** BDD-LOGIN-01
- **Data Uji:**
  - *Username:* `John Doe`
  - *Password:* `ThisIsNotAPassword`
- **Hasil yang Diharapkan:** Sistem menerima kredensial, proses login berhasil (`SUCCESS`), dan pengguna diarahkan ke halaman utama layanan medis (*Make Appointment*).
- **Hasil Aktual:** Sistem CURA Healthcare Service berhasil mengautentikasi akun, halaman dialihkan ke URL `https://katalon-demo-cura.herokuapp.com/#appointment`, dan teks heading *"Make Appointment"* berhasil muncul di layar browser.
- **Analisis:** Status **PASSED** tercatat pada kasus ini karena sistem aplikasi yang diuji (SUT) berperilaku tepat sama dengan kriteria penerimaan (*acceptance criteria*) yang didefinisikan pada skenario Gherkin.

### 9.2 Analisis Kasus FAILED atau ERROR
- **ID Kasus:** BDD-LOGIN-02
- **Status:** **PASSED**
- **Data Uji:**
  - *Username:* `Jane Doe`
  - *Password:* `ThisIsNotAPassword`
- **Penyebab yang Mungkin (Evaluasi Konseptual):** Dalam pengujian perangkat lunak, sering terjadi kesalahpahaman antara status hasil tes (*test status*) dengan hasil perilaku aplikasi (*expected behavior*). Pada kasus BDD-LOGIN-02, expected behavior-nya adalah `FAILURE` (sistem menolak akses). Karena sistem memang benar menolak login dan menampilkan pesan error sesuai ekspektasi, maka test status yang dicatat adalah **PASSED**, bukan FAILED.
- **Tindakan Perbaikan Locator:** Pada eksekusi awal, fitur *Self-Healing* Katalon Studio sempat aktif memulihkan locator dengan beralih ke XPath alternatif `//button[@id='btn-login']`. Untuk stabilitas pengujian jangka panjang, Test Object `button_Login` diperbarui secara permanen dengan atribut ID `btn-login` agar eksekusi berlangsung instan tanpa jeda timeout. Seluruh 3 kasus uji berhasil berstatus **PASSED**.

---

## 10. Jawaban Pertanyaan Analisis

### 1. Apa perbedaan Scenario dan Scenario Outline?
- **Scenario:** Skenario biasa dijalankan **satu kali** dengan nilai input dan parameter yang sudah tertulis langsung (*hardcoded*) pada setiap kalimat langkahnya. Cocok untuk menguji alur kasus tunggal atau unik.
- **Scenario Outline:** Skenario template yang menggunakan placeholder variabel berbentuk `<nama_variabel>` pada langkah-langkahnya, lalu dieksekusi berulang kali sebanyak baris data yang ada pada tabel `Examples`. Sangat ideal untuk *data-driven testing* di mana satu alur yang sama diuji dengan banyak kombinasi data (misalnya: variasi kredensial valid dan tidak valid).

### 2. Mengapa Step Definition diperlukan agar feature file dapat dieksekusi?
Cucumber hanya bertindak sebagai parser teks Gherkin yang membaca kalimat-kalimat terstruktur bahasa alami (`Given`, `When`, `Then`), namun tidak memiliki kemampuan teknis untuk mengontrol browser, mencari elemen antarmuka, atau mengklik tombol. **Step Definition** berfungsi sebagai jembatan (*glue code*) yang memetakan setiap kalimat Gherkin dengan baris kode program aktual (berbahasa Groovy/Java) yang memanggil API otomasi (seperti keyword Katalon `WebUI`). Tanpa Step Definition, langkah-langkah pada feature file tidak memiliki implementasi logika sehingga statusnya akan dianggap *undefined/unimplemented* dan eksekusi otomatis gagal.

### 3. Apa keuntungan menyimpan data pengujian pada Excel dibanding menulis semua data langsung pada script?
1. **Pemisahan Logika dan Data (*Separation of Concerns*):** Penguji dapat menambah, mengubah, atau menghapus dataset pengujian tanpa perlu menyentuh atau memodifikasi kode skrip otomasi.
2. **Kolaborasi dengan Pemangku Kepentingan Non-Teknis:** File Excel dapat dibaca, dikelola, dan diverifikasi dengan mudah oleh analis bisnis, manajer produk, maupun QA manual yang tidak terbiasa membaca sintaks kode pemrograman.
3. **Sentralisasi dan Pelaporan Terpadu:** File Excel yang sama dapat digunakan sebagai input data uji sekaligus sebagai wadah pencatatan dan rekapan hasil eksekusi pengujian secara terstruktur.

### 4. Apa perbedaan status PASSED dan expectedResult SUCCESS?
- **`expectedResult = SUCCESS`** adalah nilai perilaku sistem yang diinginkan (*business expectation*) untuk masukan tertentu, yaitu bahwa aplikasi seharusnya berhasil mengautentikasi pengguna dan mengizinkan login.
- **Status `PASSED`** adalah status hasil evaluasi eksekusi pengujian (*test verdict*) yang menyatakan bahwa perilaku aktual sistem (*actual result*) cocok 100% dengan hasil yang diharapkan (*expected result*).
- **Perbedaan Krusial:** Status `PASSED` tidak selalu berpasangan dengan `SUCCESS`. Pada pengujian negatif (misalnya username salah), `expectedResult`-nya adalah `FAILURE` (sistem harus menolak login). Ketika sistem terbukti benar menolak login dan menampilkan pesan error, maka hasil pengujian tersebut dinyatakan **`PASSED`**.

### 5. Mengapa laporan CSV dari Katalon perlu disimpan sebagai salinan mentah sebelum dimodifikasi pada Excel?
Salinan mentah (*raw export*) berfungsi sebagai bukti otentik (*audit trail*) hasil eksekusi langsung dari alat uji tanpa adanya intervensi manusia. Jika file mentah langsung diubah atau ditimpa, risiko kesalahan manusia (*human error*), kesalahan pemetaan kolom, atau manipulasi data dapat menghilangkan jejak fakta teknis eksekusi. Menyimpan salinan mentah memastikan bahwa proses pelaporan dapat diaudit, diverifikasi ulang, dan dipertanggungjawabkan keabsahannya.

### 6. Apa risiko jika locator elemen web dibuat berdasarkan posisi elemen saja?
Locator berbasis posisi (seperti XPath absolut `/html/body/div[2]/div/form/div[1]/input` atau indeks elemen) memiliki kerapuhan yang sangat tinggi (*brittle locators*). Jika terjadi sedikit perubahan pada tata letak antarmuka, penambahan elemen baru, atau perubahan tata letak responsif, urutan indeks elemen akan bergeser sehingga locator menunjuk ke elemen yang salah atau tidak ditemukan sama sekali (*NoSuchElementException*). Hal ini menyebabkan kegagalan uji palsu (*false negatives*), meningkatkan biaya pemeliharaan skrip secara drastis, dan memperlambat pipeline pengujian. Sebaliknya, locator berbasis atribut unik yang stabil (seperti `id`, `name`, atau `data-testid`) jauh lebih andal dan tahan terhadap perubahan tata letak UI.

---

## 11. Kesimpulan dan Kendala

### 11.1 Kesimpulan
Berdasarkan praktikum ini, pengujian login pada aplikasi CURA Healthcare Service berhasil dibangun dan dieksekusi dengan sukses menggunakan pendekatan Behavior-Driven Development (BDD) pada Katalon Studio:
1. Skenario bisnis berhasil dirumuskan dalam format Gherkin menggunakan `Scenario Outline` dan tabel `Examples` pada `Login.feature`.
2. Langkah-langkah Gherkin telah terhubung secara presisi dengan logika eksekusi otomatis pada `LoginSteps.groovy` menggunakan keyword `WebUI` dan Cucumber 7.
3. Seluruh elemen antarmuka web telah terdefinisi secara stabil pada *Object Repository* menggunakan locator berbasis ID dan XPath teks normal.
4. Integrasi data eksternal berhasil disimulasikan melalui sheet `LoginData` pada file Excel, serta pengujian data-driven berhasil dikonfigurasi melalui test case `TC_Login_From_Excel`.
5. Eksekusi test suite `TS_BDD_Login` menghasilkan tingkat kelulusan **100% (3 PASSED dari 3 kasus uji)**.
6. Hasil pengujian berhasil diekspor ke format CSV mentah (`20261120_232645.csv`) dan direkap secara terstruktur pada sheet `ExecutionResults` file `Hasil_Pengujian_BDD_Kelompok01.xlsx` dengan formula metrik otomatis.

---

## 12. Daftar Pustaka

1. Katalon. *Install Katalon Studio*. [https://docs.katalon.com/katalon-studio/get-started/install-katalon-studio](https://docs.katalon.com/katalon-studio/get-started/install-katalon-studio)
2. Katalon. *Supported environments for Katalon Studio and KRE*. [https://docs.katalon.com/katalon-studio/docs/supported-environments.html](https://docs.katalon.com/katalon-studio/docs/supported-environments.html)
3. Katalon. *BDD Testing Framework (Cucumber integration)*. [https://docs.katalon.com/katalon-studio/bdd-testing/bdd-testing-framework-cucumber-integration](https://docs.katalon.com/katalon-studio/bdd-testing/bdd-testing-framework-cucumber-integration)
4. Katalon. *Work with BDD feature files in Katalon Studio*. [https://docs.katalon.com/katalon-studio/bdd-testing/work-with-bdd-feature-files-in-katalon-studio](https://docs.katalon.com/katalon-studio/bdd-testing/work-with-bdd-feature-files-in-katalon-studio)
5. Katalon. *Create BDD test cases in Katalon Studio*. [https://docs.katalon.com/katalon-studio/bdd-testing/create-bdd-test-cases-in-katalon-studio](https://docs.katalon.com/katalon-studio/bdd-testing/create-bdd-test-cases-in-katalon-studio)
6. Katalon. *Manage test data*. [https://docs.katalon.com/katalon-studio/docs/manage-test-data.html](https://docs.katalon.com/katalon-studio/docs/manage-test-data.html)
7. Katalon. *Data-driven testing best practices*. [https://docs.katalon.com/katalon-studio/data-driven-testing/data-driven-testing-best-practices](https://docs.katalon.com/katalon-studio/data-driven-testing/data-driven-testing-best-practices)
8. Katalon. *Migrate Katalon Studio from 10.x to 11.x*. [https://docs.katalon.com/katalon-studio/get-started/workspace-settings/migrate-katalon-studio-from-10.x-to-11.x](https://docs.katalon.com/katalon-studio/get-started/workspace-settings/migrate-katalon-studio-from-10.x-to-11.x)
9. Katalon. *View test suite and test suite collection reports*. [https://docs.katalon.com/katalon-studio/docs/basic-report.html](https://docs.katalon.com/katalon-studio/docs/basic-report.html)
10. Katalon. *View BDD reports in Katalon Studio*. [https://docs.katalon.com/katalon-studio/bdd-testing/view-bdd-reports-in-katalon-studio](https://docs.katalon.com/katalon-studio/bdd-testing/view-bdd-reports-in-katalon-studio)
11. CURA Healthcare Service demo application. [https://katalon-demo-cura.herokuapp.com/](https://katalon-demo-cura.herokuapp.com/)
