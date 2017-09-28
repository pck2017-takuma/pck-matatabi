package kosien.procon.application;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import kosien.procon.application.matatabidb.mydatabase.RecordDaoItem;
import kosien.procon.application.matatabidb.mydatabase.RecordItem;
import su.heartlove.matatabi.R;

/**
 * Created by i15317 on 2017/09/22.
 */

public class DiaryEdit extends Fragment {

    private RecordItem Record = null;

    private EditText diary = null;
    private Button btn_voice = null;
    private Button btn_save = null;
    private Button btn_cancel = null;

    private LinearLayout all = null;

    final Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH) + 1;
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

    private static final int VOICE_WORD = 1;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){

        View layout = inflater.inflate(R.layout.diary_add,container);
        //リソース割り当て
        diary = (EditText)layout.findViewById(R.id.EditText_diary);
        btn_voice = (Button)layout.findViewById(R.id.Button_add_voice);
        btn_save = (Button)layout.findViewById(R.id.Buttonadd_save);
        btn_cancel = (Button)layout.findViewById(R.id.Button_add_cancel);
        all = (LinearLayout)layout.findViewById(R.id.LinearLayout_add_all);

        //日記の本文をタップした時
        diary.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                /* フォーカスイベントを有効にする */
                diary.setFocusable(true);
                diary.setFocusableInTouchMode(true);
                return false;
            }
        });

        //日記を編集したとき
        diary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int cnt) {
                //編集中にセーブ画面を表示
                if(charSequence.length()>0){
                    btn_save.setEnabled(true);
                }else{
                    btn_save.setEnabled(false);
                }
            }

            //この２つは空実装
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                DispToastVoiceNG(null);
            }
        });

        //謎の音声入力機能入り
        btn_voice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    //インテントはフラグメントに直して

                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, R.string.btn_add_voice);

                    startActivityForResult(intent, VOICE_WORD);

                } catch (ActivityNotFoundException e) {
                    //DispToast(null);
                }
            }
        });

        //saveボタンを押したとき
        btn_save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getContext(),"114514",Toast.LENGTH_SHORT).show();

                Record = new RecordItem();
                Record.setDiaryRecord(diary.getText().toString());
                Record.setDiaryYear(year);
                Record.setDiaryMon(month);
                Record.setDiaryDay(day);

                DataLoadTask task = new DataLoadTask();
                task.execute();

            }
        });

//
//        //戻るボタンをクリック
//        btn_cancel.setOnClickListener(new View.OnClickListener(){
////            public void onClick(View v){
////                if(diary.getText().length() > 0){
////                    showDialog(0);
////                }else{
////                    finish();
////                }
////            }
////        });


        //何も書かないとセーブさせないようにする
        btn_save.setEnabled(false);
        //初期起動時にはフォーカスを当てないようにする
        diary.setFocusable(false);

        return layout;

    }//OnCreateここまで

    //削除確認ダイアログ
//    @Override
//    protected Dialog onCreateDialog(int id){
//
//
//        Dialog dialog = super.onCreateDialog(id);
//        //複数のダイアログをIDで判定
//        switch(id){
//            case 0:
//                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
//                dialogBuilder.setTitle(R.string.cancel_check_title);
//                dialogBuilder.setMessage(R.string.cancel_check_main);
//
//                dialogBuilder.setPositiveButton(
//                        "Yes",
//                        new DialogInterface.OnClickListener(){
//                            public void onClick(DialogInterface dialog,int whichButton){
//                                finish();
//                            }
//                        }
//                );
//                dialogBuilder.setNegativeButton(
//                        "No",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int whichbutton) {
//                                //何もしない
//                            }
//                        }
//                );
//                //ダイアログ起動
//                dialog = dialogBuilder.create();
//                break;
//        }
//        return dialog;
//    }


    //保存処理
    public class DataLoadTask extends AsyncTask<Object,Integer,RecordItem> {
        //処理中ダイアログ
        private ProgressDialog progressDialog = null;

        @Override
        protected void onPreExecute(){
            //処理中ダイアログ表示
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage(getResources().getText(R.string.loading));
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }


        @Override
        protected RecordItem doInBackground(Object... params){
            //保存処理
            RecordDaoItem dao = new RecordDaoItem(getContext());

            return dao.sava_diary(Record);
        }

        //処理中ダイアログをクローズ
        protected void onPostExecute(RecordItem result){
            //処理中ダイアログをクローズ
            progressDialog.dismiss();

         //   clearKeyboard();
            //保存完了表示
            DispToast(null);
            getFragmentManager().popBackStack();
          //  finish();
        }

    }

    //トースト表示
    public void DispToast(View v){
        Toast.makeText(getContext(),R.string.add_ok,Toast.LENGTH_SHORT).show();
    }
//
//    //ソフトキーボードを消去
//    private void clearKeyboard(){
//        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(all.getWindowToken(),0);
//    }

    public void DispToastVoiceNG(View v) {
        Toast.makeText(getContext(), R.string.voice_ng, Toast.LENGTH_SHORT).show();
    }


    //戻り処理

    public void onActivity(int requestCode,int resultCode,Intent data){
        //声入力からの戻り

        if(requestCode == VOICE_WORD){
            String resultString = "";

            //結果文字列リスト
            ArrayList<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            for(int i = 0;i < results.size();i++){
              resultString += results.get(i);
            }
            diary.setText(diary.getText() + " " + resultString);

        }
        super.onActivityResult(requestCode,resultCode,data);
    }


    //キー処理
//
//    public boolean onKeyDown(int keyCode,KeyEvent event){
//        if(keyCode == KeyEvent.KEYCODE_BACK){
//            if(diary.getText().length() > 0){
//                showDialog(0);
//            }else{
//                finish();
//            }
//            return true;
//        }else{
//            return false;
//        }
//
//    }




}

