package com.example.admin.entity;

/**
 * eventbus 通用类
 */

public class EventCommon<T>{
    public static final int INTENT_TOOLBAR_IMAGE=1;

    private int tag=0;
    private T event;

    public EventCommon(T event, int tag) {
        this.event = event;
        this.tag = tag;
    }

    public EventCommon(T event) {
        this.event = event;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public T getEvent() {
        return event;
    }

    public void setEvent(T event) {
        this.event = event;
    }
}
