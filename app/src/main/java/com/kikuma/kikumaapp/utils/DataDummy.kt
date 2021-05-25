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
                    "Cacar Air",
                    "Penyakit cacar air atau dalam istilah medis disebut varicella adalah infeksi yang disebabkan virus Varicella zoster. Penderita yang terinfeksi virus ini ditandai dengan munculnya ruam kemerahan berisi cairan yang sangat gatal di seluruh tubuh. Pada sebagian besar penderitanya, cacar air merupakan penyakit ringan, khususnya setelah digalakkan program vaksinasi cacar air pada pertengahan tahun 1990-an. Kendati demikian, cacar air tetap dapat menimbulkan komplikasi yang lebih serius pada penderita yang memiliki sistem kekebalan tubuh lemah, misalnya penderita HIV/AIDS.")
        )

        result.add(
            DiseaseEntity("r2",
                    "Campak",
                    "Campak adalah munculnya ruam kemerahan di seluruh tubuh akibat infeksi virus. Campak merupakan penyakit menular dan dapat menyebabkan komplikasi serius, terutama pada bayi dan anak-anak. Campak disebabkan oleh virus, yang menular melalui percikan air liur yang dikeluarkan penderita saat batuk atau bersin. Penularan juga bisa terjadi bila seseorang menyentuh hidung atau mulut, setelah memegang benda yang terpercik air liur penderita.")
        )

        result.add(
            DiseaseEntity("r3",
                    "Kudis",
                    "Kudis adalah kondisi yang ditandai dengan munculnya rasa sangat gatal di kulit, terutama pada malam hari, disertai dengan timbulnya ruam bintik-bintik menyerupai jerawat atau lepuhan kecil bersisik. Kondisi ini merupakan dampak dari adanya tungau yang hidup dan bersarang di kulit. Jumlah tungau yang terdapat di kulit penderita kudis berkisar 10-15 ekor, dan dapat berkembang biak hingga berjumlah jutaan, dan menyebar ke bagian tubuh lain, jika tidak mendapatkan penanganan tepat, tungau. Kudis merupakan penyakit yang mudah menular, baik secara kontak langsung atau tidak. Maka dari itu, jika telah merasakan gejala-gejala kudis, dianjurkan untuk segera menemui dokter.")
        )

        return result
    }

    fun generateDummyTips(resultId: String): List<TipsEntity> {

        val tips = ArrayList<TipsEntity>()

        tips.add(
            TipsEntity(
                "{$resultId}t1",
                "r1",
                "Hindari Menggaruk Ruam"
            )
        )

        tips.add(
            TipsEntity(
                "{$resultId}t2",
                "r1",
                "Berikan Obat Penurun Panas Demam"
            )
        )

        tips.add(
            TipsEntity(
                "{$resultId}t3",
                "r1",
                "Mandi Air Dingin"
            )
        )

        tips.add(
            TipsEntity(
                "{$resultId}t4",
                "r1",
                "Istirahat di Rumah"
            )
        )

        tips.add(
            TipsEntity(
                "{$resultId}t5",
                "r1",
                "Rutin Menjaga Kulit dan Tubuh Tetap Terhidrasi"
            )
        )

        return tips
    }
}