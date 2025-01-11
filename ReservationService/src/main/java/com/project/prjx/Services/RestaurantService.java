package com.project.prjx.Services;

import com.project.prjx.Data.Mappers.Mappers;
import com.project.prjx.Data.Model.Dto.Restaurants.*;
import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import com.project.prjx.Data.Model.Entity.KitchenType;
import com.project.prjx.Data.Model.Entity.Restaurants.*;
import com.project.prjx.Data.Repositories.*;
import com.project.prjx.Security.UserAuthentication;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;
    private final ZoneRepository zoneRepository;
    private final TableTypeRepository tableTypeRepository;
    private final ScheduleRepository scheduleRepository;
    private final SchedulePerDayRepository schedulePerDayRepository;
    private final RewardsRepository rewardsRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, TableRepository tableRepository, ZoneRepository zoneRepository, TableTypeRepository tableTypeRepository, ScheduleRepository scheduleRepository, SchedulePerDayRepository schedulePerDayRepository, RewardsRepository rewardsRepository) {
        this.restaurantRepository = restaurantRepository;
        this.tableRepository = tableRepository;
        this.zoneRepository = zoneRepository;
        this.tableTypeRepository = tableTypeRepository;
        this.scheduleRepository = scheduleRepository;
        this.schedulePerDayRepository = schedulePerDayRepository;
        this.rewardsRepository = rewardsRepository;
    }

    private BaseUserDto getManager() {
        return ((UserAuthentication) SecurityContextHolder.getContext().getAuthentication()).getUser();
    }

    public List<RestaurantDto> getRestaurantsPublic(KitchenType kitchenType, Boolean smokingAllowed, Integer minCapacity, LocalDateTime dateTime) {
        return Mappers.RestaurantMapper.reverseMap(restaurantRepository.findByCriteria(kitchenType, smokingAllowed, minCapacity));
    }

    private Map<String, Map<String, String>> filteredSchedule(Map<String, Map<String, String>> schedule, LocalDateTime dateTime) {
        Map<String, Map<String, String>> filteredSchedule = new HashMap<>();

        for (Map.Entry<String, Map<String, String>> entry : schedule.entrySet()) {
            String timeSlot = entry.getKey();
            Map<String, String> tableReservations = entry.getValue();

            Map<String, String> filteredTableReservations = new HashMap<>();
            for (Map.Entry<String, String> reservation : tableReservations.entrySet()) {
                if ("null".equals(reservation.getValue())) {
                    filteredTableReservations.put(reservation.getKey(), reservation.getValue());
                }
            }

            if (!filteredTableReservations.isEmpty()) {
                filteredSchedule.put(timeSlot, filteredTableReservations);
            }
        }

        return filteredSchedule;
    }

    public List<RestaurantDto> getRestaurants() {
        BaseUserDto manager = getManager();
        return Mappers.RestaurantMapper.reverseMap(restaurantRepository.findAllByOwnerIdOrManagerId(manager.getId(), manager.getId()));
    }

    public RestaurantDto addRestaurant(@Valid RestaurantDto restaurantDto) {
        restaurantDto.setManagerId(getManager().getId());
        restaurantDto.setOwnerId(getManager().getId());
        return Mappers.RestaurantMapper.reverseMap(restaurantRepository.save(Mappers.RestaurantMapper.map(restaurantDto)));
    }

    public RestaurantDto updateRestaurant(@Valid RestaurantDto restaurantDto, int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        restaurant.setKitchenType(restaurantDto.getKitchenType());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setDescription(restaurantDto.getDescription());
        restaurant.setPhone(restaurantDto.getPhone());
        restaurant.setName(restaurantDto.getName());

        restaurant = restaurantRepository.save(restaurant);
        return Mappers.RestaurantMapper.reverseMap(restaurant);
    }

    public RestaurantDto removeRestaurant(int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isOwned(manager, restaurant);

        restaurantRepository.deleteById(restaurantId);
        return Mappers.RestaurantMapper.reverseMap(restaurant);
    }

    public List<TableDto> getTables(int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        return Mappers.TableMapper.reverseMap(tableRepository.findAllByRestaurant_Id(restaurantId));
    }

    public TableDto addTable(@Valid TableDto tableDto, int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        Tables table = Mappers.TableMapper.map(tableDto);
        table.setRestaurant(restaurant);

        return Mappers.TableMapper.reverseMap(tableRepository.save(table));
    }

    public TableDto removeTable(int tableId, int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        Tables table = tableRepository.findById(tableId).get();
        exists(table, Tables.class);
        isManaged(restaurant, table);

        tableRepository.deleteById(tableId);
        return Mappers.TableMapper.reverseMap(table);
    }

    public List<ZoneDto> getZones(int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        return Mappers.ZoneMapper.reverseMap(zoneRepository.findAllByRestaurant_Id(restaurantId));
    }

    public ZoneDto addZone(@Valid ZoneDto zoneDto, int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        Zone zone = Mappers.ZoneMapper.map(zoneDto);
        zone.setRestaurant(restaurant);

        return Mappers.ZoneMapper.reverseMap(zoneRepository.save(zone));
    }

    public ZoneDto removeZone(int zoneId, int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        Zone zone = zoneRepository.findById(zoneId).get();
        exists(zone, Zone.class);
        isManaged(restaurant, zone);

        zoneRepository.deleteById(zoneId);
        return Mappers.ZoneMapper.reverseMap(zone);
    }

    public List<TableTypeDto> getTableTypes(int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        return Mappers.TableTypeMapper.reverseMap(tableTypeRepository.findAllByRestaurant_Id(restaurantId));
    }

    public TableTypeDto newTableType(@Valid TableTypeDto tableTypeDto, int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        TableType tableType = Mappers.TableTypeMapper.map(tableTypeDto);
        tableType.setRestaurant(restaurant);

        return Mappers.TableTypeMapper.reverseMap(tableTypeRepository.save(tableType));
    }

    public TableTypeDto removeTableType(int tableTypeId, int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        TableType tableType = tableTypeRepository.findById(tableTypeId).get();
        exists(tableType, TableType.class);

        tableTypeRepository.deleteById(tableTypeId);
        return Mappers.TableTypeMapper.reverseMap(tableType);
    }

    public RewardsDto getRewards(int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        return Mappers.RewardsMapper.reverseMap(rewardsRepository.findAllByRestaurant_Id(restaurantId));
    }

    public RewardsDto newReward(@Valid RewardsDto rewardsDto, int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        Rewards reward = Mappers.RewardsMapper.map(rewardsDto);
        reward.setRestaurant(restaurant);

        rewardsRepository.save(reward);
        return rewardsDto;
    }

    public RewardsDto deleteReward(int rewardId, int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        Rewards reward = rewardsRepository.findById(rewardId).get();
        exists(reward, Rewards.class);
        isManaged(restaurant, reward);

        rewardsRepository.deleteById(rewardId);
        return Mappers.RewardsMapper.reverseMap(reward);
    }

    public List<ScheduleDto> getSchedules(int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        return Mappers.ScheduleMapper.reverseMap(scheduleRepository.findAllByRestaurant_Id(restaurantId));
    }

    public ScheduleDto newSchedule(@Valid ScheduleDto scheduleDto, int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        Schedule schedule = Mappers.ScheduleMapper.map(scheduleDto);
        schedule.setRestaurant(restaurant);

        if (!isScheduleValid(schedule.getSchedule()))
            throw new RuntimeException("Schedule is not valid");

        return Mappers.ScheduleMapper.reverseMap(scheduleRepository.save(schedule));
    }

    public ScheduleDto removeSchedule(int scheduleId, int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        exists(schedule, Schedule.class);
        isManaged(restaurant, schedule);

        scheduleRepository.deleteById(scheduleId);
        return Mappers.ScheduleMapper.reverseMap(schedule);
    }

    public SchedulePerDayDto getSchedulesPerDay(int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        return Mappers.SchedulePerDayMapper.reverseMap(schedulePerDayRepository.findAllByRestaurant_Id(restaurantId));
    }

    public SchedulePerDayDto newSchedulePerDay(@Valid SchedulePerDayDto schedulePerDayDto, int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        SchedulePerDay schedulePerDay = Mappers.SchedulePerDayMapper.map(schedulePerDayDto);
        schedulePerDay.setRestaurant(restaurant);

        if (!isScheduleValid(schedulePerDay.getSchedule().getSchedule()))
            throw new RuntimeException("Schedule is not valid");

        return Mappers.SchedulePerDayMapper.reverseMap(schedulePerDayRepository.save(schedulePerDay));
    }

    public SchedulePerDayDto updateSchedulePerDay(@Valid SchedulePerDayDto schedulePerDayDto, int schedulePerDayId, int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        SchedulePerDay schedulePerDay = schedulePerDayRepository.findById(schedulePerDayId).get();
        exists(schedulePerDay, schedulePerDay.getClass());
        isManaged(restaurant, schedulePerDay);

        if (!isScheduleValid(schedulePerDay.getSchedule().getSchedule()))
            throw new RuntimeException("Schedule is not valid");

        schedulePerDay.setSchedule(Mappers.ScheduleMapper.map(schedulePerDayDto.getSchedule()));
        schedulePerDay.setDate(schedulePerDayDto.getDate());

        return Mappers.SchedulePerDayMapper.reverseMap(schedulePerDayRepository.save(schedulePerDay));
    }

    public SchedulePerDayDto deleteSchedulePerDay(int schedulePerDayId, int restaurantId) {
        BaseUserDto manager = getManager();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        exists(restaurant, Restaurant.class);
        isManaged(manager, restaurant);

        SchedulePerDay schedulePerDay = schedulePerDayRepository.findById(schedulePerDayId).get();
        exists(schedulePerDay, schedulePerDay.getClass());
        isManaged(restaurant, schedulePerDay);

        schedulePerDayRepository.deleteById(schedulePerDayId);
        return Mappers.SchedulePerDayMapper.reverseMap(schedulePerDay);
    }

    private boolean isOwned(BaseUserDto manager, Restaurant restaurant) {
        if (manager.getId().equals(restaurant.getOwnerId())) {
            return true;
        }
        throw new RuntimeException("You are not authorized to do this action");
    }

    private void isManaged(BaseUserDto manager, Restaurant restaurant) {
        if (manager.getId().equals(restaurant.getOwnerId()) || isOwned(manager, restaurant)) {
            return;
        }
        throw new RuntimeException("You are not authorized to do this action");
    }

    private void isManaged(Restaurant restaurant, Tables table) {
        if (table.getRestaurant().getId() != restaurant.getId())
            throw new RuntimeException("You are not authorized to do this action");
    }

    private void isManaged(Restaurant restaurant, Zone zone) {
        if (zone.getRestaurant().getId() != restaurant.getId())
            throw new RuntimeException("You are not authorized to do this action");
    }

    private void isManaged(Restaurant restaurant, Rewards reward) {
        if (reward.getRestaurant().getId() != restaurant.getId())
            throw new RuntimeException("You are not authorized to do this action");
    }

    private void isManaged(Restaurant restaurant, Schedule schedule) {
        if (schedule.getRestaurant().getId() != restaurant.getId())
            throw new RuntimeException("You are not authorized to do this action");
    }

    private void isManaged(Restaurant restaurant, SchedulePerDay schedulePerDay) {
        if (schedulePerDay.getRestaurant().getId() != restaurant.getId())
            throw new RuntimeException("You are not authorized to do this action");
    }

    private void exists(Object o, Class<?> c) {
        if (o == null)
            throw new RuntimeException("Object " + c.getName() + " does not exist");
    }

    public boolean isScheduleValid(Map<String, Map<String, String>> schedule) {
        Map<String, List<String>> tableTimeSlots = new HashMap<>();

        for (Map.Entry<String, Map<String, String>> entry : schedule.entrySet()) {
            String timeSlot = entry.getKey();
            Map<String, String> tableReservations = entry.getValue();

            for (Map.Entry<String, String> reservation : tableReservations.entrySet()) {
                String tableId = reservation.getKey();
                tableTimeSlots.computeIfAbsent(tableId, k -> new ArrayList<>()).add(timeSlot);
            }
        }

        for (Map.Entry<String, List<String>> entry : tableTimeSlots.entrySet()) {
            List<String> timeSlots = entry.getValue();

            for (int i = 0; i < timeSlots.size(); i++) {
                for (int j = i + 1; j < timeSlots.size(); j++) {
                    if (isOverlap(timeSlots.get(i), timeSlots.get(j))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean isOverlap(String timeSlot1, String timeSlot2) {
        String[] times1 = timeSlot1.split("-");
        LocalTime startTime1 = LocalTime.parse(times1[0]);
        LocalTime endTime1 = LocalTime.parse(times1[1]);

        String[] times2 = timeSlot2.split("-");
        LocalTime startTime2 = LocalTime.parse(times2[0]);
        LocalTime endTime2 = LocalTime.parse(times2[1]);

        return startTime1.isBefore(endTime2) && endTime1.isAfter(startTime2);
    }
}