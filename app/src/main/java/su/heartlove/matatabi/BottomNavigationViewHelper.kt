package su.heartlove.matatabi

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log
import java.lang.reflect.Field

object BottomNavigationViewHelper {
	@SuppressLint("RestrictedApi")
	
	internal fun removeShiftMode(view: BottomNavigationView) {
		val menuView = view.getChildAt(0) as BottomNavigationMenuView
		
		try {
			val shiftingMode: Field = menuView.javaClass.getDeclaredField("mShiftingMode")
			shiftingMode.isAccessible = true
			shiftingMode.setBoolean(menuView, false)
			
			for (i in 0 until menuView.childCount) {
				val item = menuView.getChildAt(i) as BottomNavigationItemView
				item.setShiftingMode(false)
				// set once again checked value, so view will be updated
				item.setChecked(item.itemData.isChecked)
			}
			shiftingMode.isAccessible = false
		} catch (e: NoSuchFieldException) {
			Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field")
		} catch (e: IllegalAccessException) {
			Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode")
		}
	}
}
