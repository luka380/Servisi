package com.project.prjx.API;

import com.project.prjx.Data.Model.Dto.Restaurants.*;
import com.project.prjx.Data.Model.Entity.KitchenType;
import com.project.prjx.Services.ManagerServiceInterface;
import com.project.prjx.Services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantManagement {
    private final RestaurantService restaurantService;
    private final ManagerServiceInterface managerService;

    public RestaurantManagement(RestaurantService restaurantService, ManagerServiceInterface managerService) {
        this.restaurantService = restaurantService;
        this.managerService = managerService;
    }

    @GetMapping("/public")
    public ResponseEntity<List<RestaurantDto>> getRestaurants(@RequestParam(required = false) KitchenType kitchenType,
                                                              @RequestParam(required = false) Boolean smokingAllowed,
                                                              @RequestParam(required = false) Integer minCapacity,
                                                              @RequestParam(required = false) LocalDateTime dateTime) {
        return ResponseEntity.ok(restaurantService.getRestaurantsPublic(kitchenType, smokingAllowed, minCapacity, dateTime));
    }

    @GetMapping("")
    public ResponseEntity<List<RestaurantDto>> getManagedRestaurants() {
        return ResponseEntity.ok(restaurantService.getRestaurants());
    }

    @PostMapping("")
    public ResponseEntity<RestaurantDto> newRestaurant(@Valid @RequestBody RestaurantDto restaurantDto) {
        return ResponseEntity.ok(restaurantService.addRestaurant(restaurantDto));
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@Valid @RequestBody RestaurantDto restaurantDto,
                                                          @PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantDto, restaurantId));
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> deleteRestaurant(@PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.removeRestaurant(restaurantId));
    }

    @GetMapping("/{restaurantId}/tables")
    public ResponseEntity<List<TableDto>> getTables(@PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.getTables(restaurantId));
    }

    @PostMapping("/{restaurantId}/tables")
    public ResponseEntity<TableDto> newTable(@Valid @RequestBody TableDto tableDto,
                                             @PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.addTable(tableDto, restaurantId));
    }

    @DeleteMapping("/{restaurantId}/tables/{tableId}")
    public ResponseEntity<TableDto> deleteTable(@PathVariable int restaurantId,
                                                @PathVariable int tableId) {
        return ResponseEntity.ok(restaurantService.removeTable(tableId, restaurantId));
    }

    @GetMapping("/{restaurantId}/zones")
    public ResponseEntity<List<ZoneDto>> getZones(@PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.getZones(restaurantId));
    }

    @PostMapping("/{restaurantId}/zones")
    public ResponseEntity<ZoneDto> newZone(@Valid @RequestBody ZoneDto zoneDto,
                                           @PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.addZone(zoneDto, restaurantId));
    }

    @DeleteMapping("/{restaurantId}/zones/{zoneId}")
    public ResponseEntity<ZoneDto> removeZone(@PathVariable int restaurantId,
                                              @PathVariable int zoneId) {
        return ResponseEntity.ok(restaurantService.removeZone(zoneId, restaurantId));
    }

    @GetMapping("/{restaurantId}/tableTypes")
    public ResponseEntity<List<TableTypeDto>> getTableTypes(@PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.getTableTypes(restaurantId));
    }

    @PostMapping("/{restaurantId}/tableTypes")
    public ResponseEntity<TableTypeDto> newTableType(@Valid @RequestBody TableTypeDto tableTypeDto,
                                                     @PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.newTableType(tableTypeDto, restaurantId));
    }

    @DeleteMapping("/{restaurantId}/tableTypes/{tableTypeId}")
    public ResponseEntity<TableTypeDto> removeTableType(@PathVariable int restaurantId,
                                                        @PathVariable int tableTypeId) {
        return ResponseEntity.ok(restaurantService.removeTableType(tableTypeId, restaurantId));
    }

    @GetMapping("/{restaurantId}/rewards")
    public ResponseEntity<RewardsDto> getRewards(@PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.getRewards(restaurantId));
    }

    @PostMapping("/{restaurantId}/rewards")
    public ResponseEntity<RewardsDto> newReward(@Valid @RequestBody RewardsDto rewardsDto,
                                                @PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.newReward(rewardsDto, restaurantId));
    }

    @DeleteMapping("/{restaurantId}/rewards/{rewardId}")
    public ResponseEntity<RewardsDto> deleteReward(@Valid @RequestBody RewardsDto rewardsDto,
                                                   @PathVariable int restaurantId,
                                                   @PathVariable int rewardId) {
        return ResponseEntity.ok(restaurantService.deleteReward(rewardId, restaurantId));
    }

    @GetMapping("/{restaurantId}/schedules")
    public ResponseEntity<List<ScheduleDto>> getSchedules(@PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.getSchedules(restaurantId));
    }

    @PostMapping("/{restaurantId}/schedules")
    public ResponseEntity<ScheduleDto> newSchedule(@Valid @RequestBody ScheduleDto scheduleDto,
                                                   @PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.newSchedule(scheduleDto, restaurantId));
    }

    @DeleteMapping("/{restaurantId}/schedules/{scheduleId}")
    public ResponseEntity<ScheduleDto> deleteSchedule(@PathVariable int restaurantId,
                                                      @PathVariable int scheduleId) {
        return ResponseEntity.ok(restaurantService.removeSchedule(scheduleId, restaurantId));
    }

    @GetMapping("/{restaurantId}/schedulesCalendar")
    public ResponseEntity<SchedulePerDayDto> getSchedulesPerDay(@PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.getSchedulesPerDay(restaurantId));
    }

    @PostMapping("/{restaurantId}/schedulesCalendar")
    public ResponseEntity<SchedulePerDayDto> addSchedulePerDay(@Valid @RequestBody SchedulePerDayDto schedulePerDayDto,
                                                               @PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.newSchedulePerDay(schedulePerDayDto, restaurantId));
    }

    @PatchMapping("/{restaurantId}/schedulesCalendar/{schedulePerDayId}")
    public ResponseEntity<SchedulePerDayDto> updateSchedulePerDay(@Valid @RequestBody SchedulePerDayDto schedulePerDayDto,
                                                                  @PathVariable int restaurantId,
                                                                  @PathVariable int schedulePerDayId) {
        return ResponseEntity.ok(restaurantService.updateSchedulePerDay(schedulePerDayDto, schedulePerDayId, restaurantId));
    }

    @DeleteMapping("/{restaurantId}/schedulesCalendar/{schedulePerDayId}")
    public ResponseEntity<SchedulePerDayDto> deleteSchedulePerDay(@PathVariable int restaurantId,
                                                                  @PathVariable int schedulePerDayId) {
        return ResponseEntity.ok(restaurantService.deleteSchedulePerDay(schedulePerDayId, restaurantId));
    }
}
