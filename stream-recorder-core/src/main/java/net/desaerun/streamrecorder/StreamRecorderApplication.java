package net.desaerun.streamrecorder;

import net.bramp.ffmpeg.FFmpeg;
import net.desaerun.streamrecorder.ffmpeg.config.SimpleCaptureConfig;

import java.io.IOException;

public class StreamRecorderApplication {

    private static final String FFMPEG_LOCATION = "C:\\Program Files\\ffmpeg\\bin\\ffmpeg.exe";

    public static void main(String[] args) throws IOException {
        FFmpeg ffmpeg = new FFmpeg(FFMPEG_LOCATION);

        CaptureController controller = new CaptureController(ffmpeg);
        SimpleCaptureConfig config = new SimpleCaptureConfig("default", "rtsp://desaerun:kjqyws@192.168.1.216", "z.mp4");

        controller.addConfig(config);
        controller.run(config);
    }

}
