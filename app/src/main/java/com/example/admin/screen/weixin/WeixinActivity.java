package com.example.admin.screen.weixin;

import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.admin.base.SwipeBackActivity;
import com.example.admin.entity.WeixinBean;
import com.example.admin.network.BaseInterceptor;
import com.example.admin.network.NetClient;
import com.example.admin.rxjava.Transformer;
import com.example.admin.screen.R;
import com.example.admin.screen.databinding.ActivityWeixinBinding;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class WeixinActivity extends SwipeBackActivity<ActivityWeixinBinding> implements ViewPager.OnPageChangeListener {

    @BindView(R.id.vp_weixin)
    ViewPager vpWeixin;

    private int pno = 1;
    private int ps = 5;
    private ArrayList<WeixinBean.Content> list = new ArrayList<>();
    private WeinxinPagerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_weixin;
    }

    @Override
    public void initData() {
        adapter = new WeinxinPagerAdapter(this,list);
        vpWeixin.setAdapter(adapter);
        getData();
    }


    private void getData() {
        new NetClient(BaseInterceptor.WEIXIN_TYPE).getService().getWeixin(pno + "", ps + "").compose(Transformer.<WeixinBean>retrofit())
                .subscribe(new Subscriber<WeixinBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(WeixinBean value) {
                        List<WeixinBean.Content> temp = value.getList();
                        list.addAll(temp);
                        adapter.setList(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(WeixinActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void setupView() {

    }

    @Override
    public void bindEvent() {
        vpWeixin.addOnPageChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        vpWeixin.removeOnPageChangeListener(this);
        super.onDestroy();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //划到右侧边缘
        if (position == pno * ps - 1) {
            pno = pno + 1;
            getData();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
