package muhammad.reynaldi.hitungpengeluaran.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import muhammad.reynaldi.hitungpengeluaran.database.dao.DatabaseDao;
import muhammad.reynaldi.hitungpengeluaran.model.ModelDatabase;

@Database(entities = {ModelDatabase.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatabaseDao databaseDao();
}
