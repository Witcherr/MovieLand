package com.potopalskyi.movieland.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "movies")
@XmlAccessorType(XmlAccessType.FIELD)
public class MovieListDTO {

    @XmlElement(name = "movie")
    @JsonProperty("movies")
    private List<MovieDTO> movieDTOList;

    public List<MovieDTO> getMovieDTOList() {
        return movieDTOList;
    }

    public void setMovieDTOList(List<MovieDTO> movieDTOList) {
        this.movieDTOList = movieDTOList;
    }
}
