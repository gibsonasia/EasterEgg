package nesada.c4q.nyc.sqliteprototype;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class AddTurtle extends AppCompatActivity {

    ImageView mTurtleImage;
    EditText mName;
    Button mBtnAdd;
    byte[] byteImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_turtle);

        mTurtleImage = (ImageView) findViewById(R.id.image_view);
        mName = (EditText) findViewById(R.id.et_name);
        mBtnAdd = (Button) findViewById(R.id.btn_add_turtle);

//we get the image that was clicked in the reminder, and we set in mTurtleImage
        Bundle extras = getIntent().getExtras();
        byteImage = extras.getByteArray("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
        mTurtleImage.setImageBitmap(bmp);

//we get the name of the image clicked and we set in mName
        String imageName = (String)mTurtleImage.getTag();
        mName.setText(imageName);


        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetupDB setupDB = new SetupDB(AddTurtle.this);
                SQLiteDatabase db = setupDB.getWritableDatabase();

                Turtle turtle = new Turtle(mName.getText().toString(), "");

                long id = cupboard().withDatabase(db).put(turtle);

                Toast.makeText(AddTurtle.this, "Turtle added successfuly to database!", Toast.LENGTH_SHORT).show();

//                SystemClock.sleep(2000);
//                onDestroy();
            }
        });
    }
}
