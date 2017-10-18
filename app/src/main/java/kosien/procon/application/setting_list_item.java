package kosien.procon.application;

/**
 * Created by Owner on 2017/10/18.
 */


//セットクラス
public class setting_list_item {
    long id;
    private String statusText;
    private String clickText;

    public long getId(){
        return this.id;
    }
    public void setId(long _id){
        this.id = _id;
    }

    public String getStatusText(){
        return this.statusText;
    }

    public void setStatusText(String _setText){
        this.statusText = _setText;
    }

    public String getClickText(){
        return this.clickText;
    }

    public void setClickText(String _setText){this.clickText = _setText;}



}
