package kosien.procon.application;

/**
 * Created by procon-kyougi on 2017/10/23.
 */

public class place_detail_list_item {
    //表示項目名
    private String columnName = null;

    //コンストラクタ
    public place_detail_list_item(String _columnName){
        this.columnName = _columnName;
    }

    public void setColumnName(String _columnName){
        this.columnName = _columnName;
    }
    public String getColumnName(){
        return this.columnName;
    }
}
