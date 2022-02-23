package com.desaerun.streamrecorder;

import com.desaerun.streamrecorder.ffmpeg.CaptureThread;
import com.desaerun.streamrecorder.ffmpeg.config.AbstractCaptureConfig;
import com.desaerun.ffmpeg.FFmpeg;
import com.desaerun.ffmpeg.FFmpegExecutor;
import com.desaerun.ffmpeg.job.FFmpegJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaptureController {
    private static final Logger LOG = LoggerFactory.getLogger(CaptureController.class);

    final FFmpeg ffmpeg;

    final FFmpegExecutor executor;

    final List<AbstractCaptureConfig> configs = new ArrayList<>();

    final Map<String, CaptureThread> captures = new HashMap<>();

    public CaptureController(FFmpeg ffmpeg) throws IOException {
        this.ffmpeg = ffmpeg;

        executor = new FFmpegExecutor(ffmpeg);
    }

    public List<AbstractCaptureConfig> getConfigs() {
        return configs;
    }

    public CaptureController addConfig(AbstractCaptureConfig config) {
        if (config == null) {
            return this;
        }
        configs.add(config);

        return this;
    }

    public Map<String, CaptureThread> getCaptureMap() {
        return captures;
    }

    public List<CaptureThread> getCaptureList() {
        return (List<CaptureThread>) captures.values();
    }

    public CaptureThread getCapture(String name) {
        return captures.get(name);
    }

    public void startAll() {
        for (AbstractCaptureConfig config : configs) {
            start(config);
        }
    }

    public CaptureThread start(AbstractCaptureConfig config) {
        CaptureThread captureThread = captures.get(config.getName());

        if (captureThread == null) {
            LOG.info("captureThread was null, creating thread...");
            FFmpegJob job = executor.createJob(config.getBuilder());

            captureThread = new CaptureThread(job, config);

            LOG.info("captureThread created.");
            captures.put(config.getName(), captureThread);
        }
        if (!captureThread.isStarted()) {
            LOG.info("starting CaptureThread...");
            captureThread.start();
        }

        return captureThread;
    }

}
