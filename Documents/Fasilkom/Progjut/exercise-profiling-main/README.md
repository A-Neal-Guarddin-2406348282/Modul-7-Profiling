Neal Guarddin\
2406348282\
Advance Programming A\
Modul 7 Profiling

## Performance Testing via GUI
**test_plan_all-student-name VIA GUI**
![Screenshot from 2026-04-22 06-21-31.png](assets/images/Screenshot%20from%202026-04-22%2006-21-31.png)

**test_plan_highest-gpa VIA GUI**
![Screenshot from 2026-04-22 06-22-05.png](assets/images/Screenshot%20from%202026-04-22%2006-22-05.png)

# Performance Testing via CLI/Command Line
**test_plan_all-student-name VIA CLI**
![Screenshot from 2026-04-22 06-26-15.png](assets/images/Screenshot%20from%202026-04-22%2006-26-15.png)

**test_plan_highest-gpa VIA CLI**
![Screenshot from 2026-04-22 06-26-29.png](assets/images/Screenshot%20from%202026-04-22%2006-26-29.png)

## Kesimpulan
Berdasarkan hasil profiling pada tampilan **CPU Time**, kedua endpoint yang dioptimasi berhasil memperoleh peningkatan performa yang jauh melampaui target minimum **20%**. Pada endpoint `/all-student-name`, CPU time method `joinStudentNames()` turun dari **1.811 ms** sebelum refactor menjadi **60 ms** setelah refactor, atau setara dengan peningkatan sekitar **96,69%**. Sementara itu, pada endpoint `/highest-gpa`, CPU time method `findStudentWithHighestGpa()` turun dari **460 ms** menjadi **70 ms**, atau setara dengan peningkatan sekitar **84,78%**. Dengan demikian, kedua endpoint telah memenuhi bahkan melampaui requirement optimisasi yang diminta.

Hasil pengujian menggunakan **JMeter** juga mengonfirmasi bahwa refactor yang dilakukan benar-benar meningkatkan performa dari sisi response time. Nilai **Sample Time** setelah optimisasi menjadi jauh lebih kecil, yang menunjukkan bahwa endpoint merespons lebih cepat dan lebih stabil saat diuji. Secara khusus, pada endpoint `/highest-gpa`, rata-rata Sample Time turun dari sekitar **917,2 ms** pada pengujian awal menjadi sekitar **11,3 ms** setelah optimisasi. Sementara itu, endpoint `/all-student-name` setelah optimisasi menghasilkan rata-rata Sample Time sebesar **45 ms**, yang konsisten dengan penurunan CPU time yang terlihat pada profiler.
Secara keseluruhan, refactor yang dilakukan berhasil me nurunkan biaya pemrosesan, meningkatkan kecepatan respons, dan **membuat aplikasi lebih ringan** untuk kedua endpoint tersebut.

## Reflection

### 1. Apa perbedaan pendekatan performance testing dengan JMeter dan profiling dengan IntelliJ Profiler dalam konteks optimisasi performa aplikasi?
JMeter dan IntelliJ Profiler memiliki fokus yang berbeda. **JMeter** digunakan untuk mengukur performa aplikasi dari sisi eksternal, misalnya response time, throughput, dan kestabilan endpoint saat diberi beban tertentu. Dengan kata lain, JMeter membantu melihat bagaimana aplikasi dirasakan oleh pengguna atau client. Sementara itu, **IntelliJ Profiler** digunakan untuk melihat kondisi internal aplikasi, seperti method mana yang paling banyak memakan CPU time atau memori. Jadi, JMeter menjawab pertanyaan *“seberapa cepat endpoint merespons?”*, sedangkan profiler menjawab *“bagian kode mana yang menyebabkan lambat?”*. Keduanya saling melengkapi dalam proses optimisasi.

### 2. Bagaimana proses profiling membantu dalam mengidentifikasi dan memahami titik lemah pada aplikasi?
Proses profiling membantu dengan menunjukkan method yang paling mahal dari sisi CPU time atau memory usage. Dari hasil profiling, saya dapat melihat dengan lebih jelas bahwa bottleneck memang berada pada method tertentu, misalnya `joinStudentNames()` dan `findStudentWithHighestGpa()`. Sebelum profiling, saya hanya tahu endpoint terasa lambat. Setelah profiling, saya bisa mengetahui penyebab teknisnya secara spesifik, misalnya adanya pemrosesan berulang di level Java yang sebenarnya bisa dipindahkan ke database. Dengan begitu, proses optimisasi menjadi lebih terarah dan tidak sekadar menebak-nebak.

### 3. Apakah IntelliJ Profiler efektif untuk membantu menganalisis dan mengidentifikasi bottleneck pada kode aplikasi?
Ya, menurut saya **IntelliJ Profiler sangat efektif**. Alat ini mempermudah saya untuk melihat method list, CPU time, dan perbandingan hasil sebelum serta sesudah refactor. Dengan profiler, saya tidak perlu menebak-nebak method mana yang paling mahal, karena datanya terlihat langsung. Fitur comparison view juga sangat membantu untuk membuktikan bahwa perubahan kode memang memberi dampak nyata. Dalam tugas ini, profiler memudahkan saya membuktikan bahwa optimisasi yang dilakukan benar-benar menurunkan CPU time secara signifikan.

### 4. Apa tantangan utama saat melakukan performance testing dan profiling, serta bagaimana cara mengatasinya?
Tantangan utamanya adalah hasil pengukuran tidak selalu konsisten pada run pertama karena adanya **JIT warm-up**, inisialisasi bean, cache, dan proses startup lain dari JVM maupun Spring Boot. Selain itu, hasil JMeter dan profiler kadang terlihat berbeda karena keduanya mengukur hal yang berbeda. Untuk mengatasinya, saya melakukan beberapa langkah: tidak memakai hasil run pertama sebagai acuan, menjalankan ulang aplikasi beberapa kali, melakukan warm-up terlebih dahulu, lalu membandingkan hasil yang lebih stabil. Saya juga memisahkan analisis antara metrik internal dari profiler dan metrik eksternal dari JMeter agar interpretasinya tidak tercampur.

### 5. Apa manfaat utama yang didapat dari penggunaan IntelliJ Profiler untuk profiling kode aplikasi?
Manfaat utamanya adalah saya bisa mengetahui **sumber bottleneck secara presisi**. Profiler membantu menghemat waktu karena saya dapat langsung fokus pada method yang benar-benar bermasalah. Selain itu, profiler juga membantu memvalidasi hasil refactor dengan data kuantitatif, bukan hanya asumsi. Dalam kasus ini, saya bisa melihat penurunan CPU time yang sangat besar setelah logika dipindahkan agar lebih efisien, misalnya dengan memanfaatkan query database yang lebih tepat daripada memproses semuanya di Java.

### 6. Bagaimana menangani situasi ketika hasil profiling dari IntelliJ Profiler tidak sepenuhnya konsisten dengan temuan performance testing menggunakan JMeter?
Saya menanganinya dengan memahami bahwa kedua alat tersebut memang mengukur perspektif yang berbeda. Jika **JMeter** mengukur total waktu respons endpoint, maka **IntelliJ Profiler** lebih fokus pada biaya eksekusi di dalam aplikasi. Jadi, ketika hasilnya tidak identik, saya tidak langsung menganggap salah satu hasil itu keliru. Saya melihat kembali konteks pengukurannya: apakah ada faktor network, serialization, inisialisasi framework, atau perbedaan beban yang memengaruhi hasil JMeter. Setelah itu, saya menggunakan kedua hasil tersebut secara bersama-sama: profiler untuk menemukan bottleneck kode, dan JMeter untuk memastikan bahwa perbaikan tersebut benar-benar terasa pada response time endpoint.

### 7. Strategi apa yang diterapkan saat mengoptimalkan kode setelah menganalisis hasil performance testing dan profiling? Bagaimana memastikan perubahan tidak merusak fungsionalitas aplikasi?
Strategi yang saya gunakan adalah mengoptimalkan bagian yang paling mahal terlebih dahulu berdasarkan data profiling. Untuk endpoint yang diuji, saya mengurangi pemrosesan yang tidak perlu di level Java dan memindahkan pekerjaan yang lebih cocok dilakukan oleh database, misalnya pencarian nilai maksimum dan penggabungan data yang bisa dilakukan langsung lewat query. Selain itu, saya juga menghindari pembuatan object atau operasi string yang berlebihan. Agar perubahan tidak merusak fungsionalitas, saya tetap menjalankan endpoint yang sama setelah refactor dan membandingkan hasil output-nya. Setelah itu, saya melakukan pengujian ulang dengan JMeter dan profiling ulang dengan IntelliJ Profiler untuk memastikan bahwa aplikasi tetap benar secara fungsi sekaligus lebih cepat dari sisi performa.