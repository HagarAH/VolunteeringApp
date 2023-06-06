package com.mobil.bizden.seeders;

import com.mobil.bizden.controllers.GatheringAreaController;
import com.mobil.bizden.controllers.GatheringAreaInfoController;
import com.mobil.bizden.models.GatheringArea;
import com.mobil.bizden.models.GatheringAreaInfo;
import com.mobil.bizden.models.TimeRequirement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GatheringAreasSeeder {
    private GatheringAreaInfoController gatheringAreasController;

    public GatheringAreasSeeder(GatheringAreaInfoController gatheringAreasController) {
        this.gatheringAreasController = gatheringAreasController;
    }

    private static String getRandomOrganization() {
        String[] organizations = {
                "Türk Kızılayı", // Turkish Red Crescent
                "Türkiye Yeşilay Cemiyeti", // Green Crescent of Turkey
                "Lösev", // Foundation for Children with Leukemia
                "TÜSİAD", // Turkish Industry and Business Association
                "TEMA Vakfı", // The Turkish Foundation for Combating Soil Erosion
                "Türkiye Eğitim Gönüllüleri Vakfı" // Education Volunteers Foundation of Turkey
        };

        Random random = new Random();
        int index = random.nextInt(organizations.length);
        return organizations[index];
    }

    public void seedGatheringAreas() {
        List<TimeRequirement> timeRequirements1 = new ArrayList<>();
        timeRequirements1.add(new TimeRequirement("09:00", "13:00", 20));
        timeRequirements1.add(new TimeRequirement("14:00", "18:00", 15));

        List<TimeRequirement> timeRequirements2 = new ArrayList<>();
        timeRequirements2.add(new TimeRequirement("10:00", "14:00", 25));
        timeRequirements2.add(new TimeRequirement("15:00", "19:00", 20));

        List<TimeRequirement> timeRequirements3 = new ArrayList<>();
        timeRequirements3.add(new TimeRequirement("08:00", "12:00", 30));
        timeRequirements3.add(new TimeRequirement("13:00", "17:00", 25));

        GatheringArea gatheringArea1 = new GatheringArea(
                "Area1",
                "Gezi Parkı",
                150,
                "İstanbul",
                "Beyoğlu",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea2 = new GatheringArea(
                "Area2",
                "Kuğulu Parkı",
                200,
                "Ankara",
                "Çankaya",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea3 = new GatheringArea(
                "Area3",
                "Düden Parkı",
                250,
                "Antalya",
                "Muratpaşa",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea4 = new GatheringArea(
                "Area4",
                "Kent Parkı",
                300,
                "Eskişehir",
                "Odunpazarı",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea5 = new GatheringArea(
                "Area5",
                "Atatürk Parkı",
                350,
                "Bursa",
                "Osmangazi",
                "Açık",
                timeRequirements2
        );
        GatheringArea gatheringArea6 = new GatheringArea(
                "Area6",
                "Gençlik Merkezi Parkı",
                400,
                "İzmir",
                "Konak",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea7 = new GatheringArea(
                "Area7",
                "Sakarya Gençlik Merkezi",
                450,
                "Sakarya",
                "Serdivan",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea8 = new GatheringArea(
                "Area8",
                "Mersin Gençlik Merkezi Parkı",
                500,
                "Mersin",
                "Akdeniz",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea9 = new GatheringArea(
                "Area9",
                "Trabzon Gençlik Merkezi",
                550,
                "Trabzon",
                "Ortahisar",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea10 = new GatheringArea(
                "Area10",
                "Kocaeli Gençlik Merkezi Parkı",
                600,
                "Kocaeli",
                "İzmit",
                "Açık",
                timeRequirements1
        );
        GatheringArea gatheringArea11 = new GatheringArea(
                "Area11",
                "Gülhane Parkı",
                300,
                "İstanbul",
                "Eminönü",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea12 = new GatheringArea(
                "Area12",
                "Çankaya Gençlik Merkezi",
                350,
                "Ankara",
                "Çankaya",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea13 = new GatheringArea(
                "Area13",
                "Lara Gençlik Merkezi Parkı",
                400,
                "Antalya",
                "Muratpaşa",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea14 = new GatheringArea(
                "Area14",
                "Kent Meydanı Parkı",
                450,
                "Eskişehir",
                "Tepebaşı",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea15 = new GatheringArea(
                "Area15",
                "Orhangazi Gençlik Merkezi",
                500,
                "Bursa",
                "Nilüfer",
                "Açık",
                timeRequirements3
        );
        GatheringArea gatheringArea16 = new GatheringArea(
                "Area16",
                "Atatürk Gençlik Merkezi Parkı",
                550,
                "İzmir",
                "Karşıyaka",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea17 = new GatheringArea(
                "Area17",
                "Denizli Gençlik Merkezi",
                600,
                "Denizli",
                "Merkezefendi",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea18 = new GatheringArea(
                "Area18",
                "Adana Gençlik Merkezi Parkı",
                650,
                "Adana",
                "Seyhan",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea19 = new GatheringArea(
                "Area19",
                "Göztepe Parkı",
                700,
                "İstanbul",
                "Kadıköy",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea20 = new GatheringArea(
                "Area20",
                "Konya Gençlik Merkezi",
                750,
                "Konya",
                "Selçuklu",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea21 = new GatheringArea(
                "Area21",
                "Antalya Gençlik Merkezi Parkı",
                800,
                "Antalya",
                "Kepez",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea22 = new GatheringArea(
                "Area22",
                "Bolu Gençlik Merkezi",
                850,
                "Bolu",
                "Merkez",
                "Açık",
                timeRequirements1
        );
        GatheringArea gatheringArea23 = new GatheringArea(
                "Area23",
                "Yenimahalle Parkı",
                300,
                "Ankara",
                "Yenimahalle",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea24 = new GatheringArea(
                "Area24",
                "Gençlik Merkezi Parkı",
                350,
                "Ankara",
                "Yenimahalle",
                "Açık",
                timeRequirements2
        );
        GatheringArea gatheringArea25 = new GatheringArea(
                "Area25",
                "Atatürk Ormanı",
                400,
                "Ankara",
                "Yenimahalle",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea26 = new GatheringArea(
                "Area26",
                "Gazi Ormanı",
                450,
                "Ankara",
                "Yenimahalle",
                "Açık",
                timeRequirements1
        );
        GatheringArea gatheringArea27 = new GatheringArea(
                "Area27",
                "Kurtuluş Parkı",
                500,
                "Ankara",
                "Yenimahalle",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea28 = new GatheringArea(
                "Area28",
                "Ümitköy Parkı",
                550,
                "Ankara",
                "Yenimahalle",
                "Açık",
                timeRequirements3
        );
        GatheringArea gatheringArea29 = new GatheringArea(
                "Area29",
                "Kurtuluş Parkı",
                400,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea30 = new GatheringArea(
                "Area30",
                "Gençlik Merkezi Parkı",
                450,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements2
        );
        GatheringArea gatheringArea31 = new GatheringArea(
                "Area31",
                "Hüseyin Gazi Parkı",
                500,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea32 = new GatheringArea(
                "Area32",
                "Sıhhiye Parkı",
                550,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements1
        );
        GatheringArea gatheringArea33 = new GatheringArea(
                "Area33",
                "Ulucanlar Cezaevi Müzesi Parkı",
                600,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea34 = new GatheringArea(
                "Area34",
                "Hacı Bayram Veli Camii Yanı Park",
                650,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea35 = new GatheringArea(
                "Area35",
                "Roma Hamamı Parkı",
                700,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea36 = new GatheringArea(
                "Area36",
                "Gençlik Parkı",
                750,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea37 = new GatheringArea(
                "Area37",
                "Ankara Kalesi Parkı",
                800,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements3
        );



        // Initialize an instance of the class that has addGatheringArea method
        GatheringAreaController gatheringArea = new GatheringAreaController();

        GatheringAreaController.GatheringAreaCallback gatheringAreaCallback = new GatheringAreaController.GatheringAreaCallback() {
            @Override
            public void onGatheringAreaAdded() {
                System.out.println("Gathering Area Added Successfully");

            }

            @Override
            public void onGatheringAreaUpdated() {
                System.out.println("Gathering Area Updated Successfully");
            }

            @Override
            public void onGatheringAreaDeleted() {
                System.out.println("Gathering Area Deleted Successfully");
            }

            @Override
            public void onGatheringAreaLoadFailed(String error) {
                System.out.println("Failed to Load Gathering Area: " + error);
            }

            @Override
            public void onGatheringAreaLoaded(GatheringArea gatheringArea) {
                System.out.println("Gathering Area Loaded Successfully: " + gatheringArea.getName());
            }

            @Override
            public void onGatheringAreaByLocationLoaded(List<GatheringArea> gatheringAreas) {

            }

            @Override
            public void onCollectionEmpty() {

            }
        };

        gatheringArea.addGatheringArea(gatheringArea1, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea2, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea3, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea4, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea5, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea6, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea7, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea8, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea9, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea10, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea11, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea12, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea13, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea14, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea15, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea16, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea17, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea18, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea19, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea20, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea21, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea22, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea23, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea24, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea25, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea26, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea27, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea28, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea29, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea30, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea31, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea32, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea33, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea34, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea35, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea36, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea37, gatheringAreaCallback);
        seedAreaInfo();
    }

    public void seedAreaInfo() {
        GatheringAreaInfo gatheringAreaInfo1 = new GatheringAreaInfo(
                "Area1",
                "Gezi Parkı, İstiklal Caddesi, Beyoğlu",
                0,
                "Gezi Parkı, İstanbul'un Beyoğlu ilçesinde yer alan popüler bir toplanma alanıdır. Ağaçlar ve banklarla çevrili huzurlu bir ortam sunarak dinlenme ve sosyalleşme için ideal bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo2 = new GatheringAreaInfo(
                "Area2",
                "Kuğulu Parkı, Kennedy Caddesi, Çankaya",
                0,
                "Kuğulu Parkı, Ankara'nın Çankaya ilçesinde yer alan şirin bir parktır. Güzel kuğu gölü ve manzaralı yürüyüş yollarıyla ünlüdür, toplanma ve piknik yapma için huzurlu bir ortam sunar."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo3 = new GatheringAreaInfo(
                "Area3",
                "Düden Parkı, Lara Caddesi, Muratpaşa",
                0,
                "Düden Parkı, Antalya'nın Muratpaşa ilçesinde bulunan büyüleyici bir parktır. Muhteşem şelaleleri ve yeşilliklerle dolu ortamıyla dikkat çeker, toplanma ve açık hava etkinlikleri için sakin bir ortam sunar."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo4 = new GatheringAreaInfo(
                "Area4",
                "Kent Parkı, Sakarya Caddesi, Odunpazarı",
                0,
                "Kent Parkı, Eskişehir'in Odunpazarı ilçesinde yer alan geniş bir parktır. Oyun alanları, spor sahaları ve piknik alanları gibi çeşitli rekreasyonel imkanlar sunar, toplanma ve aile gezileri için popüler bir tercihtir."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo5 = new GatheringAreaInfo(
                "Area5",
                "Atatürk Parkı, İzmir Caddesi, Osmangazi",
                0,
                "Atatürk Parkı, Bursa'nın Osmangazi ilçesinde bulunan canlı bir parktır. Bakımlı bahçeleri, geniş meydanı ve canlı atmosferi ile dikkat çeker, sosyalleşme ve açık hava etkinliklerinin keyfini çıkarmak için ideal bir toplanma noktasıdır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo6 = new GatheringAreaInfo(
                "Area6",
                "Gençlik Merkezi Parkı, Gazi Bulvarı, Konak",
                0,
                "Gençlik Merkezi Parkı, İzmir'in Konak ilçesinde yer alan bir gençlik merkezine bağlı bir parktır. Spor alanları, etkinlik mekanları ve dinlenme alanları ile gençlere yönelik birçok imkan sunar, toplanma ve eğlence için popüler bir seçenektir."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo7 = new GatheringAreaInfo(
                "Area7",
                "Sakarya Gençlik Merkezi, Serdivan Caddesi, Serdivan",
                0,
                "Sakarya Gençlik Merkezi, Sakarya'nın Serdivan ilçesinde yer alan bir gençlik merkezine bağlı bir toplanma alanıdır. Aktivite ve etkinliklere yönelik tesisleri ve gençlere yönelik hizmetleri ile bilinir, toplanma ve sosyalleşme için ideal bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo8 = new GatheringAreaInfo(
                "Area8",
                "Mersin Gençlik Merkezi Parkı, Akdeniz Caddesi, Akdeniz",
                0,
                "Mersin Gençlik Merkezi Parkı, Mersin'in Akdeniz ilçesinde yer alan bir gençlik merkezi etrafında düzenlenmiş bir parktır. Oyun alanları, yeşil alanlar ve spor alanları gibi farklı aktivite seçenekleri sunar, toplanma ve eğlence için gençler arasında popülerdir."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo9 = new GatheringAreaInfo(
                "Area9",
                "Trabzon Gençlik Merkezi, Ortahisar Caddesi, Ortahisar",
                0,
                "Trabzon Gençlik Merkezi, Trabzon'un Ortahisar ilçesinde yer alan bir gençlik merkezine bağlı bir toplanma alanıdır. Gençlere yönelik etkinlikler, kurslar ve spor imkanları sunar, toplanma ve eğlence için ideal bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo10 = new GatheringAreaInfo(
                "Area10",
                "Kocaeli Gençlik Merkezi Parkı, İzmit Caddesi, İzmit",
                0,
                "Kocaeli Gençlik Merkezi Parkı, Kocaeli'nin İzmit ilçesinde yer alan bir gençlik merkezine bağlı bir parktır. Dinlenme alanları, spor alanları ve etkinlik mekanları gibi çeşitli imkanlar sunar, toplanma ve gençler arasında popüler bir buluşma noktasıdır."
                , getRandomOrganization());
        GatheringAreaInfo gatheringAreaInfo11 = new GatheringAreaInfo(
                "Area11",
                "Gülhane Parkı, Eminönü Meydanı, İstanbul",
                0,
                "Gülhane Parkı, İstanbul'un Eminönü ilçesinde bulunan tarihi bir parktır. Yeşillikler, çiçekler ve tarihi atmosferiyle ünlüdür, toplanma ve keyifli zaman geçirmek için ideal bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo12 = new GatheringAreaInfo(
                "Area12",
                "Çankaya Gençlik Merkezi, Çankaya Caddesi, Çankaya",
                0,
                "Çankaya Gençlik Merkezi, Ankara'nın Çankaya ilçesinde yer alan bir gençlik merkezine bağlı bir toplanma alanıdır. Gençlere yönelik etkinlikler, spor imkanları ve eğitim programları sunar, toplanma ve gençler arasında sosyal etkileşimi teşvik eder."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo13 = new GatheringAreaInfo(
                "Area13",
                "Lara Gençlik Merkezi Parkı, Muratpaşa Caddesi, Muratpaşa",
                0,
                "Lara Gençlik Merkezi Parkı, Antalya'nın Muratpaşa ilçesinde yer alan bir gençlik merkezine bağlı bir parktır. Spor sahaları, yeşil alanlar ve etkinlik mekanları gibi farklı aktiviteler sunar, toplanma ve eğlence için gençler arasında popülerdir."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo14 = new GatheringAreaInfo(
                "Area14",
                "Kent Meydanı Parkı, Tepebaşı Caddesi, Tepebaşı",
                0,
                "Kent Meydanı Parkı, Eskişehir'in Tepebaşı ilçesinde bulunan bir parktır. Merkezi konumu, oturma alanları ve etkinlikler için uygun bir atmosfer sunar, toplanma ve sosyalleşme için ideal bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo15 = new GatheringAreaInfo(
                "Area15",
                "Orhangazi Gençlik Merkezi, Nilüfer Caddesi, Nilüfer",
                0,
                "Orhangazi Gençlik Merkezi, Bursa'nın Nilüfer ilçesinde yer alan bir gençlik merkezine bağlı bir toplanma alanıdır. Gençlere yönelik etkinlikler, spor imkanları ve sosyal hizmetler sunar, toplanma ve eğlence için gençler arasında popülerdir."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo16 = new GatheringAreaInfo(
                "Area16",
                "Atatürk Gençlik Merkezi Parkı, Karşıyaka Caddesi, Karşıyaka",
                0,
                "Atatürk Gençlik Merkezi Parkı, İzmir'in Karşıyaka ilçesinde yer alan bir gençlik merkezine bağlı bir parktır. Oyun alanları, yeşil alanlar ve etkinlik mekanları gibi farklı aktiviteler sunar, toplanma ve gençler arasında popüler bir buluşma noktasıdır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo17 = new GatheringAreaInfo(
                "Area17",
                "Denizli Gençlik Merkezi, Merkezefendi Caddesi, Merkezefendi",
                0,
                "Denizli Gençlik Merkezi, Denizli'nin Merkezefendi ilçesinde yer alan bir gençlik merkezine bağlı bir toplanma alanıdır. Gençlere yönelik etkinlikler, spor imkanları ve sosyal hizmetler sunar, toplanma ve eğlence için gençler arasında popüler bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo18 = new GatheringAreaInfo(
                "Area18",
                "Adana Gençlik Merkezi Parkı, Seyhan Caddesi, Seyhan",
                0,
                "Adana Gençlik Merkezi Parkı, Adana'nın Seyhan ilçesinde yer alan bir gençlik merkezine bağlı bir parktır. Spor alanları, yeşil alanlar ve etkinlik mekanları gibi farklı aktiviteler sunar, toplanma ve eğlence için gençler arasında popüler bir buluşma noktasıdır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo19 = new GatheringAreaInfo(
                "Area19",
                "Göztepe Parkı, Kadıköy Caddesi, Kadıköy",
                0,
                "Göztepe Parkı, İstanbul'un Kadıköy ilçesinde yer alan bir parktır. Geniş yeşil alanları, oturma alanları ve etkinlik mekanlarıyla bilinir, toplanma ve dinlenme için popüler bir seçenektir."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo20 = new GatheringAreaInfo(
                "Area20",
                "Konya Gençlik Merkezi, Selçuklu Caddesi, Selçuklu",
                0,
                "Konya Gençlik Merkezi, Konya'nın Selçuklu ilçesinde yer alan bir gençlik merkezine bağlı bir toplanma alanıdır. Gençlere yönelik etkinlikler, spor imkanları ve sosyal hizmetler sunar, toplanma ve eğlence için ideal bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo21 = new GatheringAreaInfo(
                "Area21",
                "Antalya Gençlik Merkezi Parkı, Kepez Caddesi, Kepez",
                0,
                "Antalya Gençlik Merkezi Parkı, Antalya'nın Kepez ilçesinde yer alan bir gençlik merkezine bağlı bir parktır. Spor alanları, yeşil alanlar ve etkinlik mekanları gibi farklı aktiviteler sunar, toplanma ve eğlence için gençler arasında popüler bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo22 = new GatheringAreaInfo(
                "Area22",
                "Bolu Gençlik Merkezi, Merkez Caddesi, Merkez",
                0,
                "Bolu Gençlik Merkezi, Bolu'nun Merkez ilçesinde yer alan bir gençlik merkezine bağlı bir toplanma alanıdır. Gençlere yönelik etkinlikler, spor imkanları ve eğitim programları sunar, toplanma ve gençler arasında sosyal etkileşimi teşvik eder."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo23 = new GatheringAreaInfo(
                "Area23",
                "Yenimahalle Parkı, Yenimahalle Caddesi, Yenimahalle",
                0,
                "Yenimahalle Parkı, Ankara'nın Yenimahalle ilçesinde yer alan bir parktır. Yeşillikler, oturma alanları ve etkinlikler için uygun bir atmosfer sunar, toplanma ve dinlenme için ideal bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo24 = new GatheringAreaInfo(
                "Area24",
                "Gençlik Merkezi Parkı, Yenimahalle Caddesi, Yenimahalle",
                0,
                "Gençlik Merkezi Parkı, Ankara'nın Yenimahalle ilçesinde yer alan bir gençlik merkezine bağlı bir parktır. Gençlere yönelik etkinlikler, spor imkanları ve eğlence mekanları sunar, toplanma ve sosyal etkileşimi teşvik eder."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo25 = new GatheringAreaInfo(
                "Area25",
                "Atatürk Ormanı, Yenimahalle Caddesi, Yenimahalle",
                0,
                "Atatürk Ormanı, Ankara'nın Yenimahalle ilçesinde yer alan büyük bir ormanlık alandır. Doğal güzellikleri, yürüyüş parkurları ve dinlenme alanlarıyla ünlüdür, toplanma ve doğa ile iç içe vakit geçirmek için ideal bir seçenektir."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo26 = new GatheringAreaInfo(
                "Area26",
                "Gazi Ormanı, Yenimahalle Caddesi, Yenimahalle",
                0,
                "Gazi Ormanı, Ankara'nın Yenimahalle ilçesinde yer alan bir ormanlık alandır. Piknik alanları, yürüyüş parkurları ve doğal yaşamı keşfetmek için ideal bir seçenek sunar, toplanma ve doğa ile iç içe vakit geçirmek için ideal bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo27 = new GatheringAreaInfo(
                "Area27",
                "Kurtuluş Parkı, Yenimahalle Caddesi, Yenimahalle",
                0,
                "Kurtuluş Parkı, Ankara'nın Yenimahalle ilçesinde yer alan bir parktır. Yeşillikler, oturma alanları ve çocuk oyun alanlarıyla bilinir, toplanma ve aileler için keyifli bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo28 = new GatheringAreaInfo(
                "Area28",
                "Ümitköy Parkı, Yenimahalle Caddesi, Yenimahalle",
                0,
                "Ümitköy Parkı, Ankara'nın Yenimahalle ilçesinde yer alan bir parktır. Yeşillikler, oturma alanları ve spor imkanlarıyla popülerdir, toplanma ve dinlenme için ideal bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo29 = new GatheringAreaInfo(
                "Area29",
                "Kurtuluş Parkı, Altındağ Caddesi, Altındağ",
                0,
                "Kurtuluş Parkı, Ankara'nın Altındağ ilçesinde bulunan bir parktır. Doğal güzellikleri, yeşil alanları ve oturma noktalarıyla ünlüdür, toplanma ve keyifli zaman geçirmek için ideal bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo30 = new GatheringAreaInfo(
                "Area30",
                "Gençlik Merkezi Parkı, Altındağ Caddesi, Altındağ",
                0,
                "Gençlik Merkezi Parkı, Ankara'nın Altındağ ilçesinde yer alan bir gençlik merkezine bağlı bir parktır. Gençlere yönelik etkinlikler, spor alanları ve sosyal hizmetler sunar, toplanma ve gençler arasında sosyal etkileşimi teşvik eder."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo31 = new GatheringAreaInfo(
                "Area31",
                "Hüseyin Gazi Parkı, Altındağ Caddesi, Altındağ",
                0,
                "Hüseyin Gazi Parkı, Ankara'nın Altındağ ilçesinde yer alan bir parktır. Yeşillikler, oturma alanları ve çocuk oyun alanlarıyla ünlüdür, toplanma ve aileler için keyifli bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo32 = new GatheringAreaInfo(
                "Area32",
                "Sıhhiye Parkı, Altındağ Caddesi, Altındağ",
                0,
                "Sıhhiye Parkı, Ankara'nın Altındağ ilçesinde yer alan bir parktır. Merkezi konumu, oturma alanları ve etkinlikler için uygun bir atmosfer sunar, toplanma ve dinlenme için ideal bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo33 = new GatheringAreaInfo(
                "Area33",
                "Ulucanlar Cezaevi Müzesi Parkı, Altındağ Caddesi, Altındağ",
                0,
                "Ulucanlar Cezaevi Müzesi Parkı, Ankara'nın Altındağ ilçesinde bulunan bir parktır. Tarihi bir cezaevi müzesi yakınında yer alır ve ziyaretçilere açık yeşil alanlar ve oturma noktaları sunar, toplanma ve tarihî bir atmosferde vakit geçirmek için ideal bir seçenektir."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo34 = new GatheringAreaInfo(
                "Area34",
                "Hacı Bayram Veli Camii Yanı Park, Altındağ Caddesi, Altındağ",
                0,
                "Hacı Bayram Veli Camii Yanı Parkı, Ankara'nın Altındağ ilçesinde bulunan bir parktır. Hacı Bayram Veli Camii'ne yakınlığıyla bilinir ve yeşil alanları, oturma noktaları ve tarihi dokusuyla öne çıkar, toplanma ve dinlenme için ideal bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo35 = new GatheringAreaInfo(
                "Area35",
                "Roma Hamamı Parkı, Altındağ Caddesi, Altındağ",
                0,
                "Roma Hamamı Parkı, Ankara'nın Altındağ ilçesinde yer alan bir parktır. Tarihi bir Roma hamamının yanında bulunur ve yeşillikler, oturma alanları ve etkinlikler için uygun bir atmosfer sunar, toplanma ve keyifli zaman geçirmek için ideal bir mekandır."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo36 = new GatheringAreaInfo(
                "Area36",
                "Gençlik Parkı, Altındağ Caddesi, Altındağ",
                0,
                "Gençlik Parkı, Ankara'nın Altındağ ilçesinde yer alan bir parktır. Gençlere yönelik etkinlikler, spor alanları ve eğlence mekanları sunar, toplanma ve gençler arasında sosyal etkileşimi teşvik eder."
                , getRandomOrganization());

        GatheringAreaInfo gatheringAreaInfo37 = new GatheringAreaInfo(
                "Area37",
                "Ankara Kalesi Parkı, Altındağ Caddesi, Altındağ",
                0,
                "Ankara Kalesi Parkı, Ankara'nın Altındağ ilçesinde yer alan bir parktır. Ankara Kalesi'nin yakınında yer alır ve yeşillikler, oturma alanları ve tarihi bir atmosfer sunar, toplanma ve tarihî bir mekanda vakit geçirmek için ideal bir seçenektir."
                , getRandomOrganization());

        GatheringAreaInfoController controller = new GatheringAreaInfoController();

        controller.addGatheringArea(gatheringAreaInfo1);
        controller.addGatheringArea(gatheringAreaInfo2);
        controller.addGatheringArea(gatheringAreaInfo3);
        controller.addGatheringArea(gatheringAreaInfo4);
        controller.addGatheringArea(gatheringAreaInfo5);
        controller.addGatheringArea(gatheringAreaInfo6);
        controller.addGatheringArea(gatheringAreaInfo7);
        controller.addGatheringArea(gatheringAreaInfo8);
        controller.addGatheringArea(gatheringAreaInfo9);
        controller.addGatheringArea(gatheringAreaInfo10);
        controller.addGatheringArea(gatheringAreaInfo11);
        controller.addGatheringArea(gatheringAreaInfo12);
        controller.addGatheringArea(gatheringAreaInfo13);
        controller.addGatheringArea(gatheringAreaInfo14);
        controller.addGatheringArea(gatheringAreaInfo15);
        controller.addGatheringArea(gatheringAreaInfo16);
        controller.addGatheringArea(gatheringAreaInfo17);
        controller.addGatheringArea(gatheringAreaInfo18);
        controller.addGatheringArea(gatheringAreaInfo19);
        controller.addGatheringArea(gatheringAreaInfo21);
        controller.addGatheringArea(gatheringAreaInfo22);
        controller.addGatheringArea(gatheringAreaInfo23);
        controller.addGatheringArea(gatheringAreaInfo24);
        controller.addGatheringArea(gatheringAreaInfo25);
        controller.addGatheringArea(gatheringAreaInfo26);
        controller.addGatheringArea(gatheringAreaInfo27);
        controller.addGatheringArea(gatheringAreaInfo28);
        controller.addGatheringArea(gatheringAreaInfo29);
        controller.addGatheringArea(gatheringAreaInfo31);
        controller.addGatheringArea(gatheringAreaInfo32);
        controller.addGatheringArea(gatheringAreaInfo33);
        controller.addGatheringArea(gatheringAreaInfo34);
        controller.addGatheringArea(gatheringAreaInfo35);
        controller.addGatheringArea(gatheringAreaInfo36);
        controller.addGatheringArea(gatheringAreaInfo37);
    }
}