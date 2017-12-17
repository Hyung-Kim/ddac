package com.example.taehyung.ddac.DataBase;

import android.provider.BaseColumns;

/**
 * Created by TaeHyungKim on 2017-12-17.
 */

// DataBase Table

public class DataBases {



    public static final class CreateDB implements BaseColumns {
        public static final String CONTENTS_ID = "contents_id";
        public static final String BUY_TIME = "buy_time";
        public static final String CURRENT_LEVEL = "current_level";
        public static final String _TABLENAME = "ddac";
        public static final String _CREATE =
                "create table "+_TABLENAME+"("
                        +_ID+" integer primary key autoincrement, "
                        +CONTENTS_ID+" integer, "
                        +BUY_TIME+" timestamp default CURRENT_TIMESTAMP, "
                        +CURRENT_LEVEL+" integer);";
    }
}
