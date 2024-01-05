package mintychochip.genesis.manager;

public abstract class GenesisHandler {
    protected final String name;

    protected final int id;


    protected GenesisHandler(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract void cancel();

}
