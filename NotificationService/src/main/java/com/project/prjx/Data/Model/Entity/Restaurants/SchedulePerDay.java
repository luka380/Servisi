package com.project.prjx.Data.Model.Entity.Restaurants;

import jakarta.persistence.*;

@Entity
public class SchedulePerDay {
    @EmbeddedId
    private SchedulePerDayCompositeKey schedulePerDayCompositeKey;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
