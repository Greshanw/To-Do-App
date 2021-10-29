package Database;

import android.provider.BaseColumns;

public final class TaskMaster {
    private TaskMaster(){}

    public static class Tasks implements BaseColumns{
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_NAME_TASK = "task";
    }
}
