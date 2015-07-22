package com.qq.lionel.wan;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private  int num1,num2,num3,num4;

    public ImageView image1;
    public ImageView image2;
    public ImageView image3;
    public ImageView image4;

    public Button chutiBtn;
    public Button fenxBtn;
    public TextView card1;
    public TextView card2;
    public TextView card3;
    public TextView card4;

    public int[] m_string;

    private static final String APP_ID = "wxdd2d21c02cb4fffe";
    private IWXAPI api;
    private void regToWx(){
        api = WXAPIFactory.createWXAPI(this,APP_ID,true);
        api.registerApp(APP_ID);
    }

    public void OnWxInit(){
        regToWx();
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public void OnInit() {
        m_string = new int[4];
        m_string[0] = R.drawable.card1;
        m_string[1] = R.drawable.card2;
        m_string[2] = R.drawable.card3;
        m_string[3] = R.drawable.card4;
        image1 = (ImageView)findViewById(R.id.imageView);
        image2 = (ImageView)findViewById(R.id.imageView2);
        image3 = (ImageView)findViewById(R.id.imageView3);
        image4 = (ImageView)findViewById(R.id.imageView4);
        chutiBtn = (Button)findViewById(R.id.button);
        fenxBtn = (Button)findViewById(R.id.button2);
        card1 = (TextView)findViewById(R.id.text1);
        //card1.setText(String.valueOf(7));
        card2 = (TextView)findViewById(R.id.text2);
        //card2.setText(String.valueOf(1));
        card3 = (TextView)findViewById(R.id.text3);
        //card3.setText(String.valueOf(1));
        card4 = (TextView)findViewById(R.id.text4);
        //card4.setText(String.valueOf(7));
        Random random = new Random();
        num1 = Math.abs(random.nextInt())%9 +1;
        card1.setText(String.valueOf(num1));
        ((TextView)findViewById(R.id.text11)).setText(String.valueOf(num1));
        num2 = Math.abs(random.nextInt())%9 +1;
        card2.setText(String.valueOf(num2));
        ((TextView)findViewById(R.id.text22)).setText(String.valueOf(num2));
        num3 = Math.abs(random.nextInt())%9 +1;
        card3.setText(String.valueOf(num3));
        ((TextView)findViewById(R.id.text33)).setText(String.valueOf(num3));
        num4 = Math.abs(random.nextInt())%9 +1;
        card4.setText(String.valueOf(num4));
        ((TextView)findViewById(R.id.text44)).setText(String.valueOf(num4));
        fenxBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              //  startActivity(new Intent(MainActivity.this,WXEntryActivity.class));


                String text = "我算不出来啦，谁来帮帮忙啦，帮忙不约哦\n"+num1+","+num2+","+num3+","+num4;
                        //"@string/hello_world"+"?\n "+num1+","+num2+","+num3+","+num4;
                if (text == null || text.length() == 0) {
                    return;
                }

                //
                WXTextObject textObj = new WXTextObject();
                textObj.text = text;

                //
                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = textObj;

                // msg.title =
                msg.description = text;

                //
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("text"); //
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;
                //req.scene = isTimelineCb.isChecked() ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;

                //
                api.sendReq(req);
                //finish();

            }
        });
        chutiBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Random random = new Random();
                int num = Math.abs(random.nextInt())%9 +1;
                int tunum = Math.abs(random.nextInt())%4;
                image1.setImageResource(m_string[tunum]);
                card1.setText(String.valueOf(num));
                num = Math.abs(random.nextInt())%9 +1;
                tunum = Math.abs(random.nextInt())%4;
                image2.setImageResource(m_string[tunum]);
                card2.setText(String.valueOf(num));
                num = Math.abs(random.nextInt())%9 +1;
                tunum = Math.abs(random.nextInt())%4;
                image3.setImageResource(m_string[tunum]);
                card3.setText(String.valueOf(num));
                num = Math.abs(random.nextInt())%9 +1;
                tunum = Math.abs(random.nextInt())%4;
                image4.setImageResource(m_string[tunum]);
                card4.setText(String.valueOf(num));
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnInit();
        OnWxInit();
    }

    @Override
    public void onResume(){
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
