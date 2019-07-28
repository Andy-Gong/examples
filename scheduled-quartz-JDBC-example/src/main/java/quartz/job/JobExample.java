package quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

import java.util.Date;

public class JobExample implements Job {

    /**
     * <p>
     * Called by the <code>{@link Scheduler}</code> when a <code>{@link Trigger}</code> fires that is associated with the <code>Job</code>.
     * </p>
     *
     * <p>
     * The implementation may wish to set a {@link JobExecutionContext#setResult(Object) result} object on the {@link JobExecutionContext} before this method
     * exits.  The result itself is meaningless to Quartz, but may be informative to
     * <code>{@link JobListener}s</code> or
     * <code>{@link TriggerListener}s</code> that are watching the job's
     * execution.
     * </p>
     *
     * @throws JobExecutionException if there is an exception while executing the job.
     */
    @Override
    public void execute(JobExecutionContext context) {
        try {
            System.out.println(new Date() + ", Scheduler name: " + context.getScheduler().getSchedulerName()
                    + "; Execution Job key: " + context.getJobDetail().getKey());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
