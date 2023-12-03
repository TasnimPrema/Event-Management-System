package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Events")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    @Column
    private String name;
    @Column
    private String desc;
    @Column
    private String price;
    @Column
    private String img;

}
