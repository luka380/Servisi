package com.project.prjx.Data.Model.Entity.Restaurants;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchedulePerDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @Column(nullable = false)
    private LocalDate date;
    @ManyToOne()
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Map<String, String>> schedulePerDay;
}
