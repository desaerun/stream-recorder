package com.desaerun.streamrecorder;

import com.desaerun.streamrecorder.ffmpeg.config.SimpleCaptureConfig;
import com.desaerun.ffmpeg.FFmpeg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class StreamRecorderApplication {
	private static final String FFMPEG_LOCATION = "C:\\Program Files\\ffmpeg\\bin\\ffmpeg.exe";

	public static void main(String[] args) throws IOException {
		SpringApplication.run(StreamRecorderApplication.class, args);

		doStuff();
	}

	private static void doStuff() throws IOException {

		FFmpeg ffmpeg = new FFmpeg(FFMPEG_LOCATION);

		CaptureController controller = new CaptureController(ffmpeg);
		SimpleCaptureConfig config = new SimpleCaptureConfig("default", "rtsp://desaerun:kjqyws@192.168.1.216");

		controller.addConfig(config);
		controller.start(config);
	}

}
