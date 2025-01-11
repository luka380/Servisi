package com.project.prjx.Data.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MessageType {
    ACTIVATION,
    PASSWORD_RESET,
    RESERVATION_CONFIRMED,
    RESERVATION_CANCELLED,
    REMINDER;
}
