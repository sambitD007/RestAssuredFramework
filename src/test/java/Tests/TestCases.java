package Tests;

import Endpoints.Route;
import Model.User;
import RestClient.RestClient;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases {
    public RestClient restClient = new RestClient(Route.baseurl);

    @Test
    public void Get(){
        Response response = restClient.whenGetIsInvoked(Route.getEndpoint,null,null,null,null);
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void Put(){
        User payload = new User("Sambit","QA");
        Gson gson = new Gson();
        Response response = restClient.whenPutIsInvoked(Route.putEndpoint,null,gson.toJson(payload));
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void Post(){
        User payload = new User("Sambit","QA");
        Gson gson = new Gson();
        Response response = restClient.whenPostIsInvoked(Route.postEndpoint,null,gson.toJson(payload));
        Assert.assertEquals(response.getStatusCode(),201);
    }

    @Test
    public void Delete(){
        Response response = restClient.whenDeleteIsInvoked(Route.deleteEndpoint,null,null,null);
        Assert.assertEquals(response.getStatusCode(),204);
    }
}
