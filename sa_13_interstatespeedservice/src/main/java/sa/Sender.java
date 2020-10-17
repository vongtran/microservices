package sa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    @Autowired
    private KafkaTemplate<String, SpeedRecord> kafkaTemplate;

    public void send(String topic, SpeedRecord speedRecord){
        kafkaTemplate.send(topic, speedRecord);
    }
}
