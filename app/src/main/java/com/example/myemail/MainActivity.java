package com.example.myemail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.utils.EmailUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class MainActivity extends AppCompatActivity {
    EditText email,tittle,content;
    Button sub;
    boolean be=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.emailaddress);
        sub = (Button)findViewById(R.id.submit_email);
        tittle = (EditText) findViewById(R.id.emailtittle);
        content = (EditText) findViewById(R.id.emailtext);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                Pattern pattern = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");//\w表示a-z，A-Z，0-9(\\转义符)
                Matcher matcher = pattern.matcher(mail);
                String t = tittle.getText().toString();
                String c = content.getText().toString();
                boolean b = matcher.matches();
                if (!b) {
                    Toast.makeText(MainActivity.this, "请输入正确的邮箱格式", Toast.LENGTH_SHORT).show();
                } else if ((t.equals("")) || (c.equals(""))) {
                    Toast.makeText(MainActivity.this, "请输入需要发送内容", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //new EmailUtil("363127772@qq.com").sendTextEmail("hishad");
                                Email e = new Email();;
                                e.setToAddress(mail);
                                e.setSubject(t);
                                e.setContent(c);
                                be=EmailUtil.sendMail(e);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    if(be){
                        Toast.makeText(MainActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(MainActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void sendEmail() {
        Log.i("发送邮件", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "363127772@qq.com");
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "您的标题");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "这里是邮件消息");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("邮件发送完成...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "没有安装电子邮件客户端。", Toast.LENGTH_SHORT).show();
        }
    }
}