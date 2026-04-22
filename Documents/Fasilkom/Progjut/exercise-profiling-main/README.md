Neal Guarddin\
2406348282\
Advance Programming A\
Modul 7 Profiling

## Performance Testing via GUI

### test_plan_all-student-name VIA GUI
![Screenshot from 2026-04-22 06-21-31.png](assets/images/Screenshot%20from%202026-04-22%2006-21-31.png)

### test_plan_highest-gpa VIA GUI
![Screenshot from 2026-04-22 06-22-05.png](assets/images/Screenshot%20from%202026-04-22%2006-22-05.png)

## Performance Testing via CLI/Command Line

### test_plan_all-student-name VIA CLI
![Screenshot from 2026-04-22 06-26-15.png](assets/images/Screenshot%20from%202026-04-22%2006-26-15.png)

### test_plan_highest-gpa VIA CLI
![Screenshot from 2026-04-22 06-26-29.png](assets/images/Screenshot%20from%202026-04-22%2006-26-29.png)

## Kesimpulan

Berdasarkan hasil profiling pada tampilan **CPU Time**, kedua endpoint yang dioptimasi berhasil memperoleh peningkatan performa yang jauh melampaui target minimum **20%**.

### Hasil CPU Time

- Pada endpoint `/all-student-name`, CPU time method `joinStudentNames()` turun dari **1.811 ms** sebelum refactor menjadi **60 ms** setelah refactor. Ini setara dengan peningkatan sekitar **96,69%**.
- Pada endpoint `/highest-gpa`, CPU time method `findStudentWithHighestGpa()` turun dari **460 ms** menjadi **70 ms**. Ini setara dengan peningkatan sekitar **84,78%**.

Dengan demikian, kedua endpoint telah memenuhi bahkan melampaui requirement optimisasi yang diminta.

### Hasil JMeter

Hasil pengujian menggunakan **JMeter** juga mengonfirmasi bahwa refactor yang dilakukan benar-benar meningkatkan performa dari sisi response time. Nilai **Sample Time** setelah optimisasi menjadi jauh lebih kecil, sehingga endpoint dapat merespons lebih cepat dan lebih stabil saat diuji.

- Pada endpoint `/highest-gpa`, rata-rata **Sample Time** turun dari sekitar **917,2 ms** pada pengujian awal menjadi sekitar **11,3 ms** setelah optimisasi.
- Pada endpoint `/all-student-name`, setelah optimisasi diperoleh rata-rata **Sample Time** sebesar **45 ms**, yang konsisten dengan penurunan CPU time pada profiler.

Secara keseluruhan, refactor yang dilakukan berhasil menurunkan biaya pemrosesan, meningkatkan kecepatan respons, dan **membuat aplikasi lebih ringan** untuk kedua endpoint tersebut.

## Reflection

### 1. Apa perbedaan pendekatan performance testing dengan JMeter dan profiling dengan IntelliJ Profiler dalam konteks optimisasi performa aplikasi?

JMeter dan IntelliJ Profiler memiliki fokus yang berbeda.

- **JMeter** digunakan untuk mengukur performa aplikasi dari sisi eksternal, seperti response time, throughput, dan kestabilan endpoint saat diberi beban tertentu.
- **IntelliJ Profiler** digunakan untuk melihat kondisi internal aplikasi, seperti method mana yang paling banyak memakan CPU time atau memori.

Dengan kata lain:

- JMeter menjawab pertanyaan: **“seberapa cepat endpoint merespons?”**
- IntelliJ Profiler menjawab pertanyaan: **“bagian kode mana yang menyebabkan lambat?”**

Karena itu, keduanya saling melengkapi dalam proses optimisasi performa.

### 2. Bagaimana proses profiling membantu dalam mengidentifikasi dan memahami titik lemah pada aplikasi?

Proses profiling membantu dengan menunjukkan method yang paling mahal dari sisi **CPU time** maupun **memory usage**. Dari hasil profiling, saya dapat melihat dengan lebih jelas bahwa bottleneck memang berada pada method tertentu, seperti `joinStudentNames()` dan `findStudentWithHighestGpa()`.

Sebelum profiling, saya hanya mengetahui bahwa endpoint terasa lambat. Setelah profiling, saya bisa memahami penyebab teknisnya secara lebih spesifik, misalnya:

- adanya pemrosesan berulang di level Java,
- penggunaan operasi yang kurang efisien,
- dan logika yang sebenarnya lebih tepat dijalankan langsung di database.

Dengan begitu, proses optimisasi menjadi lebih terarah dan tidak sekadar berdasarkan tebakan.

### 3. Apakah IntelliJ Profiler efektif untuk membantu menganalisis dan mengidentifikasi bottleneck pada kode aplikasi?

Ya, menurut saya **IntelliJ Profiler sangat efektif**.

Profiler mempermudah saya untuk:

- melihat **method list**,
- memeriksa **CPU time**,
- dan membandingkan hasil **sebelum** serta **sesudah** refactor.

Dengan profiler, saya tidak perlu menebak-nebak method mana yang paling mahal, karena datanya terlihat langsung. Fitur **comparison view** juga sangat membantu untuk membuktikan bahwa perubahan kode memang memberi dampak nyata.

Dalam tugas ini, profiler memudahkan saya membuktikan bahwa optimisasi yang dilakukan benar-benar menurunkan CPU time secara signifikan.

### 4. Apa tantangan utama saat melakukan performance testing dan profiling, serta bagaimana cara mengatasinya?

Tantangan utamanya adalah hasil pengukuran tidak selalu konsisten, terutama pada run pertama. Hal ini bisa terjadi karena:

- **JIT warm-up**,
- inisialisasi bean,
- cache,
- dan proses startup lain dari JVM maupun Spring Boot.

Selain itu, hasil JMeter dan profiler kadang tampak berbeda karena keduanya memang mengukur hal yang berbeda.

Cara saya mengatasinya adalah:

- tidak menggunakan hasil run pertama sebagai acuan,
- menjalankan ulang aplikasi beberapa kali,
- melakukan warm-up terlebih dahulu,
- lalu membandingkan hasil yang sudah lebih stabil.

Saya juga memisahkan analisis antara metrik internal dari profiler dan metrik eksternal dari JMeter agar interpretasinya tidak tercampur.

### 5. Apa manfaat utama yang didapat dari penggunaan IntelliJ Profiler untuk profiling kode aplikasi?

Manfaat utamanya adalah saya bisa mengetahui **sumber bottleneck secara presisi**.

Profiler membantu saya untuk:

- fokus pada method yang benar-benar bermasalah,
- menghemat waktu analisis,
- dan memvalidasi hasil refactor dengan data kuantitatif, bukan hanya asumsi.

Dalam kasus ini, saya bisa melihat penurunan CPU time yang sangat besar setelah logika dipindahkan agar lebih efisien, misalnya dengan memanfaatkan query database yang lebih tepat daripada memproses semuanya di Java.

### 6. Bagaimana menangani situasi ketika hasil profiling dari IntelliJ Profiler tidak sepenuhnya konsisten dengan temuan performance testing menggunakan JMeter?

Saya menanganinya dengan memahami bahwa kedua alat tersebut memang mengukur perspektif yang berbeda.

- **JMeter** mengukur total waktu respons endpoint.
- **IntelliJ Profiler** lebih fokus pada biaya eksekusi di dalam aplikasi.

Jadi, ketika hasilnya tidak identik, saya tidak langsung menganggap salah satu hasil tersebut salah. Saya melihat kembali konteks pengukurannya, misalnya apakah ada pengaruh dari:

- network,
- serialization,
- inisialisasi framework,
- atau perbedaan beban saat pengujian.

Setelah itu, saya menggunakan keduanya secara bersama-sama:

- profiler untuk menemukan bottleneck kode,
- dan JMeter untuk memastikan bahwa perbaikan tersebut benar-benar terasa pada response time endpoint.

### 7. Strategi apa yang diterapkan saat mengoptimalkan kode setelah menganalisis hasil performance testing dan profiling? Bagaimana memastikan perubahan tidak merusak fungsionalitas aplikasi?

Strategi yang saya gunakan adalah mengoptimalkan bagian yang paling mahal terlebih dahulu berdasarkan data profiling.

Untuk endpoint yang diuji, saya melakukan beberapa langkah berikut:

- mengurangi pemrosesan yang tidak perlu di level Java,
- memindahkan pekerjaan yang lebih cocok dilakukan oleh database,
- mengoptimalkan query,
- dan menghindari pembuatan object atau operasi string yang berlebihan.

Contohnya, pencarian nilai maksimum dan penggabungan data lebih efisien jika dilakukan langsung melalui query database daripada diproses seluruhnya di Java.

Agar perubahan tidak merusak fungsionalitas aplikasi, saya tetap:

- menjalankan endpoint yang sama setelah refactor,
- membandingkan output sebelum dan sesudah perubahan,
- melakukan pengujian ulang dengan JMeter,
- dan melakukan profiling ulang dengan IntelliJ Profiler.

Dengan cara tersebut, saya dapat memastikan bahwa aplikasi tetap benar secara fungsi sekaligus menjadi lebih cepat dari sisi performa.