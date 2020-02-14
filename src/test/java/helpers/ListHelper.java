package helpers;

import clients.ClickupClient;
import domain.ClickupList;


public class ListHelper {
    private static final ClickupClient CLICKUP_CLIENT = new ClickupClient();

    public static ClickupList createClickupList(String folderId, String listName) {
        return CLICKUP_CLIENT.createList(folderId, listName)
                .extract()
                .response()
                .as(ClickupList.class);
    }


    public static ClickupList getClickupList(String listId) {
        return CLICKUP_CLIENT.fetchList(listId)
                .extract()
                .response()
                .as(ClickupList.class);
    }
}
