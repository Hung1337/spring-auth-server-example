package me.hung.auth.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class MessageReader {

    private final Properties properties;

    public MessageReader() {
        this.properties = new Properties();
        try {
            InputStream is = MessageReader.class.getClassLoader().getResourceAsStream("messages.properties");
            properties.load(is);
            if (is != null) {
                is.close();
            }
        } catch (IOException ignored) {
            // ignored
        }
    }

    public String getMessage(String code) {
        Object obj = this.properties.get(code);
        if (obj == null) {
            return "Message not found.";
        }
        return obj.toString();
    }


}
