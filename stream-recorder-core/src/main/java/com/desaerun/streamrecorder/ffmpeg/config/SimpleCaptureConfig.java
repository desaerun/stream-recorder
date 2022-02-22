package com.desaerun.streamrecorder.ffmpeg.config;

import java.net.MalformedURLException;
import java.net.URI;

public class SimpleCaptureConfig extends AbstractCaptureConfig {
    private final String urlString;

    SimpleCaptureConfig(String name, String urlString) throws MalformedURLException {
        setName(name);
        this.urlString = URI.create(urlString).toURL().toString();
    }

    @Override
    public String getUrlString() {
        return urlString;
    }

}
