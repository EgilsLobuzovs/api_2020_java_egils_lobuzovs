package helpers;

import clients.ClickupClient;
import domain.ClickupFolder;

public class FolderHelper {
    private static final ClickupClient CLICKUP_CLIENT = new ClickupClient();

    public static ClickupFolder createClickupFolder(String spaceId, String folderName) {
        return CLICKUP_CLIENT.createFolder(spaceId, folderName)
                .extract()
                .response()
                .as(ClickupFolder.class);
    }

    public static ClickupFolder getClickupFolder(String folderId) {
        return CLICKUP_CLIENT.fetchFolder(folderId)
                .extract()
                .response()
                .as(ClickupFolder.class);
    }

}
