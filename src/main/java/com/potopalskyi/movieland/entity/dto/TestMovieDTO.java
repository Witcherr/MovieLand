package com.potopalskyi.movieland.entity.dto;

import com.potopalskyi.movieland.entity.business.Genre;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "testMovie")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestMovieDTO {

    private int id;

    @XmlElementWrapper(name = "Genres")
    @XmlElement(name = "genre")
    private List<Genre> genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }
}
