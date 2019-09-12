package tech.ganyaozi.warframe.stat.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.ganyaozi.warframe.stat.translate.TranslationDictionary;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TranslateDictionaryTest {

    private static final Logger loggerException = LoggerFactory.getLogger(TranslateDictionaryTest.class);

    @Test
    @Ignore
    public void test() throws IOException {
        // 加载警报词典
        TranslationDictionary alertDictionary = new TranslationDictionary("alert");

        // 随便从字典里挑了个
        String raw = "Wildfire";
        String translated = alertDictionary.translate(raw);
        Assert.assertNotSame(raw, translated);

        loggerException.info("raw is : {}, translated is {} ", raw, translated);
    }
}
