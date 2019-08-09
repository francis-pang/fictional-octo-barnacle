package crackingthecodinginterview.hard;

import java.util.HashMap;
import java.util.Map;

public class TheMasseuse {
  private static int NO_TIME = 0;
  private static int INVALID_ENTRY = -1;
  private Map<Integer, Integer> maximumTimeByStartSlot;

  public int getMaximumNumberOfMinutesForMasseuse(int[] appointmentRequests) {
    maximumTimeByStartSlot = new HashMap<>();
    if (appointmentRequests == null) {
      return INVALID_ENTRY;
    }

    if (appointmentRequests.length == 0) {
      return NO_TIME;
    }

    // Add entry for last slot
    maximumTimeByStartSlot.put(appointmentRequests.length - 1, appointmentRequests[appointmentRequests.length - 1]);
    return maxTimeForAppointment(appointmentRequests, 0, appointmentRequests.length - 1);
  }

  private int maxTimeForAppointment(int[] appointmentSlots, int start, int end) {
    if (maximumTimeByStartSlot.containsKey(start)) {
      return maximumTimeByStartSlot.get(start);
    }
    if (start > end) {
      return NO_TIME;
    }
    int maxTimeIfTakeSlot = appointmentSlots[start] + maxTimeForAppointment(appointmentSlots, start + 2, end);
    int maxTimeIfDontTakeSlot = maxTimeForAppointment(appointmentSlots, start + 1, end);
    int maxTime = Math.max(maxTimeIfDontTakeSlot, maxTimeIfTakeSlot);
    maximumTimeByStartSlot.put(start, maxTime);
    return maxTime;
  }
}
