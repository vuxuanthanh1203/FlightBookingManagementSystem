package group3.persistance;

public class User {
    private String email;
    private String pass;
    private String name;
    private String tel;
    private int id_card;
    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getId_card() {
        return id_card;
    }

    public void setId_card(int id_card) {
        this.id_card = id_card;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User() {
        
    }

    public User(String email, String pass, String name, String tel, int id_card, String address) {
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.tel = tel;
        this.id_card = id_card;
        this.address = address;
    }
}
