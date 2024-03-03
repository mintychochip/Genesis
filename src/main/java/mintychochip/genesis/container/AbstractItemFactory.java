package mintychochip.genesis.container;

import com.google.gson.Gson;

public class AbstractItemFactory {

    private static Gson GSON;
    private AbstractItemFactory(Gson gsonInstance) {
        GSON = gsonInstance;
    }

    public static AbstractItemFactory createAbstractItemFactory(Gson gson) {
        return new AbstractItemFactory(gson);
    }
}
