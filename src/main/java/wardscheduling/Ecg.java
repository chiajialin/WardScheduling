package wardscheduling;

/**
 * ECG (subclass) - ONLY what differs.
 */
public class Ecg extends Service {

    public Ecg(String name) {
        super(name);
    }

    @Override
    protected int jobHours() {
        return 1;
    }

    @Override
    public String getType() {
        return "ECG";
    }

    @Override
    public final String home() {
        return "Seacole";
    }
}
