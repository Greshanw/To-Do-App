package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.to_doapp.Items;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TaskInfo.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TaskMaster.Tasks.TABLE_NAME + " (" +
                        TaskMaster.Tasks._ID + " INTEGER PRIMARY KEY," +
                        TaskMaster.Tasks.COLUMN_NAME_TASK + " TEXT)";

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addTask(String task){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TaskMaster.Tasks.COLUMN_NAME_TASK, task);

        long newRowId = db.insert(TaskMaster.Tasks.TABLE_NAME, null, values);

        return  newRowId;
    }

    public ArrayList<Items> readAllTasks(){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                TaskMaster.Tasks._ID,
                TaskMaster.Tasks.COLUMN_NAME_TASK,
        };

        String sortOrder = TaskMaster.Tasks.COLUMN_NAME_TASK + " DESC";

        Cursor cursor = db.query(
                TaskMaster.Tasks.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Items> info = new ArrayList<>();

        while (cursor.moveToNext()){
            String task = cursor.getString(cursor.getColumnIndexOrThrow(TaskMaster.Tasks.COLUMN_NAME_TASK));
            Items item = new Items(task);
            info.add(item);
        }
        return info;
    }
}
