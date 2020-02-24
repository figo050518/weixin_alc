package com.fcgo.weixin.model.constant;

import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PictureType {

    JPG("JPG","image/jpeg"),
    JPEG("JPEG","image/jpeg"),
    PNG("PNG","image/png"),
    GIF("GIF","image/gif"),
    BMP("BMP","image/bmp"),
    ICO("ICO","image/x-icon");
    @Getter
    String ext;
    @Getter
    String mediaType;

    PictureType(String ext, String mediaType) {
        this.ext = ext;
        this.mediaType = mediaType;
    }

    private static final Map<String,PictureType> cache;
    static {
        cache = Stream.of(values()).collect(Collectors.toMap(PictureType::getExt, Function.identity()));
    }

    public static PictureType getPictureType(String ext){
        String finalExt;
        if (Objects.isNull(ext) || (finalExt=ext.trim()).length()==0 ){
            return null;
        }
        finalExt = finalExt.toUpperCase();
        return cache.get(finalExt);
    }
}
