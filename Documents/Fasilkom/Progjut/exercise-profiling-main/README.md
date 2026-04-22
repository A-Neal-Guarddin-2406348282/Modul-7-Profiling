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
Secara keseluruhan, refactor yang dilakukan berhasil menurunkan biaya pemrosesan, meningkatkan kecepatan respons, dan **membuat aplikasi lebih ringan** untuk kedua endpoint tersebut.