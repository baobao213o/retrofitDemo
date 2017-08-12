package com.example.admin.screen.weixin;

import android.support.v4.view.ViewPager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;


public class WeixinActivity extends SwipeBackActivity<ActivityWeixinBinding> implements ViewPager.OnPageChangeListener {

    @BindView(R.id.vp_weixin)
    ViewPager vpWeixin;

    private int pno = 1;
    private int ps = 5;
    private ArrayList<WebView> views = new ArrayList<>();

    private HashMap<String,WeixinBean.Content> map=new HashMap<>();

    private WeinxinPagerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_weixin;
    }

    @Override
    public void initData() {
        for (int i = 0; i < 3; i++) {
            WebView view = new WebView(WeixinActivity.this);
            view.getSettings().setAppCacheEnabled(true);
            view.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            view.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            views.add(view);
        }
        getData();
    }


    private void getData(){
        new NetClient(BaseInterceptor.WEIXIN_TYPE).getService().getWeixin(pno + "", ps + "").compose(Transformer.<WeixinBean>retrofit())
                .subscribe(new Subscriber<WeixinBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(WeixinBean value) {
                        List<WeixinBean.Content> temp = value.getList();
                        for(WeixinBean.Content bean:temp){
                            if(!map.containsKey(bean.getId())){
                                map.put(bean.getId(),bean);
                            }
                        }
                        for(int i=0;i<views.size();i++){
                            views.get(i).loadUrl(temp.get(i).getUrl());
                        }
                        adapter = new WeinxinPagerAdapter(views,vpWeixin);
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
        if(position==pno*ps-1){
            pno=pno+1;
            getData();
        }else if(position>=views.size()&&position<pno*ps-1){
            
        }
        System.out.println(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
