package com.example.delivery1.data

import androidx.room.*
import androidx.room.Dao

@Dao
interface SiteDao {
    @Query("SELECT * FROM Sites")
    fun getSites(): Array<Site>

    @Query("SELECT * FROM Sites WHERE role = :role")
    fun getSitesWithRole(role: SiteRole): Array<Site>

    @Insert
    fun addSite(site: Site)

    @Update
    fun updSite(site: Site)

    @Delete
    fun delSite(site: Site)
}