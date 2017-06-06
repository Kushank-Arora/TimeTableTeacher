package arora.kushank.teachertt;

/**
 * Created by Password on 24-Feb-17.
 */
public class Period {

    public Subject subject;
    public boolean active;
    public Batch batch;

    Period(){
        subject=new Subject();
        active=true;
        batch=new Batch();
    }

}
