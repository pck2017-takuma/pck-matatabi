package kosien.procon.application.matatabidb.mydatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.widget.ArrayAdapter;
import java.util.List;

/**
 * Created by procon-kyougi on 2017/09/13.
 */

public class TrainInfoDao {

    private trainDBOpenHelper helper = null;

    public TrainInfoDao(Context context) {
        helper = new trainDBOpenHelper(context);
    }



    public Train_Info save(Train_Info trainInfo) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Train_Info result = null;
        try {
            ContentValues values = new ContentValues();
            values.put(Train_Info.TRAIN_NUMBER, trainInfo.getTrainNumber());
            values.put(Train_Info.STARTING_STATION, trainInfo.getstatingStation());
            values.put(Train_Info.TERMINAL_STATION, trainInfo.getTerminalStation());
            values.put(Train_Info.TRAIN_ASSORTMENT, trainInfo.getTrainAssortment());
            values.put(Train_Info.TRAIN_CONDITION, trainInfo.getTrainCondition());
            values.put(Train_Info.TRAIN_COLUMN, trainInfo.getTrainColumn());

            Long rowId = trainInfo.getRowid();

            if (rowId == null) {
                rowId = db.insert(Train_Info.TABLE_NAME, null, values);
            } else {
                db.update(Train_Info.TABLE_NAME, values, Train_Info.COLUMN_ID + "=?", new String[]{String.valueOf(rowId)});
            }
            result = load(rowId);
        } finally {
            db.close();
        }
        return result;
    }


    //

    /**
     * レコードの削除
     *
     * @param TrainInfo 削除対象のオブジェクト
     */
    public void delete(Train_Info TrainInfo) {
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.delete(Train_Info.TABLE_NAME, Train_Info.COLUMN_ID + "=?", new String[]{String.valueOf(TrainInfo.getRowid())});
        } finally {
            db.close();
        }
    }

    /**
     * idでBizCardをロードする
     *
     * @param rowId PK
     * @return ロード結果
     */
    public Train_Info load(Long rowId) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Train_Info TrainInfo = null;
        try {
            Cursor cursor = db.query(Train_Info.TABLE_NAME, null, Train_Info.COLUMN_ID + "=?", new String[]{String.valueOf(rowId)}, null, null, null);
            cursor.moveToFirst();
            TrainInfo = getTrainInfo(cursor);
        } finally {
            db.close();
        }
        return TrainInfo;
    }

    /**
     * 一覧を取得する
     *
     * @return 検索結果
     */
/*
    public List<Train_Info> list() {
        SQLiteDatabase db = helper.getReadableDatabase();

        List<Train_Info> bizCardList;
        try {
            Cursor cursor = db.query(Train_Info.TABLE_NAME, null, null, null, null, null, Train_Info.COLUMN_ID);
            bizCardList = new ArrayAdapter<Train_Info>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                bizCardList.add(getTrainInfo(cursor));
                cursor.moveToNext();
            }
        } finally {
            db.close();
        }
        return bizCardList;
    }
*/
    /**
     * カーソルからオブジェクトへの変換
     *
     * @param cursor カーソル
     * @return 変換結果
     */
    private Train_Info getTrainInfo(Cursor cursor) {
        Train_Info trainInfo = new Train_Info();

        trainInfo.setRowid(cursor.getLong(0));
        trainInfo.setTrainNumber(cursor.getString(1));
        trainInfo.setstatingStation(cursor.getString(2));
        trainInfo.setTerminalStaiton(cursor.getString(3));
        trainInfo.setTrainAssortment(cursor.getString(4));
        trainInfo.setTrainCondition(cursor.getString(5));
        trainInfo.setTrainColumn(cursor.getString(6));
        return trainInfo;
    }


}
