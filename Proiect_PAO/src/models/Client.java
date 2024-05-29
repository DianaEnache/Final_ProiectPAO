package models;

public class Client {
    private int client_id;
    private String client_name;
    private String email;

    public Client(Integer client_id,String client_name,String email){
        this.client_id = client_id;
        this.client_name = client_name;
        this.email = email;
    }

    //Getters
    public Integer getClientId(){
        return client_id;
    }
    public String getClientName(){
        return client_name;
    }

    public String getEmail(){
        return email;
    }

    //Setters
    public void setClientId(Integer client_id){
        this.client_id = client_id;
    }

    public void setClientName(String client_name){
        this.client_name = client_name;
    }
    public void setEmail(String email){
        this.email = email;
    }

}
