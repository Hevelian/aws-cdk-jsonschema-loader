package com.hevelian.awscdkutils.jsonschemaloader.mapper;

import software.amazon.awscdk.services.apigateway.JsonSchema;

/**
 * A generic interface for mapping objects of type T to {@link  JsonSchema} representations.
 *
 * @param <T> The type of the object to be mapped to the AWS CDK JSON schema.
 * @see JsonSchema
 */
public interface JsonSchemaMapper<T> {

  /**
   * Maps the provided object of a generic type T to a corresponding {@link JsonSchema}.
   *
   * @param jsonSchema The input object to be mapped to the AWS CDK JSON schema.
   * @return The mapped {@linkplain JsonSchema} representation.
   */
  JsonSchema map(T jsonSchema);
}