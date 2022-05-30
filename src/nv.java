import java.io.Serializable;

public class nv implements Serializable{
    private String id, name, pass;
    private boolean gender;
    public nv(){
        
    }

    nv(String id, String name, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
        public nv(String id, String name, String pass, boolean gender) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.gender = gender;
    }

    void add(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}