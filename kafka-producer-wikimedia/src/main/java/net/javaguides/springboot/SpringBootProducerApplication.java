package net.javaguides.springboot;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@AllArgsConstructor
public class SpringBootProducerApplication implements CommandLineRunner
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringBootProducerApplication.class);
    }

    private WikimediaChangesProducer wikimediaChangesProducer;

    @Override
    public void run(String... args) throws Exception {
        wikimediaChangesProducer.sendMessage();
    }
}
