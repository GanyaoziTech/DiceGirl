package tech.ganyaozi.warframe.stat.translate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @author Derek
 */
public class TranslationDictionary {

    private static final Logger loggerException = LoggerFactory.getLogger(TranslationDictionary.class);

    /**
     * file path pattern
     */
    private static final String FILE_PATTERN = "lexicon/WF_%s.json";

    /**
     * EN to zhCN map
     */
    private HashMap<String, String> dictionary = new HashMap<>();

    public TranslationDictionary(String dictionaryAlias) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(String.format(FILE_PATTERN, dictionaryAlias));
        loadDictJson(classPathResource.getInputStream());
    }

    /**
     * translate EN word from api to zhCN
     *
     * @param enStr raw string in English
     * @return string in zhCN, or origin text
     */
    public String translate(String enStr) {
        if (StringUtils.isBlank(enStr)) {
            loggerException.error("Expect string in English, but get empty");
            return enStr;
        }
        String translated = dictionary.get(enStr);
        return translated == null ? enStr : translated;
    }

    /**
     * load translation dictionary from json file
     *
     * @param fileInputStream file
     * @throws IOException e
     */
    private void loadDictJson(InputStream fileInputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String tempString;
                while ((tempString = reader.readLine()) != null) {
                    stringBuilder.append(tempString);
                }
            }
            JSONArray array = JSONArray.parseArray(stringBuilder.toString());

            array.forEach(obj -> {
                JSONObject json = (JSONObject) obj;
                dictionary.put(json.getString("en"), json.getString("zh"));
            });
    }
}
