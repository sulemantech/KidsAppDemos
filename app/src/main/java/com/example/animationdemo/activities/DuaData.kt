package com.example.animationdemo.activities

import com.example.animationdemo.R

data class Dua(
    val arabic: String,
    val translation: String,
    val reference: String,
    val backgroundResId: Int,
    val statusBarColorResId: Int,
    val fullAudioResId: Int,
    val image:Int,
    val wordAudioPairs: List<Pair<String, Int>>
)

val duaList = listOf(
    Dua(
        arabic = "سُبْحَانَ اللّٰہِ وَبِحَمْدِہِ سُبْحَانَ اللّٰہِ الْعَظِیْمِ",
        translation = "Glory be to Allah and all praise be to Him; Glory be to Allah, the Most Great.",
        reference = "[Sahih Muslim]",
        backgroundResId = R.drawable.duaa1_bg,
        statusBarColorResId = R.color.top_nav,
        image = R.drawable.kaaba,
        fullAudioResId = R.raw.dua1_part1_audio1,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہِ الْعَظِیْم" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہ" to R.raw.audioo3,
        )
    ),
    Dua(
        arabic = "اَللّٰہُ اَکْبَرُکَبِیْرًاوَّالْحَمْدُ لِلّٰہِ کَثِیْرًاوَّسُبْحَانَ اللّٰہِ بُکْرَةً وَّاَصِیْلًا",
        translation = "Allah is truly Great, praise be to Allah in abundance and glory be to Allah in the morning and the evening.",
        reference = "[Sahih Muslim]",
        backgroundResId = R.drawable.duaa5_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "کَثِیْرًاوَّسُبْحَانَُ" to R.raw.dua1_part2_02,
            "وَّالْحَمْدُ لِلّٰہ" to R.raw.dua1_part2_03,
            "اَللّٰہُ اَکْبَرُکَبِیْرًًًَِ" to R.raw.dua1_part2_04,
            "اللّٰہِ بُکْرَةً وَّاَصِیْلًا" to R.raw.dua1_part2_01,

        )
    ),
    Dua(
        arabic = "اَللّٰھُمَّ صَلِّ عَلٰی مُحَمَّدٍ وَّعَلٰی آلِ مُحَمَّدٍِ",
        translation = "O Allah! Bestow Your mercy upon Muhammad and upon the descendants of Muhammad.",
        reference = "[Sunan al-Nasā‘ī]",
        backgroundResId = R.drawable.duaa2_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "آلِ مُحَمَّدَ" to R.raw.audioo3,
            "مُحَمَّدٍ وَّعَلٰیَ" to R.raw.audioo3,
            "صَلِّ عَلٰی" to R.raw.audioo2,
            "اَللّٰھُمَّ" to R.raw.audioo1,
        )
    ),
    Dua(
        arabic = "لَا اِلٰہَ اِلَّا اللّٰہُ وَحْدَہُ لَا شَرِیْکَ لَہُ،لَہُ الْمُلْکُ وَلَہُ الْحَمْدُ  یُحْیی وَ یُمِیْتُ وَھُوَ حَی لَّا یَمُوْتُ بِیَدِہِ الْخَیْرُ وَھُوَ عَلٰی کُلِّ شَیْئٍ قَدِیْردٍِ",
        translation = "None has the right to be worshipped except Allah, alone, He has no partner. To Him belongs the dominion and to Him is all praise. He gives life and causes death, and He is living and does not die. In His hand is all good and He is over all things All-Powerful.",
        reference = "[Sahih Muslim]",
        backgroundResId = R.drawable.duaa5_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "لَا اِلٰہَ اِلَّا اللّٰہُ" to R.raw.audioo1,
            "وَحْدَہُ لَا شَرِیْکَ لَہ" to R.raw.audioo2,
            "لَہُ الْمُلْکُ وَلَہُ الْحَمْدَ" to R.raw.audioo3,
            "یُحْیی وَ یُمِیْتَُ" to R.raw.audioo3,
            "وَھُوَ حَی لَّا یَمُوْتُ" to R.raw.audioo3,
            "بِیَدِہِ الْخَیْرُ" to R.raw.audioo3,
            "وَھُوَ عَلٰی کُلِّ شَیْئٍ قَدِیْرد" to R.raw.audioo3,
        )
    ),
    Dua(
        arabic = "اَللّٰھُمَّ بِکَ اَصْبَحْنَا وَ بِکَ اَمْسَیْنَا وَ بِکَ نَحْیَا وَ بِکَ نَمُوْتُ وَ إِلَـيْكَ اَلْمَصِیْرِ",
        translation = "O Allah, by Your leave we have reached the morning and by Your leave we have reached the evening (In the evening say; O Allah, by Your leave we have reached the evening…), by Your leave we live and die, and unto You is our return",
        reference = "[Sunan al-Tirmidhī]",
        backgroundResId = R.drawable.duaa3_bg,
        statusBarColorResId = R.color.top_nav4,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "وَإِلَـيْكَ اَلْمَصِیْر" to R.raw.audioo2,
            "وَبِکَ نَمُوْتَُ" to R.raw.audioo3,
            "وَبِکَ نَحْیَاَُ" to R.raw.audioo3,
            "وَبِکَ نَحْیَاُ" to R.raw.audioo3,
            "وَبِکَ اَمْسَیْنَا" to R.raw.audioo3,
            "اَللّٰھُمَّ بِکَ اَصْبَحْنَا" to R.raw.audioo3,
        )
    ),
    Dua(
        arabic = "اَللّٰھُمَّ بِکَ اَصْبَحْنَا وَ بِکَ اَمْسَیْنَا وَ بِکَ نَحْیَا وَ بِکَ نَمُوْتُ وَ إِلَـيْكَ اَلْمَصِیْرِ",
        translation = "O Allah, by Your leave we have reached the morning and by Your leave we have reached the evening (In the evening say; O Allah, by Your leave we have reached the evening…), by Your leave we live and die, and unto You is our return",
        reference = "[Sunan al-Tirmidhī]",
        backgroundResId = R.drawable.duaa5_bg,
        statusBarColorResId = R.color.top_nav5,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "اَللّٰھُمَّ بِکَ اَصْبَحْنَا" to R.raw.audioo3,
            "وَبِکَ اَمْسَیْنَا" to R.raw.audioo3,
            "وَبِکَ نَحْیَاُ" to R.raw.audioo3,
            "وَبِکَ نَحْیَاَُ" to R.raw.audioo3,
            "وَبِکَ نَمُوْتَُ" to R.raw.audioo3,
            "وَإِلَـيْكَ اَلْمَصِیْر" to R.raw.audioo2,

        )
    ),
    Dua(
        arabic = "اَللّٰھُمَّ صَلِّ عَلٰی مُحَمَّدٍ عَبْدِکَ وَ رَسُوْلِکَ کَمَا صَلَّیْتَ عَلٰی اِبْرَاھِیْمَ وَ بَارِکْ عَلٰی مُحَمَّدٍ وَّعَلٰی آلِ مُحَمَّدٍ کَمَا بَارَکْتَ عَلٰی اِبْرَاھِیْمَ وَآلِ اِبْرَاھِیْمَِ",
        translation = "O Allah! Bestow Your mercy upon your servant and messenger, Muhammad as You bestowed upon Ibrahim and bless Muhammad and the descendants of Muhammad, as You blessed Ibrahim and the descendants of Ibrahim.",
        reference = "[Ṣaḥīḥ al-Bukhārī]",
        backgroundResId = R.drawable.duaa5_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "لَا اِلٰہَ اِلَّا اللّٰہُ" to R.raw.audioo1,
            "وَحْدَہُ لَا شَرِیْکَ لَہ" to R.raw.audioo2,
            "لَہُ الْمُلْکُ وَلَہُ الْحَمْدَ" to R.raw.audioo3,
            "یُحْیی وَ یُمِیْتَُ" to R.raw.audioo3,
            "وَھُوَ حَی لَّا یَمُوْتُ" to R.raw.audioo3,
            "بِیَدِہِ الْخَیْرُ" to R.raw.audioo3,
            "وَھُوَ عَلٰی کُلِّ شَیْئٍ قَدِیْرد" to R.raw.audioo3,
        )
    ),
    Dua(
        arabic = "اَللّٰھُمَّ بِکَ اَمْسَیْنَا وَبِکَ اَصْبَحْنَا وَبِکَ نَحْیَا وَبِکَ نَمُوْتُ وَاِلَیْکَ النُّشُوْرُِ",
        translation = "O Allah! By Your leave we reach the evening and by Your leave we reach the morning and by Your leave we live and by Your leave we will die and to You is our resurrection.",
        reference = "[Sunan al-Tirmidhī]",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    ),
    Dua(
        arabic = "اَعُوْذُ بِکَلِمَاتِ اللّٰہِ التَّامَّاتِ مِنْ شَرِّ مَا خَلَقَِ",
        translation = "I seek refuge in the complete, perfect words of Allah from the evil of what He has created.",
        reference = "[Sahih Muslim]",
        backgroundResId = R.drawable.duaa6_bg,
        statusBarColorResId = R.color.top_nav6,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "مِنْ شَرِّ مَا خَلَق" to R.raw.audioo1,
            "اللّٰہِ التَّامَّاتِ" to R.raw.audioo2,
            "اَعُوْذُ بِکَلِمَاتِ" to R.raw.audioo3,
        )
    ),
    Dua(
        arabic = "اَللّٰھُمَّ بِاسْمِكَ اَمُوْتُ وَاَحْیَاِ",
        translation = "O Allah! In your name I die and I live.",
        reference = "[Ṣaḥīḥ al-Bukhārī]",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    ),
    Dua(
        arabic = "اَلْحَمْدُ لِلهِ الَّذِیْ اَحْيَانَا بَعْدَ مَا اَمَاتَنَا وَاِلَيْهِ النُّشُوْرُِ",
        translation = "All praise is for Allah ho gave us life after death (sleep) and to Him is the resurrection.",
        reference = "[Sahih Muslim]",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    ),
    Dua(
        arabic = "اَللّٰھُمَّ اِنِّیْ اَعُوْذُبِکَ مِنَ الْخُبُثِ وَ الْخَبَائِثُِِ",
        translation = "O Allah, indeed I seek refuge in You from the impure male jinns and impure female jinns.",
        reference = "[Ṣaḥīḥ al-Bukhārī]",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    ),
    Dua(
        arabic = "سُبْحَانَ اللّٰہِ وَبِحَمْدِہِ سُبْحَانَ اللّٰہِ الْعَظِیْمُِِِ",
        translation = "Glory be to Allah and all praise be to Him; Glory be to Allah, the Most Great.",
        reference = "[Sahih Muslim]",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    ),
    Dua(
        arabic = "الحَمْدُ لِلهِ الَّذِي كَسَانِي هَذَا الثَّوْبَ وَرَزَقَنِيهِ مِنْ غَيْرِ حَوْلٍ مِنِّي وَلَا قُوَّةٍُِِِ",
        translation = "All praise is for Allah who has clothed me with this garment and provided it for me, with no power or might from myself. ",
        reference = "[Sahih Muslim]",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    ),
    Dua(
        arabic = "بِسْمِ اللّٰہُِِِِ",
        translation = "In the Name of Allah.",
        reference = "[Ṣaḥīḥ Jāmi’ al-Ṣaghīr]",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    ),Dua(
        arabic = "بِسْمِ اللّٰہِ تَوَکَّلْتُ عَلَی اللّٰہِ لَاحَوْلَ وَلَا قُوَّةَ اِلَّا بِاللّٰہِ ُِِِِ",
        translation = "All praise is for Allah who has clothed me with this garment and provided it for me, with no power or might from myself.",
        reference = "[Sunan Abī Dawūd]",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    ),Dua(
        arabic = " اَلسَّلَامُ عَلَیْکُمُِِِِْ",
        translation = "May Peace (of Allah) be upon you.",
        reference = "[al-Nūr: 27]",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    ),Dua(
        arabic = "اَللّٰهُمَّ إِنِّیْ أَعُوْذُبِکَ مِنْ مُنْکَرَاتِ الْأخلاَ قِ، وَالْأعْمَالِ، وَالْأھْوَاءُِِِِِ",
        translation = "O Allah! Indeed I seek refuge in You from a detestable conduct and from disliked deeds and desires.",
        reference = "[Sunan al-Tirmidhī]",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    ),Dua(
        arabic = "اَلسَّلَامُ عَلَیْکُمْ وَرَحْمَةُ اللّٰہِ وَ بَرَکَاتُہُُِِِِ",
        translation = "May peace, mercy and blessings (of Allah) be upon You.",
        reference = "[Sunan Abī Dawūd] ",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    ),
    Dua(
        arabic = "الحَمْدُ لِلهِ الَّذِي كَسَانِي هَذَا الثَّوْبَ وَرَزَقَنِيهِ مِنْ غَيْرِ حَوْلٍ مِنِّي وَلَا قُوَّةٍُِِِِ",
        translation = "All praise is for Allah who has clothed me with this garment and provided it for me, with no power or might from myself.",
        reference = "[Sunan Abī Dawūd]",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    ),Dua(
        arabic = "الحَمْدُ لِلهِ الَّذِي كَسَانِي هَذَا الثَّوْبَ وَرَزَقَنِيهِ مِنْ غَيْرِ حَوْلٍ مِنِّي وَلَا قُوَّةٍُِِِِ",
        translation = "All praise is for Allah who has clothed me with this garment and provided it for me, with no power or might from myself. ",
        reference = "[Sunan Abī Dawūd]",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    ),Dua(
        arabic = "الحَمْدُ لِلهِ الَّذِي كَسَانِي هَذَا الثَّوْبَ وَرَزَقَنِيهِ مِنْ غَيْرِ حَوْلٍ مِنِّي وَلَا قُوَّةٍُِِِِ",
        translation = "All praise is for Allah who has clothed me with this garment and provided it for me, with no power or might from myself. ",
        reference = "[Sunan Abī Dawūd]",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    ),Dua(
        arabic = "الحَمْدُ لِلهِ الَّذِي كَسَانِي هَذَا الثَّوْبَ وَرَزَقَنِيهِ مِنْ غَيْرِ حَوْلٍ مِنِّي وَلَا قُوَّةٍُِِِِ",
        translation = "All praise is for Allah who has clothed me with this garment and provided it for me, with no power or might from myself.",
        reference = "[Sunan Abī Dawūd]",
        backgroundResId = R.drawable.praiseandglory_bg,
        statusBarColorResId = R.color.top_nav,
        fullAudioResId = R.raw.dua1_part1_audio1,
        image = R.drawable.kaaba,
        wordAudioPairs = listOf(
            "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
            "وَبِحَمْدِہِ" to R.raw.audioo2,
            "سُبْحَانَ اللّٰہِ الْعَظِیْمَ" to R.raw.audioo3,
        )
    )
)
