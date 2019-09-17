package itsurena.ir.demo2.xmlClass.dto;

import java.util.List;

public interface CommonValueService {
    CommonValueDto createCommonValueByCommonType(CommonValueDto commonValueDto, long commonTypeCode);

    CommonValueDto updateCommonValue(CommonValueDto commonValueDto);

    List<CommonValueDto> getCommonValuesByCommonTypeCode(long commonTypeCode);

    List<CommonValueDto> findAll();
}
