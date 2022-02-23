package com.desaerun.ffmpeg.job;

import com.google.common.base.Throwables;
import com.desaerun.ffmpeg.FFmpeg;
import com.desaerun.ffmpeg.builder.FFmpegBuilder;
import com.desaerun.ffmpeg.progress.ProgressListener;

import javax.annotation.Nullable;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class SinglePassFFmpegJob extends FFmpegJob {

  public final FFmpegBuilder builder;

  private Process process;

  public SinglePassFFmpegJob(FFmpeg ffmpeg, FFmpegBuilder builder) {
    this(ffmpeg, builder, null);
  }

  public SinglePassFFmpegJob(
      FFmpeg ffmpeg, FFmpegBuilder builder, @Nullable ProgressListener listener) {
    super(ffmpeg, listener);
    this.builder = checkNotNull(builder);

    // Build the args now (but throw away the results). This allows the illegal arguments to be
    // caught early, but also allows the ffmpeg command to actually alter the arguments when
    // running.
    List<String> unused = this.builder.build();
  }

  @Override
  public void run() {

    state = State.RUNNING;

    try {
      process = ffmpeg.run(builder, listener);
      state = State.FINISHED;

    } catch (Throwable t) {
      state = State.FAILED;

      Throwables.throwIfUnchecked(t);
      throw new RuntimeException(t);
    }
  }

  public Process getProcess() {
    return process;
  }

}
