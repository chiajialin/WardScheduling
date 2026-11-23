package wardscheduling;

/**
 * Dialysis (subclass) - ONLY what differs.
 */
public class Dialysis extends Service {

    public Dialysis(String name) {
        super(name);
    }

    @Override
    protected int jobHours() {
        return 4;
    }

    @Override
    public String getType() {
        return "Dialysis";
    }

    @Override
    public final String home() {
        return "Henderson";
    }

}
