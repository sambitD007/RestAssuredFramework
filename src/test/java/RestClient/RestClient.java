package RestClient;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.StringBufferInputStream;
import java.text.MessageFormat;
import java.util.HashMap;

enum HTTPRequestType{
    GET,
    POST,
    PUT,
    DELETE
}
public class RestClient {
    protected RequestSpecification request;
    protected Response response;
    private String baseURL;

    public RestClient(String baseURL){ this.baseURL=baseURL; }

    public Response whenRequestIsInvoked(HTTPRequestType type,String url, HashMap<String,String> headers,String payload,String entityId,String queryParameter,String... urlParams){
        RestAssured.baseURI = this.baseURL;
        request = RestAssured.given();
        if(headers != null){
          request.headers(headers);
      }
      if(payload != null){
          request.body(payload);
      }
      String endpoint = formattedURL(url,entityId,queryParameter,urlParams);
      switch (type){
          case GET:
              if(endpoint!=null){
                  return request.get(endpoint);
              }else return request.get();
          case PUT:
              if(endpoint!=null){
                  return request.put(endpoint);
              }else return request.put();
          case POST:
              if(endpoint!=null){
                  return request.post(endpoint);
              }else return request.post();
          case DELETE:
              if(endpoint!=null){
                  return request.delete(endpoint);
              }else return request.delete();
          default:
              throw new RuntimeException("Invalid request");
      }
    }

    public Response whenGetIsInvoked(String endpoint,HashMap<String,String> headers,String entityId,String queryParam,String... urlParams){
        return whenRequestIsInvoked(HTTPRequestType.GET,endpoint,headers,null,entityId,queryParam,urlParams);
    }


    public Response whenPostIsInvoked(String endpoint,HashMap<String,String> headers,String payload){
        return whenRequestIsInvoked(HTTPRequestType.POST,endpoint,headers,payload,null,null,null);
    }

    public Response whenPutIsInvoked(String endpoint,HashMap<String,String> headers,String payload){
        return whenRequestIsInvoked(HTTPRequestType.PUT,endpoint,headers,payload,null,null,null);
    }

    public Response whenDeleteIsInvoked(String endpoint,HashMap<String,String> headers,String entityId,String queryParam,String... urlParams){
        return whenRequestIsInvoked(HTTPRequestType.DELETE,endpoint,headers,null,entityId,queryParam,urlParams);
    }


    private String formattedURL(String url,String entityId,String queryParameter, String... urlParams){
        if(url.equals(null)){
            return null;
        }
        String endpoint = url;
        if(entityId!=null){
            endpoint = endpoint + "/" +entityId;
        }
        if(queryParameter!=null){
            endpoint = endpoint + "?" + queryParameter;
        }
        if(urlParams != null){
            endpoint = MessageFormat.format(endpoint,urlParams);
        }
        return endpoint;
    }
}
