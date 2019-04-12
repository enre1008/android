package com.example.sunny_joy.baseadapterdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private int[] imgResIds={
            R.drawable.peony,
            R.drawable.pluto,
            R.drawable.tulip
    };
    private String[] names={
            "peony", "pluto", "tulip"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.listView1);
        listView.setAdapter(new MyAdapter());
    }


    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
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
            ViewHolder holder=null;

            if(convertView==null){
                LayoutInflater layoutInflater=LayoutInflater.from(MainActivity.this);
                view=layoutInflater.inflate(R.layout.list_item,null);
                holder=new ViewHolder(view);

                view.setTag(holder);

            }else{
                view=convertView;
                holder=(ViewHolder) view.getTag();
            }
            ImageView image=holder.getImage();
            image.setImageResource(imgResIds[position%names.length]);
            TextView dogName=holder.getDogName();
            dogName.setText(names[position%names.length]);

            return view;
        }
    }
    class ViewHolder{
        private View view;
        private ImageView image;
        private TextView dogName;

        public ViewHolder(View view){
            this.view=view;
        }

        public ImageView getImage(){
            if(image==null){
                image=view.findViewById(R.id.imgDog);
            }
            return image;
        }

        public TextView getDogName(){
            if(dogName ==null){
                dogName=view.findViewById(R.id.tvDogName);
            }
            return dogName;
        }
    }

}
