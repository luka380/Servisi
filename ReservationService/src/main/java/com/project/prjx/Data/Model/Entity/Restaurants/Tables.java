package com.project.prjx.Data.Model.Entity.Restaurants;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Tables")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tableName;
    private int tableNumber;
    @OneToOne
    @JoinColumn(name = "tabletype_id", nullable = false)
    private TableType tableType;
    @OneToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    private Boolean isDeleted;
}
