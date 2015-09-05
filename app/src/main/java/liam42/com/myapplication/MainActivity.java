package liam42.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    private ListView mListView;
    private MainAdapter mAdapter;

    private View mHeader;

    private int HEADER_HEIGHT = 220;

    private float mLastOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.list);
        mAdapter = new MainAdapter(this);
        mListView.setAdapter(mAdapter);
        initHeader();
        mListView.setOnScrollListener(this);
    }

    private void initHeader() {
        mHeader = getLayoutInflater().inflate(R.layout.view_header, null);
        mListView.addHeaderView(mHeader);
    }

    private void scrollToPosition(int distance) {
        mListView.smoothScrollBy(dp2Px(HEADER_HEIGHT) + distance, 200);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        float offest = mHeader.getY();
        if (mLastOffset > offest && offest > -200 && offest < -150) {
            scrollToPosition((int) offest);
        }
        mLastOffset = offest;
    }

    private int dp2Px(float dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
