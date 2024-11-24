package com.thomas.applicationandroid

import TmdbActor
import TmdbMovie
import TmdbTv
import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.ProvidedTypeConverter
import androidx.room.Query
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.Moshi
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Entity
data class FilmEntity(@PrimaryKey val id: String, val fiche: TmdbMovie)

@Entity
data class SerieEntity(@PrimaryKey val id: String, val fiche: TmdbTv)

@Entity
data class ActeurEntity(@PrimaryKey val id: String, val fiche: TmdbActor)


@ProvidedTypeConverter
class Converters(moshi: Moshi) {
    val filmJsonadapter = moshi.adapter(TmdbMovie::class.java)
    val serieJsonadapter = moshi.adapter(TmdbTv::class.java)
    val actorJsonadapter = moshi.adapter(TmdbActor::class.java)

    @TypeConverter
    fun StringToTmdbMovie(value: String): TmdbMovie? {
        return filmJsonadapter.fromJson(value)
    }

    @TypeConverter
    fun StringToTmdbTv(value: String): TmdbTv? {
        return serieJsonadapter.fromJson(value)
    }

    @TypeConverter
    fun StringToTmdbActor(value: String): TmdbActor? {
        return actorJsonadapter.fromJson(value)
    }

    @TypeConverter
    fun TmdbMovieToString(film: TmdbMovie): String {
        return filmJsonadapter.toJson(film)
    }

    @TypeConverter
    fun TmdbTvToString(serie: TmdbTv): String {
        return serieJsonadapter.toJson(serie)
    }

    @TypeConverter
    fun TmdbActorToString(actor: TmdbActor): String {
        return actorJsonadapter.toJson(actor)
    }
}

@Dao
interface FilmDao {
    @Query("SELECT * FROM filmentity")
    suspend fun getFavFilms(): List<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: FilmEntity)

    @Query("DELETE FROM filmentity WHERE id = :id")
    suspend fun deleteFilm(id: String)
}

interface SerieDao {
    @Query("SELECT * FROM serieentity")
    suspend fun getFavSeries(): List<SerieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSerie(serie: SerieEntity)

    @Query("DELETE FROM serieentity WHERE id = :id")
    suspend fun deleteSerie(id: String)
}

interface ActeurDao {
    @Query("SELECT * FROM acteurentity")
    suspend fun getFavActeurs(): List<ActeurEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActeur(acteur: ActeurEntity)

    @Query("DELETE FROM acteurentity WHERE id = :id")
    suspend fun deleteActeur(id: String)
}

@Database(
    entities = [FilmEntity::class, SerieEntity::class, ActeurEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
// pensez bien à faire évoluer le numéro de version à chaque fois
// que vous changez le schéma de la base
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): Dao
}
