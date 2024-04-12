package com.etraveli.enums;

public enum MovieType {

  REGULAR(""),
  CHILDREN(""),
  NEW("new");

  private String type;

  MovieType(String type) {
    this.type = type;
  }

}
