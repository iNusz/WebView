# WebView
#### 간단한 버튼을 활용한 웹뷰 만들기
<br/>

## WebView 클라이언트
###### WebView Setting
```java
        // 1. 웹뷰 클라이언트를 지정... (안하면 내장 웹브라우저가 팝업된다)    / http
        webView.setWebViewClient(new WebViewClient());
        // 2. 둘다 세팅할것 : 프로토콜에 따라 클라이언트가 선택되는것으로 파악됨 . .  /  https
        webView.setWebChromeClient(new WebChromeClient());
        // 클라이언트가 지정되지 않으면 내장 웹앱이 실행된다
        loadUrl("naver.com");
```

## loadUrl
###### Url을 불러오는 메소드

```java
private void loadUrl(String url){
        // 문자열의 앞에 프로토콜인 http문자열이 없다면 붙혀준다
        if(!url.startsWith("http")){
            url = "http://" + url;
        }

        // url 호출하기
        webView.loadUrl(url);
    }
```

## onClick
###### 각 버튼의 기능
<br/>
뒤로가기
```java
case R.id.previous : // 뒤로가기
                //cangoback은 마지막까지 다다랐을때 앱이 꺼지지않게하는거
                if(webView.canGoBack()) {
                    webView.goBack();
                }
                break;
```
앞으로가기
```java
case R.id.next : //앞으로가기
                // cangoforward 마지막까지 다다랐을떄 앱이 못가게끔
                if(webView.canGoForward()){
                    webView.goForward();
                }
                break;
```
Url 이동
```java
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
```

## WebView 내의 javascript 활성화
이 기능이 없다면 웹뷰로 들어가도 javascript로 되어있는 부분이 로드가 안될 것이다.
script 설정은 필수적인 기능이다.
```java
// script 사용 설정 (필수) - 페이지내의 javascript가 동작하도록 한다
        webView.getSettings().setJavaScriptEnabled(true);
```

## 권한설정
App내에서 인터넷(네트워크)을 사용하기 위해서는 권한(Permission)이 필요하다. 그 권한을 manifest에서 해줄 수 있다.
```xml
<uses-permission android:name="android.permission.INTERNET"/>
```



## Android Emulator
![WebView.jpg](https://github.com/iNusz/WebView/blob/master/Webview.jpg)
