package com.project.prjx.Data.Model.Entity.Restaurants;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private Boolean isSmoking;
}
