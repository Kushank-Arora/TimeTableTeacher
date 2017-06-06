package arora.kushank.teachertt;

/**
 * Created by Password on 24-Feb-17.
 */
public class Subject {
    public String id;
    public String name;
    public String teacher;
    public String description;
    Subject(){
        this.name="-";
        this.id="-";
        this.teacher="-";
        this.description="-";
    }

    @Override
    public String toString() {
        return id+":"+name+":"+teacher+":"+description;
    }
}
