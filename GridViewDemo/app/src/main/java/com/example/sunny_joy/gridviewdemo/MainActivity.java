package com.example.sunny_joy.gridviewdemo;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private static final String[] data={"上海","北京", "广州","深圳","湖南","湖北","河南","河北","辽宁","大连"};
    private GridView gridView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView=(GridView)findViewById(R.id.gridView1);
        ArrayAdapter<String > adapter=new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                data
        );
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, data[position], Toast.LENGTH_LONG).show();
            }
        });
        //有图片有蚊子的GridView
        gridView2=(GridView)findViewById(R.id.gridView2);
        gridView2.setAdapter(new MyAdapter());

    }
    class  MyAdapter extends BaseAdapter{
        private int[] imgResId={
                R.drawable.peony,
                R.drawable.pluto,
                R.drawable.tulip
        };
        private String[] names={
                "peony","pluto","tulip"
        };

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder holder;//持有者

            if(convertView==null){
                view= LayoutInflater.from(MainActivity.this).inflate(R.layout.grid_item,null);
                holder=new ViewHolder();
                //第一次
                holder.image=view.findViewById(R.id.img);
                holder.tv=view.findViewById(R.id.text1);
                view.setTag(holder);
            }else{
                view=convertView;
                holder=(ViewHolder) view.getTag();
            }
            ImageView image=holder.image;
            TextView text=holder.tv;
            image.setImageResource(imgResId[position]);
            text.setText(names[position]);

            return view;
        }
    }
    class ViewHolder{
        ImageView image;
        TextView tv;
    }
}
