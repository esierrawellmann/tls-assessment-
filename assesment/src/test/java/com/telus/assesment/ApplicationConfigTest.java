package com.telus.assesment;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.assertj.core.api.Assertions;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ApplicationConfigTest {
    @Autowired
    ApplicationConfig applicationConfig;
    @Test
    void modelMapperTest(){
        ModelMapper mapper = applicationConfig.modelMapper();
        Assertions.assertThat(mapper).isInstanceOfAny(ModelMapper.class);
    }
}
