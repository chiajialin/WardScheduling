package wardscheduling;

/**
 * Massage (subclass) - ONLY what differs.
 */
public class Massage extends Service {

    public Massage(String name) {
        super(name);
    }

    @Override
    protected int jobHours() {
        return 2;
    }

    @Override
    public String getType() {
        return "Massage";
    }

    @Override
    public final String home() {
        return "Seacole";
    }
}
