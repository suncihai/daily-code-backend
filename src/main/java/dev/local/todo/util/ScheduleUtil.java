package dev.local.todo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import dev.local.todo.util.JavaMailUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class ScheduleUtil {

    @Autowired
    private JavaMailUtil javaMailUtil;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 0 10,20 * * ?")
    public void reportCurrentTime() {
        try {
            javaMailUtil.sendSimpleEmail("happydailycode@gmail.com", new String[] { "suncihai@gmail.com" },
                    new String[] {}, "Reminder from Daily Code", "Please practise more problems");
        } catch (Exception e) {
            logger.error("邮件发送失败, 失败原因 :{} 。", e.getMessage(), e);
        }
    }
}