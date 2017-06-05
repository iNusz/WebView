package com.seunghoshin.android.WebView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    WebView webView;
    Button go, next, previous;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        go = (Button) findViewById(R.id.go);
        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);
        editText = (EditText) findViewById(R.id.editText);

        previous.setOnClickListener(this);
        next.setOnClickListener(this);
        go.setOnClickListener(this);

        // script 사용 설정 (필수) - 페이지내의 javascript가 동작하도록 한다
        webView.getSettings().setJavaScriptEnabled(true);

        // 1. 웹뷰 클라이언트를 지정... (안하면 내장 웹브라우저가 팝업된다)    / http
        webView.setWebViewClient(new WebViewClient());
        // 2. 둘다 세팅할것 : 프로토콜에 따라 클라이언트가 선택되는것으로 파악됨 . .  /  https
        webView.setWebChromeClient(new WebChromeClient());
        // 클라이언트가 지정되지 않으면 내장 웹앱이 실행된다
        // ------------------------------------------------------

        loadUrl("naver.com");
    }

    private void loadUrl(String url){
        // 문자열의 앞에 프로토콜인 http문자열이 없다면 붙혀준다
        if(!url.startsWith("http")){
            url = "http://" + url;
        }

        // url 호출하기
        webView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.previous : // 뒤로가기
                //cangoback은 마지막까지 다다랐을때 앱이 꺼지지않게하는거
                if(webView.canGoBack()) {
                    webView.goBack();
                }
                break;
            case R.id.next :
                //앞으로가기
                // cangoforward 마지막까지 다다랐을떄 앱이 못가게끔
                if(webView.canGoForward()){
                    webView.goForward();
                }
                break;
            case R.id.go : // url 이동
                String url = editText.getText().toString();
                if(!"".equals(url)) { // 공백이 아닐경우 처리
                    // 문자열이 url 패턴일때만
                    if(url.matches("^(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")) {
                        loadUrl(url);
                    }else{
                        Toast.makeText(this, "url이 잘못되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
