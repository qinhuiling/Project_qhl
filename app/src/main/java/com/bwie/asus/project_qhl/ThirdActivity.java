package com.bwie.asus.project_qhl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.asus.project_qhl.adapter.LeftListViewAdapter;
import com.bwie.asus.project_qhl.bean.LeftListViewBean;
import com.bwie.asus.project_qhl.bean.TabLayoutBean;
import com.bwie.asus.project_qhl.fragment.Fragment01;
import com.example.city_picker.CityListActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;

    private ImageView head;
    private ImageView right;
    private TabLayout tab;
    private ImageView pindao;
    private ViewPager vp;
    private View view;
    private TextView left_more;
    private ListView left_lv;

    private List<TabLayoutBean> tablist;
    int a = 0;

    private String path1 = "http://ic.snssdk.com/2/article/v25/stream/?count=20&min_behot_time=1457659690&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1457672153&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6";
    private String path2 = "http://ic.snssdk.com/2/article/v25/stream/?category=news_entertainment&count=20&min_behot_time=1455522338&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private String path3 = "http://ic.snssdk.com/2/article/v25/stream/?category=news_local&count=20&min_behot_time=1455521226&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455521401&loc_mode=5&user_city=%E5%8C%97%E4%BA%AC&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private String path4 = "http://ic.snssdk.com/2/article/v25/stream/?category=news_local&count=20&min_behot_time=1455521226&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455521401&loc_mode=5&user_city=%E5%8C%97%E4%BA%AC&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private String path5 = "http://ic.snssdk.com/2/article/v25/stream/?category=news_health&count=20&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455524092&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private String path6 = "http://ic.snssdk.com/2/article/v25/stream/?category=news_society&count=20&min_behot_time=1455521720&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522107&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private String path7 = "http://ic.snssdk.com/2/article/v25/stream/?category=news_entertainment&count=20&min_behot_time=1455522338&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private String path8 = "http://ic.snssdk.com/2/article/v25/stream/?category=news_tech&count=20&min_behot_time=1455522427&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private String path9 = "http://ic.snssdk.com/2/article/v25/stream/?category=news_car&count=20&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private String path10 = "http://ic.snssdk.com/2/article/v25/stream/?category=news_sports&count=20&min_behot_time=1455522629&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private String path11 = "http://ic.snssdk.com/2/article/v25/stream/?category=news_finance&count=20&min_behot_time=1455522899&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455523440&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private String path12 = "http://ic.snssdk.com/2/article/v25/stream/?category=news_military&count=20&min_behot_time=1455522991&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455523440&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private String path13 = "http://ic.snssdk.com/2/article/v25/stream/?category=news_world&count=20&min_behot_time=1455523059&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455523440&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private String path14 = "http://ic.snssdk.com/2/article/v25/stream/?count=20&min_behot_time=1455521444&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82" + "&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455521401&loc_mode=5&la" + "c=4527&cid=28883&iid=3642583580&d" + "evice_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&" + "version_code=460&device_platform=android&d" + "evice_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private String path15 = "http://ic.snssdk.com/2/article/v25/stream/?category=news_health&count=20&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455524092&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private String path16 = "http://ic.snssdk.com/2/article/v25/stream/?category=news_military&count=20&min_behot_time=1455522991&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455523440&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";

    private String[] datas = {"城市列表", "语音", "扫一扫", "文件助手", "消息管理器"};
    private PopupWindow window;
    private ListView lsvMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

//        if (NetWork()) {
//        } else {
//            show();
//        }

            getSupportActionBar().hide();

            //传入参数APPID和全局Context上下文
            mTencent = Tencent.createInstance(APP_ID, ThirdActivity.this.getApplicationContext());

            //控件方法
            initView();
            //侧滑方法
            initData();
            initTab();
            //频道管理
            initPinDao();
            //左侧滑的listview
            initLeftSlidingMenu();
            initLeftMore();
            initPop();

    }

    private void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("标题");
        builder.setMessage("检查网络状态");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
            }
        });
        builder.show();
    }

    private boolean NetWork() {
        ConnectivityManager manager = (ConnectivityManager) ThirdActivity.this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null || !info.isAvailable()) {
            return false;
        }
        return true;
    }

    private void initPop() {

        right.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //构建一个popupwindow的布局
                View popupView = ThirdActivity.this.getLayoutInflater().inflate(R.layout.popitem, null);

                // 为了演示效果，简单的设置了一些数据，实际中大家自己设置数据即可，相信大家都会。
                lsvMore = (ListView) popupView.findViewById(R.id.lsvMore);
                lsvMore.setAdapter(new ArrayAdapter<String>(ThirdActivity.this, android.R.layout.simple_list_item_1, datas));

                // 创建PopupWindow对象，指定宽度和高度
                window = new PopupWindow(popupView, 250, 370);
                // 设置背景颜色
                window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
                // 设置可以获取焦点
                window.setFocusable(true);
                // 设置可以触摸弹出框以外的区域
                window.setOutsideTouchable(true);
                // 更新popupwindow的状态
                window.update();
                // 以下拉的方式显示，并且可以设置显示的位置
                window.showAsDropDown(right, 0, 20);

                lsvMore.setAdapter(new PopAdapter());
                lsvMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String data = datas[i];
                        if (data.equals("城市列表")) {
                            CityListActivity.startCityActivityForResult(ThirdActivity.this);
                        }
                    }
                });

            }
        });

    }

    class PopAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = View.inflate(ThirdActivity.this, R.layout.item_chengshi, null);
            TextView tv_chengshi = view.findViewById(R.id.tv_chengshi);
            tv_chengshi.setText(datas[i]);
            return view;
        }
    }

    private void initPinDao() {
        pindao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThirdActivity.this, PinDaoActivity.class));
            }
        });
    }

    //日夜间切换模式
    public void yejian(View v) {
        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (mode == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            // blah blah
        }

        recreate();

    }

    //查找控件
    private void initView() {
        //查找控件
        head = (ImageView) findViewById(R.id.head);
        right = (ImageView) findViewById(R.id.right);
        tab = (TabLayout) findViewById(R.id.tab);
        pindao = (ImageView) findViewById(R.id.pindao);
        vp = (ViewPager) findViewById(R.id.vp);

        view = View.inflate(this, R.layout.leftslidingmenu, null);
        left_more = view.findViewById(R.id.left_more);
        left_lv = view.findViewById(R.id.left_lv);

    }

    public void phone(View view) {
        startActivity(new Intent(ThirdActivity.this, NoteActivity.class));
    }

    public void set(View view) {
        startActivity(new Intent(ThirdActivity.this, SetActivity.class));
    }

    //左侧滑
    private void initData() {
        //实例化SlidingMenu
        final SlidingMenu slidingMenu = new SlidingMenu(this);
        //左侧拉出
        slidingMenu.setMode(SlidingMenu.LEFT);
        //添加布局
        slidingMenu.setMenu(view);
        //设置拉出宽度
        slidingMenu.setBehindOffset(100);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //头部点击侧滑
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenu.toggle();
            }
        });

    }

    private void initTab() {
        //设置Tab滚动
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        //设置Tab和ViewPager联动
        tab.setupWithViewPager(vp);

        if (a == 0) {
            tablist = new ArrayList<>();
            tablist.add(new TabLayoutBean("推荐", path1));

            tablist.add(new TabLayoutBean("热点", path2));
            tablist.add(new TabLayoutBean("本地", path3));
            tablist.add(new TabLayoutBean("视频", path4));
            tablist.add(new TabLayoutBean("社会", path5));
            tablist.add(new TabLayoutBean("娱乐", path6));
            tablist.add(new TabLayoutBean("科技", path7));
            tablist.add(new TabLayoutBean("汽车", path8));
            tablist.add(new TabLayoutBean("体育", path9));
            tablist.add(new TabLayoutBean("财经", path10));
            tablist.add(new TabLayoutBean("军事", path11));
            tablist.add(new TabLayoutBean("国际", path12));
            tablist.add(new TabLayoutBean("段子", path13));
            tablist.add(new TabLayoutBean("趣图", path14));
            tablist.add(new TabLayoutBean("健康", path15));
            tablist.add(new TabLayoutBean("美女", path16));

//            tablist.add(new TabLayoutBean("热点", path1));
//            tablist.add(new TabLayoutBean("本地", path1));
//            tablist.add(new TabLayoutBean("视频", path1));
//            tablist.add(new TabLayoutBean("社会", path1));
//            tablist.add(new TabLayoutBean("娱乐", path1));
//            tablist.add(new TabLayoutBean("科技", path1));
//            tablist.add(new TabLayoutBean("汽车", path1));
//            tablist.add(new TabLayoutBean("体育", path1));
//            tablist.add(new TabLayoutBean("财经", path1));
//            tablist.add(new TabLayoutBean("军事", path1));
//            tablist.add(new TabLayoutBean("国际", path1));
//            tablist.add(new TabLayoutBean("段子", path1));
//            tablist.add(new TabLayoutBean("趣图", path1));
//            tablist.add(new TabLayoutBean("健康", path1));
//            tablist.add(new TabLayoutBean("美女", path1));
            a++;

            for (int i = 0; i < tablist.size(); i++) {
                tab.addTab(tab.newTab().setText(tablist.get(i).getTitle()));
            }

            vp.setAdapter(new MyTabAdapter(getSupportFragmentManager()));
            vp.setOffscreenPageLimit(16);

        }
    }

    class MyTabAdapter extends FragmentPagerAdapter {

        public MyTabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment01.newInstance(tablist.get(position).getPath());
        }

        @Override
        public int getCount() {
            return tablist.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tablist.get(position).getTitle();
        }

    }

    //更多登录方式
    private void initLeftMore() {
        left_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThirdActivity.this, MoreActivity.class));
            }
        });
    }

    //左侧滑的listview
    private void initLeftSlidingMenu() {
        List<LeftListViewBean> leftlist = new ArrayList<>();
        LeftListViewBean bean1 = new LeftListViewBean(R.mipmap.p1, "好友动态");
        LeftListViewBean bean2 = new LeftListViewBean(R.mipmap.p2, "我的话题");
        LeftListViewBean bean3 = new LeftListViewBean(R.mipmap.p3, "收藏");
        LeftListViewBean bean4 = new LeftListViewBean(R.mipmap.p4, "活动");
        LeftListViewBean bean5 = new LeftListViewBean(R.mipmap.p5, "商城");
        leftlist.add(bean1);
        leftlist.add(bean2);
        leftlist.add(bean3);
        leftlist.add(bean4);
        leftlist.add(bean5);
        LeftListViewAdapter leftAdapter = new LeftListViewAdapter(this, leftlist);
        left_lv.setAdapter(leftAdapter);
    }


    public void buttonLogin(View v) {
        /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
         官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
         第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
        mIUiListener = new BaseUiListener();
        //all表示获取所有权限
        mTencent.login(ThirdActivity.this, "all", mIUiListener);
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(ThirdActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG, "登录成功" + response.toString());

                        if (response == null) {
                            return;
                        }
                        JSONObject obj = (JSONObject) response;
                        try {
                            String img = obj.getString("figureurl_1");
                            DisplayImageOptions options = new DisplayImageOptions.Builder()
                                    .displayer(new CircleBitmapDisplayer())
                                    .build();
                            ImageLoader.getInstance().displayImage(img, head, options);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG, "登录失败" + uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(ThirdActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(ThirdActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
