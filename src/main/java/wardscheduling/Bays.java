package wardscheduling;

import common.Displayable;

import java.awt.*;
import java.util.List;

/**
 * ============================================================
 * ONE GRID BOX = ONE PLACE (ward, bay, room).
 * It stores nothing about services itself: every redraw it asks
 * "which services are here right now?" and shows those.
 * ============================================================
 */

public class Bays implements Displayable {

    private final String name;
    private final List<Service> services;

    public Bays(String name, List<Service> services) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        this.name = name;
        this.services = services;
    }

    @Override
    public String getCellText() {
        StringBuilder sb = new StringBuilder("<html><b>" + name + "</b>");
        boolean any = false;
        for (Service r : services) {
            if (r.isAt(name)) {
                sb.append("<br>").append(r.getName()).append(": ").append(r.getStatus());
                any = true;
            }
        }
        if (!any) {
            sb.append("<br>(empty)");
        }
        return sb.append("</html>").toString();
    }
}
