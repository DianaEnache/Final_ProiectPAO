package models;

public class Admin{
    private int admin_id;
    private String admin_name;
    private String passsword;

    private String email_admin;

    public Admin(Integer admin_id,String admin_name, String passsword, String email_admin){
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.passsword = passsword;
        this.email_admin = email_admin;
    }

    //Getters
    public Integer getAdminId() {
        return admin_id;
    }
    public String getAdminName(){
        return admin_name;
    }
    public String getPasssword(){
        return passsword;
    }
    public String getAdminEmail(){
        return email_admin;
    }

    //Setters
    public void setAdminId(Integer admin_id){
        this.admin_id = admin_id;
    }

    public void setAdminName(String admin_name){
        this.admin_name = admin_name;
    }
    public void setPasssword(String passsword){
        this.passsword = passsword;
    }
    public void setAdminEmail(String email_admin){
        this.email_admin = email_admin;
    }

}
