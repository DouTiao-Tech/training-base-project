package com.darcytech.training.core.catalog.dto;

import java.time.LocalDateTime;

public interface CartP {

    Long getId();

    LocalDateTime getCreatedTime();

    int getPayment();

    String getName();

}
