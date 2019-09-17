package itsurena.ir.demo2.xmlClass.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by m.parvizi on 3/4/2019.
 */
@Component
public class CommonValueUtil {
    private static final Logger log = LoggerFactory.getLogger(CommonValueUtil.class);
    public static final String COMMON_VALUE_EXIST_BEFORE = "FATAL: commonValue exist before";

    private static ConcurrentHashMap<String, CommonValueDto> allCommonValues = new ConcurrentHashMap<>();
    @Autowired
    private CommonValueService commonValueService;

    public static CommonValueDto getByCommonValueByTypeValueKey(String typeValueKey) {
        return allCommonValues.get(typeValueKey);
    }

    @PostConstruct
    public synchronized void init() {

        List<CommonValueDto> commonValueDtos = commonValueService.findAll();
        for (CommonValueDto commonValueDto : commonValueDtos) {
            CommonTypeDto commonType = commonValueDto.getCommonType();
            String storedKey = commonType.getKey() + "." + commonValueDto.getKey();

            if (allCommonValues.containsKey(storedKey)) {
                log.error(COMMON_VALUE_EXIST_BEFORE + storedKey);
            } else {
                allCommonValues.put(storedKey, commonValueDto);
            }
        }
    }

}
