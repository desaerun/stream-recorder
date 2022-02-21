package com.desaerun.streamrecorder;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.job.FFmpegJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component("controller")
public class Controller {
    final FFmpeg ffmpeg = new FFmpeg(FFMPEG_LOCATION);

    FFmpegExecutor executor;

    List<FFmpegJob> jobs = new ArrayList<>();

    public Controller(FFmpeg ffmpeg) throws IOException {
        this.ffmpeg = ffmpeg;

        executor = new FFmpegExecutor(ffmpeg);
    }

}
