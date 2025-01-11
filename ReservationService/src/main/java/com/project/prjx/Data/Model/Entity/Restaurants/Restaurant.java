package com.project.prjx.Data.Model.Entity.Restaurants;

import com.project.prjx.Data.Model.Entity.KitchenType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

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
    private String description;
    private UUID ownerId;
    private UUID managerId;
}
