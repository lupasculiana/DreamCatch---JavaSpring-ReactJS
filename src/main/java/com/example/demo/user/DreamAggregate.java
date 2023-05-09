package com.example.demo.user;

import com.example.demo.command.api.CreateDreamCommand;
import com.example.demo.user.events.DreamCreatedEvent;
import jakarta.persistence.*;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class DreamAggregate {
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


    public DreamAggregate(CreateDreamCommand createDreamCommand){
        //You can perform all the validations
        DreamCreatedEvent dreamCreatedEvent =
                new DreamCreatedEvent();

        BeanUtils.copyProperties(createDreamCommand, dreamCreatedEvent);
        AggregateLifecycle.apply(dreamCreatedEvent);

    }

    public DreamAggregate(){

    }

    @EventSourcingHandler
    public void on(DreamCreatedEvent dreamCreatedEvent){
        this.pid = dreamCreatedEvent.getPid();
        this.userId = dreamCreatedEvent.getUserId();
        this.description = dreamCreatedEvent.getDescription();
        this.duration = dreamCreatedEvent.getDuration();
        this.energyLevel = dreamCreatedEvent.getEnergyLevel();
        this.stress = dreamCreatedEvent.getStress();
        this.tag = dreamCreatedEvent.getTag();
        this.week = dreamCreatedEvent.getWeek();
        this.month = dreamCreatedEvent.getMonth();

    }

}
