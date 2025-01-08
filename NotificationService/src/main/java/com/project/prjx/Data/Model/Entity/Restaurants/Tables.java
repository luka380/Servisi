package com.project.prjx.Data.Model.Entity.Restaurants;

import jakarta.persistence.*;

@Entity
@jakarta.persistence.Table(name = "Tables")
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
}
