package kosien.procon.application;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

/**
 * Created by i15317 on 2017/10/14.
 */

public class BottomNavigationHelper {
    public static void disableShiftMode(BottomNavigationView view){
        BottomNavigationMenuView  menuView = (BottomNavigationMenuView)view.getChildAt(0);
        try{
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView,false);
            shiftingMode.setAccessible(false);
            for(int i = 0; i < menuView.getChildCount();i++){
                BottomNavigationItemView item = (BottomNavigationItemView)menuView.getChildAt(i);
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        }catch (NoSuchFieldException e){
            //特に何もしない
        }catch (IllegalAccessException e){
            //特に何もしない
        }
    }
}
