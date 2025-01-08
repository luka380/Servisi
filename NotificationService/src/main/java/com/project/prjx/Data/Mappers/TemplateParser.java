package com.project.prjx.Data.Mappers;

import com.project.prjx.Data.Model.Dto.Notifications.NotificationData;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TemplateParser {
    public String parseTemplate(String template, NotificationData data) {

        Map<String, String> values = data.getPlaceholderValues();

        Pattern pattern = Pattern.compile("\\$(\\w+)\\$");
        Matcher matcher = pattern.matcher(template);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String placeholder = matcher.group(1);
            String replacement = values.getOrDefault(placeholder, " ");
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }

        matcher.appendTail(result);
        return result.toString();
    }
}
