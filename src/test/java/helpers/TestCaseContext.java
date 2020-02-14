package helpers;

import domain.ClickupFolder;
import domain.ClickupList;
import domain.ClickupSpace;
import domain.ClickupTask;

import java.util.List;

public class TestCaseContext {
    private static final ThreadLocal<TestCaseContext> THREAD_LOCAL = new ThreadLocal<>();

    private TestCaseContext() {
    }
    private ClickupFolder clickupFolder = new ClickupFolder();
    private ClickupList clickupList = new ClickupList();
    private ClickupSpace clickupSpace = new ClickupSpace();
    private List<ClickupTask> tasks;

    public void setTasks(List<ClickupTask> tasks) {
        this.tasks = tasks;
    }

    public List<ClickupTask> getTasks() {
        return tasks;
    }

    public ClickupFolder getClickupFolder() {
        return clickupFolder;
    }

    public ClickupList getClickupList() {
        return clickupList;
    }

    public ClickupSpace getClickupSpace() {
        return clickupSpace;
    }

    public void setClickupFolder(ClickupFolder clickupFolder) {
        this.clickupFolder = clickupFolder;
    }

    public void setClickupList(ClickupList clickupList) {
        this.clickupList = clickupList;
    }

    public void setClickupSpace(ClickupSpace clickupSpace) {
        this.clickupSpace = clickupSpace;
    }

    public static TestCaseContext get() {
        return THREAD_LOCAL.get();
    }

    public static void init() {

        THREAD_LOCAL.set(new TestCaseContext());

    }
}
