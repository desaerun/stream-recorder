package com.desaerun.streamrecorder.ffmpeg.config;

import net.bramp.ffmpeg.builder.FFmpegBuilder;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractCaptureConfig {
    private String name;

    private FFmpegBuilder builder;

    public AbstractCaptureConfig(CaptureConfigBuilder builder) {
        this.name = builder.name;

        this.builder = new FFmpegBuilder()
                .setInput(builder.getInputs().get(0));
    }

    protected AbstractCaptureConfig() {
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public abstract String getUrlString();

    public FFmpegBuilder getBuilder() {
        return builder;
    }

}
