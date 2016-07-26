package com.potopalskyi.movieland.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.potopalskyi.movieland.entity.business.Genre;

import java.io.IOException;

public class CustomGenreSerializer extends JsonSerializer<Genre>{
    @Override
    public void serialize(Genre genre, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        //jsonGenerator.writeStartArray();
        jsonGenerator.writeString(genre.getName());
        //jsonGenerator.writeEndArray();
    }
}
