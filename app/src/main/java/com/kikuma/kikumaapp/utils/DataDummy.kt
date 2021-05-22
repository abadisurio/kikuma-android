package com.kikuma.kikumaapp.utils

import com.kikuma.kikumaapp.data.source.local.entity.ArticleEntity
import com.kikuma.kikumaapp.data.source.local.entity.HistoryEntity

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
        "Saturday, 8 May 2021 13:42"))

        history.add(HistoryEntity("h2",
            "Cacar Air",
            "Saturday, 8 May 2021 13:42"))

        history.add(HistoryEntity("h3",
            "Campak",
            "Saturday, 8 May 2021 13:42"))

        history.add(HistoryEntity("h4",
            "Cacar Air",
            "Saturday, 8 May 2021 13:42"))

        history.add(HistoryEntity("h5",
            "Kudis",
            "Saturday, 8 May 2021 13:42"))

        return history
    }
}