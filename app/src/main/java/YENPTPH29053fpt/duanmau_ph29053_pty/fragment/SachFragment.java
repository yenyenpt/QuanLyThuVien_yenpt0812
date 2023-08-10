package YENPTPH29053fpt.duanmau_ph29053_pty.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.LoaiSachDao;
import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.SachDao;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.LoaiSach;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.Sach;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;
import YENPTPH29053fpt.duanmau_ph29053_pty.adapter.LoaiSachSpinnerAdapter;
import YENPTPH29053fpt.duanmau_ph29053_pty.adapter.SachAdapter;


public class SachFragment extends Fragment {
    ListView lv;
    ArrayList<Sach> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach, edTenSach, edGiaThue;
    Spinner spinner;
    Button btnLuu, btnHuy;
    static SachDao dao;
    SachAdapter adapter;
    Sach item;
    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDao loaiSachDao;
    LoaiSach loaiSach;
    int maLoaiSach, position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        lv = view.findViewById(R.id.lvSach);
        fab = view.findViewById(R.id.fab_add_Sach);
        dao = new SachDao(getActivity());
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
                openDialog(getActivity(), 1);//update
                return false;
            }
        });
        return view;
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_sach);

        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edTenSach);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spinner = dialog.findViewById(R.id.spLoaiSach);
        btnHuy = dialog.findViewById(R.id.btnHuySach);
        btnLuu = dialog.findViewById(R.id.btnLuuSach);

        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDao = new LoaiSachDao(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDao.getAll();
        spinnerAdapter = new LoaiSachSpinnerAdapter(context, listLoaiSach);
        spinner.setAdapter(spinnerAdapter);
        //lay maLoaiSach
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach.get(position).maLoai;
                Toast.makeText(context, "Chọn "+listLoaiSach.get(position).tenLoai, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //type = 0 là insert, = 1 là update
        edMaSach.setEnabled(false);
        if (type != 0){
            edMaSach.setText( String.valueOf(item.maSach));
            edTenSach.setText( item.tenSach);
            edGiaThue.setText( String.valueOf(item.giaThue));
            for (int i=0; i<listLoaiSach.size(); i++){
                if (item.maLoai == listLoaiSach.get(i).maLoai ){
                    position= i;
                }
            }
            Log.i("TAG", "posSach: "+position);
            spinner.setSelection(position);
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
                item = new Sach();
                item.tenSach = edTenSach.getText().toString();
                item.giaThue = Integer.parseInt(edGiaThue.getText().toString());
                item.maLoai = maLoaiSach;
                if (validate() > 0){
                    if (type ==0){
                        if (dao.insertSach(item) >0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.maSach = Integer.parseInt( edMaSach.getText().toString());
                        if (dao.updateSach(item) >0){
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
    void capNhatLv(){
        list = (ArrayList<Sach>) dao.getAll();
        adapter = new SachAdapter(getActivity(), this, list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
            if (edTenSach.getText().length() ==0 || edGiaThue.getText().length() == 0){
                Toast.makeText(getContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        return check;
    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn muốn xóa?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.deleteSach(Id);
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
}
