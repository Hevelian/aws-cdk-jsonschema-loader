AWS JSON schema loader is the tool designed to streamline the process of parsing JSON schemas into AWS CDK JSONSchema model.

## Example of usage

Initialize the loader:

``` 
private final JsonSchemaLoader loader = new JsonSchemaLoader()
```

Parse the input to AWS JsonSchema by providing an inputStream:

``` 
    try(var fileInputStream = new FileInputStream("src/test/resources/test.json")) { 
        JsonSchema schema = loader.read(fileInputStream);     
    }
```

String example

``` 
{
   "type":"object",
   "properties":{
      "A":{
         "type":"object",
         "properties":{
            "A1":{
               "type":"string"
            },
            "A2":{
               "type":"string"
            }
         },
         "required":[
            "A1",
            "A2"
         ]
      },
      "B":{
         "type":"object",
         "properties":{
            "B1":{
               "type":"string"
            },
            "B2":{
               "type":"string"
            }
         },
         "required":[
            "B1",
            "B2"
         ]
      }
   },
   "required":[
      "A",
      "B"
   ]
}
``` 

## Overriding of the current behavior

If you need to override the current mapping behavior, you can implement the AwsCDKSchemaMapper interface

``` 
public class CustomSchemaMapper implements JsonSchemaMapper<JsonSchemaModel> {
 {
    // tbd
}
``` 

And then initialize the JsonSchemaMapper:

``` 
private final JsonSchemaLoader loader = new JsonSchemaLoader(new MyCustomMapper())
``` 

**Note**: If you need to change mapping model, then you should clone the repo and update the model by hand
