package YENPTPH29053fpt.duanmau_ph29053_pty.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.PhieuMuon;
import YENPTPH29053fpt.duanmau_ph29053_pty.DbHelper.DbHelper;



public class PhieuMuonDao {
    SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Context context;

    public PhieuMuonDao(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insertPhieuMuon(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("maTV", obj.getMaTV());
        values.put("maSach", obj.getMaSach());
        values.put("tienThue", obj.getTienThue());
        values.put("traSach", obj.getTraSach());
        values.put("ngay", sdf.format(obj.ngay));
        return db.insert("PhieuMuon", null, values);
    }
    public int updatePhieuMuon( PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("maTV", obj.getMaTV());
        values.put("maSach", obj.getMaSach());
        values.put("tienThue", obj.getTienThue());
        values.put("traSach", obj.getTraSach());
        values.put("ngay", sdf.format(obj.ngay));
        return db.update("PhieuMuon", values, "maPM=?", new String[]{ obj.getMaPM()+ ""});
    }
    public int deletePhieuMuon( String id){
        return db.delete("PhieuMuon", "maPM=?", new String[]{ id });
    }

    @SuppressLint("Range")
    private List< PhieuMuon> getData(String sql, String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext() ){
            PhieuMuon obj = new PhieuMuon();
            obj.maPM = Integer.parseInt(c.getString( c.getColumnIndex("maPM")));
            obj.maTT = c.getString( c.getColumnIndex("maTT"));
            obj.maTV = Integer.parseInt(c.getString( c.getColumnIndex("maTV")));
            obj.maSach = Integer.parseInt(c.getString( c.getColumnIndex("maSach")));
            obj.tienThue = Integer.parseInt(c.getString( c.getColumnIndex("tienThue")));
            obj.traSach = Integer.parseInt(c.getString( c.getColumnIndex("traSach")));

            try {
                obj.ngay = sdf.parse(c.getString(c.getColumnIndex("ngay")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list.add(obj);
        }
        return list;
    }

    public List<PhieuMuon> getAll(){
        String sql = "select * from PhieuMuon";
        return getData(sql);
    }

    public PhieuMuon getID(String id){
        String sql = "select * from PhieuMuon where maPM=?";
        List<PhieuMuon> list = getData(sql, id);
        return list.get(0);
    }
}
