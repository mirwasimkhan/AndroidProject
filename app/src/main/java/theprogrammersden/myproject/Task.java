package theprogrammersden.myproject;

import com.orm.SugarRecord;

import java.util.List;


public class Task extends SugarRecord{
    private String task;

    public Task(){

    }
    public Task(String task){
        this.task = task;
    }
    public String getTask(){
        return task;
    }

    public static List<Task> read(){
        List<Task> lists;

        try {
            lists = Task.listAll(Task.class);
            return lists;
        }
        catch(Exception e){
            return null;
        }
    }
    public void write(Task task){
        try {
            task.save();
        }
        catch(Exception e){

        }
    }
    public static Task getTask(String tsk) {
        List<Task> tasks = read();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTask().equals(tsk))
                return tasks.get(i);
        }
        return null;
    }
}
