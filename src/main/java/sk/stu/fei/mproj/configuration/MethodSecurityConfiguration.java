package sk.stu.fei.mproj.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.SecuredAnnotationSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import sk.stu.fei.mproj.security.RoleSecuredAnnotationMetadataExtractor;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
    @Bean
    @Override
    protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
        return new SecuredAnnotationSecurityMetadataSource(new RoleSecuredAnnotationMetadataExtractor());
    }
}
