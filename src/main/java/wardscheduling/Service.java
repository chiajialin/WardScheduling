package wardscheduling;

import notifications.HospitalInformation;

/**
 * ============================================================
 *  Service (abstract parent) that MOVES between places.
 *  It does NOT get its own grid box - each BAY is a box (see Bays).
 *  States:  IDLE at home --sendTo()--> servicing at a place --tick() hits 0--> IDLE at home
 * ============================================================
 */
public abstract class Service {



    // ---------- FIELDS (per object - passed the field test) ----------
    private final String name;                          // also its ID for the library
    private String location = home();                     // which place (box) it is in right now
    private int hoursLeft = 0;                          // 0 = free

    // ---------- SOURCE 1: constructor ----------
    public Service(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");      // error situation 3
        }
        this.name = name;
    }

    // ---------- SOURCE 2: decided by the TYPE -> each subclass fills in ----------
    protected abstract int jobHours();
    public abstract String getType();
    public abstract String home();

    // ---------- SOURCE 3: the arrows - methods that change state ----------

    /** Inactive -> Active. The COMMAND lives here. */
    public void sendTo(String bay) {
        if (!isFree()) {
            throw new IllegalStateException(name + " is busy");                // error situation 3
        }
        HospitalInformation.requestPorter(bay, name);
        location = bay;                               // update own fields straight after the command
        hoursLeft = jobHours();                         // POLYMORPHISM line
    }

    /** One hour passes. No library calls in here - it only counts. */
    public void tick() {
        if (hoursLeft > 0) {
            hoursLeft--;
        }
    }

    // ---------- SOURCE 4: questions the controller / places need to ask ----------
    public boolean isFree() {
        return hoursLeft == 0;
    }

    public boolean canDo(String type) {
        return getType().equals(type);
    }

    public boolean isAt(String place) {                 // used by Place to show only robots that are HERE
        return location.equals(place);
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }



    // ---------- SOURCE 5: display ----------
    public String getStatus() {
        return isFree() ? "Inactive" : String.format("Active (%dh left)", hoursLeft);
    }

    @Override
    public String toString() {                          // one report line
        return String.format("%-10s %-10s at %-9s %-22s",
                name, getType(), location, getStatus());
    }
}
