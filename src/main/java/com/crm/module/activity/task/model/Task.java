package com.crm.module.activity.task.model;

import com.crm.module.activity.model.Activity;
import com.crm.module.activity.model.RelatedModule;
import com.crm.user.appuser.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Task extends Activity {

    private String taskName;

    private String description;

    private TaskStatus status;

    private LocalDate dueDate;

    public Task(RelatedModule relatedModule, UUID relatedId, User owner,String taskName, String description, LocalDate dueDate) {
        super(relatedModule, relatedId, owner);
        this.taskName = taskName;
        this.description = description;
        this.status = TaskStatus.IN_PROGRESS;
        this.dueDate = dueDate;
    }

    public void close() {
        this.status = TaskStatus.DONE;
    }

    public void update(String taskName, String description, LocalDate dueDate, User owner) {
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
        this.setOwner(owner);
    }
}
