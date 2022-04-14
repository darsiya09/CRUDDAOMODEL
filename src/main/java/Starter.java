import org.apache.kafka.common.protocol.types.Field;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import ru.amazin.daoModel.config.KafkaConfig;
import ru.amazin.daoModel.models.AnyObject;
import ru.amazin.daoModel.models.Message;
import ru.amazin.daoModel.models.MessageTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Starter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KafkaConfig.class);
        KafkaTemplate<Long, MessageTemplate> template = context.getBean(KafkaTemplate.class);
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Dimon");
        map.put(2, "Klyan");
        map.put(3, "Vanek");
        try {
            template.send("test1", new Message("first1", "second1")).get();
            template.send("test1", new AnyObject("first", "second",
                    map, new String[]{"aaaa", "bbbb", "cccc"}));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
