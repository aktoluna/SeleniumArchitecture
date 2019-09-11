package io.aktoluna.slnarch.common.file.parser;

import static org.hamcrest.Matchers.is;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class YamlParserTest {

  @Test
  public void parseFile() throws Exception {
    YamlParser yamlParser = new YamlParser();
    List<String> expect = new ArrayList<>();
    expect.add("Hesperiidae");
    expect.add("Papilionidae");
    expect.add("Apatelodidae");
    expect.add("Epiplemidae");
    List output2 = yamlParser
        .parseFile(new FileInputStream("src/test/resources/yaml-example.yaml"), expect.getClass());
    Assert.assertThat(output2, is(expect));
  }

}