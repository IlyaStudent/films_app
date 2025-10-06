package com.org.filmsapplication.features.films_list.utils

import com.org.filmsapplication.R

enum class Genre(
    val stringResource: Int,
    // Добавил для фильтрации данных по жанру
    // Возможно, проверку на соответствие жанру можно сделать как то элегантнее
    val russianName: String,
) {
    BIOGRAPHY(R.string.biography, "биография"),
    ACTION(R.string.action, "боевик"),
    DETECTIVE(R.string.detective, "детектив"),
    DRAMA(R.string.drama, "драма"),
    COMEDY(R.string.comedy, "комедия"),
    CRIMINAL(R.string.criminal, "криминал"),
    MELODRAMA(R.string.melodrama, "мелодрама"),
    MUSICAL(R.string.musical, "мюзикл"),
    ADVENTURE(R.string.adventure, "приключения"),
    THRILLER(R.string.thriller, "триллер"),
    HORROR(R.string.horror, "ужасы"),
    FANTASY(R.string.fantasy, "фантастика");
}