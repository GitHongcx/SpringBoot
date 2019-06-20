package com.test.cn;

        import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
        import com.test.cn.endpoint.MyEndPoint;
        import org.mybatis.spring.annotation.MapperScan;
        import org.mybatis.spring.annotation.MapperScans;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
        import org.springframework.context.annotation.*;

@SpringBootApplication
@EnableDubbo
@PropertySource({"classpath:dubbo.properties"})
@ComponentScan("com.test.cn")
@MapperScan("com.test.cn.dao.mybatis")
public class CnApplication {

    public static void main(String[] args) {
        SpringApplication.run(CnApplication.class, args);
    }

    @Configuration
    static class MyEndpointConfiguration{
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnEnabledEndpoint
        public MyEndPoint myEndPoint(){
            return new MyEndPoint();
        }
    }
}
