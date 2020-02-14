package clients;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import javax.ws.rs.core.Response;
import static io.restassured.RestAssured.given;

public class ClickupClient {

    private static final String API_TOKEN = "pk_4532474_VM8NJ8PPXRCJ48S7VX8FXJP5VNP46HIL";
    private static final String CLICKUP_BASE_URL = "https://api.clickup.com/api/v2";

    private static final String CLICKUP_GET_LIST_URL = "%s/list/%s";
    private static final String CLICKUP_GET_SPACE_URL = "%s/space/%s";
    private static final String CLICKUP_GET_ALL_TASKS_URL = "%s/list/%s/task?archived=false";
    private static final String CLICKUP_GET_FOLDER_URL = "%s/folder/%s";

    private static final String CLICKUP_CREATE_TASK_URL = "%s/list/%s/task";
    private static final String CLICKUP_CREATE_FOLDER_URL = "%s/space/%s/folder";
    private static final String CLICKUP_CREATE_LIST_URL = "%s/folder/%s/list";



    public ValidatableResponse fetchSpace(String spaceId){
        return given().
                header("Authorization", API_TOKEN).
                when().
                get(String.format(CLICKUP_GET_SPACE_URL, CLICKUP_BASE_URL, spaceId)).
                then().
                log().
                ifValidationFails().
                statusCode(Response.Status.OK.getStatusCode());
    }

    public ValidatableResponse fetchFolder(String folderId) {
        return given().
                header("Authorization", API_TOKEN).
                when().
                get(String.format(CLICKUP_GET_FOLDER_URL, CLICKUP_BASE_URL, folderId)).
                then().
                log().
                ifValidationFails().
                statusCode(Response.Status.OK.getStatusCode());
    }

    public ValidatableResponse fetchList (String listId){
        return given().
                header("Authorization", API_TOKEN).
                contentType(ContentType.JSON).
                when().
                get(String.format(CLICKUP_GET_LIST_URL, CLICKUP_BASE_URL, listId)).
                then().
                log().
                ifValidationFails().
                statusCode(Response.Status.OK.getStatusCode());
    }

    public ValidatableResponse createFolder(String spaceId, String name) {
        return given().
                header("Authorization", API_TOKEN).
                body("{\"name\": \"" + name + "\"}").
                contentType(ContentType.JSON).
                when().
                post(String.format(CLICKUP_CREATE_FOLDER_URL, CLICKUP_BASE_URL, spaceId)).
                then().
                log().
                ifValidationFails().
                statusCode(Response.Status.OK.getStatusCode());
    }


    public void deleteFolder(String folderId) {
        given().
                header("Authorization", API_TOKEN).
                when().
                delete(String.format(CLICKUP_GET_FOLDER_URL, CLICKUP_BASE_URL, folderId)).
                then().
                log().
                ifValidationFails().
                statusCode(Response.Status.OK.getStatusCode());
    }

    public ValidatableResponse createList(String folderId, String listName){
        return given().
                body("{\"name\": \"" + listName + "\"}").
                header("Authorization", API_TOKEN).
                contentType(ContentType.JSON).
                when().
                post(String.format(CLICKUP_CREATE_LIST_URL, CLICKUP_BASE_URL, folderId)).
                then().
                log().
                ifValidationFails().
                statusCode(Response.Status.OK.getStatusCode());
    }


    public ValidatableResponse updateSpaceName(String spaceId, String spaceName){
        return given().
                param("name",  spaceName).
                header("Authorization", API_TOKEN).
                when().
                put(String.format(CLICKUP_GET_SPACE_URL, CLICKUP_BASE_URL, spaceId)).
                then().
                log().
                ifValidationFails().
                statusCode(Response.Status.OK.getStatusCode());
    }

    public ValidatableResponse createTask(String listId, String taskName){
        return given().
                body("{\"name\": \"" + taskName + "\"}").
                header("Authorization", API_TOKEN).
                contentType(ContentType.JSON).
                when().
                post(String.format(CLICKUP_CREATE_TASK_URL, CLICKUP_BASE_URL, listId)).
                then().
                log().
                ifValidationFails().
                statusCode(Response.Status.OK.getStatusCode());
    }

    public ValidatableResponse getAllTasks(String listId){
        return given().
                header("Authorization", API_TOKEN).
                contentType(ContentType.JSON).
                when().
                get(String.format(CLICKUP_GET_ALL_TASKS_URL, CLICKUP_BASE_URL, listId)).
                then().
                log().
                ifValidationFails().
                statusCode(Response.Status.OK.getStatusCode());
    }
}
