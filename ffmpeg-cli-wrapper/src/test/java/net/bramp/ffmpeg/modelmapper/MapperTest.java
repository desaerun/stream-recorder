package com.desaerun.ffmpeg.modelmapper;

import com.desaerun.ffmpeg.builder.FFmpegOutputBuilder;
import com.desaerun.ffmpeg.options.AudioEncodingOptions;
import com.desaerun.ffmpeg.options.EncodingOptions;
import com.desaerun.ffmpeg.options.MainEncodingOptions;
import com.desaerun.ffmpeg.options.VideoEncodingOptions;
import org.junit.Test;

public class MapperTest {

  @Test
  public void testMapping() {
    MainEncodingOptions main = new MainEncodingOptions("mp4", 0L, null);
    AudioEncodingOptions audio = new AudioEncodingOptions(false, null, 0, 0, null, 0, 0.0);
    VideoEncodingOptions video =
        new VideoEncodingOptions(
            true, null, null, 320, 0, 0, null, "scale='320:trunc(ow/a/2)*2'", null);

    EncodingOptions options = new EncodingOptions(main, audio, video);

    FFmpegOutputBuilder mappedObj = new FFmpegOutputBuilder();

    Mapper.map(options, mappedObj);

    // TODO Add actual test!
  }
}
