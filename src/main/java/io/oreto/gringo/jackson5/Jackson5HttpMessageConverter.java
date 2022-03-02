package io.oreto.gringo.jackson5;

import io.oreto.jackson.Jackson5;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Jackson5HttpMessageConverter extends AbstractHttpMessageConverter<Object> {
    static List<MediaType> mediaTypes = new ArrayList<MediaType>() {{
        add(MediaType.APPLICATION_JSON);
    }};

    @Override
    protected boolean supports(Class<?> clazz) {
        return Jackson5Response.class.isAssignableFrom(clazz);
    }

    @Override
    protected Object readInternal(Class<?> clazz
            , HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String text = new BufferedReader(
                new InputStreamReader(inputMessage.getBody(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        return Jackson5.getOrDefault(clazz).deserialize(text, clazz);
    }

    @Override
    protected void writeInternal(Object response
            , HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Jackson5Response j5Response = (Jackson5Response) response;
        outputMessage.getBody().write(
                Jackson5.getOrDefault(j5Response.getName())
                        .serialize(j5Response.getBody(), j5Response.getFields(), j5Response.isPretty())
                        .getBytes(StandardCharsets.UTF_8)
        );
    }

    @Override
    public List<MediaType> getSupportedMediaTypes(Class<?> clazz) {
        return mediaTypes;
    }

    @Override
    protected boolean canRead(MediaType mediaType) {
        return mediaTypes.contains(mediaType);
    }

    @Override
    protected boolean canWrite(MediaType mediaType) {
        return mediaType == null || mediaTypes.contains(mediaType);
    }
}
