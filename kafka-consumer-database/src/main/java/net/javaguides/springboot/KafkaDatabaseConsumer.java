package net.javaguides.springboot;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.entity.WikiData;
import net.javaguides.springboot.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaDatabaseConsumer {

    private WikimediaDataRepository wikimediaDataRepository;
    private static final Logger LOGGER= LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
    @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String messageEvent){
        LOGGER.info(String.format("Event Message Received -> %s",messageEvent));
        WikiData wikiData=new WikiData();
        wikiData.setWikiEventData(messageEvent);
        wikimediaDataRepository.save(wikiData);
    }
}
