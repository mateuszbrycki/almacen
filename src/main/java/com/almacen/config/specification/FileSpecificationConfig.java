package com.almacen.config.specification;


import com.almacen.module.file.UserFile;
import com.almacen.module.file.specification.UserFileSpecification;
import com.almacen.module.file.specification.extension.UserFileExtensionSpecification;
import com.almacen.module.file.specification.size.UserFileSizeSpecification;
import com.almacen.specification.Specification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileSpecificationConfig {

    @Bean
    @Qualifier("extensionSpecification")
    public Specification<UserFile> userFileExtensionSpecification() {
        return new UserFileExtensionSpecification();
    }

    @Bean
    @Qualifier("userFileSizeSpecification")
    public Specification<UserFile> userFileSizeSpecification() {
        return new UserFileSizeSpecification();
    }

    @Bean
    public UserFileSpecification userFileSpecification(
            @Qualifier("extensionSpecification") Specification<UserFile> userFileExtensionSpecification,
            @Qualifier("userFileSizeSpecification") Specification<UserFile> userFileSizeSpecification) {
        return new UserFileSpecification(userFileExtensionSpecification, userFileSizeSpecification);
    }
}
