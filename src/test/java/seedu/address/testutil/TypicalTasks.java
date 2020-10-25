package seedu.address.testutil;

import seedu.address.model.project.Deadline;
import seedu.address.model.task.Task;

public class TypicalTasks {
    public static final String VALID_TASK_DEADLINE = "31-12-2020 10:00:00";
    public static final String VALID_TASK_NAME = "Plan for group meeting on milestone v1.4";
    public static final String VALID_TASK_DESCRIPTION = "";
    public static final double VALID_TASK_PROGRESS = 0;
    public static final double VALID_TASK_PROGRESS_HALF = 0.5;
    public static final boolean VALID_TASK_DONE = true;

    public static final Task PLAN_MEETING = new Task(VALID_TASK_NAME, VALID_TASK_DESCRIPTION,
            new Deadline(VALID_TASK_DEADLINE), VALID_TASK_PROGRESS, VALID_TASK_DONE);
}
