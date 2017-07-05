package com.safetyhuge.chanlian.safetyhuge.Bean;

/**
 * Created by Chinalink on 2017/3/25 0025.
 */

public class PersonageBean {
    private Object pic;
    private String username;
    private Object address;
    private Object phone;
    private String email;

    public Object getPic() {
        return pic;
    }

    public void setPic(Object pic) {
        this.pic = pic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "PersonageBean{" +
                "pic=" + pic +
                ", username='" + username + '\'' +
                ", address=" + address +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}
