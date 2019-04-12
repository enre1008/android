package com.example.sunny_joy.jsonparsedemo;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    String data="{\"name\":\"Tom\", \"age\":\"12\"}";
    private Button btnParser;
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnParser=(Button)findViewById(R.id.btnParser);
        tvContent=(TextView)findViewById(R.id.tvContent);

        //得到Json对象
//        try {
//            JSONObject jsonObj = new JSONObject(data);
//            //通过key 得到值
//            String value=jsonObj.getString("name");
//            String age=jsonObj.getString("age");
//            Log.i("TEST",value+", "+age);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    public void parser(View view){
        try {
            parseJson();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void parseJson() throws JSONException {

        //得到文件中的内容
        try {
            String data=getContent(getResources(), R.raw.person);
            JSONObject person=new JSONObject(data);
            String firstName=person.getString("firstName");
            String lastName=person.getString("lastName");
            String age=person.getString("age");

            tvContent.append(firstName+"\n"+lastName+"\n"+age+"\n");

            JSONObject address=person.getJSONObject("address");
            String street=address.getString("streetAddress");
            String city=address.getString("city");
            String state=address.getString("state");
            String postalCode=address.getString("postalCode");

            tvContent.append(street+", "+city+", "+state+","+ postalCode+"\n");

            //数组
            JSONArray phone=person.getJSONArray("phoneNumber");
            for(int index=0; index<phone.length(); index++){
                JSONObject obj = phone.getJSONObject(index);
                String type=obj.getString("type");
                String number=obj.getString("number");
                tvContent.append(type+": "+number+" ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getContent(Resources res, int id) throws IOException {
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        try {

            is = res.openRawResource(id);
            byte[] buffer = new byte[1024];
            int len = is.read(buffer, 0, 1024);
            while (len != -1) {
                String s = new String(buffer, 0, len);
                sb.append(s);
                len = is.read(buffer, 0, 1024);

            }
        }finally {
            {
                if(is!=null){
                    is.close();
                }
            }
            return sb.toString();
        }

    }
}
