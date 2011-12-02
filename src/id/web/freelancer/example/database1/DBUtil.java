package id.web.freelancer.example.database1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtil {
	public static void createTable(SQLiteDatabase database, String tableName, String sqlCreate)
    {
            try
            {
                    //begin the transaction
                    database.beginTransaction();

                    database.execSQL(sqlCreate);
                    
                    //this makes sure transaction is committed
                    database.setTransactionSuccessful();
            } 
            finally
            {
                    database.endTransaction();
            }
    }

	public static boolean isTableEmpty(SQLiteDatabase db, String table)
    {
            Cursor cursor = null;
            try
            {
                    cursor = db.rawQuery("SELECT count(*) FROM "+table, null);
                    
                    int countIndex = cursor.getColumnIndex("count(*)");
                    cursor.moveToFirst();
                    int rowCount = cursor.getInt(countIndex);
                    if(rowCount > 0)
                    {
                            return false;
                    }
                    
                    return true;
            }
            finally
            {
                    if(cursor != null)
                    {
                            cursor.close();
                    }
            }
    }}
