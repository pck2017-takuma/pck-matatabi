package kosien.procon.application;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import kosien.procon.application.matatabidb.mydatabase.RecordDaoItem;
import kosien.procon.application.matatabidb.mydatabase.RecordItem;
import su.heartlove.matatabi.R;


/**
 * Created by i15317 on 2017/09/22.
 */

//日記実装

public class DiaryTop extends Fragment {

    public OnMenuItemSelectedListener mCallback; // あとで宣言するinterfaceのフィールド

    private RecordItem Record = null;

    /* 操作したいItemを宣言 */
    private TextView tv_last = null;
    private TextView tv_date = null;
    private TextView tv_next = null;
    private EditText et_search = null;
    private Button btn_top_search = null;
    private Button btn_top_add = null;
    private Button file_add = null;
    private Button sns_add = null;

    final Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

    /* 一覧表示用ListView */
    private ListView listView = null;
    private ArrayAdapter<RecordItem> arrayAdapter = null;

    private LinearLayout all = null;
    boolean isWordSearch = false;

    /* Toast制御 */
    private Handler stopToastHandler = new Handler();
    private Toast g_Toast = null;
    private boolean toastFlag = true;


    // コールバック用のinterfaceを定義
    public interface OnMenuItemSelectedListener {
        public void onMenuItemSelected(int position, String text);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnMenuItemSelectedListener) {
            mCallback = (OnMenuItemSelectedListener) activity; // 親Activityとの紐付け
        }
    }

    //コンストラクタ

    public DiaryTop(){}


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        return inflater.inflate(R.layout.diary_top,container,false);
    }


    @Override
    public void onViewCreated(View layout,Bundle savedInstanceState){


        /* どのItemを操作するのかリソースを割り当て */
        tv_last = (TextView)layout.findViewById(R.id.TextView_last_month);
        tv_date = (TextView)layout.findViewById(R.id.TextView_date);
        tv_next = (TextView)layout.findViewById(R.id.TextView_next_month);
        et_search = (EditText)layout.findViewById(R.id.EditText_top_search);
        btn_top_search = (Button)layout.findViewById(R.id.Button_top_search);
        btn_top_add = (Button)layout.findViewById(R.id.Button_top_add);
        file_add = (Button)layout.findViewById(R.id.Button_add_file);
        sns_add = (Button)layout.findViewById(R.id.Button_add_sns);
        all = (LinearLayout)layout.findViewById(R.id.LinearLayout_all);
        listView = (ListView)layout.findViewById( R.id.ListView_searchlist );


        /* 先月をクリック */
        tv_last.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if( month == 1 ){
                    month = 12;
                    year--;
                }
                else{
                    month--;
                }
                Record.setDiaryYear(year);
                Record.setDiaryMon(month);
                /* データ取得タスクの実行 */
                DataLoadTask task = new DataLoadTask();
                task.execute();
            }
        });

        /* 翌月をクリック */
        tv_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if( month == 12 ){
                    month = 1;
                    year++;
                }
                else{
                    month++;
                }
                Record.setItemSearchYear((year));
                Record.setItemSearchMon((month));
                /* データ取得タスクの実行 */
                DataLoadTask task = new DataLoadTask();
                task.execute();
            }
        });

    	/* 検索文字をタップ */
        et_search.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                /* フォーカスイベントを有効にする */
                et_search.setFocusable(true);
                et_search.setFocusableInTouchMode(true);
                return false;
            }
        });

        /* 検索文字を編集した時 */
        et_search.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /* 何か入力してるなら、Saveキーを有効にする */
                if( s.length() > 0 ){
                    btn_top_search.setEnabled(true);
                }
                else{
                    btn_top_search.setEnabled(false);
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count,    int after) {}
            public void afterTextChanged(Editable s) {}
        });

        /* 検索ボタンを押下 */
        btn_top_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 検索する文字を設定する */
                Record.setItemSearchRecord(String.valueOf(et_search.getText()));
                /* ワード検索を有効に */
                isWordSearch = true;
                /* データ取得タスクの実行 */
                DataLoadTask task = new DataLoadTask();
                task.execute();
            }
        });

        /* 新規追加ボタンを押下 */
        btn_top_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 追加クラスのインテントを生成して呼び出し */

                FragmentManager maneger = getFragmentManager();
                FragmentTransaction  fragmentTransaction = maneger.beginTransaction();
                RecordFragment recordFragment = new RecordFragment();
                fragmentTransaction.replace(R.id.fragment_record,recordFragment);
                fragmentTransaction.commit();

            }

        });
        
                /* 一覧表示のアダプタ */
        arrayAdapter = new ArrayAdapter<RecordItem>(getContext(), R.layout.diary_top);
        listView.setAdapter(arrayAdapter);

        /* リストから日記を選択した時 */
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                RecordItem item = arrayAdapter.getItem(position);

                /* 参照画面で表示情報を引き継ぎ */

                Bundle bundle = new Bundle();

                bundle.putInt("year", item.getDiaryYear());
                bundle.putInt("month", item.getDiaryMon());
                bundle.putInt("day", item.getDiaryDay());
                bundle.putString("diary", item.getDiaryRecord());

                FragmentManager maneger = getFragmentManager();
                FragmentTransaction fragmentTransaction = maneger.beginTransaction();
                DiaryView recordFragment = new DiaryView();
                recordFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_record,recordFragment);
                fragmentTransaction.commit();



            }
        });
        /* ボタンやエディットテキストの初期化 */
        et_search.setFocusable(false);
        btn_top_search.setEnabled(false);

        /* Recordの日付データを初期化 */
        Record = new RecordItem();
        Record.setItemSearchYear((year));
        Record.setItemSearchMon((month));
        /* 起動時の１発目は日付で一覧を取得する為、falseに設定 */
        isWordSearch = false;



    }

    /**
     * アクティビティが前面に来るたびに一覧を更新
     */
    @Override
    public void onResume() {
        super.onResume();

        /* 一覧取得タスクの実行 */
        DataLoadTask task = new DataLoadTask();
        task.execute();
    }





/**
     * 一覧データの取得と表示を行うタスク
     */
    public class DataLoadTask extends AsyncTask<Object, Integer, List<RecordItem>> {

        private ProgressDialog progressDialog = null;

        @Override
        protected void onPreExecute() {
            /* 処理中ダイアログ表示 */
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage(getResources().getText(R.string.loading));
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected List<RecordItem> doInBackground(Object... params) {
            /* 一覧を呼び出し */
            RecordDaoItem dao = new RecordDaoItem(getContext());

            return dao.list_search_item( Record ,isWordSearch );
        }

        protected void onPostExecute(List<RecordItem> result) {
            /* 処理中ダイアログをクローズ */
            progressDialog.dismiss();

            /* 表示日付を更新 */
            tv_date.setText(String.valueOf(year) + " / " + String.valueOf(month));
            if( year == calendar.get(Calendar.YEAR) ){
                /* 現在月より未来は選択出来ないように */
                if( month == calendar.get(Calendar.MONTH) + 1 ){
                    tv_next.setEnabled(false);                        // 翌月の選択を無効
                    tv_next.setTextColor(Color.rgb(255, 255, 102));   // フォントの色を背景と同じにして見えないように細工
                }
                else{
                    tv_next.setEnabled(true);
                    tv_next.setTextColor(Color.rgb(0, 0, 255));
                }
            }

            /* 現在の一覧表示をクリア */
            arrayAdapter.clear();

            /* 一覧表示のデータを設定 */
            for (RecordItem item : result) {
                arrayAdapter.add(item);
                if( item.getDiaryYear() == ((year))
                        && item.getDiaryMon()==((month))
                        && item.getDiaryDay()==((day)) ){
                    btn_top_add.setVisibility(View.GONE);
                }
            }
           // clearKeyboard();

            /* 検索結果が０件の時はトーストを */
            if( arrayAdapter.getCount() == 0 ){
                SetToast(false, R.string.search_non, Toast.LENGTH_SHORT);
            }
            isWordSearch = false;
        }
    }

    /**
     * Toast制御
     */
    private void SetToast(boolean mustDisp, int resId, int duration){
    	/* トーストを表示していない時、または必ず表示しないといけない時は表示するけど、特に必須でないなら表示を行わない */
        /* レコードのない前月や翌月を連打された時の警告トーストが一杯でないようにする対応です */
        if( toastFlag || mustDisp ){
            if(g_Toast != null){
                g_Toast.cancel();
                g_Toast = null;
            }
            g_Toast = Toast.makeText(getContext(), resId, duration);
            g_Toast.show();
            toastFlag = false;
            Runnable toastRunnable = new Runnable() {
                /* 現在のトースト表示から２秒後に呼ばれてトースト表示フラグを有効にします */
                public void run() {
                    toastFlag = true;
                }
            };
            stopToastHandler.postDelayed(toastRunnable, 2000);
        }
    }

    /**
     * ソフトキーボードを消去
     */
//    private void clearKeyboard() {
//        InputMethodManager inputMethodManager =
//                (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(all.getWindowToken(), 0);
//    }

}
