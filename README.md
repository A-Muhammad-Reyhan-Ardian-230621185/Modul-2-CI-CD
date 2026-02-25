## Refleksi Module 2

### 1. Masalah Kualitas Kode yang Diperbaiki

Beberapa masalah yang saya perbaiki selama exercise ini:

- **Pinning GitHub Actions ke commit SHA** — Mereferensikan action menggunakan tag mutable (`@v4`) berisiko terhadap serangan supply chain
- **Update annotation pada HTTP method** — Saya memperbarui annotation pada method controller agar sesuai dengan best practice Spring Boot (menggunakan `@GetMapping` pada HomePageController).
- **Menambahkan file verification metadata** — Saya menambahkan file verification metadata untuk memastikan integritas dan keamanan dependency yang digunakan dalam project.

### 2. Apakah CI/CD Workflow Sudah Memenuhi Definisi CI/CD?

Ya, menurut saya implementasi saat ini sudah memenuhi definisi CI/CD. **CI** tercapai karena setiap push otomatis memicu workflow `ci.yml` yang melakukan unit test, build dengan gradle dan analisis kode dengan sonarcloud. **CD** tercapai karena workflow `deploy.yml` secara otomatis melakukan deploy ke koyeb. Kedua workflow ini membentuk pipeline CI/CD yang lengkap: kode otomatis diuji dan dianalisis, lalu langsung di-deploy
