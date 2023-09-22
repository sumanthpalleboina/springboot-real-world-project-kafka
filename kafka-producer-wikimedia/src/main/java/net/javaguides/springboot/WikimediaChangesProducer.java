package net.javaguides.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private KafkaTemplate<String,String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final Logger LOGGER= LoggerFactory.getLogger(WikimediaChangesProducer.class);

    public void sendMessage() throws InterruptedException {
        EventHandler eventHandler=new WikimediaChangesHandler(kafkaTemplate,topicName);
        EventSource.Builder builder=new EventSource.Builder(eventHandler,
                URI.create("https://stream.wikimedia.org/v2/stream/recentchange"));
        EventSource eventSource=builder.build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(5);
    }

}
