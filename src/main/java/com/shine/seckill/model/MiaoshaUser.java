package com.shine.seckill.model;

public class MiaoshaUser {
  private Long id;
  private String nickname;
  private String password;
  private String salt;
  private String head;
  private java.sql.Timestamp register_date;
  private java.sql.Timestamp last_login_date;
  private Long login_count;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getHead() {
    return head;
  }

  public void setHead(String head) {
    this.head = head;
  }

  public java.sql.Timestamp getRegister_date() {
    return register_date;
  }

  public void setRegister_date(java.sql.Timestamp register_date) {
    this.register_date = register_date;
  }

  public java.sql.Timestamp getLast_login_date() {
    return last_login_date;
  }

  public void setLast_login_date(java.sql.Timestamp last_login_date) {
    this.last_login_date = last_login_date;
  }

  public Long getLogin_count() {
    return login_count;
  }

  public void setLogin_count(Long login_count) {
    this.login_count = login_count;
  }
}
