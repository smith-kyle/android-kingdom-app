package com.dev.kylesmith.myriadmobilechallenge.model;

import java.util.List;

/**
 * Created by kylesmith on 3/24/15.
 */
public class Kingdom {
    public int id;
    public String name;
    public String image;
    public String climate;
    public int population;
    public List<quest> quests;

    public Kingdom(){
        id = 0;
        name = "";
        image = "";
    }

    public Kingdom(int id, String name, String image){
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getImage(){
        return image;
    }
}
