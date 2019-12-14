package abiz.ir.demo2.jms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


@Component
public class RequestObjectPool {

    private final Logger logger = LogManager.getLogger(RequestObjectPool.class);
    private ConcurrentHashMap<String, ObjectPoolType> objectPoolHashMap = new ConcurrentHashMap<>();

    public void insert(String messageId, Object obj) {
        ObjectPoolType dto = new ObjectPoolType();
        dto.setHasResponse(false);
        dto.setObj(obj);

        objectPoolHashMap.put(messageId, dto);
        logger.info(String.format("message_id %s is adding into objectPoolHashMap and waiting for given response from central bank!", messageId));
    }

    public ObjectPoolType retrieveAndRemove(String messageId) {
        ObjectPoolType result = objectPoolHashMap.remove(messageId);
        if (result != null) {
            logger.info(String.format("message_id %s is retrieved and remove from objectPoolHashMap!", messageId));
            return result;
        } else {
            return null;
        }
    }

    public ObjectPoolType retrieve(String messageId) {
        ObjectPoolType result = objectPoolHashMap.get(messageId);
        if (result != null) {
            result.setHasResponse(true);

            logger.info(String.format("message_id %s is retrieved!", messageId));
            return result;
        }
        return null;
    }
}

