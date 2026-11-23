package wardscheduling;

import common.GridView;
import notifications.HospitalInformation;
import notifications.MedicalNotification;

import javax.swing.*;

/**
 * ============================================================
 * CLOCK
 * Every lap:  ASK -> WardController (tick, add, serve) -> REDRAW -> REPORT -> WAIT
 * ============================================================
 */
public class WardMain {

    private static final int REPORT_EVERY = 24;

    public static void main(String[] args) {
        WardController boss = new WardController();
        GridView view = new GridView("Ward Scheduling", 2, 4, 800, 400);
        view.refresh(boss.getPlaces());
        view.show();

        int tick = 0;
        while (true) {
            MedicalNotification r = HospitalInformation.getMedicalNotificationFromDoctor();   // 1 ASK
            boss.hour(r);                                               // 2 UPDATE
            SwingUtilities.invokeLater(() -> view.refresh(boss.getPlaces()));   // 3 REDRAW
            tick++;
            if (tick % REPORT_EVERY == 0) {                             // 4 REPORT
                System.out.print(boss.buildReport(tick));
            }
            HospitalInformation.waitAnHour();                           // 5 WAIT
        }
    }
}
