package kosien.procon.application.matatabidb.mydatabase;



/**
 * Created by procon-kyougi on 2017/09/14.
 */

public class MAKE_SQL {
    //SQL文作成用クラス
        static StringBuilder createTraininfo() {
            StringBuilder createSql = new StringBuilder();
            createSql.append("create table if not exists " + Train_Info.TABLE_NAME + " (");
            createSql.append(Train_Info.COLUMN_ID + " integer primary key autoincrement not null, ");
            createSql.append(Train_Info.TRAIN_NUMBER + " text not null,");
            createSql.append(Train_Info.STARTING_STATION + " text,");
            createSql.append(Train_Info.TERMINAL_STATION + " text,");
            createSql.append(Train_Info.TRAIN_ASSORTMENT + " text,");
            createSql.append(Train_Info.TRAIN_COLUMN + " text ");
            createSql.append(");");

            return createSql;
        }


        static StringBuilder createBusInfomation() {
            StringBuilder createSql = new StringBuilder();
            createSql.append("create table if not exists " + BUS_INFOMATION.TABLE_NAME + " (");
            createSql.append(BUS_INFOMATION.COLUMN_ID + " integer primary key autoincrement not null, ");
            createSql.append(BUS_INFOMATION.BUS_STOPNAME + " text not null,");
            createSql.append(BUS_INFOMATION.KANA_NAME + " text not null,");
            createSql.append(BUS_INFOMATION.BET_NAME + " text not null,");
            createSql.append(BUS_INFOMATION.BUS_NUMBER + " text not null,");
            createSql.append(BUS_INFOMATION.STATION_NUMBER + " text not null ");
            createSql.append(");");
            return createSql;
        }

       static StringBuilder createBusTable() {
            StringBuilder createSql = new StringBuilder();
            createSql.append("create table if not exists " + BUS_TABLE.TABLE_NAME + " (");
            createSql.append(BUS_TABLE.COLUMN_ID + " integer primary key autoincrement not null, ");
            createSql.append(BUS_TABLE.LOAD_NUMBER + " text not null,");
            createSql.append(BUS_TABLE.BUS_AWAY + " text not null,");
            createSql.append(BUS_TABLE.DEPARTING_TIME + " text not null,");
            createSql.append(BUS_TABLE.ARRIVING_TIME + " text not null,");
            createSql.append(BUS_TABLE.STATION_NUMBER + " text not null,");
            createSql.append(BUS_TABLE.PLACE_NUMBER + " text not null ");
            createSql.append(");");
            return createSql;
        }

       static  StringBuilder createStationInfomation() {
            StringBuilder createSql = new StringBuilder();
            createSql.append("create table if not exists " + Station_Infomation.TABLE_NAME + " (");
            createSql.append(Station_Infomation.COLUMN_ID + " integer primary key autoincrement not null, ");
            createSql.append(Station_Infomation.STATION_NAME + " text not null, ");
            createSql.append(Station_Infomation.STATION_CODE + " text not null, ");
            createSql.append(Station_Infomation.KANA_NAME + " text not null, ");
            createSql.append(Station_Infomation.BET_NAME + " text not null, ");
            createSql.append(Station_Infomation.STATION_NUMBER + " text not null, ");
            createSql.append(Station_Infomation.STATION_POST + " text not null, ");
            createSql.append(Station_Infomation.STATION_ADDRESS + " text not null, ");
            createSql.append(Station_Infomation.STATION_LATITUDE + " real, ");
            createSql.append(Station_Infomation.STATION_LONGITUDE + " real, ");
            createSql.append(Station_Infomation.TOURIST_FLAG + " text not null, ");
            createSql.append(Station_Infomation.CITY_EVA_VALUE + " text not null ");
            createSql.append(");");

            return createSql;
        }

    static StringBuilder createtravelSchedule() {
        StringBuilder createSql = new StringBuilder();
        createSql.append("create table if not exists " + Station_Infomation.TABLE_NAME + " (");
        createSql.append(travelSchedule.COLUMN_ID + " integer primary key autoincrement not null, ");
        createSql.append(travelSchedule.TRAVEL_NUM + " integer not null, ");
        createSql.append(travelSchedule.PLACE_NAME + " text not null, ");
        createSql.append(travelSchedule.ROUTE_NUM + " integer not null ");
        createSql.append(travelSchedule.FLAG + " integer not null ");
        createSql.append(");");
        return createSql;
    }


        static StringBuilder createDiary(){
            StringBuilder createSql = new StringBuilder();
            createSql.append("create table if not exists " + RecordItem.TABLE_NAME + " (");
            createSql.append(RecordItem.COLUMN_ID + " integer primary key autoincrement not null,");
            createSql.append(RecordItem.DIARY_TITLE  + " text, ");
            createSql.append(RecordItem.DIARY_RECORD + " text, ");
            createSql.append(RecordItem.DIARY_YEAR + " integer, ");
            createSql.append(RecordItem.DIARY_MON + " integer, ");
            createSql.append(RecordItem.DIARY_Day + " integer, ");
            createSql.append(RecordItem.DIARY_TIME + " integer, ");
            createSql.append(RecordItem.TRAVEL_NUM + " integer, ");
            createSql.append(RecordItem.SCHEDULE_NUM + " integer ");
            createSql.append(");");

            return createSql;
        }

        static StringBuilder createDiaryUpdateTime(){
            StringBuilder createSql = new StringBuilder();
            createSql.append("create table if not exists " + RecordTime.TABLE_NAME + " (");
            createSql.append(RecordTime.COLUMN_ID + " integer primary key autoincrement not null, ");
            createSql.append(RecordTime.DIARY_ID + " integer not null, ");
            createSql.append(RecordTime.UPDATE_TIME + " integer not null, ");
            createSql.append(RecordTime.UPDATE_YEAR + " integer not null, ");
            createSql.append(RecordTime.UPDATE_DATE + " integer not null, ");
            createSql.append(RecordTime.UPDATE_DAY + " integer not null, ");
            createSql.append(RecordTime.UPDATE_DAYTIME+ " integer not null ");
            createSql.append(");");
            return createSql;
        }

    static StringBuilder createtravelInfo(){
        StringBuilder createSql = new StringBuilder();
        createSql.append("create table if not exists " + infoTravel.TABLE_NAME + " (");
        createSql.append(infoTravel.COLUMN_ID + " integer primary key autoincrement not null, ");
        createSql.append(infoTravel.TRAVEL_NUM + " integer not null, ");
        createSql.append(infoTravel.TRAVEL_TITEL + " text not null, ");
        createSql.append(infoTravel.TRAVEL_FLAG + " integer default 0 ");
        createSql.append(");");
        return createSql;
    }

        
        static StringBuilder createRouteInfo(){
            StringBuilder createSql = new StringBuilder();
            createSql.append("create table if not exists " + RouteInfo.TABLE_NAME + " (");
            createSql.append(RouteInfo.COLUMN_ID + " integer primary key autoincrement not null, ");
            createSql.append(RouteInfo.ROUTE_DEPARTURE + " text not null, ");
            createSql.append(RouteInfo.ROUTE_DEPTTIME + " text not null, ");
            createSql.append(RouteInfo.ROUTE_DESTINATION + " text not null, ");
            createSql.append(RouteInfo.ROUTE_DEPTTIME + " text not null, ");
            createSql.append(RouteInfo.ROUTE_TRAIN + " text not null, ");
            createSql.append(RouteInfo.TRAVEL_NUM + " integer not null, ");
            createSql.append(RouteInfo.ROUTE_FLAG + " integer default 0 ");
            createSql.append(");");
            return createSql;
        }

    static StringBuilder createPlaceInfo(){
        StringBuilder createSql = new StringBuilder();
        createSql.append("create table if not exists " + placeInfomation.TABLE_NAME + " (");
        createSql.append(placeInfomation.COLUMN_ID + " integer primary key autoincrement not null, ");
        createSql.append(placeInfomation.PLACE_NAME + " text not null, ");
        createSql.append(placeInfomation.PLACE_STATION + " text not null, ");
        createSql.append(placeInfomation.LATITUDE + " text not null, ");
        createSql.append(placeInfomation.LONGITUDE + " text not null, ");
        createSql.append(placeInfomation.ADDRESS + " text not null, ");
        createSql.append(placeInfomation.COLUMN + " text not null, ");
        createSql.append(placeInfomation.PHONE_NUMBER + " text not null, ");
        createSql.append(placeInfomation.POST_NUMBER + " text not null, ");
        createSql.append(placeInfomation.OPEN_TIME + " text not null, ");
        createSql.append(placeInfomation.CLOSE_TIME + " text not null, ");
        createSql.append(placeInfomation.PLACE_VALUE + " integer not null ");
        createSql.append(");");
        return createSql;
    }

        static StringBuilder createDiaryImage(){
            StringBuilder createSql = new StringBuilder();
            createSql.append("create table if not exists " + Diary_Image.TABLE_NAME + " (");
            createSql.append(Station_Infomation.COLUMN_ID + " integer primary key autoincrement not null, ");
            createSql.append(Diary_Image.DIARY_ID + " integer not null, ");
            createSql.append(Diary_Image.SAVE_IMAGE  + " text ");
            createSql.append(");");
            return createSql;
        }

        static StringBuilder createStationImage(){
            StringBuilder createSql = new StringBuilder();
            createSql.append("create table if not exists " + StationImage.TABLE_NAME + " (");
            createSql.append(Station_Infomation.COLUMN_ID + " integer primary key autoincrement not null, ");
            createSql.append(StationImage.STATION_NUMBER + " text not null, ");
            createSql.append(StationImage.STATION_PICTURE + " text ");
            createSql.append(");");
            return createSql;
        }   
        
        static StringBuilder createPlaceImage(){
            StringBuilder createSql = new StringBuilder();
            createSql.append("create table if not exists " + placeImage.TABLE_NAME + " (");
            createSql.append(Station_Infomation.COLUMN_ID + " integer primary key autoincrement not null, ");
            createSql.append(placeImage.PLACE_ID + " integer not null, ");
            createSql.append(placeImage.SAVE_IMAGE  + " text ");
            createSql.append(");");
            return createSql;
        }


        //テーブルから全件数削除するSQL文を生成する
    static String query_deleteAlldata(String tableName,String columnId){
        String sql = "delete from " + tableName + " where " + columnId + "> -1;";
        return sql;
    }


    //idを指定してロードするSQLを作成する
    static String query_load(String tableName,String columnId,int itemID){
        String sql = "select * " + " from " + tableName + " where " + columnId + " = '" + itemID + "';";
        return sql;
    }
     
    static  String query_deleteAlldata2(String tableName,String columnId){
          String sql = "delete from " + tableName + " where " + columnId + "> -1;";
          return sql;
     }
     
     
     //idを指定してロードするSQLを作成する
    static  String query_load2(String tableName,String columnId,int itemID){
          String sql = "select * " + " from " + tableName + " where " + columnId + " = '" + itemID + "';";
          return sql;
     }
     
     
     
     
}


