package seedu.address.storage;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Participation;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public final String taskName;
    private final String description;
    private final LocalDate publishDate;
    private final Deadline deadline;
    private final double progress;
    private final boolean isDone;
    private final Set<Participation> assignees;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName}.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskName") String taskName,
                            @JsonProperty("taskDescription") String description,
                            @JsonProperty("taskPublishDate")LocalDate publishDate,
                            @JsonProperty("taskDeadline")Deadline deadline,
                            @JsonProperty("taskProgress")double progress,
                            @JsonProperty("taskIsDone")boolean isDone,
                            @JsonProperty("taskAssignees")Set<Participation> assignees) {
        this.taskName = taskName;
        this.description = description;
        this.publishDate = publishDate;
        this.deadline = deadline;
        this.progress = progress;
        this.isDone = isDone;
        this.assignees = assignees;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskName = source.taskName;
        description = source.getDescription();
        publishDate = source.getPublishDate();
        deadline = source.getDeadline();
        progress = source.getProgress();
        isDone = source.isDone();
        assignees = source.getAssignees();
    }

    @JsonProperty
    public String getTaskName() {
        return taskName;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public LocalDate getPublishDate() {
        return publishDate;
    }

    @JsonProperty
    public Deadline getDeadline() {
        return deadline;
    }

    @JsonProperty
    public double getProgress() {
        return progress;
    }

    @JsonProperty
    public boolean isDone() {
        return isDone;
    }

    @JsonProperty
    public Set<Participation> getAssignees() {
        return assignees;
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        Task task = new Task(taskName, description, deadline, progress, isDone);
        task.setPublishDate(publishDate);
        return task;
    }

}
