package com.project.prjx.Data.Mappers;

import com.project.prjx.Data.Model.Dto.Restaurants.*;
import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import com.project.prjx.Data.Model.Dto.Users.ClientDto;
import com.project.prjx.Data.Model.Dto.Users.EmailDto;
import com.project.prjx.Data.Model.Dto.Users.ManagerDto;
import com.project.prjx.Data.Model.Entity.Restaurants.*;
import com.project.prjx.Data.Model.Entity.Users.BaseUser;
import com.project.prjx.Data.Model.Entity.Users.Client;
import com.project.prjx.Data.Model.Entity.Users.Email;
import com.project.prjx.Data.Model.Entity.Users.Manager;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Mappers {
    public static ClientMapper ClientMapper = new ClientMapper();
    public static EmailMapper EmailMapper = new EmailMapper();
    public static RestaurantMapper RestaurantMapper = new RestaurantMapper();
    public static ManagerMapper ManagerMapper = new ManagerMapper();
    public static BaseUserMapper BaseUserMapper = new BaseUserMapper();
    public static TableMapper TableMapper = new TableMapper();
    public static TableTypeMapper TableTypeMapper = new TableTypeMapper();
    public static ZoneMapper ZoneMapper = new ZoneMapper();
    public static RewardsMapper RewardsMapper = new RewardsMapper();
    public static ScheduleMapper ScheduleMapper = new ScheduleMapper();
    public static SchedulePerDayMapper SchedulePerDayMapper = new SchedulePerDayMapper();

    public static class ClientMapper implements Mapper<Client, ClientDto> {

        @Override
        public ClientDto map(Client object) {
            if (object == null)
                return null;
            return ClientDto.builder()
                    .id(object.getId())
                    .username(object.getUsername())
                    .email(Mappers.EmailMapper.reverseMap(object.getEmail()))
                    .birthday(object.getBirthday())
                    .firstName(object.getFirstName())
                    .lastName(object.getLastName())
                    .phoneNumber(object.getPhoneNumber())
                    .isEnabled(object.getIsEnabled())
                    .password(object.getPassword())
                    .build();
        }

        @Override
        public Client reverseMap(ClientDto object) {
            if (object == null)
                return null;
            return Client.builder()
                    .id(object.getId())
                    .username(object.getUsername())
                    .email(Mappers.EmailMapper.map(object.getEmail()))
                    .birthday(object.getBirthday())
                    .firstName(object.getFirstName())
                    .lastName(object.getLastName())
                    .phoneNumber(object.getPhoneNumber())
                    .isEnabled(object.getIsEnabled())
                    .role(object.getRole())
                    .password(object.getPassword())
                    .build();
        }
    }

    public static class BaseUserMapper implements Mapper<BaseUser, BaseUserDto> {

        @Override
        public BaseUserDto map(BaseUser object) {
            if (object == null)
                return null;
            return ClientDto.builder()
                    .id(object.getId())
                    .username(object.getUsername())
                    .email(Mappers.EmailMapper.reverseMap(object.getEmail()))
                    .birthday(object.getBirthday())
                    .firstName(object.getFirstName())
                    .lastName(object.getLastName())
                    .phoneNumber(object.getPhoneNumber())
                    .isEnabled(object.getIsEnabled())
                    .password(object.getPassword())
                    .build();
        }

        @Override
        public BaseUser reverseMap(BaseUserDto object) {
            if (object == null)
                return null;
            return Client.builder()
                    .id(object.getId())
                    .username(object.getUsername())
                    .email(Mappers.EmailMapper.map(object.getEmail()))
                    .birthday(object.getBirthday())
                    .firstName(object.getFirstName())
                    .lastName(object.getLastName())
                    .phoneNumber(object.getPhoneNumber())
                    .isEnabled(object.getIsEnabled())
                    .role(object.getRole())
                    .password(object.getPassword())
                    .build();
        }
    }


    public static class EmailMapper implements Mapper<EmailDto, Email> {

        @Override
        public Email map(EmailDto object) {
            if (object == null)
                return null;

            return Email.builder()
                    .email(object.email())
                    .isVerified(object.isVerified())
                    .id(object.id())
                    .build();
        }

        @Override
        public EmailDto reverseMap(Email object) {
            if (object == null)
                return null;
            return EmailDto.builder()
                    .id(object.getId())
                    .email(object.getEmail())
                    .isVerified(object.getIsVerified())
                    .build();
        }
    }

    public static class RestaurantMapper implements Mapper<RestaurantDto, Restaurant> {

        @Override
        public Restaurant map(RestaurantDto object) {
            if (object == null)
                return null;
            return Restaurant.builder()
                    .address(object.getAddress())
                    .name(object.getName())
                    .phone(object.getPhone())
                    .id(object.getId())
                    .build();
        }

        @Override
        public RestaurantDto reverseMap(Restaurant object) {
            if (object == null)
                return null;
            return RestaurantDto.builder()
                    .address(object.getAddress())
                    .name(object.getName())
                    .phone(object.getPhone())
                    .id(object.getId())
                    .build();
        }
    }

    public static class ManagerMapper implements Mapper<ManagerDto, Manager> {

        @Override
        public Manager map(ManagerDto object) {
            if (object == null)
                return null;
            return Manager.builder()
                    .hiringDate(object.getHiringDate())
                    .restaurant(Mappers.RestaurantMapper.map(object.getRestaurant()))
                    .id(object.getId())
                    .birthday(object.getBirthday())
                    .firstName(object.getFirstName())
                    .lastName(object.getLastName())
                    .phoneNumber(object.getPhoneNumber())
                    .isEnabled(object.getIsEnabled())
                    .role(object.getRole())
                    .email(Mappers.EmailMapper.map(object.getEmail()))
                    .password(object.getPassword())
                    .username(object.getUsername())
                    .id(object.getId())
                    .build();
        }

        @Override
        public ManagerDto reverseMap(Manager object) {
            if (object == null)
                return null;
            return ManagerDto.builder()
                    .hiringDate(object.getHiringDate())
                    .restaurant(Mappers.RestaurantMapper.reverseMap(object.getRestaurant()))
                    .id(object.getId())
                    .birthday(object.getBirthday())
                    .firstName(object.getFirstName())
                    .lastName(object.getLastName())
                    .phoneNumber(object.getPhoneNumber())
                    .isEnabled(object.getIsEnabled())
                    .email(Mappers.EmailMapper.reverseMap(object.getEmail()))
                    .password(object.getPassword())
                    .username(object.getUsername())
                    .id(object.getId())
                    .build();
        }
    }
    
    public static class TableMapper implements Mapper<TableDto, Tables>{

        @Override
        public Tables map(TableDto object) {
            if (object == null)
                return null;
            return Tables.builder()
                    .id(object.getId())
                    .tableName(object.getTableName())
                    .tableNumber(object.getTableNumber())
                    .tableType(Mappers.TableTypeMapper.map(object.getTableType()))
                    .zone(Mappers.ZoneMapper.map(object.getZone()))
                    .restaurant(Mappers.RestaurantMapper.map(object.getRestaurant()))
                    .isDeleted(object.getIsDeleted())
                    .build();
        }

        @Override
        public TableDto reverseMap(Tables object) {
            if (object == null)
                return null;
            return TableDto.builder()
                    .id(object.getId())
                    .tableName(object.getTableName())
                    .tableNumber(object.getTableNumber())
                    .tableType(Mappers.TableTypeMapper.reverseMap(object.getTableType()))
                    .zone(Mappers.ZoneMapper.reverseMap(object.getZone()))
                    .restaurant(Mappers.RestaurantMapper.reverseMap(object.getRestaurant()))
                    .isDeleted(object.getIsDeleted())
                    .build();
        }
    }

    public static class TableTypeMapper implements Mapper<TableTypeDto, TableType> {

        @Override
        public TableType map(TableTypeDto object) {
            if (object == null)
                return null;
            return TableType.builder()
                    .id(object.getId())
                    .name(object.getName())
                    .seatingCapacity(object.getSeatingCapacity())
                    .restaurant(Mappers.RestaurantMapper.map(object.getRestaurant()))
                    .build();
        }

        @Override
        public TableTypeDto reverseMap(TableType object) {
            if (object == null)
                return null;
            return TableTypeDto.builder()
                    .id(object.getId())
                    .name(object.getName())
                    .restaurant(Mappers.RestaurantMapper.reverseMap(object.getRestaurant()))
                    .seatingCapacity(object.getSeatingCapacity())
                    .build();
        }
    }

    public static class ZoneMapper implements Mapper<ZoneDto, Zone> {

        @Override
        public Zone map(ZoneDto object) {
            if (object == null)
                return null;
            return Zone.builder()
                    .id(object.getId())
                    .name(object.getName())
                    .description(object.getDescription())
                    .restaurant(Mappers.RestaurantMapper.map(object.getRestaurant()))
                    .isSmoking(object.getIsSmoking())
                    .location(object.getLocation())
                    .build();
        }

        @Override
        public ZoneDto reverseMap(Zone object) {
            if (object == null)
                return null;
            return ZoneDto.builder()
                    .id(object.getId())
                    .name(object.getName())
                    .description(object.getDescription())
                    .isSmoking(object.getIsSmoking())
                    .location(object.getLocation())
                    .restaurant(Mappers.RestaurantMapper.reverseMap(object.getRestaurant()))
                    .build();
        }
    }

    public static class RewardsMapper implements Mapper<RewardsDto, Rewards> {
        @Override
        public Rewards map(RewardsDto object) {
            if (object == null)
                return null;
            return Rewards.builder()
                    .id(object.getRewardId())
                    .reward(object.getReward())
                    .requirement(object.getRequirement())
                    .restaurant(Mappers.RestaurantMapper.map(object.getRestaurant()))
                    .build();
        }

        @Override
        public RewardsDto reverseMap(Rewards object) {
            if (object == null)
                return null;
            return RewardsDto.builder()
                    .rewardId(object.getId())
                    .reward(object.getReward())
                    .requirement(object.getRequirement())
                    .restaurant(Mappers.RestaurantMapper.reverseMap(object.getRestaurant()))
                    .build();
        }
    }

    public static class ScheduleMapper implements Mapper<ScheduleDto, Schedule> {
        @Override
        public Schedule map(ScheduleDto object) {
            if (object == null)
                return null;
            return Schedule.builder()
                    .id(object.getId())
                    .Schedule(object.getSchedule())
                    .restaurant(Mappers.RestaurantMapper.map(object.getRestaurant()))
                    .build();
        }

        @Override
        public ScheduleDto reverseMap(Schedule object) {
            if (object == null)
                return null;
            return ScheduleDto.builder()
                    .Schedule(object.getSchedule())
                    .restaurant(Mappers.RestaurantMapper.reverseMap(object.getRestaurant()))
                    .id(object.getId())
                    .build();
        }
    }

    public static class SchedulePerDayMapper implements Mapper<SchedulePerDayDto, SchedulePerDay> {
        @Override
        public SchedulePerDay map(SchedulePerDayDto object) {
            if (object == null)
                return null;
            return SchedulePerDay.builder()
                    .schedule(Mappers.ScheduleMapper.map(object.getSchedule()))
                    .restaurant(Mappers.RestaurantMapper.map(object.getRestaurant()))
                    .date(object.getDate())
                    .id(object.getId())
                    .build();
        }

        @Override
        public SchedulePerDayDto reverseMap(SchedulePerDay object) {
            if (object == null)
                return null;
            return SchedulePerDayDto.builder()
                    .schedule(Mappers.ScheduleMapper.reverseMap(object.getSchedule()))
                    .restaurant(Mappers.RestaurantMapper.reverseMap(object.getRestaurant()))
                    .id(object.getId())
                    .date(object.getDate())
                    .build();
        }
    }
}
