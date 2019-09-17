package itsurena.ir.demo2.utils;

import org.modelmapper.ModelMapper;

public class ModelMapperUtil {

    private ModelMapperUtil(){}

    public static ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
