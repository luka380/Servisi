package com.project.prjx.Data.Model.Dto.Notifications;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.prjx.Data.Model.MessageType;
import lombok.*;

import java.util.HashMap;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonDeserialize
@JsonSerialize
@Data
public class NotificationData {
    private MessageType type;
    private String restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String reservationId;
    private String reservationTime;
    private String reservationDate;
    private String reservationStatus;
    private String receiverEmail;
    private String receiverName;
    private String receiverPhone;
    private String receiverId;
    private String senderName;
    private String securityCode;
    private int numberOfSeats;

    public HashMap<String, String> getPlaceholderValues() {
        HashMap<String, String> placeholderValues = new HashMap<>();
        placeholderValues.put("restaurant_name", restaurantName);
        placeholderValues.put("restaurant_address", restaurantAddress);
        placeholderValues.put("reservation_time", reservationTime);
        placeholderValues.put("reservation_date", reservationDate);
        placeholderValues.put("reservation_status", reservationStatus);
        placeholderValues.put("receiver_name", receiverName);
        placeholderValues.put("receiver_phone", receiverPhone);
        placeholderValues.put("sender_name", senderName);
        placeholderValues.put("security_code", securityCode);
        placeholderValues.put("number_of_seats", String.valueOf(numberOfSeats));
        placeholderValues.put("reservation_id", reservationId);
        placeholderValues.put("restaurant_id", restaurantId);
        placeholderValues.put("receiver_id", receiverId);
        placeholderValues.put("sender_email", receiverEmail);
        placeholderValues.put("receiver_email", receiverEmail);
        placeholderValues.put("sender_phone", receiverPhone);
        return placeholderValues;
    }
}
