package com.laurier.joelucy.CP670project.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.laurier.joelucy.CP670project.ListMoodRecord;
import com.laurier.joelucy.CP670project.MainActivity;
import com.laurier.joelucy.CP670project.R;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    View root;
    ArrayList<ImageView> imageViews= new ArrayList<>();
    ArrayList<View>dots=new ArrayList<>();
    ViewPager vp;
    ViewPagerAdapter adapter;
    int[] image = {R.drawable.main1, R.drawable.main2, R.drawable.main3, R.drawable.main4};
    int oldPosition=0;
    int currentItem;
    ScheduledExecutorService scheduledExecutorService;
    //

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        setView();
        return root;
    }
    public void setView() {
        vp = (ViewPager)root.findViewById(R.id.viewContent);
        for (int i = 0; i < image.length; i++) {
            ImageView imageView = new ImageView(getActivity());//(this);
            imageView.setImageResource(image[i]);
            imageViews.add(imageView);
        }

        dots.add(root.findViewById(R.id.p1));
        dots.add(root.findViewById(R.id.p2));
        dots.add(root.findViewById(R.id.p3));
        dots.add(root.findViewById(R.id.p4));

        adapter = new ViewPagerAdapter();
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                dots.get(position).setBackgroundResource(R.drawable.dot_focus);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View v=imageViews.get(position);
            container.removeView(v);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View v=imageViews.get(position);
            container.addView(v);
            return v;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(),2,2, TimeUnit.SECONDS);
    }
    class ViewPagerTask implements Runnable{

        @Override
        public void run() {
            currentItem=(currentItem+1)%image.length;
            handler.obtainMessage().sendToTarget();
        }
    }
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            vp.setCurrentItem(currentItem);
        }
    };
}