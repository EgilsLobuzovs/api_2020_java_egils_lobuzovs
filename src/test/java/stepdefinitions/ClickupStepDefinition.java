package stepdefinitions;

import clients.ClickupClient;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.ClickupFolder;
import domain.ClickupList;
import domain.ClickupSpace;
import domain.ClickupTask;
import helpers.*;

import javax.ws.rs.core.Response;
import java.util.ArrayList;


import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ClickupStepDefinition {

    private final ClickupClient CLICKUP_CLIENT = new ClickupClient();


    @When("User creates a new folder with id {string}")
    public void CreatesNewFolder(String spaceId) {
        String name = RandomString.getRandomString();
        TestCaseContext.get().setClickupFolder(FolderHelper.createClickupFolder(spaceId, name));
        TestCaseContext.get().setClickupSpace(SpaceHelper.getClickupSpace(spaceId));
    }

    @And("Check if that folder is created")
    public void checksIfThatFolderIsCreated() {
        String id = TestCaseContext.get().getClickupFolder().getId();
        ClickupFolder fetchedClickupFolder = FolderHelper.getClickupFolder(TestCaseContext.get().getClickupFolder().getId());
        assertThat(fetchedClickupFolder.getId()).isEqualTo(id);
        assertThat(fetchedClickupFolder.getName()).isEqualTo(TestCaseContext.get().getClickupFolder().getName());

    }

    @Then("Delete created folder")
    public void deleteCreatedFolder() {
        CLICKUP_CLIENT.deleteFolder(TestCaseContext.get().getClickupFolder().getId());
    }

    @When("User creates a new folder with id {string} and name {string}")
    public void userCreatesNewFolderInSpaceWithIdAndName(String spaceId, String folderName) {
        TestCaseContext.get().setClickupFolder(FolderHelper.createClickupFolder(spaceId, folderName));
        TestCaseContext.get().setClickupSpace(SpaceHelper.getClickupSpace(spaceId));

    }

    @And("Create list in folder with name {string}")
    public void createListInFolderWithName(String listName) {
        ClickupList clickupList = ListHelper.createClickupList(TestCaseContext.get().getClickupFolder().getId(), listName);
        TestCaseContext.get().setClickupList(clickupList);
    }

    @Then("Check if that list is created")
    public void CheckIfThatListIsCreated() {
        String listId = TestCaseContext.get().getClickupList().getId();
        ClickupList fetchedClickupList = ListHelper.getClickupList(listId);
        assertThat(fetchedClickupList.getId()).isEqualTo(listId);
        assertThat(fetchedClickupList.getName()).isEqualTo(TestCaseContext.get().getClickupList().getName());
    }

    @When("User updates space with id {string} name to {string}")
    public void userUpdatesSpaceWithIdNameTo(String spaceId, String spaceName) {
        ClickupSpace fetchedClickupSpace = SpaceHelper.updateClickupSpace(spaceId, spaceName);
        TestCaseContext.get().setClickupSpace(fetchedClickupSpace);
    }

    @Then("Verify Space Id and Name")
    public void verifySpaceIdAndName() {
        String spaceId = TestCaseContext.get().getClickupSpace().getId();
        ClickupSpace fetchedClickupSpace = SpaceHelper.getClickupSpace(spaceId);
        assertThat(fetchedClickupSpace.getId()).isEqualTo(spaceId);
        assertThat(fetchedClickupSpace.getName()).isEqualTo(TestCaseContext.get().getClickupSpace().getName());
    }


    @And("User creates new folder with name {string}")
    public void userCreatesNewFolderWithName(String folderName) {
        ClickupFolder fetchedClickupFolder = FolderHelper.createClickupFolder(TestCaseContext.get().getClickupSpace().getId(), folderName);
        TestCaseContext.get().setClickupFolder(fetchedClickupFolder);
    }

    @Then("Create {int} tasks")
    public void createTasks(int taskCount) {
        ClickupTask clickupTask;
        ArrayList list = new ArrayList();
        for (int i = 0; i < taskCount; i++){
            String name = RandomString.getRandomString();
            clickupTask = TaskHelper.createClickupTask(TestCaseContext.get().getClickupList().getId(), name);
            list.add(clickupTask);
        }
        TestCaseContext.get().setTasks(list);
    }

    @When("get space id {string}")
    public void getSpaceId(String spaceId) {
        given().
                header("Authorization", "pk_4532474_VM8NJ8PPXRCJ48S7VX8FXJP5VNP46HIL").
                when().
                get(String.format("https://api.clickup.com/api/v2/space/" + spaceId)).
                then().
                log().
                ifValidationFails().
                statusCode(Response.Status.OK.getStatusCode());
    }
}
