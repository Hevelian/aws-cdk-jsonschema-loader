package com.hevelian.awscdkutils.jsonschemaloader.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class JsonSchemaModel {

  private String type;
  private Map<String, JsonSchemaModel> properties;
  private List<String> required;
}