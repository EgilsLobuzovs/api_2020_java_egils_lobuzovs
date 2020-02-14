package helpers;

import clients.ClickupClient;
import domain.ClickupSpace;

public class SpaceHelper {
    private static final ClickupClient CLICKUP_CLIENT = new ClickupClient();

    public static ClickupSpace getClickupSpace(String spaceId) {
        return CLICKUP_CLIENT.fetchSpace(spaceId)
                .extract()
                .response()
                .as(ClickupSpace.class);
    }

    public static ClickupSpace updateClickupSpace(String spaceId, String newName) {
        return CLICKUP_CLIENT.updateSpaceName(spaceId, newName)
                .extract()
                .response()
                .as(ClickupSpace.class);
    }

}