package net.desaerun.streamrecorder.ffmpeg.config;

import java.util.ArrayList;
import java.util.List;

public class CaptureConfigBuilder {
    private String name;
    private List<String> inputs;
    private List<String> outputs;
    private RecordingSchedule schedule;

    public CaptureConfigBuilder(String name, String input, String output) {
        this.name = name;
        this.inputs = new ArrayList<>(List.of(input));
        this.outputs = new ArrayList<>(List.of(output));
    }

    public String getName() {
        return name;
    }

    public CaptureConfigBuilder name(String name) {
        this.name = name;
        return this;
    }

    public List<String> getInputs() {
        return inputs;
    }

    public CaptureConfigBuilder inputs(List<String> inputs) {
        this.inputs = inputs;
        return this;
    }

    public List<String> getOutputs() {
        return outputs;
    }

    public CaptureConfigBuilder outputs(List<String> outputs) {
        this.outputs = outputs;
        return this;
    }

    public RecordingSchedule getSchedule() {
        return schedule;
    }

    public CaptureConfigBuilder schedule(RecordingSchedule schedule) {
        this.schedule = schedule;
        return this;
    }

}
