package tech.ganyaozi.warframe.stat;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tech.ganyaozi.warframe.stat.util.TranslationDictionary;

import java.io.IOException;

/**
 * @author jv190
 */
@EnableSwagger2
@SpringBootApplication
public class WarframeStateApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarframeStateApplication.class, args);
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteDateUseDateFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters((HttpMessageConverter<?>) fastConverter);
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("tech.ganyaozi.warframe"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Warframe Stat api")
                .description("干腰子科技的warfrmae api文档，欢迎PR")
                .termsOfServiceUrl("https://github.com/GanyaoziTech")
                .version("1.0")
                .build();
    }

    @Bean(name = "nightwaveDictionary")
    public TranslationDictionary nightwaveDictionary() throws IOException {
        return new TranslationDictionary("NightWave");
    }

    @Bean(name = "commonDictionary")
    public TranslationDictionary commonDictionary() throws IOException {
        return new TranslationDictionary("Dict");
    }


    @Bean(name = "alertDictionary")
    public TranslationDictionary alertDictionary() throws IOException {
        return new TranslationDictionary("Alert");
    }

    @Bean(name = "invasionDictionary")
    public TranslationDictionary invasionDictionary() throws IOException {
        return new TranslationDictionary("Invasion");
    }

    @Bean(name = "modifierDictionary")
    public TranslationDictionary modifierDictionary() throws IOException {
        return new TranslationDictionary("Modifier");
    }

    @Bean(name = "relicDictionary")
    public TranslationDictionary relicDictionary() throws IOException {
        return new TranslationDictionary("Relic");
    }

    @Bean(name = "rivenDictionary")
    public TranslationDictionary rivenDictionary() throws IOException {
        return new TranslationDictionary("Riven");
    }

    @Bean(name = "saleDictionary")
    public TranslationDictionary saleDictionary() throws IOException {
        return new TranslationDictionary("Sale");
    }


}
