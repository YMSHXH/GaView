package com.ysm.gaview;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysm.gaview.weight.FlipperLayout;
import com.ysm.gaview.weight.GalleryViewPager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FlipperLayout.TouchListener {

//    final private static int[] PIC_RES = new int[] {
//            R.mipmap.pic1, R.mipmap.pic2, R.mipmap.pic3, R.mipmap.pic4, R.mipmap.pic5
//    };

    private String text = "";
    private int textLenght = 0;

    private static final int COUNT = 900;

    private int currentTopEndIndex = 0;

    private int currentShowEndIndex = 0;

    private int currentBottomEndIndex = 0;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            FlipperLayout rootLayout = (FlipperLayout) findViewById(R.id.container);
            View recoverView = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_new, null);
            View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_new, null);
            View view2 = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_new, null);
            rootLayout.initFlipperViews(MainActivity.this, view2, view1, recoverView);

            textLenght = text.length();

            System.out.println("----textLenght----->" + textLenght);

            TextView textView = (TextView) view1.findViewById(R.id.textview);
            if (textLenght > COUNT) {
                textView.setText(text.subSequence(0, COUNT));
                textView = (TextView) view2.findViewById(R.id.textview);
                if (textLenght > (COUNT << 1)) {
                    textView.setText(text.subSequence(COUNT, COUNT * 2));
                    currentShowEndIndex = COUNT;
                    currentBottomEndIndex = COUNT << 1;
                } else {
                    textView.setText(text.subSequence(COUNT, textLenght));
                    currentShowEndIndex = textLenght;
                    currentBottomEndIndex = textLenght;
                }
            } else {
                textView.setText(text.subSequence(0, textLenght));
                currentShowEndIndex = textLenght;
                currentBottomEndIndex = textLenght;
            }
        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ReadingThread().start();
    }

    @Override
    public void onClick(View v) {
    }
    @Override
    public View createView(final int direction) {
        String txt = "";
        if (direction == FlipperLayout.TouchListener.MOVE_TO_LEFT) {
            currentTopEndIndex = currentShowEndIndex;
            final int nextIndex = currentBottomEndIndex + COUNT;
            currentShowEndIndex = currentBottomEndIndex;
            if (textLenght > nextIndex) {
                txt = text.substring(currentBottomEndIndex, nextIndex);
                currentBottomEndIndex = nextIndex;
            } else {
                txt = text.substring(currentBottomEndIndex, textLenght);
                currentBottomEndIndex = textLenght;
            }
        } else {
            currentBottomEndIndex = currentShowEndIndex;
            currentShowEndIndex = currentTopEndIndex;
            currentTopEndIndex = currentTopEndIndex - COUNT;
            txt = text.substring(currentTopEndIndex - COUNT, currentTopEndIndex);
        }

        View view = LayoutInflater.from(this).inflate(R.layout.view_new, null);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(txt);

        System.out.println("-top->" + currentTopEndIndex + "-show->" + currentShowEndIndex + "--bottom-->" + currentBottomEndIndex);
        return view;
    }

    @Override
    public boolean whetherHasPreviousPage() {
        return currentShowEndIndex > COUNT;
    }

    @Override
    public boolean whetherHasNextPage() {
        return currentShowEndIndex < textLenght;
    }
    @Override
    public boolean currentIsFirstPage() {
        boolean should = currentTopEndIndex > COUNT;
        if (!should) {
            currentBottomEndIndex = currentShowEndIndex;
            currentShowEndIndex = currentTopEndIndex;
            currentTopEndIndex = currentTopEndIndex - COUNT;
        }
        return should;
    }

    @Override
    public boolean currentIsLastPage() {
        boolean should = currentBottomEndIndex < textLenght;
        if (!should) {
            currentTopEndIndex = currentShowEndIndex;
            final int nextIndex = currentBottomEndIndex + COUNT;
            currentShowEndIndex = currentBottomEndIndex;
            if (textLenght > nextIndex) {
                currentBottomEndIndex = nextIndex;
            } else {
                currentBottomEndIndex = textLenght;
            }
        }
        return should;
    }

    private class ReadingThread extends Thread {
        public void run() {
            AssetManager am = getAssets();
            InputStream response;
            try {
                response = am.open("悲剧的诞生.txt");
                if (response != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = -1;
                    while ((i = response.read()) != -1) {
                        baos.write(i);
                    }
                    text = new String(baos.toByteArray(), "GBK");
                    baos.close();
                    response.close();
                    handler.sendEmptyMessage(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

//    private void setData() {
//        GalleryViewPager viewPager = findViewById(R.id.viewPager);
//
//        viewPager.setAdapter(new PagerAdapter() {
//            @Override public int getCount() {
//                return PIC_RES.length;
//            }
//
//            @Override public boolean isViewFromObject(View view, Object object) {
//                return view == object;
//            }
//
//            @Override public Object instantiateItem(ViewGroup container, int position) {
//                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.gallery_item, container, false);
//                ((ImageView) view.findViewById(R.id.imageview)).setImageResource(PIC_RES[position]);
//                container.addView(view);
//                return view;
//            }
//
//            @Override public void destroyItem(ViewGroup container, int position, Object object) {
//                container.removeView((View) object);
//            }
//
//            @Override public float getPageWidth(int position) {
//
//                /**
//                 * 这个是调节viewpager直接的间距的
//                 * 越小 间距越小
//                 */
//                return 0.6f;//建议值为0.6~1.0之间
//            }
//        });
//
//        /**
//         * 这个是调节 中心页 两侧的页面 比中心页小一些
//         *
//         * 越小 中间的图片越突出
//         */
//        viewPager.setNarrowFactor(0.9f);
//
//
//        viewPager.setCurrentItem(2);
//    }

}
