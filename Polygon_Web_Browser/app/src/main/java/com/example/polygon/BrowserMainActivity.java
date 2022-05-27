package com.example.polygon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Browser;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BrowserMainActivity extends AppCompatActivity {

    ImageButton homeButton, tabButton, reloadButton, backwardButton, forwardButton, navBookmark;
    Button searchViewButton01;
    Dialog dialogOne;
    WebView webView;
    SearchView searchView01;
    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    ConstraintLayout browserMainLayout, floatingButtonHolder, shiftButton01Holder;
    LinearLayout homeLayout,tabLayout,reloadLayout,backwardLayout,forwardLayout,topNavLayout;
    Animation blurBackground;
    ProgressBar progressForWebLoad;
    Button floatingActionButton_01, shiftButton01;
    public static final int message_request=1;
    private Bookmark_SQLiteHelper bookmark_sqLiteHelper;
    private History_SQLiteHelper history_sqLiteHelper;
    public Bitmap imageH;
    boolean insertH;

    int bookmarkCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_main);
        dialogOne = new Dialog(BrowserMainActivity.this);

        try {

            Window window = getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.deep_color_03));

            //find by id
//            homeButton = findViewById(R.id.navHome);
            tabButton = findViewById(R.id.navTab);
            reloadButton = findViewById(R.id.navReload);
            backwardButton = findViewById(R.id.navBackward);
            forwardButton = findViewById(R.id.navForward);
            shiftButton01 = findViewById(R.id.shiftButton01);
            floatingActionButton_01 = findViewById(R.id.floatingActionButton_01);
            searchViewButton01 = findViewById(R.id.searchViewButton01);
            webView = findViewById(R.id.webViewMain);
            browserMainLayout = findViewById(R.id.browserMainLayoutID);
            floatingButtonHolder = findViewById(R.id.floatingButtonHolder);
//            homeLayout = findViewById(R.id.navHomeLayout);
            tabLayout = findViewById(R.id.navTabLayout);
            reloadLayout = findViewById(R.id.navReloadLayout);
            backwardLayout = findViewById(R.id.navBackwardLayout);
            shiftButton01Holder = findViewById(R.id.shiftButton01Holder);
            forwardLayout = findViewById(R.id.navForwardLayout);
            topNavLayout = findViewById(R.id.topNavLayout);
            progressForWebLoad = findViewById(R.id.progressForWebLoad);

            navBookmark = findViewById(R.id.navBookmark);

            bookmark_sqLiteHelper = new Bookmark_SQLiteHelper(BrowserMainActivity.this);

            //animation
            blurBackground = AnimationUtils.loadAnimation(this,R.anim.blur_background);

            WebSettings webSet = webView.getSettings();
            webSet.setJavaScriptEnabled(true);

            if(webView.isActivated()){
                String wUrl = webView.getUrl();
                preCheckBookmark(wUrl);
            }


//            SQLite entries
            navBookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bookmark_sqLiteHelper = new Bookmark_SQLiteHelper(BrowserMainActivity.this);
                    bookmark_sqLiteHelper.getWritableDatabase();
                    if(bookmarkCount==0){
                        boolean insert;
                        String title = webView.getTitle();
                        String url = webView.getUrl();
                        Bitmap image = webView.getFavicon();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        image.compress(Bitmap.CompressFormat.PNG, 0, stream);
                        byte[] img= stream.toByteArray();

                        if(!title.equals("") || !url.equals("") || image != null ){
                            insert = bookmark_sqLiteHelper.insertData(title,url,img);
                            if(insert){
                                navBookmark.setImageResource(R.drawable.nav_icon_bookmark_orange_50px);
                                Toast.makeText(BrowserMainActivity.this, "data added", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(BrowserMainActivity.this, "data insert failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(BrowserMainActivity.this, "Cannot add data", Toast.LENGTH_SHORT).show();
                        }

                        bookmarkCount=1;
                    }else{
                        navBookmark.setImageResource(R.drawable.icon_bookmark_black_02_50px);
                        bookmarkCount=0;
                    }

                }
            });


            webView.setWebViewClient(new WebViewClient(){

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);

                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                        history_sqLiteHelper = new History_SQLiteHelper(BrowserMainActivity.this);
                        history_sqLiteHelper.getWritableDatabase();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
                                try {
//                                imageH = webView.getFavicon();
                                String titleH = webView.getTitle();
                                String urlH = webView.getUrl();
                                Date date = Calendar.getInstance().getTime();
                                SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
                                String formattedDate = dFormat.format(date);
//                                Log.d("tag",formattedDate+"<-current date");
//                                Toast.makeText(BrowserMainActivity.this,formattedDate+"<-current date", Toast.LENGTH_SHORT).show();

//                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                                imageH.compress(Bitmap.CompressFormat.PNG, 0, stream);
//                                byte[] imgH = stream.toByteArray();

//                                    final int lnth=imageH.getByteCount();
//                                    ByteBuffer dst= ByteBuffer.allocate(lnth);
//                                    imageH.copyPixelsToBuffer( dst);
//                                    byte[] imgH=dst.array();

//                                if(!titleH.equals("") || !url.equals("") || imageH != null ){
                                if(!titleH.equals("") || !urlH.equals("")){
//                                    insertH = history_sqLiteHelper.insertData(titleH,urlH,imgH);
                                    insertH = history_sqLiteHelper.insertData(titleH,urlH,formattedDate);
                                    if(insertH){
                                        Toast.makeText(BrowserMainActivity.this, "data added", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(BrowserMainActivity.this, "data insert failed", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(BrowserMainActivity.this, "Cannot add data", Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e){
                            Toast.makeText(BrowserMainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
//                        }
//                    },500);


                }
            });







            backwardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(webView.canGoBack()){
                        webView.goBack();
                    }
                }
            });
            forwardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(webView.canGoForward()){
                        webView.goForward();
                    }
                        forwardButton.setImageDrawable(getResources().getDrawable(R.drawable.nav_icon_forward_orange_50px));

                }
            });

//            progressBar command
            progressForWebLoad.setMax(100);
            progressForWebLoad.setProgress(0);

//          getting extra intent values
            Bundle fromPreBrowserMain = getIntent().getExtras();
            if(fromPreBrowserMain!=null) {
                String value = fromPreBrowserMain.getString("url");
                String value02 = fromPreBrowserMain.getString("hotstar");
                String value03 = fromPreBrowserMain.getString("animekisa");
                String value04 = fromPreBrowserMain.getString("voice");
                String popupSearch = fromPreBrowserMain.getString("popupSearch");
                String fullUrl = fromPreBrowserMain.getString("full_Url");
                if (value != null) {
                    webView.loadUrl("https://www." + value + ".com");
                    String webUrl = webView.getUrl();
                    preCheckBookmark(webUrl);
                } else if (value02 != null) {
                    webView.loadUrl("https://www." + value02 + ".com/in");
                    String webUrl = webView.getUrl();
                    preCheckBookmark(webUrl);
                } else if (value03 != null) {
                    webView.loadUrl("https://" + value03 + ".tv");
                    String webUrl = webView.getUrl();
                    preCheckBookmark(webUrl);
                } else if (value04 != null) {
                    webView.loadUrl("https://www.google.com/search?q=" + value04);
                    String webUrl = webView.getUrl();
                    preCheckBookmark(webUrl);
                } else if (popupSearch != null) {
                    if (popupSearch.contains("https://")) {
                        webView.loadUrl(popupSearch);
                        String webUrl = webView.getUrl();
                        preCheckBookmark(webUrl);
                    } else {
                        webView.loadUrl("https://www.google.com/search?q=" + popupSearch);
                        String webUrl = webView.getUrl();
                        preCheckBookmark(webUrl);
                    }
                } else if (fullUrl != null) {
                    webView.loadUrl(fullUrl);
                    String webUrl = webView.getUrl();
                    preCheckBookmark(webUrl);
                } else {
                    Toast.makeText(BrowserMainActivity.this, "oops! did you just forgot to enter your keywords!", Toast.LENGTH_SHORT).show();
                }

//              set web url
//                String storeUrl = webView.getUrl();
//                searchView01.setQuery(storeUrl,false);

//                WebSettings webSet = webView.getSettings();
//                webSet.setJavaScriptEnabled(true);


                webView.setDownloadListener(new DownloadListener() {
                    public void onDownloadStart(String url, String userAgent,
                                                String contentDisposition, String mimetype,
                                                long contentLength) {
                        DownloadManager.Request request = new DownloadManager.Request(
                                Uri.parse(url));

                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Name of your downloadble file goes here, example: Mathematics II ");
                        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        dm.enqueue(request);
                        Toast.makeText(getApplicationContext(), "Downloading File", //To notify the Client that the file is being downloaded
                                Toast.LENGTH_LONG).show();
                    }
                });


                searchViewButton01.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent popupIntent = new Intent(BrowserMainActivity.this, System_SearchBar_Popup_Activity.class);
                        popupIntent.putExtra("currentWebUrl", webView.getUrl());
                        startActivity(popupIntent);
                        overridePendingTransition(R.anim.slide_in_bottom, R.anim.hold);
                    }
                });

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    webView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                        @Override
                        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                                    ConstraintSet constraintSet = new ConstraintSet();
                                    if (scrollY > oldScrollY) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                constraintSet.clone(browserMainLayout);
                                                constraintSet.connect(R.id.webViewMain, ConstraintSet.TOP, R.id.browserMainLayoutID, ConstraintSet.TOP, 0);
                                                constraintSet.applyTo(browserMainLayout);
                                            }
                                        }, 300);
                                        floatingButtonHolder.setVisibility(View.GONE);
//                                Toast.makeText(BrowserMainActivity.this, "scrolled up", Toast.LENGTH_SHORT).show();
                                    } else if (scrollY < oldScrollY) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                constraintSet.clone(browserMainLayout);
                                                constraintSet.connect(R.id.webViewMain, ConstraintSet.TOP, R.id.topNavLayout, ConstraintSet.BOTTOM, 0);
                                                constraintSet.applyTo(browserMainLayout);
                                            }
                                        }, 300);
                                        floatingButtonHolder.setVisibility(View.VISIBLE);
//                                Toast.makeText(BrowserMainActivity.this, "scrolled down", Toast.LENGTH_SHORT).show();
                                    }
                        }
                    });
                }

                try {

                    webView.setWebChromeClient(new WebChromeClient() {
                        @Override
                        public void onProgressChanged(WebView view, int newProgress) {
                            progressForWebLoad.setProgress(newProgress);
                            if (newProgress == 100) {
                                progressForWebLoad.setVisibility(View.INVISIBLE);

                            } else {
                                progressForWebLoad.setVisibility(View.VISIBLE);
                            }

                            super.onProgressChanged(view, newProgress);

//              set web url
//                        String storeUrl = webView.getUrl();
//                        searchView01.setQuery(storeUrl,false);
                            String storeTitle = webView.getTitle();
//                        searchView01.setQuery(storeTitle,false);
                            searchViewButton01.setText(storeTitle);


//              forward backward button
                            if (webView.canGoForward()) {
//                            forwardButton.setImageDrawable(getResources().getDrawable(R.drawable.nav_icon_forward_white_50px));
                                backwardButton.setImageResource(R.drawable.nav_icon_forward_orange_50px);
                            } else {
                                backwardButton.setImageResource(R.drawable.nav_icon_forward_white_50px);
                            }
                            if (webView.canGoBack()) {
                                backwardButton.setImageResource(R.drawable.nav_icon_backward_orange_50px);
                            } else {
                                backwardButton.setImageResource(R.drawable.nav_icon_backward_white_50px);
                            }


                        }
                    });
                }catch (Exception e){
                    Toast.makeText(BrowserMainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

//            webView.setWebViewClient(new WebViewClient());
//            searchView01.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    webView.loadUrl("https://www.google.com/search?q="+searchView01.getQuery());
//
//                    WebSettings webSet = webView.getSettings();
//                    webSet.setJavaScriptEnabled(true);
//
//                    webView.setWebChromeClient(new WebChromeClient(){
//                        @Override
//                        public void onProgressChanged(WebView view, int newProgress) {
//                            progressForWebLoad.setProgress(newProgress);
//                            if(newProgress==100){
//                                progressForWebLoad.setVisibility(View.INVISIBLE);
//                            }else{
//                                progressForWebLoad.setVisibility(View.VISIBLE);
//                            }
//
//                            super.onProgressChanged(view, newProgress);
//
////              set web url
//                            String storeUrl = webView.getUrl();
//                            String storeTitle = webView.getTitle();
////                            searchView01.setQuery(storeUrl,false);
//
////                            setting title to Button
//                            searchViewButton01.setText(storeTitle);
//                        }
//                    });
//
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    return false;
//                }
//            });

//            webView Listeners
//            floatingActionButton_01.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    Toast.makeText(BrowserMainActivity.this, "Pressed for long", Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//            });

//            shiftButton01.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    shiftButton01Holder.setVisibility(View.GONE);
//                    floatingActionButton_01.setVisibility(View.VISIBLE);
//                }
//            });



        onClickListenerForNavButtons(homeButton,homeLayout);
        onClickListenerForNavButtons(tabButton,tabLayout);
        onClickListenerForNavButtons(reloadButton,reloadLayout);
        onClickListenerForNavButtons(backwardButton,backwardLayout);
        onClickListenerForNavButtons(forwardButton,forwardLayout);


        }catch (Exception e){
            Log.d("tag","Exception occurred");
        }

        floatingActionButton_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    Intent popupIntent = new Intent(BrowserMainActivity.this,System_Popup_Activity.class);
                    startActivityForResult(popupIntent,message_request);
                    overridePendingTransition(R.anim.slide_in_bottom,R.anim.hold);
                    floatingButtonHolder.setVisibility(View.GONE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            floatingButtonHolder.setVisibility(View.VISIBLE);
                        }
                    },200);

                }catch (Exception e){
                    Log.d("tag","Exception occurred");
                }
//                Toast.makeText(BrowserMainActivity.this,"popup_Active",Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }

    }

    public void preCheckBookmark(String webUrl){

//            setting the bookmark by checking data of previous bookmarked websites
        bookmark_sqLiteHelper = new Bookmark_SQLiteHelper(BrowserMainActivity.this);
        Cursor cursor = bookmark_sqLiteHelper.getCursor();
        String[] urlDB = new String[cursor.getCount()];
//            Bitmap[] imageDB = new Bitmap[cursor.getCount()];

        if(cursor.getCount()==0){
//            Toast.makeText(BrowserMainActivity.this,"no data is available in the database",Toast.LENGTH_SHORT).show();
        }else {
            int i = cursor.getCount() - 1;
            while (cursor.moveToNext()) {
                urlDB[i] = cursor.getString(1);
//                Toast.makeText(BrowserMainActivity.this,webUrl+","+urlDB[i], Toast.LENGTH_SHORT).show();
                if(webUrl.contains(urlDB[i])){
                    navBookmark.setImageResource(R.drawable.nav_icon_bookmark_orange_50px);
                    break;
                }else{
                    navBookmark.setImageResource(R.drawable.icon_bookmark_black_02_50px);
                }
                i--;
            }
        }
    }

    public void onClickListenerForNavButtons(ImageButton imageButton, LinearLayout linearLayout){
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        linearLayout.setBackgroundResource(R.color.transparent);

                    }
                }, 250);
                linearLayout.setBackgroundResource(R.color.orange_01);
            }
        });

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (message_request == requestCode) {
//            if (resultCode == RESULT_OK) {
//                floatingButtonHolder.setVisibility(View.GONE);
//                Toast.makeText(BrowserMainActivity.this, resultCode, Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}