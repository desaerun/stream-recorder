package com.desaerun.streamrecorder;

import com.desaerun.streamrecorder.ffmpeg.CaptureThread;
import com.desaerun.streamrecorder.ffmpeg.JobThread;
import com.desaerun.streamrecorder.ffmpeg.CaptureConfig;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.job.FFmpegJob;

import java.io.IOException;
import java.util.*;

public class CaptureController {
    final FFmpeg ffmpeg;

    final FFmpegExecutor executor;

    final List<CaptureConfig> configs = new ArrayList<>();

    final Map<String, CaptureThread> captures = new HashMap<>();

    public CaptureController(FFmpeg ffmpeg) throws IOException {
        this.ffmpeg = ffmpeg;

        executor = new FFmpegExecutor(ffmpeg);
    }

    public List<CaptureConfig> getConfigs() {
        return configs;
    }

    public CaptureController addConfig(CaptureConfig config) {
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

    public CaptureThread start(CaptureConfig config) {
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

    public void startAll() {
        for (CaptureConfig config : configs) {
            start(config);
        }
    }
}
