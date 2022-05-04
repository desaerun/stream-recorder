package net.desaerun.streamrecorder.ffmpeg.config;

import net.bramp.ffmpeg.builder.FFmpegBuilder;

public abstract class AbstractCaptureConfig {
    private String name;

    private FFmpegBuilder builder;

    public AbstractCaptureConfig(CaptureConfigBuilder builder) {
        this.name = builder.getName();

        this.builder = new FFmpegBuilder()
                .setInput(builder.getInputs().get(0));
        this.builder.addOutput(builder.getOutputs().get(0));
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
