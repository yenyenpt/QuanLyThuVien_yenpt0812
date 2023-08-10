package YENPTPH29053fpt.duanmau_ph29053_pty.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.ThongKeDao;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;


public class DoanhThuFragment extends Fragment {
    Button btnTuNgay, btnDenNgay, btnDoanhThu;
    EditText edTuNgay, edDenNgay;
    TextView tvDoanhThu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mYear, mMonth, mDay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        edTuNgay = view.findViewById(R.id.edTuNgay);
        edDenNgay = view.findViewById(R.id.edDenNgay);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu);
        btnTuNgay = view.findViewById(R.id.btnTuNgay);
        btnDenNgay = view.findViewById(R.id.btnDenNgay);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);

        DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                mYear = i;
                mMonth = i1;
                mDay = i2;
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
                edTuNgay.setText( sdf.format(c.getTime()));
            }
        };

        DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                mYear = i;
                mMonth = i1;
                mDay = i2;
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
                edDenNgay.setText( sdf.format(c.getTime()));
            }
        };


        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get( Calendar.YEAR);
                mMonth = c.get( Calendar.MONTH);
                mDay = c.get( Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateTuNgay, mYear, mMonth, mDay);
                d.show();
            }
        });

        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get( Calendar.YEAR);
                mMonth = c.get( Calendar.MONTH);
                mDay = c.get( Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateDenNgay, mYear, mMonth, mDay);
                d.show();
            }
        });

        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                ThongKeDao thongKeDao = new ThongKeDao(getActivity());
                tvDoanhThu.setText( "Doanh thu: "+thongKeDao.getDoanhThu(tuNgay, denNgay) +" VNĐ");
                Log.d("TAG", "onClick: "+tuNgay+" "+ denNgay);
            }
        });
        return view;
    }

}
