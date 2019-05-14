package com.bw.movie.di.presenter;
//类注释设置模板


import com.bw.movie.data.bean.CommingSoonBeen;
import com.bw.movie.data.bean.HotBeen;
import com.bw.movie.data.bean.ReplaceBeen;
import com.bw.movie.di.contract.MovieContrast;
import com.bw.movie.di.model.MovieMolde;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: $description$ 类（或接口）是
 * @Author: yuhua
 * @Date: $date$
 */
public class MoviePersenter<T> implements MovieContrast.Persenters {
    MovieMolde movieMolde;
    T tt;

    public MoviePersenter(T t) {
        movieMolde=new MovieMolde();
        tt=t;
    }

    @Override
    public void toView(int userld, String sesionld, int page,int count) {
        movieMolde.setMyCallBack(new MovieMolde.MyCallBack() {
            @Override
            public void success(Object o) {
                MovieContrast.Views views= (MovieContrast.Views) tt;
                views.showData((HotBeen) o);
            }

        });

        Map<String,String> map=new HashMap<>();
        map.put("page",page+"");
        map.put("count",count+"");
        movieMolde.hot(userld,sesionld,map);
    }

    @Override
    public void toView1(int userld, String sesionld,int page,int count) {
        movieMolde.setMyCallBack1(new MovieMolde.MyCallBack() {
            @Override
            public void success(Object o) {
                MovieContrast.Views views= (MovieContrast.Views) tt;
                views.showData1((ReplaceBeen) o);
            }

        });

        Map<String,String> map=new HashMap<>();
        map.put("page",page+"");
        map.put("count",count+"");
        movieMolde.replace(userld,sesionld,map);
    }

    @Override
    public void toView2(int userld, String sesionld, int page,int count) {
        movieMolde.setMyCallBack2(new MovieMolde.MyCallBack() {
            @Override
            public void success(Object o) {
                MovieContrast.Views views= (MovieContrast.Views) tt;
                views.showData2((CommingSoonBeen) o);
            }

        });

        Map<String,String> map=new HashMap<>();
        map.put("page",page+"");
        map.put("count",count+"");
        movieMolde.comming(userld,sesionld,map);
    }
}
