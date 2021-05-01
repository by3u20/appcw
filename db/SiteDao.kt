package moe.yhi.apps.playground.data

import androidx.room.*
import androidx.room.Dao

@Dao
interface SiteDao {
    @Query("SELECT * FROM Sites")
    suspend fun getSites(): Array<Site>

    @Query("SELECT * FROM Sites WHERE role = :role")
    suspend fun getSitesWithRole(role: SiteRole): Array<Site>

    @Insert
    suspend fun addSite(site: Site)

    @Update
    suspend fun updSite(site: Site)

    @Delete
    suspend fun delSite(site: Site)
}