package com.crm.module.activity.call.model;

import com.crm.module.activity.model.Activity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Call extends Activity {
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private CallType type;
}
