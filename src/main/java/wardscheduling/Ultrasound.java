package wardscheduling;

/**
 * Ultrasound (subclass) - ONLY what differs.
 */
public class Ultrasound extends Service {

    public Ultrasound(String name) {
        super(name);
    }

    @Override
    protected int jobHours() {
        return 3;
    }

    @Override
    public String getType() {
        return "Ultrasound";
    }

    @Override
    public final String home() {
        return "Nightingale";
    }
}
