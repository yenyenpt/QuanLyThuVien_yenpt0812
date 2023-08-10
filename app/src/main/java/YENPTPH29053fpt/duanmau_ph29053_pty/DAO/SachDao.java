package YENPTPH29053fpt.duanmau_ph29053_pty.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.Sach;
import YENPTPH29053fpt.duanmau_ph29053_pty.DbHelper.DbHelper;


public class SachDao {
    SQLiteDatabase db;

    public SachDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insertSach(Sach obj){
        ContentValues values = new ContentValues();
        values.put("tenSach", obj.getTenSach());
        values.put("giaThue", obj.getGiaThue());
        values.put("maLoai", obj.getMaLoai());
        return db.insert("Sach", null, values);
    }
    public int updateSach( Sach obj){
        ContentValues values = new ContentValues();
        values.put("tenSach", obj.getTenSach());
        values.put("giaThue", obj.getGiaThue());
        values.put("maLoai", obj.getMaLoai());
        return db.update("Sach", values, "maSach=?", new String[]{ obj.getMaSach()+ ""});
    }
    public int deleteSach( String id){
        return db.delete("Sach", "maSach=?", new String[]{ id });
    }

    @SuppressLint("Range")
    private List< Sach> getData(String sql, String...selectionArgs){
        List<Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext() ){
            Sach obj = new Sach();
            obj.maSach = Integer.parseInt(c.getString( c.getColumnIndex("maSach")));
            obj.tenSach = c.getString( c.getColumnIndex("tenSach"));
            obj.giaThue = Integer.parseInt(c.getString( c.getColumnIndex("giaThue")));
            obj.maLoai = Integer.parseInt(c.getString( c.getColumnIndex("maLoai")));
            list.add(obj);
        }
        return list;
    }

    public List<Sach> getAll(){
        String sql = "select * from Sach";
        return getData(sql);
    }

    public Sach getID(String id){
        String sql = "select * from Sach where maSach=?";
        List<Sach> list = getData(sql, id);
        return list.get(0);
    }
}
