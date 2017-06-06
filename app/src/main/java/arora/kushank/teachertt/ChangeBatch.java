package arora.kushank.teachertt;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import static java.util.Arrays.sort;

/**
 * Created by Password on 27-Feb-17.
 */
public class ChangeBatch extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changebatch);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        final TimeTableDB db = new TimeTableDB(this);
        db.open();
        final String[] cTeacher = db.getTeacher();
        db.close();

        final String fileNameSharedPref = "TIMETABLEPREF";
        final SharedPreferences someData = getSharedPreferences(fileNameSharedPref, MODE_PRIVATE);

        final Spinner spTeacher;
        Button submit;
        spTeacher = (Spinner) findViewById(R.id.spcourse);
        submit = (Button) findViewById(R.id.bsubmit);
        final String selTeacher = (someData.getString("teacher", "SH. HARISH"));
        final String uniqueTeachers[] = new String[100];
        int count = 0;
        for (String aCbranch : cTeacher) {
            boolean absent = true;
            for (int j = 0; j < count; j++)
                if (aCbranch.equals(uniqueTeachers[j])) {
                    absent = false;
                    break;
                }

            if (absent&&!aCbranch.contains("/")&&!aCbranch.trim().equals("")) {
                uniqueTeachers[count++] = aCbranch;
            }
        }
        final String finalTeachers[] = new String[count];
        System.arraycopy(uniqueTeachers, 0, finalTeachers, 0, count);

        sort(finalTeachers);

        if (spTeacher != null) {
            spTeacher.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, finalTeachers));
            int posCourse = findPosition(selTeacher, finalTeachers);
            spTeacher.setSelection(posCourse);
        }

        assert submit != null;
        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = someData.edit();
                try {
                    assert spTeacher != null;
                    editor.putString("teacher", spTeacher.getSelectedItem().toString());
                    Toast.makeText(ChangeBatch.this, "Updating Successful!", Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(ChangeBatch.this, "Error while updating!", Toast.LENGTH_SHORT).show();
                }
                editor.commit();
                finish();
            }
        });
    }

    int findPosition(String s, String[] arr) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i].equals(s)) {
                return i;
            }
        return 0;
    }

}
