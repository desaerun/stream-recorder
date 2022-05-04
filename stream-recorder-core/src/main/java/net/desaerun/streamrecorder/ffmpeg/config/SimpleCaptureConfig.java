package net.desaerun.streamrecorder.ffmpeg.config;

import com.google.common.base.Preconditions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class SimpleCaptureConfig extends AbstractCaptureConfig {
    private static Pattern urlPattern =
            Pattern.compile("^(https?|ftp|file|rtsp|)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    private final String urlString;

    public SimpleCaptureConfig(String name, String input, String output) {
        super(new CaptureConfigBuilder(name, input, output));
        Preconditions.checkState(urlPattern.matcher(input).matches(), "This is not a valid url.");

        this.urlString = input;
    }

    @Override
    public String getUrlString() {
        return urlString;
    }

}
