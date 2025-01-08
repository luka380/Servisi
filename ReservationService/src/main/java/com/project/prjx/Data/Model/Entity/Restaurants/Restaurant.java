package com.project.prjx.Data.Model.Entity.Restaurants;

import com.project.prjx.Data.Model.Entity.KitchenType;
import com.project.prjx.Data.Model.Entity.Users.Manager;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String phone;
    private KitchenType kitchenType;
    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager owner;
    private String description;
    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;
}
