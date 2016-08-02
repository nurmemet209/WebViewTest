package com.example.nurmemet.webviewtest;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private float mScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.setWebChromeClient(new WebChromeClient() {

        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(MainActivity.this, "onReceiveError", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
                super.onScaleChanged(view, oldScale, newScale);
                mScale = newScale;
            }

            /**
             * 可以拦截到所有的网页中资源请求，比如加载JS，图片以及Ajax请求等等
             * @param view
             * @param request
             * @return
             */
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }

            /**
             * 可以拦截超链接请求，页面跳转
             * @param view
             * @param url
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl("http://v.youkupppppppppp.com/v_show/id_XMTY2NzE4NjQ3Ng==.html?from=y1.3-idx-beta-1519-23042.223465.1-1");
        //mWebView.loadUrl("http://baidu.com");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.stopLoading();
        mWebView.removeAllViews();
        mWebView.destroy();
    }


    //cookie是自动清楚的，如果自己要手动清楚可调用下面的方法
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void clearCookie() {
        CookieManager.getInstance().removeSessionCookies(null);
    }

    /**
     * 清楚缓存
     */
    private void clearCoach() {
        mWebView.clearCache(true);
        mWebView.clearHistory();
        ;
    }

    /**
     * 是否滚动到底部
     *
     * @return
     */
    private boolean isScrooll2Bottom() {
        //getScrollY getScrollY()方法返回的是当前可见区域的顶端距整个页面顶端的距离,也就是当前内容滚动的距离.
        //getHeight 返回当前WebView 这个容器的高度
        //getContentHeight 返回的是整个html 的高度,但并不等同于当前整个页面的高度,因为WebView 有缩放功能, 所以当前整个页面的高度实际上应该是原始html 的高度再乘上缩放比例. 因此,更正后的结果,准确的判断方法应该是
        if (mWebView.getContentHeight() * mScale == mWebView.getHeight() + mWebView.getScrollY()) {
            return true;
        }
        return false;
    }

    /**
     * 是不是顶部
     *
     * @return
     */
    private boolean isScroolTop() {
        if (mWebView.getScrollY() == 0) {
            return true;
        }
        return false;
    }
}
