import java.io.Serializable;

public class hd implements Serializable{
    private String mahd, tennv, tenkh, tensp, sl, gia, thanhtien, ngaylap;
    private boolean gender;
    public hd(){
        
    }

    public hd(String mahd, String tennv, String tenkh, String tensp, String sl, String gia, String thanhtien, String ngaylap, boolean gender) {
        this.mahd = mahd;
        this.tennv = tennv;
        this.tenkh = tenkh;
        this.tensp = tensp;
        this.sl = sl;
        this.gia = gia;
        this.thanhtien = thanhtien;
        this.ngaylap = ngaylap;
        this.gender = gender;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(String thanhtien) {
        this.thanhtien = thanhtien;
    }

    public String getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(String ngaylap) {
        this.ngaylap = ngaylap;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
    
}
