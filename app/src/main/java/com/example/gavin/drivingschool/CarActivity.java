package com.example.gavin.drivingschool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gavin.drivingschool.Config.Util.SessionManagerHelper;
import com.example.gavin.drivingschool.Config.Util.Utility;
import com.example.gavin.drivingschool.Domain.Car;
import com.example.gavin.drivingschool.Domain.Image;
import com.example.gavin.drivingschool.Domain.User;
import com.example.gavin.drivingschool.Services.Impl.CarServiceImpl;
import com.example.gavin.drivingschool.Services.Impl.ImageServiceImpl;
import com.example.gavin.drivingschool.Services.Impl.UserServiceImpl;
import com.example.gavin.drivingschool.Services.UserService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by gavin on 2017/06/27.
 */
public class CarActivity extends AppCompatActivity {
    private boolean isBound = false;
    private UserService activateAccountService;
    EditText TypeText,LicenseText,YearText,MilaegeText;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Button btnSelect;
    byte [] picture;
    private ImageView ivImage2;
    private String userChoosenTask;
    SessionManagerHelper sess;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_activity);
        sess = new SessionManagerHelper(getApplicationContext());
        TypeText = (EditText)findViewById(R.id.typeText);
        LicenseText = (EditText)findViewById(R.id.licenseText);
        YearText = (EditText)findViewById(R.id.yearText);
        MilaegeText = (EditText)findViewById(R.id.milaegeText);
        HashMap<String, String> userDetails = sess.getUserDetails();
        ivImage2 = (ImageView) findViewById(R.id.ivImage);
        UserServiceImpl userService = new UserServiceImpl();
        user = userService.getUserByEmail(userDetails.get("email"));

        System.out.println("User Email "+user.getEmail());
        System.out.println("picture Bytes "+picture);
    }

    public void submitClick(View v) {
        String type = TypeText.getText().toString();
        String license = LicenseText.getText().toString();
        String yearT = YearText.getText().toString();
        String miles = MilaegeText.getText().toString();
        CarServiceImpl carService = new CarServiceImpl();
        ImageServiceImpl imageService = new ImageServiceImpl();
        //  userService = new UserServiceImpl();

        Calendar calendar = Calendar.getInstance();
        int mseconds = calendar.get(Calendar.MILLISECOND);
        String imgName = "Driving School" + mseconds;
        Image image = new Image.Builder()
                .image(picture)
                .name(imgName)
                .userId(user.getid())
                .build();
        Image imageId = imageService.addImage(image);
        Car car = new Car.Builder()
                .UserId(imageId.getId())
                .Mileage(Integer.parseInt(miles))
                .License(license)
                .Year(Integer.parseInt(yearT))
                        .Type(type)
                        .UserId(imageId.getId())
                .build();


        Car carId = carService.addCar(car);


        Intent intentLogin = new Intent(this, CarListActivity.class);
        startActivity(intentLogin);
        finish();

    }
    public void selectImage(View v) {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(CarActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= Utility.checkPermission(CarActivity.this);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask="Take Photo";
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask="Choose from Library";
                    if(result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        picture = bytes.toByteArray();
        Log.v("Log", picture.toString());
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ivImage2.setImageBitmap(thumbnail);
        System.out.println("Pictire bytes "+picture);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ivImage2.setImageBitmap(bm);
        picture = getBytesFromBitmap(bm);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> "+picture);
    }
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        return stream.toByteArray();
    }
}
