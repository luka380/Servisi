package com.project.prjx.Data.Model.Entity.Users;

import com.project.prjx.Data.Model.Entity.Restaurants.Restaurant;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@DiscriminatorValue("MANAGER")
public class Manager extends BaseUser {
    @NotNull
    private LocalDateTime hiringDate;
    @NotBlank
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Builder
    public Manager(UUID id, String username, String password, Email email, LocalDateTime birthday, String firstName, String lastName, Number phoneNumber, Boolean isEnabled, String role, LocalDateTime hiringDate, Restaurant restaurant) {
        super(id, username, password, email, birthday, firstName, lastName, phoneNumber, isEnabled, role);
        this.hiringDate = hiringDate;
        this.restaurant = restaurant;
    }
}
