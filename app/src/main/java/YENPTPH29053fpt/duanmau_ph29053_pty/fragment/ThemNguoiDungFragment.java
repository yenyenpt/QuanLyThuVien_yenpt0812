package YENPTPH29053fpt.duanmau_ph29053_pty.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.ThuThuDao;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.ThuThu;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;


public class ThemNguoiDungFragment extends Fragment {
    TextInputLayout edTenDangNhap, edHoTen, edMatKhau, edNhapLaiMatKhau;
    Button btnLuu, btnHuy;
    ThuThuDao dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_nguoi_dung, container, false);
        edTenDangNhap = view.findViewById(R.id.edTenDangNhapTND);
        edHoTen = view.findViewById(R.id.edHoTen);
        edMatKhau = view.findViewById(R.id.edMatKhauTND);
        edNhapLaiMatKhau = view.findViewById(R.id.edNhapLaiMatKhauTND);
        btnHuy = view.findViewById(R.id.btnHuyTND);
        btnLuu = view.findViewById(R.id.btnLuuTND);

        dao = new ThuThuDao(getActivity());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edTenDangNhap.getEditText().setText("");
                edHoTen.getEditText().setText("");
                edMatKhau.getEditText().setText("");
                edNhapLaiMatKhau.getEditText().setText("");
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThuThu obj = new ThuThu();
                obj.maTT = edTenDangNhap.getEditText().getText().toString();
                obj.hoTen = edHoTen.getEditText().getText().toString();
                obj.matKhau = edMatKhau.getEditText().getText().toString();
                if (validate() > 0){
                    if (dao.insertThuThu(obj) >0){
                        Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                        edTenDangNhap.getEditText().setText("");
                        edHoTen.getEditText().setText("");
                        edMatKhau.getEditText().setText("");
                        edNhapLaiMatKhau.getEditText().setText("");
                    }else {
                        Toast.makeText(getActivity(), "Lưu không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    private int validate() {
        int check = 1;
        if (edTenDangNhap.getEditText().getText().length() ==0 ||
            edHoTen.getEditText().getText().length() == 0 ||
            edMatKhau.getEditText().getText().length() == 0 ||
            edNhapLaiMatKhau.getEditText().getText().length() == 0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            String matKhau = edMatKhau.getEditText().getText().toString();
            String matKhauNhapLai = edNhapLaiMatKhau.getEditText().getText().toString();
            if ( !matKhau.equals(matKhauNhapLai) ){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}
