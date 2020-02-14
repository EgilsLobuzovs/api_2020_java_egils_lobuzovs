package stepdefinitions;

import clients.ClickupClient;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.TestCaseContext;

public class Hooks {
    ClickupClient clickupClient = new ClickupClient();

    @Before
    public void beforeAll(){
        TestCaseContext.init();
    }

    @After("@NewFolder")
    public void deleteFolder(){
        clickupClient.deleteFolder(TestCaseContext.get().getClickupFolder().getId());
    }

    @After("@SpaceNameChanged")
    public void afterBoardNamehasBeenChaged(){
        clickupClient.updateSpaceName(TestCaseContext.get().getClickupSpace().getId(), "Default space name");
    }
}
