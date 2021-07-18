package com.escola.util;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class AbstractDomainEvent {

    private List<DomainEvent> domainEventList = new ArrayList();

    public AbstractDomainEvent() {

    }

    protected void registerEvent(@NonNull DomainEvent event){
        this.domainEventList.add(event);
    }

    protected void removeEvents(){
        this.domainEventList.clear();
    }

    public Collection<DomainEvent> getEvents(){
        return this.domainEventList;
    }
}

