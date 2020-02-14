package helpers;

import clients.ClickupClient;
import domain.ClickupTask;
import java.util.List;

public class TaskHelper {
    private static final ClickupClient CLICKUP_CLIENT = new ClickupClient();

    public static ClickupTask createClickupTask(String listId, String taskName) {
        return CLICKUP_CLIENT.createTask(listId, taskName)
                .extract()
                .response()
                .as(ClickupTask.class);
    }

    public static List<ClickupTask> getClickupTasks(String listId)
    {
        return CLICKUP_CLIENT.getAllTasks(listId).extract().body().jsonPath().getList("$", ClickupTask.class);
    }
}