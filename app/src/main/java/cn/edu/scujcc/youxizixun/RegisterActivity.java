package cn.edu.scujcc.youxizixun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "Game";
    private TextInputLayout birthdayInput;
    private Button registerButton;
    private UserLab lab = UserLab.getInstance();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg != null) {
                switch (msg.what) {
                    case UserLab.USER_REGISTER_SUCCESS:
                        Toast.makeText(RegisterActivity.this, "注册成功！欢迎你的加入！", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    case UserLab.USER_REGISTER_FAIL:
                        Toast.makeText(RegisterActivity.this, "注册失败！请稍候再试。", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        birthdayInput = findViewById(R.id.r_birthday);
        registerButton = findViewById(R.id.button2);
        registerButton.setOnClickListener(v -> {
            register();
        });

        // new Builder()
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        //告诉builder我们想要的效果
        builder.setTitleText(R.string.birthday_title);
        MaterialDatePicker<Long> picker = builder.build();
        //操作日历

        //日历点击“确定”后的处理
        picker.addOnPositiveButtonClickListener(s -> {
            Log.d(TAG, "日历的结果是：" + s);
            Log.d(TAG, "标题是：" + picker.getHeaderText());
            birthdayInput.getEditText().setText(picker.getHeaderText());

        });

        birthdayInput.setEndIconOnClickListener(v -> {
            //弹出日历选择框
            Log.d(TAG, "生日图标被点击了！");
            picker.show(getSupportFragmentManager(), picker.toString());
        });
    }
    private void register() {
        User u = new User();
        //获得用户名
        TextInputLayout usernameInput = findViewById(R.id.r_username);
        Editable username = usernameInput.getEditText().getText();
        u.setUsername(username != null ? username.toString() : "");


        //获得手机号
        TextInputLayout phoneInput = findViewById(R.id.r_phone);
        Editable phone = phoneInput.getEditText().getText();
        u.setPhone(phone != null ? phone.toString() : "");

        //获得性别
        RadioGroup genderGroup = findViewById(R.id.r_gender);
        int gender = genderGroup.getCheckedRadioButtonId();
        switch (gender) {
            case R.id.r_male:
                u.setGender("男");
                break;
            case R.id.r_female:
                u.setGender("女");
                break;
            default:
                u.setGender("保密");
        }


        //把u发送到服务器
        lab.register(u, handler);
    }
}
