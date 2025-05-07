package com.crm.module.activity.meeting.model;

import com.crm.module.activity.model.Activity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Meeting extends Activity {
    private String topic;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
