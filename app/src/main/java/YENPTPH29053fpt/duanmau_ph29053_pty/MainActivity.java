package YENPTPH29053fpt.duanmau_ph29053_pty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.ThuThuDao;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.ThuThu;
import YENPTPH29053fpt.duanmau_ph29053_pty.fragment.DoanhThuFragment;
import YENPTPH29053fpt.duanmau_ph29053_pty.fragment.DoiMatKhauFragment;
import YENPTPH29053fpt.duanmau_ph29053_pty.fragment.LoaiSachFragment;
import YENPTPH29053fpt.duanmau_ph29053_pty.fragment.PhieuMuonFragment;
import YENPTPH29053fpt.duanmau_ph29053_pty.fragment.SachFragment;
import YENPTPH29053fpt.duanmau_ph29053_pty.fragment.ThanhVienFragment;
import YENPTPH29053fpt.duanmau_ph29053_pty.fragment.ThemNguoiDungFragment;
import YENPTPH29053fpt.duanmau_ph29053_pty.fragment.Top10Fragment;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    TextView tvWelcome;
    View mHeaderView;
    ThuThuDao dao;

    String username ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this;
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);


        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setHomeAsUpIndicator(R.drawable.ic_menu);
        bar.setDisplayHomeAsUpEnabled(true);

        //dùng fragment_phieuMuon làm home
        FragmentManager manager = getSupportFragmentManager();
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
        manager.beginTransaction().replace(R.id.flContent, phieuMuonFragment).commit();

        //show user lên header
        mHeaderView = navigationView.getHeaderView(0);
        tvWelcome = mHeaderView.findViewById(R.id.tvWelcome);
        Intent intent = getIntent();
        String tenDangNhap = intent.getStringExtra("user");

        dao = new ThuThuDao(context);
        ThuThu obj = dao.getID(tenDangNhap);
        username = obj.hoTen;
        tvWelcome.setText("Welcome "+username+"!");

        if (tenDangNhap.equalsIgnoreCase("admin")){
            tvWelcome.setText("Welcome admin!");
            navigationView.getMenu().findItem(R.id.nav_ThemNguoiDung).setVisible(true);
        }

        //dieu huong navigation
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.nav_PhieuMuon:
                        setTitle("Quản lý phiếu mượn");
                        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                        manager.beginTransaction().replace(R.id.flContent, phieuMuonFragment).commit();
                        break;
                    case R.id.nav_LoaiSach:
                        setTitle("Quản lý loại sách");
                        LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                        manager.beginTransaction().replace(R.id.flContent, loaiSachFragment).commit();
                        break;
                    case R.id.nav_Sach:
                        setTitle("Quản lý sách");
                        SachFragment sachFragment = new SachFragment();
                        manager.beginTransaction().replace(R.id.flContent, sachFragment).commit();
                        break;
                    case R.id.nav_ThanhVien:
                        setTitle("Quản lý thành viên");
                        ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                        manager.beginTransaction().replace(R.id.flContent, thanhVienFragment).commit();
                        break;
                    case R.id.nav_Top10:
                        setTitle("10 sách mượn nhiều nhất");
                        Top10Fragment top10Fragment = new Top10Fragment();
                        manager.beginTransaction().replace(R.id.flContent, top10Fragment).commit();
                        break;
                    case R.id.nav_DoanhThu:
                        setTitle("Doanh thu");
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction().replace(R.id.flContent, doanhThuFragment).commit();
                        break;
                    case R.id.nav_ThemNguoiDung:
                        setTitle("Thêm người dùng");
                        ThemNguoiDungFragment themNguoiDungFragment = new ThemNguoiDungFragment();
                        manager.beginTransaction().replace(R.id.flContent, themNguoiDungFragment).commit();
                        break;
                    case R.id.nav_DoiMatKhau:
                        setTitle("Đổi mật khẩu");
                        DoiMatKhauFragment doiMatKhauFragment = new DoiMatKhauFragment();
                        manager.beginTransaction().replace(R.id.flContent, doiMatKhauFragment).commit();
                        break;
                    case R.id.nav_DangXuat:
                        Intent intent1 = new Intent(MainActivity.this, ManHinhLogin.class);
                        startActivity(intent1);
                        finish();
                        break;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    public String getTenDangNhap(){
        return username;
    }
    }
