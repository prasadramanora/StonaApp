package com.ramanora.stona.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;


import com.ramanora.stona.R;
import com.ramanora.stona.adapter.VisitingImageAdapter;
import com.ramanora.stona.bean.CameraPojo;
import com.ramanora.stona.database.DataBaseHandler;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class VisitingCameraActivity extends Activity {
    ImageView addImage;
    ArrayList<CameraPojo> imageArry = new ArrayList<CameraPojo>();
    VisitingImageAdapter imageAdapter;
    private static final int CAMERA_REQUEST = 1888;
    GridView dataList;
    byte[] imageName;
    int imageId;
    Bitmap theImage;
    DataBaseHandler db;
    //private ShowcaseView showcaseView;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_visiting_card);
        dataList = (GridView) findViewById(R.id.list);
        /**
         * create DatabaseHandler object
         */
        db = new DataBaseHandler(this);
        /**
         * Reading and getting all records from database
         */
        List<CameraPojo> contacts = db.getAllContacts();
        for (CameraPojo cn : contacts) {
            String log = "ID:" + cn.getID()
                    + " ,Image: " + cn.getImage();

            // Writing Contacts to log
            Log.d("Result: ", log);
            // add contacts data in arrayList
            imageArry.add(cn);

        }
        /**
         * Set Data base Item into listview
         */


        addImage = (ImageView) findViewById(R.id.btnAdd);


        imageAdapter = new VisitingImageAdapter(this, R.layout.lay_visiting_card_grid_image,
                imageArry);
        dataList.setAdapter(imageAdapter);

		/*new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.btnAdd, this))

				.setContentText("Take picture of visiting card and stored digitally")
				.setShowcaseDrawer(new CustomShowcaseView(getResources()))
				.build();
*/

	/*	new ShowcaseView.Builder(this)
                .withMaterialShowcase()
				.setStyle(R.style.CustomShowcaseTheme2)
				.setTarget(new ViewTarget(addImage))


				.setContentText("Take picture of visiting card and stored digitally")

				.build();*/

      /*  Target viewTarget = new ViewTarget(R.id.btnAdd, this);
        String txt = "<br><br><br>Take picture of visiting card and store digitally";
        showcaseView =     new ShowcaseView.Builder(this)
                .setTarget(viewTarget)

                .setContentTitle(Html.fromHtml(txt))
                .setStyle(R.style.CustomShowcaseTheme3)
                .singleShot(42)
                .build();
        showcaseView.setButtonText("Got It!");*/
        addImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callCamera();
            }
        });

    }

    /**
     * On activity result
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {
            case CAMERA_REQUEST:

                Bundle extras = data.getExtras();

                if (extras != null) {
                    Bitmap yourImage = extras.getParcelable("data");

                    MediaStore.Images.Media.insertImage(getContentResolver(), yourImage, null, null);
                    // convert bitmap to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte imageInByte[] = stream.toByteArray();


                    // Inserting Contacts
                    Log.d("Insert: ", "Inserting ..");
                    db.addContact(new CameraPojo(imageInByte));
                    Intent i = new Intent(VisitingCameraActivity.this,
                            VisitingCameraActivity.class);
                    startActivity(i);
                    finish();

                }
                break;

        }
    }


    /**
     * open camera method
     */
    public void callCamera() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI.getPath());
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    /*	Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		cameraIntent.putExtra("crop", "true");
		cameraIntent.putExtra("aspectX", 0);
		cameraIntent.putExtra("aspectY", 0);
		cameraIntent.putExtra("outputX", 100);
		cameraIntent.putExtra("outputY", 100);
		startActivityForResult(cameraIntent, CAMERA_REQUEST);
*/
    }

   /* private static class CustomShowcaseView implements ShowcaseDrawer {

        private final float width;
        private final float height;
        private final Paint eraserPaint;
        private final Paint basicPaint;
        private final int eraseColour = 0;
        private final RectF renderRect;

        public CustomShowcaseView(Resources resources) {
            width = resources.getDimension(R.dimen.custom_showcase_width);
            height = resources.getDimension(R.dimen.custom_showcase_height);
            PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
            eraserPaint = new Paint();
            *//*eraserPaint.setColor(0xFFFFFF);
			eraserPaint.setAlpha(0);*//*
            eraserPaint.setXfermode(xfermode);
            eraserPaint.setAntiAlias(true);
            //	eraseColour = resources.getColor(R.color.custom_showcase_bg);
            basicPaint = new Paint();
            renderRect = new RectF();
        }

        @Override
        public void setShowcaseColour(int color) {
            eraserPaint.setColor(color);
        }

        @Override
        public void drawShowcase(Bitmap buffer, float x, float y, float scaleMultiplier) {
            Canvas bufferCanvas = new Canvas(buffer);
            renderRect.left = x - width / 2f;
            renderRect.right = x + width / 2f;
            renderRect.top = y - height / 2f;
            renderRect.bottom = y + height / 2f;
            bufferCanvas.drawRect(renderRect, eraserPaint);
        }

        @Override
        public int getShowcaseWidth() {
            return (int) width;
        }

        @Override
        public int getShowcaseHeight() {
            return (int) height;
        }

        @Override
        public float getBlockedRadius() {
            return width;
        }

        @Override
        public void setBackgroundColour(int backgroundColor) {
            // No-op, remove this from the API?
        }

        @Override
        public void erase(Bitmap bitmapBuffer) {
            bitmapBuffer.eraseColor(eraseColour);
        }

        @Override
        public void drawToCanvas(Canvas canvas, Bitmap bitmapBuffer) {
            canvas.drawBitmap(bitmapBuffer, 0, 0, basicPaint);
        }

    }*/

}