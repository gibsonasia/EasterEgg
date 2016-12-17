package nesada.c4q.nyc.sqliteprototype;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Nesada on 12/11/2016.
 */
public class SetupDB extends SQLiteOpenHelper{

    public SetupDB(Context context) {
        super(context, "myData.db", null, 4);
    }

    static {
        cupboard().register(Turtle.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("create table tableName (id text, name text, lastName text)");

        cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("drop table if exists tableName");
//        onCreate(db);

        cupboard().withDatabase(db).upgradeTables();
    }
}
