package YENPTPH29053fpt.duanmau_ph29053_pty.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.PhieuMuonDao;
import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.SachDao;
import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.ThanhVienDao;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.PhieuMuon;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.Sach;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.ThanhVien;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;
import YENPTPH29053fpt.duanmau_ph29053_pty.adapter.PhieuMuonAdapter;
import YENPTPH29053fpt.duanmau_ph29053_pty.adapter.SachSpinnerAdapter;
import YENPTPH29053fpt.duanmau_ph29053_pty.adapter.ThanhVienSpinnerAdapter;


public class PhieuMuonFragment extends Fragment {
    ListView lv;
    ArrayList<PhieuMuon> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spSach;
    TextView tvNgay;
    TextView tvTienThue;
    CheckBox chkTraSach;
    Button btnLuu, btnHuy;
    static PhieuMuonDao dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;

    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDao thanhVienDao;
    ThanhVien thanhVien;
    int maThanhVien;

    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDao sachDao;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;

    @SuppressLint("NewApi")
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lv = view.findViewById(R.id.lvPhieuMuon);
        fab = view.findViewById(R.id.fab_add_PhieuMuon);
        dao = new PhieuMuonDao(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return view;
    }

    @SuppressLint("NewApi")
    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView( R.layout.dialog_phieu_muon);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spMaTV);
        spSach = dialog.findViewById(R.id.spMaSach);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTienThue = dialog.findViewById(R.id.tvTienThue);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnHuy = dialog.findViewById(R.id.btnHuyPM);
        btnLuu = dialog.findViewById(R.id.btnLuuPM);

        thanhVienDao = new ThanhVienDao(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDao.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context, listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);
        //lay maLoaiSach
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).maTV;
                Toast.makeText(context, "Chọn "+listThanhVien.get(position).hoTen, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sachDao = new SachDao(context);
        listSach = new ArrayList<>();
        listSach = (ArrayList<Sach>) sachDao.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context, listSach);
        spSach.setAdapter(sachSpinnerAdapter);
        //lay maSach va tienThue
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).maSach;
                tienThue = listSach.get(position).giaThue;
                Toast.makeText(context, "Chọn "+listSach.get(position).tenSach, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //kiem tra type insert 0 hay update 1
        edMaPM.setEnabled(false);
        if (type !=0 ){
            edMaPM.setText( String.valueOf(item.maPM));
            for (int i=0; i<listThanhVien.size(); i++){
                if (item.maTV == listThanhVien.get(i).maTV){
                    positionTV = i;
                }
            }
            spTV.setSelection(positionTV);

            for (int i=0; i<listSach.size(); i++){
                if (item.maSach == listSach.get(i).maSach){
                    positionSach = i;
                }
            }
            spSach.setSelection(positionSach);
            tvNgay.setText("Ngày thuê: "+sdf.format(item.ngay));

            tvTienThue.setText("Tiền thuê: "+item.tienThue);
            if (item.traSach ==1){
                chkTraSach.setChecked(true);
            }else {
                chkTraSach.setChecked(false);
            }
        }
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new PhieuMuon();
                item.maSach = maSach;
                item.maTV = maThanhVien;
                item.ngay = new Date();
                item.tienThue = tienThue;
                if (chkTraSach.isChecked()){
                    item.traSach=1;
                }else {
                    item.traSach=0;
                }
                if (validate()>0){
                    if (type==0){
                        if (dao.insertPhieuMuon(item) >0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.maPM = Integer.parseInt(edMaPM.getText().toString());
                        if (dao.updatePhieuMuon(item) >0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sửa không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn muốn xóa?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.deletePhieuMuon(Id);
                capNhatLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    void capNhatLv(){
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(), this, list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        return check;
    }
}
