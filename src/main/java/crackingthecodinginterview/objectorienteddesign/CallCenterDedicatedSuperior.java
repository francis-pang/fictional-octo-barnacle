package crackingthecodinginterview.objectorienteddesign;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class CallCenterDedicatedSuperior {
  private Queue<Respondent> availableRespondents;

  public CallCenterDedicatedSuperior(List<Respondent> respondents) {
    availableRespondents = new ArrayDeque<>();
    availableRespondents.addAll(respondents);
  }

  // This should be made multi-thread
  public void dispatchCall() throws InterruptedException {
    Respondent handlingRespondent = null;
    synchronized (availableRespondents) {
      handlingRespondent = availableRespondents.poll();
    }
    final int WAITNG_PERIOD = 30;
    if (handlingRespondent == null) {
      Thread.sleep(WAITNG_PERIOD);
      dispatchCall(handlingRespondent);
    }
    synchronized (availableRespondents) {
      availableRespondents.offer(handlingRespondent);
    }
  }

  public void dispatchCall(Respondent respondent) {
    respondent.handleCall();
  }

  public interface Employee {
    void handleCall();
  }

  public class Respondent implements Employee {
    private final Manager reportingManager;

    public Respondent(Manager manager) {
      reportingManager = manager;
    }

    @Override
    public void handleCall() {
      boolean canHandleCall = true;

      if (!canHandleCall) {
        escalateCall();
      }
    }

    private void escalateCall() {
      reportingManager.handleCall();
    }
  }

  public class Manager implements Employee {
    private Availability availability;
    private final Director reportingDirector;

    public Manager(Director director) {
      availability = Availability.FREE;
      reportingDirector = director;
    }

    @Override
    public void handleCall() {
      if (Availability.BUSY.equals(availability)) {
        escalateCall();
      } else {
        availability.equals(Availability.BUSY);
      }
      // handle the call

      boolean canHandleCall = true;
      if (!canHandleCall) {
        escalateCall();
      }

      availability = Availability.FREE;
    }

    private void escalateCall() {
      reportingDirector.handleCall();
    }
  }

  public class Director implements Employee {
    @Override
    public void handleCall() {
      // Handle the call
    }
  }

  public enum Availability {
    FREE, BUSY
  }
}
