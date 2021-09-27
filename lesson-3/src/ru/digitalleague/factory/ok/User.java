package ru.digitalleague.factory.ok;

import ru.digitalleague.factory.ok.enums.Localize;

public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Localize local;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Localize getLanguage() {
        return local;
    }

    public User(Long id, String name, String email, String phone, Localize local) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.local = local;
    }
}
