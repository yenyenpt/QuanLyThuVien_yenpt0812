package YENPTPH29053fpt.duanmau_ph29053_pty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.ThuThuDao;

public class ManHinhLogin extends AppCompatActivity {
    TextInputLayout edTenDangNhap, edMatKhau;
    Button btnDangNhap, btnHuy;
    CheckBox chkLuu;
    ThuThuDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_login);
        edTenDangNhap = findViewById(R.id.edTenDangNhap);
        edMatKhau = findViewById(R.id.edMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnHuy = findViewById(R.id.btnHuy);
        chkLuu = findViewById(R.id.chkLuu);

        dao = new ThuThuDao(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edTenDangNhap.getEditText().setText( pref.getString("USERNAME", ""));
        edMatKhau.getEditText().setText( pref.getString("PASSWORD", ""));
        chkLuu.setChecked( pref.getBoolean("REMEMBER", false));

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edTenDangNhap.getEditText().setText("");
                edMatKhau.getEditText().setText("");
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenDangNhap = edTenDangNhap.getEditText().getText().toString();
                String matKhau = edMatKhau.getEditText().getText().toString();
                if (tenDangNhap.isEmpty() ){
                    edTenDangNhap.setError("Tên đăng nhập không được để trống");
                }else if (matKhau.isEmpty() ){
                    edMatKhau.setError("Mật khẩu không được để trống");
                }else{
                    if ( (tenDangNhap.equals("admin")&& matKhau.equals("admin") ) || dao.checkLogin(tenDangNhap, matKhau) >0){
                        Toast.makeText(ManHinhLogin.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        rememberUser(tenDangNhap, matKhau, chkLuu.isChecked() );
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("user", tenDangNhap);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(ManHinhLogin.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void rememberUser(String tenDangNhap, String matKhau, boolean checked) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if ( !checked ){
            //xoa tinh trang luu truoc do
            editor.clear();
        }else {
            //luu du lieu
            editor.putString("USERNAME", tenDangNhap);
            editor.putString("PASSWORD", matKhau);
            editor.putBoolean("REMEMBER", checked);
        }
        //luu lai toan bo
        editor.commit();
    }
}