package com.project.prjx.Data.Mappers;

import com.project.prjx.Data.Model.Dto.Restaurants.RestaurantDto;
import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import com.project.prjx.Data.Model.Dto.Users.ClientDto;
import com.project.prjx.Data.Model.Dto.Users.EmailDto;
import com.project.prjx.Data.Model.Dto.Users.ManagerDto;
import com.project.prjx.Data.Model.Entity.Restaurants.Restaurant;
import com.project.prjx.Data.Model.Entity.Users.BaseUser;
import com.project.prjx.Data.Model.Entity.Users.Client;
import com.project.prjx.Data.Model.Entity.Users.Email;
import com.project.prjx.Data.Model.Entity.Users.Manager;
import org.springframework.stereotype.Component;

@Component
public class Mappers {
    public static ClientMapper ClientMapper = new ClientMapper();
    public static EmailMapper EmailMapper = new EmailMapper();
    public static RestaurantMapper RestaurantMapper = new RestaurantMapper();
    public static ManagerMapper ManagerMapper = new ManagerMapper();
    public static BaseUserMapper BaseUserMapper = new BaseUserMapper();

    public static class ClientMapper implements Mapper<Client, ClientDto> {

        @Override
        public ClientDto map(Client object) {
            if(object == null)
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
            if(object == null)
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
            if(object == null)
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
            if(object == null)
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
            if(object == null)
                return null;

            return Email.builder()
                    .email(object.email())
                    .isVerified(object.isVerified())
                    .id(object.id())
                    .build();
        }

        @Override
        public EmailDto reverseMap(Email object) {
            if(object == null)
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
            if(object == null)
                return null;
            return Restaurant.builder()
                    .address(object.address())
                    .name(object.name())
                    .phone(object.phone())
                    .id(object.id())
                    .build();
        }

        @Override
        public RestaurantDto reverseMap(Restaurant object) {
            if(object == null)
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
            if(object == null)
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
            if(object == null)
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
}
