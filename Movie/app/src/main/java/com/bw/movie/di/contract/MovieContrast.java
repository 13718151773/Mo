package com.bw.movie.di.contract;
//类注释设置模板


import com.bw.movie.data.bean.CommingSoonBeen;
import com.bw.movie.data.bean.HotBeen;
import com.bw.movie.data.bean.ReplaceBeen;

import java.util.Map;

/**
 * @Description: $description$ 类（或接口）是
 * @Author: yuhua
 * @Date: $date$
 */
public interface MovieContrast {
    interface Views{
        void showData(HotBeen hotBeen);
        void showData1(ReplaceBeen replaceBeen);
        void showData2(CommingSoonBeen commingSoonBeen);
    }

    interface Persenters{
        void toView(int userld, String sesionld, int page, int count);

        void toView1(int userld, String sesionld, int page, int count);

        void toView2(int userld, String sesionld, int page, int count);

    }

}
