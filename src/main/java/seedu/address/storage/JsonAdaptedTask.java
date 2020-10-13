package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Participation;
import seedu.address.model.task.Task;

import java.time.LocalDate;
import java.util.Set;

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
    public JsonAdaptedTask(String taskName
                    , String description, LocalDate publishDate,
                                   Deadline deadline, double progress, boolean isDone,
                                   Set<Participation> assignees) {
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

    @JsonValue
    public String getTaskName() {
        return taskName;
    }

        @JsonValue
        public String getDescription() {
            return description;
        }

        @JsonValue
        public LocalDate getPublishDate() {
            return publishDate;
        }

        @JsonValue
        public Deadline getDeadline() {
            return deadline;
        }

        @JsonValue
        public double getProgress() {
            return progress;
        }

        @JsonValue
        public boolean isDone() {
            return isDone;
        }

        @JsonValue
        public Set<Participation> getAssignees() {
            return assignees;
        }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        return new Task(taskName, null, null, 0, false);
    }

}
