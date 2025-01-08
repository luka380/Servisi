package com.project.prjx.Data.Model.Entity.Restaurants;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rewards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    private String reward;
    private int requirement;
}
