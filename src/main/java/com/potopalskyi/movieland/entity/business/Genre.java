package com.potopalskyi.movieland.entity.business;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.potopalskyi.movieland.util.serializer.CustomGenreSerializer;

import javax.xml.bind.annotation.*;

//@JsonIgnoreProperties({ "id" })
@JsonSerialize(using = CustomGenreSerializer.class)
//@XmlRootElement(name = "genre")
public class Genre {

    private int id;

    //@JsonProperty
    private String name;

    @XmlTransient
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

