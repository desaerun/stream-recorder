package com.desaerun.ffmpeg.progress;

import com.desaerun.ffmpeg.fixtures.Progresses;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.desaerun.ffmpeg.Helper.combineResource;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class StreamProgressParserTest {

  RecordingProgressListener listener = new RecordingProgressListener();

  @Test
  public void testNormal() throws IOException {
    listener.reset();

    StreamProgressParser parser = new StreamProgressParser(listener);

    InputStream inputStream = combineResource(Progresses.allFiles);
    parser.processStream(inputStream);

    assertThat(listener.progesses, equalTo((List<Progress>) Progresses.allProgresses));
  }
}
