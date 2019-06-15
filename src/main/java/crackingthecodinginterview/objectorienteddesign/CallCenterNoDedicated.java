package crackingthecodinginterview.objectorienteddesign;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class CallCenterNoDedicated {
  private Queue<Respondent> availableRespondents;
  private Queue<Manager> availableManagers;
  private Queue<Director> availableDirectors;

  public CallCenterNoDedicated(List<Respondent> respondents, List<Manager> managers, List<Director> directors) {
    availableRespondents = new ArrayDeque<>();
    availableRespondents.addAll(respondents);

    availableManagers = new ArrayDeque<>();
    availableManagers.addAll(managers);

    availableDirectors = new ArrayDeque<>();
    availableDirectors.addAll(directors);
  }

  // This should be made multi-thread
  public void dispatchCall() throws InterruptedException {
    boolean isCallHandled = false;
    if (!availableRespondents.isEmpty()) {
      Respondent answeringRespondent = availableRespondents.poll();
      isCallHandled = answeringRespondent.handleCall();
      availableRespondents.offer(answeringRespondent);
    }

    if (!isCallHandled && !availableManagers.isEmpty()) {
      Manager answeringManager = availableManagers.poll();
      isCallHandled = answeringManager.handleCall();
      availableManagers.offer(answeringManager);
    }

    if (isCallHandled) {
      final int WAITING_PERIOD = 30;
      if (availableDirectors.isEmpty()) {
        Thread.sleep(WAITING_PERIOD);
      } else {
        Director answeringDirector = availableDirectors.poll();
        answeringDirector.handleCall(); // Assume that it will be answer
        availableDirectors.offer(answeringDirector);
      }
    }
  }

  public interface Employee {
    boolean handleCall();
  }

  public abstract class CallCentreEmployee implements Employee {
    @Override
    public boolean handleCall() {
      boolean canHandleCall = true;

      // Handle the call
      return canHandleCall;
    }
  }

  public class Respondent extends CallCentreEmployee {
  }

  public class Manager extends CallCentreEmployee {
  }

  public class Director extends CallCentreEmployee {
  }
}
