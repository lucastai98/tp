package seedu.address.storage;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.Deadline;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    public final String taskName;
    private final String description;
    private final String publishDate;
    private final String deadline;
    private final String progress;
    private final String isDone;
    private final Set<String> assignees;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName}.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskName") String taskName,
                           @JsonProperty("description") String description,
                           @JsonProperty("publishDate") String publishDate,
                           @JsonProperty("deadline") String deadline,
                           @JsonProperty("progress") String progress,
                           @JsonProperty("isDone") String isDone,
                           @JsonProperty("assignees") Set<String> assignees) {
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
        publishDate = source.getPublishDate().toString();
        if (source.getDeadline() != null) {
            deadline = source.getDeadline().toString();
        } else {
            deadline = null;
        }
        progress = source.getProgress().toString();
        isDone = source.isDone().toString();
        assignees = source.getAssignees();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {

        if (taskName == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, "Task name"));
        }
        if (!Task.isValidTaskName(taskName)) {
            throw new IllegalValueException(Task.NAME_MESSAGE_CONSTRAINTS);
        }
        if (description != null) {
            if (!Task.isValidTaskDescription(description)) {
                throw new IllegalValueException(Task.DESCRIPTION_MESSAGE_CONSTRAINTS);
            }
        }
        if (publishDate == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, "Publish date"));
        }
        if (!Task.isValidPublishDate(publishDate)) {
            throw new IllegalValueException(Task.PUBLISH_DATE_MESSAGE_CONSTRAINTS);
        }
        if (deadline != null) {
            if (!Deadline.isValidDeadline(deadline)) {
                throw new IllegalValueException(Task.DEADLINE_MESSAGE_CONSTRAINTS);
            }
        }
        if (progress == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, "Progress"));
        }
        if (!Task.isValidProgress(progress)) {
            throw new IllegalValueException(Task.PROGRESS_MESSAGE_CONSTRAINTS);
        }
        if (isDone == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, "Is done"));
        }
        if (!Task.isValidIsDone(isDone)) {
            throw new IllegalValueException(Task.IS_DONE_MESSAGE_CONSTRAINTS);
        }
        Task task = null;
        if (deadline == null) {
            task = new Task(taskName,
                    description,
                    null,
                    Double.parseDouble(progress),
                    Boolean.parseBoolean(isDone));

        } else {
            task = new Task(taskName,
                    description,
                    new Deadline(deadline),
                    Double.parseDouble(progress),
                    Boolean.parseBoolean(isDone));
        }

        task.setPublishDate(LocalDate.parse(publishDate));
        assignees.forEach(task::addAssignee);
        return task;
    }

}
