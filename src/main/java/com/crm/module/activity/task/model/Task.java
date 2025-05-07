package com.crm.module.activity.task.model;

import com.crm.module.activity.model.Activity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task extends Activity {

    private String taskName;

    private String description;

    private TaskStatus status;

    private LocalDate dueDate;
}
