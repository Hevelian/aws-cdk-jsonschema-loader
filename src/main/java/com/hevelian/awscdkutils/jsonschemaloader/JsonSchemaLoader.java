package com.hevelian.awscdkutils.jsonschemaloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hevelian.awscdkutils.jsonschemaloader.mapper.DefaultJsonSchemaMapper;
import com.hevelian.awscdkutils.jsonschemaloader.mapper.JsonSchemaMapper;
import com.hevelian.awscdkutils.jsonschemaloader.model.JsonSchemaModel;
import lombok.AllArgsConstructor;
import software.amazon.awscdk.services.apigateway.JsonSchema;

import java.io.IOException;
import java.io.InputStream;

/**
 * The facade class for loading AWS CDK JSON schemas from the provided input using a specified {@code JsonSchemaMapper}.
 * This class uses Jackson's {@link  ObjectMapper} for reading the input stream into a {@link  JsonSchemaModel},
 * and then maps it to a {@link  JsonSchema} using the provided or default {@link  JsonSchemaMapper}.
 */
@AllArgsConstructor
public class JsonSchemaLoader {

  private final ObjectMapper objectMapper;
  private final JsonSchemaMapper<JsonSchemaModel> mapper;

  public JsonSchemaLoader() {
    this.objectMapper = new ObjectMapper();
    this.mapper = new DefaultJsonSchemaMapper();
  }

  public JsonSchemaLoader(JsonSchemaMapper<JsonSchemaModel> mapper) {
    this.objectMapper = new ObjectMapper();
    this.mapper = mapper;
  }

  /**
   * Loads a {@link  JsonSchema} from the provided input stream.
   *
   * @param inputStream The input stream containing the JSON representation of a {@code JsonSchemaModel}.
   * @return The mapped {@link  JsonSchema} representation.
   * @throws IOException If an I/O error occurs while reading the input stream or mapping the schema.
   */
  public JsonSchema load(InputStream inputStream) throws IOException {
    var jsonSchema = objectMapper.readValue(inputStream, JsonSchemaModel.class);
    return mapper.map(jsonSchema);
  }
}


