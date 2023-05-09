package com.example.demo.user.events;

import com.example.demo.dream.Dream;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data@Builder@AllArgsConstructor@NoArgsConstructor
public class DreamCreatedEvent {
    @Id
    @SequenceGenerator(
            name = "dream_sequence",
            sequenceName = "dream_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "dream_sequence"
    )
    private Long pid;
    private Long userId;
    private String description;
    private String tag;
    private int duration;
    private int energyLevel;
    private int stress;
    private String week;
    private String month;

}
