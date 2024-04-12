package com.etraveli.entity;

import com.etraveli.enums.MovieType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String code;
  private String title;
  @Enumerated
  private MovieType type;

  public Movie(String code, String title, MovieType type) {
    this.code = code;
    this.title = title;
    this.type = type;
  }
}
