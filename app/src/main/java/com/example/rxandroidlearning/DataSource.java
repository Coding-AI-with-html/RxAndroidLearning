package com.example.rxandroidlearning;

import java.util.ArrayList;
import java.util.List;

public class DataSource {


    public static List<Task> createTaskList(){
        List<Task> lists = new ArrayList<>();
        lists.add(new Task("Play some games", true, 3));
        lists.add(new Task("Walt out with the dog", true, 2));
        lists.add(new Task("wash dishes", false, 1));
        lists.add(new Task("Take out the trash", true, 0));
        lists.add(new Task("Call John", false, 4));


        return lists;
    }


}
