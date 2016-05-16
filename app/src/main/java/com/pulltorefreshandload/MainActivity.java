package com.pulltorefreshandload;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.hongshi.pullToRefreshAndLoad.View.PullToRefreshLayout;
import com.hongshi.pullToRefreshAndLoad.pullableview.PullableListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.pull_list_view)
    PullableListView mPullListView;
    @Bind(R.id.pull_layout)
    PullToRefreshLayout mPullLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //设置能否上拉默认为true
        mPullListView.setCanPullUp(true);
        //设置能否下拉默认为true
        mPullListView.setCanPullDown(true);

        mPullListView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,
                new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));

        //设置下拉刷新
        mPullLayout.setPull_to_refresh("my下拉刷新");
        //设置释放立即刷新
        mPullLayout.setRelease_to_refresh("my释放立即刷新");
        //设置正在刷新...
        mPullLayout.setRefreshing("my正在刷新...");
        //设置刷新成功
        mPullLayout.setRefresh_succeed("my刷新成功");
        //设置刷新失败
        mPullLayout.setRefresh_fail("my刷新失败");
        //设置上拉加载更多
        mPullLayout.setPullup_to_load("my上拉加载更多");
        //设置释放立即加载
        mPullLayout.setRelease_to_load("my释放立即加载");
        //设置正在加载...
        mPullLayout.setLoading("my正在加载...");
        //设置加载成功
        mPullLayout.setLoad_succeed("my加载成功");
        //设置加载失败
        mPullLayout.setLoad_fail("my加载失败");
        //设置刷新背景色
        mPullLayout.setLoad_more_background(R.color.red);
        //设置加载更多背景色
        mPullLayout.setRefresh_background(R.color.blue);
        //设置刷新字体颜色
        mPullLayout.setRefresh_text_color(R.color.white);
        //设置加载更多字体颜色
        mPullLayout.setLoad_text_color(R.color.white);

        //自动下拉
        mPullLayout.autoRefresh();
        //自动上拉
        mPullLayout.autoLoad();
        //设置调用此方法监听下拉上拉
        mPullLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            //下拉刷新时调用
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                new RefreshTask().execute();
            }

            //上拉加载时调用
            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                new LoadTask().execute();
            }
        });
    }

    class RefreshTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "onRefresh", Toast.LENGTH_SHORT).show();
            //刷新结束后调用此方法，成功传PullToRefreshLayout.SUCCEED；失败传PullToRefreshLayout.FAIL
            mPullLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
        }
    }

    class LoadTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "onLoad", Toast.LENGTH_SHORT).show();
            //加载结束后调用此方法，成功传PullToRefreshLayout.SUCCEED；失败传PullToRefreshLayout.FAIL
            mPullLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
        }
    }
}
