package YENPTPH29053fpt.duanmau_ph29053_pty.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.ThuThu;
import YENPTPH29053fpt.duanmau_ph29053_pty.DbHelper.DbHelper;



public class ThuThuDao {
    SQLiteDatabase db;

    public ThuThuDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insertThuThu(ThuThu obj){
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.insert("ThuThu", null, values);
    }
    public int updateThuThu( ThuThu obj){
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.update("ThuThu", values, "maTT=?", new String[]{ obj.getMaTT()});
    }
//    public int updatePass(ThuThu obj) {
//        ContentValues values = new ContentValues();
//        values.put("matKhau", obj.getMatKhau());
//
//        return db.update("ThuThu", values, "maTT=?", new String[]{obj.getMaTT()});
//    }

    public int deleteThuThu( ThuThu obj){
        return db.delete("ThuThu", "maTT=?", new String[]{ obj.getMaTT()+ ""});
    }

    @SuppressLint("Range")
    private List< ThuThu> getData(String sql, String...selectionArgs){
        List<ThuThu> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext() ){
            ThuThu obj = new ThuThu();
            obj.maTT = c.getString( c.getColumnIndex("maTT"));
            obj.hoTen = c.getString( c.getColumnIndex("hoTen"));
            obj.matKhau = c.getString( c.getColumnIndex("matKhau"));
            list.add(obj);
        }
        return list;
    }

    public List<ThuThu> getAll(){
        String sql = "select * from ThuThu";
        return getData(sql);
    }

    public ThuThu getID(String id){
        ThuThu thuThu = new ThuThu("null", "null", "null");
        String sql = "select * from ThuThu where maTT=?";
        List<ThuThu> list = getData(sql, id);
        if (list.size() > 0){
            return list.get(0);
        }else {
            return thuThu;
        }

    }

    public int checkLogin(String user, String pass){
        String sql = "select * from ThuThu where maTT=? and matKhau=?";
        List<ThuThu> list = getData(sql, user, pass);
        if(list.size() == 0){
            return -1;
        }
        return 1;
    }
}
