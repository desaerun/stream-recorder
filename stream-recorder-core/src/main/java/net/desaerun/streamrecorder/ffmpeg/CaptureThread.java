package net.desaerun.streamrecorder.ffmpeg;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.job.SinglePassFFmpegJob;
import net.desaerun.streamrecorder.ffmpeg.config.AbstractCaptureConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaptureThread extends SinglePassFFmpegJob {
    public static final Logger LOG = LoggerFactory.getLogger(CaptureThread.class);

    private AbstractCaptureConfig config;

    public CaptureThread(FFmpeg ffmpeg, AbstractCaptureConfig config) {
        super(ffmpeg, config.getBuilder(), progress -> System.out.println(progress));

        this.config = config;
    }

    @Override
    public synchronized void run() {
        LOG.info("Starting CaptureThread.");

        super.run();
        LOG.info("CaptureThread started.");
    }

    public AbstractCaptureConfig getConfig() {
        return config;
    }

}
