package com.example.TodoApi.Service;

import com.example.TodoApi.Model.Activity;

import java.util.List;

// Interface för mina metoder som ska användas av service
public interface TodoInterface {
    List<Activity> getAllActivities();
    Activity getActivityById(int id);
    Activity getActivityByTitle(String title);
    Activity addActivity(Activity activity);
    Activity updateActivity(int id, Activity activity);
    boolean deleteActivity(int id);
}
