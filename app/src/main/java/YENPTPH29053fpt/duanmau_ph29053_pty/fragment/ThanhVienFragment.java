package YENPTPH29053fpt.duanmau_ph29053_pty.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.ThanhVienDao;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.ThanhVien;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;
import YENPTPH29053fpt.duanmau_ph29053_pty.adapter.ThanhVienAdapter;


public class ThanhVienFragment extends Fragment {
    ListView lv;
    ArrayList<ThanhVien> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnLuu, btnHuy;

    static ThanhVienDao dao;
    ThanhVienAdapter adapter;
    ThanhVien item;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        lv = view.findViewById(R.id.lvThanhVien);
        fab = view.findViewById(R.id.fab_add_ThanhVien);
        dao = new ThanhVienDao(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(getActivity(), 1);//1 update
                return false;
            }
        });
        return view;
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_thanh_vien);

        edMaTV = dialog.findViewById(R.id.edMaTV);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        btnLuu = dialog.findViewById(R.id.btnLuuTV);
        btnHuy = dialog.findViewById(R.id.btnHuyTV);

        //type = 0 là insert, 1 là update
        edMaTV.setEnabled(false);
        if (type != 0){
            edMaTV.setText(String.valueOf(item.maTV));
            edTenTV.setText( item.hoTen);
            edNamSinh.setText( item.namSinh);
        }
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new ThanhVien();
                item.hoTen = edTenTV.getText().toString();
                item.namSinh = edNamSinh.getText().toString();
                if (validate() > 0){
                    if (type == 0){ //insert
                        if ( dao.insertThanhVien(item) >0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }else { //update
                        item.maTV = Integer.parseInt( edMaTV.getText().toString());
                        if (dao.updateThanhVien(item) >0){
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

    public void xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn muốn xóa?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dao.deleteThanhVien(id) > 0){
                    capNhatLv();
                    Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }


                dialogInterface.cancel();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    void capNhatLv(){
        list = (ArrayList<ThanhVien>) dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(), this, list);
        lv.setAdapter(adapter);
    }

    public int validate(){
        int check=1;
        if ( edTenTV.getText().length() ==0 || edNamSinh.getText().length() ==0){
            Toast.makeText(getContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}
