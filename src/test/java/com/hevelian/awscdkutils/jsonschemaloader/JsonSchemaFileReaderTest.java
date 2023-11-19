package com.hevelian.awscdkutils.jsonschemaloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.amazon.awscdk.services.apigateway.JsonSchema;
import software.amazon.awscdk.services.apigateway.JsonSchemaType;

import java.io.FileInputStream;
import java.util.List;

class JsonSchemaFileReaderTest {

  private final JsonSchemaLoader tested = new JsonSchemaLoader();

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  @SneakyThrows
  public void shouldLoadFileSuccessfully() {
    // Act
    try(var fileInputStream = new FileInputStream("src/test/resources/test.json")) {
      JsonSchema jsonSchema = tested.load(new FileInputStream("src/test/resources/test.json"));
    }
    JsonSchema jsonSchema = tested.load(new FileInputStream("src/test/resources/test.json"));

    // Assert
    var aDataProperty = jsonSchema.getProperties()
        .get("A");
    var bProperty = jsonSchema.getProperties()
        .get("B");

    Assertions.assertTrue(jsonSchema.getRequired()
        .containsAll(List.of("A", "B")));
    Assertions.assertEquals(aDataProperty.getType(), JsonSchemaType.OBJECT);
    Assertions.assertEquals(bProperty.getType(), JsonSchemaType.OBJECT);
    Assertions.assertEquals(2, aDataProperty.getProperties()
        .size());
    Assertions.assertEquals(2, bProperty.getProperties()
        .size());
    Assertions.assertEquals(JsonSchemaType.STRING, aDataProperty.getProperties()
        .get("A1")
        .getType());
    Assertions.assertEquals(JsonSchemaType.STRING, aDataProperty.getProperties()
        .get("A2")
        .getType());
    Assertions.assertEquals(JsonSchemaType.STRING, bProperty.getProperties()
        .get("B1")
        .getType());
    Assertions.assertEquals(JsonSchemaType.STRING, bProperty.getProperties()
        .get("B2")
        .getType());
  }
}