package Tests;

import Endpoints.Route;
import Model.User;
import RestClient.RestClient;
import Utils.PropertyReader;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases {
    public RestClient restClient = new RestClient(PropertyReader.getInstance().getProperty("baseURL"));

    @Test
    public void Get(){
        Response response = restClient.whenGetIsInvoked(Route.GET_USER,null,"2",null,null);
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void Put(){
        User payload = new User("Sambit","QA");
        Gson gson = new Gson();
        Response response = restClient.whenPutIsInvoked(Route.PUT_USER,null,gson.toJson(payload));
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void Post(){
        User payload = new User("Sambit","QA");
        Gson gson = new Gson();
        Response response = restClient.whenPostIsInvoked(Route.POST_USER,null,gson.toJson(payload));
        Assert.assertEquals(response.getStatusCode(),201);
    }

    @Test
    public void Delete(){
        Response response = restClient.whenDeleteIsInvoked(Route.DELETE_USER,null,"2",null);
        Assert.assertEquals(response.getStatusCode(),204);
    }
}
