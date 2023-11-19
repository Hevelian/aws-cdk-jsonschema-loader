package com.hevelian.awscdkutils.jsonschemaloader.mapper;

import com.hevelian.awscdkutils.jsonschemaloader.model.JsonSchemaModel;
import software.amazon.awscdk.services.apigateway.JsonSchema;
import software.amazon.awscdk.services.apigateway.JsonSchemaType;
import software.amazon.awscdk.services.apigateway.JsonSchemaVersion;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Default implementation of the {@link  JsonSchemaMapper} interface for mapping
 * {@link  JsonSchemaModel} instances to AWS CDK {@link  JsonSchema} representations.
 */
public class DefaultJsonSchemaMapper implements JsonSchemaMapper<JsonSchemaModel> {

  /**
   * Maps the provided {@link  JsonSchemaModel} to a {@link  JsonSchema} using the DRAFT4 schema version.
   *
   * @param jsonSchema The input {@code JsonSchemaModel} to be mapped.
   * @return The mapped {@code JsonSchema} with the DRAFT4 version.
   */
  public JsonSchema map(JsonSchemaModel jsonSchema) {
    return mapSchemaProperties(jsonSchema)
        .schema(JsonSchemaVersion.DRAFT4)
        .build();
  }

  /**
   * Maps properties of the provided {@link  JsonSchemaModel} to a @{@link JsonSchema.Builder}.
   * Uses the recursive algorithm for mapping of nested objects.
   */
  protected JsonSchema.Builder mapSchemaProperties(JsonSchemaModel property) {
    var jsonType = JsonSchemaType.valueOf(property.getType()
        .toUpperCase(Locale.getDefault()));

    var jsonSchemaBuilder = JsonSchema.builder();
    jsonSchemaBuilder.type(jsonType);

    if (jsonType.equals(JsonSchemaType.OBJECT)) {
      jsonSchemaBuilder.required(property.getRequired());
      jsonSchemaBuilder.properties(property.getProperties()
          .entrySet()
          .stream()
          .map(e -> Map.entry(e.getKey(), mapSchemaProperties(e.getValue()).build()))
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
    return jsonSchemaBuilder;
  }

}