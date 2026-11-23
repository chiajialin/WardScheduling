package wardscheduling;

import notifications.MedicalNotification;

import java.util.*;

/**
 * ============================================================
 *  CONTROLLER (boss) - holds the robots, the places (grid boxes) and the waiting queue(s).
 *  Every hour:  1 TICK every robot -> 2 ADD the new request -> 3 SERVE the queue
 * ============================================================
 */

public class WardController {

    private static final List<String> bayNames = List.of("Nightingale", "Barton", "Seacole", "Dix", "Henderson", "Cavell", "Breckinridge", "Sanger");

    private final List<Service> services = new ArrayList<>();
    private final List<Bays> bays = new ArrayList<>();          // grid order

    public WardController() {
        services.add(new Ultrasound("Ultrasound"));
        services.add(new Ecg("ECG"));
        services.add(new Dialysis("Dialysis"));
        services.add(new Massage("Massage"));


        for (String name : bayNames) {
            bays.add(new Bays(name, services));
        }

    }

    /** Called once per hour from main. The same for every option. */
    public void hour(MedicalNotification r) {
        // 1 TICK - some robots finish and come home
        for (Service service : services) {
            service.tick();
        }
        try {
            addRequest(r);
        } catch (PlaceNotFoundException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        // 3 SERVE
        serveQueue();
    }


    private final Queue<MedicalNotification> waiting = new ArrayDeque<>();

    private void addRequest(MedicalNotification r) throws PlaceNotFoundException {
        if (r.getBay().equals("None")) {
            return;
        }
        if (!bayNames.contains(r.getBay())) {
            throw new PlaceNotFoundException("unknown place " + r.getBay());
        }
        waiting.add(r);
    }

    private void serveQueue() {
        Iterator<MedicalNotification> it = waiting.iterator();
        while (it.hasNext()) {
            MedicalNotification r = it.next();
            Service free = findFree(r.getService());
            if (free != null) {
                free.sendTo(r.getBay());
                it.remove();
            }
        }
    }


    /** First robot that is free AND the right type, or null. */
    private Service findFree(String type) {
        for (Service service : services) {
            if (service.isFree() && service.canDo(type)) {
                return service;
            }
        }
        return null;
    }

    // ---------- output ----------

    public List<Bays> getPlaces() {
        return List.copyOf(bays);
    }

    public String buildReport(int hour) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("===== Report at hour %d =====%n", hour));
        for (Service s : services){
            sb.append(String.format("%s at: %s, It's currently: %s%n", s.getName(), s.getLocation(), s.getStatus()));
        }
        return sb.toString();
    }
}
