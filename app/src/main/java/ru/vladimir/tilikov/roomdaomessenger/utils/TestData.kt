package ru.vladimir.tilikov.roomdaomessenger.utils

import ru.vladimir.tilikov.roomdaomessenger.data.models.Contact
import ru.vladimir.tilikov.roomdaomessenger.data.models.User
import java.time.Instant

object TestData {

    const val email = "admin@admin.ru"
    const val phoneNumber = "1234567890"
    const val nothing = "nothing"

    val imageUriList = listOf(
        "https://rossaprimavera.ru/static/files/973c5eceff66.jpg",
        "https://kto-chto-gde.ru/wp-content/uploads/2016/09/1423402942.jpg",
        "https://demotivation.ru/wp-content/uploads/2020/01/Canada_Parks_Lake_Mountains_Forests_Scenery_Rocky_567540_3840x2400-scaled.jpg",
        "https://avatars.mds.yandex.net/get-zen_doc/3957666/pub_5f8edb5633a8563beab7ddbf_5f8edc6133a8563beaba2b16/scale_1200",
        "https://img2.goodfon.ru/original/2560x1600/0/a4/lodka-priroda-peyzazh-ozero.jpg",
        "https://avatars.mds.yandex.net/get-zen_doc/1112142/pub_5e177bb01ee34f00b1812302_5e1789f68f011100ad29c1d2/scale_1200",
        "https://www.zastavki.com/pictures/originals/2015/Movies_The_protagonist_of_the_film_Pirates_of_the_Caribbean_094359_.jpg",
        "https://img2.goodfon.ru/wallpaper/nbig/2/ea/roland-zrk.jpg",
        "https://get.wallhere.com/photo/artwork-airplane-aircraft-Germany-propeller-military-aircraft-World-War-II-Messerschmitt-Bf-109-Messerschmitt-air-force-Grumman-F6F-Hellcat-Luftwaffe-Focke-Wulf-Fw-190-Flight-North-American-T-6-Texan-aviation-wing-airline-2560x1600-px-fighter-aircraft-general-aviation-aircraft-engine-air-travel-aerospace-engineering-light-aircraft-monoplane-propeller-driven-aircraft-flap-799248.jpg",
        "https://a-a-ah-ru.s3.amazonaws.com/uploads/items/54998/73103/large_59f5c116077bb452838490.jpg",
        "https://get.wallhere.com/photo/Star-Wars-digital-art-lightsaber-Jedi-Yoda-Toy-wing-figurine-262529.jpg",
        "https://img-fotki.yandex.ru/get/5814/35517494.27/0_8744c_131551d2_orig.jpg",
        "https://www.startfilm.ru/images/base/film/f_553818/big_startfilmru1283679.jpg",
        "https://s1.1zoom.ru/b5050/162/291529-Sepik_1920x1200.jpg",
        "https://w-dog.ru/wallpapers/8/4/460750886651803/risunok-samolet-istrebitel-fokke-vulf-fw-190-lyuftvaffe-nemcy.jpg",
        "https://avatars.mds.yandex.net/get-zen_doc/2046228/pub_5ec137e50bc6f5686b2ed0c0_5ec1641807fc79033f536a25/scale_1200",
        "https://w-dog.ru/today-top-picture.jpg",
        "https://www.startfilm.ru/images/base/film/f_553798/big_startfilmru1266043.jpg"
    )

    val testUser = User(
        1,
        "Test",
        "User",
        email,
        phoneNumber,
        "admin",
        "admin",
        null,
        Instant.now()
    )

    val testContacts = listOf(
        Contact(1, 1, "a fwa f awf a"),
        Contact(1, 1, "a a wf awf awa f awf a"),
        Contact(1, 1, " awf awfa fwa f awf a"),
        Contact(1, 1, "a  fwaa wf a f awf a"),
        Contact(1, 1, " tdh a fwa f awf a"),
        Contact(1, 1, " afa fwa f awf a"),
        Contact(1, 1, " awf a fwa f awf a"),
        Contact(1, 1, "a fwa f awf a"),
        Contact(1, 1, "z dga fwa f awf a"),
        Contact(1, 1, "a fwa f awf a"),
        Contact(1, 1, " aseg aseg a fwa f awf a"),
        Contact(1, 1, " asgseaa fwa f awf a"),
        Contact(1, 1, " asega fwa f awf a"),
        Contact(1, 1, "a fwa f awf a"),
        Contact(1, 1, "a sega fwa f awf a"),
        Contact(1, 1, "a fwa f awf a"),
        Contact(1, 1, " aseg asa fwa f awf a"),
        Contact(1, 1, "a fwa f awf a"),
        Contact(1, 1, "a seg a fwa f awf a"),
        Contact(1, 1, "a fwa f awf a"),
        Contact(1, 1, "a fwa f awf a"),
        Contact(1, 1, "a seg a fwa f awf a"),
        Contact(1, 1, "t rhr a fwa f awf a"),
        Contact(1, 1, "a fwa f awf a"),
    )

    val names = listOf(
        "ААРОН",
        "ДЕНИС",
        "ДЖЕК",
        "ДЖИМ",
        "ДЖОРДЖ",
        "НИЛ",
        "НИКОЛАЙ",
        "НИКИТА",
        "НИКОДИМ",
        "НОЙ",
        "НОРМАН",
        "НУРЕТДИН",
        "НУРУЛЛА",
        "ОКТАВИАН",
        "ОЛАФ",
        "ОЛЕГ",
        "ОЛИВЕР",
        "ОМАР",
        "ОНОРЕ",
        "ОСВАЛЬД",
        "ОСТАП",
        "ПАВЕЛ",
        "ПАНКРАТ",
        "ПАНТЕЛЕЙ",
        "ПАНФИЛ",
        "ПАХОМ",
        "ПЕТР",
        "ПЛАТОН",
        "ПОЛИКАРП",
        "ПОТАП",
        "ПРОХОР",
        "РАВИЛЬ",
        "РАДОМИР",
        "РАИЛЬ",
        "РАСУЛ",
        "РАХИМ",
        "РИМ",
        "РОБЕРТ",
        "САВЕЛИЙ",
        "САИД",
        "САЛЬВАДОР",
        "САМСОН",
        "СВЯТОГОР",
        "СВЯТОСЛАВ",
        "СЕМЕН",
        "СИЛЬВЕСТР",
        "СОКРАТ",
        "СПАРТАК",
        "СТЕПАН",
        "СТИВЕН",
        "ТАЛИБ",
        "ТАРАС",
        "ТИХОН",
        "УМАР",
        "УРУС",
        "ФАДДЕЙ",
        "ФЕДОР",
        "ФАУСТ",
        "ФЕЛИКС",
        "ФИДЕЛЬ",
        "ФОМА",
        "ЮРИЙ"
    )

    val surnames = listOf(
        "Смирнов",
        "Иванов",
        "Кузнецов",
        "Попов",
        "Лебедев",
        "Козлов",
        "Новиков",
        "Морозов",
        "Петров",
        "Волков",
        "Соловьев",
        "Васильев",
        "Зайцев",
        "Павлов",
        "Семенов",
        "Голубев",
        "Виноградов",
        "Богданов",
        "Воробьев",
        "Федоров",
        "Михайлов",
        "Беляев",
        "Тарасов",
        "Белов",
        "Комаров",
        "Орлов",
        "Киселев",
        "Макаров",
        "Андреев",
        "Ковалев",
        "Гусев",
        "Титов",
        "Кузьмин",
        "Кудрявцев",
        "Баранов",
        "Куликов",
        "Алексеев",
        "Степанов",
        "Яковлев"
    )

    fun getRandomName(): String {
        val firstName = names.random()
        val lastName = surnames.random()
        return "$firstName $lastName"
    }
}