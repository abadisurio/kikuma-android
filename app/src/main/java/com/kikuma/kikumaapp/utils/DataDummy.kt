package com.kikuma.kikumaapp.utils

import com.kikuma.kikumaapp.data.source.local.entity.*

object DataDummy {

    fun generateDummyArticle(): List<ArticleEntity> {
        val article = ArrayList<ArticleEntity>()

        article.add(
            ArticleEntity("a1",
        "Jerawat",
        "Jerawat (acne) adalah gangguan pada kulit yang berhubungan dengan produksi minyak (sebum) berlebih. Jerawat terjadi ketika folikel rambut atau tempat tumbuhnya rambut tersumbat oleh minyak dan sel kulit mati. Hal tersebut menyebabkan peradangan serta penyumbatan pada pori-pori kulit. Peradangan ini ditandai dengan munculnya benjolan kecil yang terkadang berisi nanah di atas kulit. Gangguan kulit ini dapat terjadi di bagian tubuh dengan kelenjar minyak terbanyak, yaitu di wajah, leher, bagian atas dada, dan punggung.",
        "https://asset.kompas.com/crops/apZr9Njk4w_Hi1QUNF4W0uzH4sM=/0x0:1000x667/750x500/data/photo/2018/06/17/3164539761.jpg",
        "Saturday, 8 May 2021",
        )
        )

        article.add(
            ArticleEntity("a2",
                "Cacar Air",
                "Penyakit cacar air atau dalam istilah medis disebut varicella adalah infeksi yang disebabkan virus Varicella zoster. Penderita yang terinfeksi virus ini ditandai dengan munculnya ruam kemerahan berisi cairan yang sangat gatal di seluruh tubuh. Pada sebagian besar penderitanya, cacar air merupakan penyakit ringan, khususnya setelah digalakkan program vaksinasi cacar air pada pertengahan tahun 1990-an. Kendati demikian, cacar air tetap dapat menimbulkan komplikasi yang lebih serius pada penderita yang memiliki sistem kekebalan tubuh lemah, misalnya penderita HIV/AIDS.",
                "https://d1bpj0tv6vfxyp.cloudfront.net/mitosataufaktacacarairsebabkanensefalitishalodoc.jpg",
                "Saturday, 8 May 2021",
            )
        )

        article.add(
            ArticleEntity("a3",
                "Campak",
                "Campak adalah munculnya ruam kemerahan di seluruh tubuh akibat infeksi virus. Campak merupakan penyakit menular dan dapat menyebabkan komplikasi serius, terutama pada bayi dan anak-anak. Campak disebabkan oleh virus, yang menular melalui percikan air liur yang dikeluarkan penderita saat batuk atau bersin. Penularan juga bisa terjadi bila seseorang menyentuh hidung atau mulut, setelah memegang benda yang terpercik air liur penderita.",
                "https://cdn-2.tstatic.net/tribunnews/foto/bank/images/korban-campak-nih2_20170412_190713.jpg",
                "Saturday, 8 May 2021",
            )
        )

        article.add(
            ArticleEntity("a4",
                "Jerawat",
                "Jerawat (acne) adalah gangguan pada kulit yang berhubungan dengan produksi minyak (sebum) berlebih. Jerawat terjadi ketika folikel rambut atau tempat tumbuhnya rambut tersumbat oleh minyak dan sel kulit mati. Hal tersebut menyebabkan peradangan serta penyumbatan pada pori-pori kulit. Peradangan ini ditandai dengan munculnya benjolan kecil yang terkadang berisi nanah di atas kulit. Gangguan kulit ini dapat terjadi di bagian tubuh dengan kelenjar minyak terbanyak, yaitu di wajah, leher, bagian atas dada, dan punggung.",
                "https://asset.kompas.com/crops/apZr9Njk4w_Hi1QUNF4W0uzH4sM=/0x0:1000x667/750x500/data/photo/2018/06/17/3164539761.jpg",
                "Saturday, 8 May 2021",
            )
        )

        article.add(
            ArticleEntity("a5",
                "Jerawat",
                "Jerawat (acne) adalah gangguan pada kulit yang berhubungan dengan produksi minyak (sebum) berlebih. Jerawat terjadi ketika folikel rambut atau tempat tumbuhnya rambut tersumbat oleh minyak dan sel kulit mati. Hal tersebut menyebabkan peradangan serta penyumbatan pada pori-pori kulit. Peradangan ini ditandai dengan munculnya benjolan kecil yang terkadang berisi nanah di atas kulit. Gangguan kulit ini dapat terjadi di bagian tubuh dengan kelenjar minyak terbanyak, yaitu di wajah, leher, bagian atas dada, dan punggung.",
                "https://asset.kompas.com/crops/apZr9Njk4w_Hi1QUNF4W0uzH4sM=/0x0:1000x667/750x500/data/photo/2018/06/17/3164539761.jpg",
                "Saturday, 8 May 2021",
            )
        )
        return article
    }

    fun generateDummyHistory(): List<HistoryEntity> {
        val history = ArrayList<HistoryEntity>()

        history.add(HistoryEntity("h1",
        "Campak",
            "wkwk",
        "Saturday, 8 May 2021 13:42"))

        history.add(HistoryEntity("h2",
            "Cacar Air",
            "wkwk",
            "Saturday, 8 May 2021 13:42"))

        history.add(HistoryEntity("h3",
            "Campak",
            "wkwk",
            "Saturday, 8 May 2021 13:42"))

        history.add(HistoryEntity("h4",
            "Cacar Air",
            "wkwk",
            "Saturday, 8 May 2021 13:42"))

        history.add(HistoryEntity("h5",
            "Kudis",
            "wkwk",
            "Saturday, 8 May 2021 13:42"))

        return history
    }

    fun generateDummyResult(): List<DiseaseEntity> {

        val result = ArrayList<DiseaseEntity>()

        result.add(
            DiseaseEntity("r1",
                    "Jerawat",
                    "Penyakit cacar air atau dalam istilah medis disebut varicella adalah infeksi yang disebabkan virus Varicella zoster. Penderita yang terinfeksi virus ini ditandai dengan munculnya ruam kemerahan berisi cairan yang sangat gatal di seluruh tubuh. Pada sebagian besar penderitanya, cacar air merupakan penyakit ringan, khususnya setelah digalakkan program vaksinasi cacar air pada pertengahan tahun 1990-an. Kendati demikian, cacar air tetap dapat menimbulkan komplikasi yang lebih serius pada penderita yang memiliki sistem kekebalan tubuh lemah, misalnya penderita HIV/AIDS.",
                    "84%")
        )

        result.add(
            DiseaseEntity("r2",
                    "Campak",
                    "Campak adalah munculnya ruam kemerahan di seluruh tubuh akibat infeksi virus. Campak merupakan penyakit menular dan dapat menyebabkan komplikasi serius, terutama pada bayi dan anak-anak. Campak disebabkan oleh virus, yang menular melalui percikan air liur yang dikeluarkan penderita saat batuk atau bersin. Penularan juga bisa terjadi bila seseorang menyentuh hidung atau mulut, setelah memegang benda yang terpercik air liur penderita.",
                    "70%")
        )

        result.add(
            DiseaseEntity("r3",
                    "Kudis",
                    "Kudis adalah kondisi yang ditandai dengan munculnya rasa sangat gatal di kulit, terutama pada malam hari, disertai dengan timbulnya ruam bintik-bintik menyerupai jerawat atau lepuhan kecil bersisik. Kondisi ini merupakan dampak dari adanya tungau yang hidup dan bersarang di kulit. Jumlah tungau yang terdapat di kulit penderita kudis berkisar 10-15 ekor, dan dapat berkembang biak hingga berjumlah jutaan, dan menyebar ke bagian tubuh lain, jika tidak mendapatkan penanganan tepat, tungau. Kudis merupakan penyakit yang mudah menular, baik secara kontak langsung atau tidak. Maka dari itu, jika telah merasakan gejala-gejala kudis, dianjurkan untuk segera menemui dokter.",
                    "80%")
        )

        return result
    }

    fun generateDummyTips(resultId: String): List<TipsEntity> {

        val tips = ArrayList<TipsEntity>()

        tips.add(
            TipsEntity(
                "{$resultId}t1",
                resultId,
                "Hindari Menggaruk Ruam"
            )
        )

        tips.add(
            TipsEntity(
                "{$resultId}t2",
                resultId,
                "Berikan Obat Penurun Panas Demam"
            )
        )

        tips.add(
            TipsEntity(
                "{$resultId}t3",
                resultId,
                "Mandi Air Dingin"
            )
        )

        tips.add(
            TipsEntity(
                "{$resultId}t4",
                resultId,
                "Istirahat di Rumah"
            )
        )

        tips.add(
            TipsEntity(
                "{$resultId}t5",
                resultId,
                "Rutin Menjaga Kulit dan Tubuh Tetap Terhidrasi"
            )
        )

        return tips
    }

    fun generateDummyHospitals(): List<HospitalEntity> {
        val hospital = ArrayList<HospitalEntity>()

        hospital.add(HospitalEntity("h1",
                "Erha Clinic Kemang",
                "Jl. Kemang Raya No.93, RT.2/RW.2, Kemang, Bangka, Kec. Mampang Prpt., Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12730",
                4.1,
                "Start From Rp200000",
                "https://istiana.sutanti.com/wp-content/uploads/2018/06/Erha-Clinic-Kemang.jpg"))

        hospital.add(HospitalEntity("h2",
                "Natasha Skin Clinic Center - Jakarta Kebayoran Baru",
                "Jl. Wijaya II No.48, RT.5/RW.7, Melawai, Kec. Kby. Baru, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12160",
                4.0,
                "Start From Rp100000",
                "https://res.cloudinary.com/dk0z4ums3/image/upload/w_360,h_240,c_fill,dpr_2.0,f_auto/v1548138725/hospital_image/cfaa90a32045_kebayoranwijaya.png.png"))

        hospital.add(HospitalEntity("h3",
                "NMW Skin Care",
                "Jl. Petogogan II No.29, RT.8/RW.6, Pulo, Kec. Kby. Baru, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12160",
                4.0,
                "Start From Rp100000",
                "https://images.weddingku.com/images/upload/articles/images/xdtf8jivu5w641720181523.jpg"))

        hospital.add(HospitalEntity("h4",
                "Ms Glow Aesthetic Clinic Jakarta",
                "Jl. Kemang Raya No.27, RT.6/RW.1, Bangka, Kec. Mampang Prpt., Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12730",
                3.8,
                "Start From Rp150000",
                "https://soc-phoenix.s3-ap-southeast-1.amazonaws.com/wp-content/uploads/2019/02/28141038/msglow-clinic.jpg"))

        hospital.add(HospitalEntity("h5",
                "Klinik Utama DR.Med. Kun Jayanata, SPKK K",
                "Jl. Hang Lekir I No.2-36, RT.5/RW.8, Grogol Sel., Kec. Kby. Lama, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12220",
                3.4,
                "Start From 350000",
                ""))

        return hospital
    }
}