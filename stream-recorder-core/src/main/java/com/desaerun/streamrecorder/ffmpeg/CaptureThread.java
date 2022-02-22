package com.desaerun.streamrecorder.ffmpeg;

import com.desaerun.streamrecorder.ffmpeg.config.AbstractCaptureConfig;
import net.bramp.ffmpeg.job.FFmpegJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class CaptureThread extends Thread {
    public static final Logger LOG = LoggerFactory.getLogger(CaptureThread.class);

    private FFmpegJob job;
    private AbstractCaptureConfig config;

    public CaptureThread(FFmpegJob job, AbstractCaptureConfig config) {
        super(checkNotNull(job, "job must not be null!"));

        this.job = job;
        this.config = config;
    }

    @Override
    public synchronized void start() {
        LOG.info("Starting CaptureThread.");
        super.start();
        LOG.info("CaptureThread started.");
    }

    public boolean isStarted() {
        return job.getState() == FFmpegJob.State.RUNNING;
    }

    public FFmpegJob getJob() {
        return job;
    }

    public AbstractCaptureConfig getConfig() {
        return config;
    }
}
