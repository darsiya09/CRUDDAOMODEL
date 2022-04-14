import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import ru.amazin.daoModel.config.KafkaConfig;
import ru.amazin.daoModel.models.Message;

import java.util.concurrent.ExecutionException;

public class Starter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KafkaConfig.class);
        KafkaTemplate<Long, Message> template = context.getBean(KafkaTemplate.class);
        try {
            template.send("test1", new Message("first", "second")).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
