package com.desaerun.ffmpeg;

import com.desaerun.ffmpeg.builder.FFmpegBuilder;
import com.desaerun.ffmpeg.job.FFmpegJob;
import com.desaerun.ffmpeg.job.SinglePassFFmpegJob;
import com.desaerun.ffmpeg.job.TwoPassFFmpegJob;
import com.desaerun.ffmpeg.progress.ProgressListener;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

public class FFmpegExecutor {

  final com.desaerun.ffmpeg.FFmpeg ffmpeg;
  final com.desaerun.ffmpeg.FFprobe ffprobe;

  public FFmpegExecutor() throws IOException {
    this(new com.desaerun.ffmpeg.FFmpeg(), new com.desaerun.ffmpeg.FFprobe());
  }

  public FFmpegExecutor(com.desaerun.ffmpeg.FFmpeg ffmpeg) throws IOException {
    this(ffmpeg, new com.desaerun.ffmpeg.FFprobe());
  }

  public FFmpegExecutor(com.desaerun.ffmpeg.FFmpeg ffmpeg, com.desaerun.ffmpeg.FFprobe ffprobe) {
    this.ffmpeg = checkNotNull(ffmpeg);
    this.ffprobe = checkNotNull(ffprobe);
  }

  public FFmpegJob createJob(FFmpegBuilder builder) {
    return new SinglePassFFmpegJob(ffmpeg, builder);
  }

  public FFmpegJob createJob(FFmpegBuilder builder, ProgressListener listener) {
    return new SinglePassFFmpegJob(ffmpeg, builder, listener);
  }

  /**
   * Creates a two pass job, which will execute FFmpeg twice to produce a better quality output.
   * More info: https://trac.ffmpeg.org/wiki/x264EncodingGuide#twopass
   *
   * @param builder The FFmpegBuilder
   * @return A new two-pass FFmpegJob
   */
  public FFmpegJob createTwoPassJob(FFmpegBuilder builder) {
    return new TwoPassFFmpegJob(ffmpeg, builder);
  }
}
