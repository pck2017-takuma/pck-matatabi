package kosien.procon.application;

import android.content.Context;

import kosien.procon.application.matatabidb.mydatabase.infoTravel;
import kosien.procon.application.matatabidb.mydatabase.infoTravelDao;
import kosien.procon.application.matatabidb.mydatabase.travelSchedule;
import kosien.procon.application.matatabidb.mydatabase.travelScheduleDao;

/**
 * Created by procon-kyougi1 on 2017/10/02.
 */

public class TravelDataWrite {

    private MainActivity main = new MainActivity();

    void dataWrite(Context cont, String str) {
        infoTravelDao xxx = new infoTravelDao(cont);
        travelScheduleDao yyy = new travelScheduleDao(cont);

        infoTravel zzz = new infoTravel();
        zzz.setTravelTitle(str);
        zzz = xxx.save_time(zzz);
        int aaa = zzz.getRowid();
        travelSchedule bbb = new travelSchedule();

        for(int i = 0; i < main.listItem.size(); i++) {
            bbb.setPlaceName(main.itemName(i));
            bbb.setRouteNum(i);
            bbb.setTravelNum(aaa);
        }

    }
}
