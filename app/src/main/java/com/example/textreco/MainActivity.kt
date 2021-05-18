package com.example.textreco

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var imageBitmap: Bitmap ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        snapBtn.setOnClickListener(View.OnClickListener {
            dispatchPictureIntent()
        })
        detectBtn.setOnClickListener(View.OnClickListener {
            detectText()
        })


    }
    val REQUEST_IMAGE_CAPTURE=1
    private fun dispatchPictureIntent(){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(takePictureIntent.resolveActivity(packageManager)!=null){
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){
            val extras = data!!.extras
            imageBitmap = extras!!.get("data") as Bitmap
            iv!!.setImageBitmap(imageBitmap)
        }

    }
    private fun detectText(){
        val image = FirebaseVisionImage.fromBitmap(imageBitmap!!)
        //val detector = FirebaseVision.getInstance().onDeviceTextRecognizer
        val detector = FirebaseVision.getInstance().cloudTextRecognizer
        detector.processImage(image).addOnSuccessListener ( OnSuccessListener<FirebaseVisionText>{

            firebaseVisionText ->
            processTxt(firebaseVisionText)
        }).addOnFailureListener( OnFailureListener {
                System.err.println("Something went wrong **************************************************")
        })
           // showToast("Something went wrong")
    }
    private fun processTxt(text: FirebaseVisionText){
        val blocks =text.textBlocks
        if(blocks.size == 0){
            Toast.makeText(this, "No text found", Toast.LENGTH_LONG).show()
            return
        }
        for (block in text.textBlocks){
            val txt = block.text
            txtView!!.textSize=16f
            txtView!!.setText(txt)
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(
            baseContext,message,
            Toast.LENGTH_SHORT
        ).show()
    }


}

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode==123){
//            if(resultCode == Activity.RESULT_OK){
//                var photo = data.extras.get("data") as Bitmap
//                //iv.setImageBitmap(photo)
//                textRec(photo)
//            }
//        }
//    }
//
//    private fun textRec(photo: Bitmap) {
//
//    }
//}
//https://stackoverflow.com/questions/3320115/android-onclicklistener-identify-a-button
//        val snapBtn1 = findViewById< Button>(R.id.snapBtn)
//        snapBtn1.setOnClickListener {
//            var moveToWorkExp = Intent(applicationContext, WorkExpActivity2::class.java)
//            startActivity(moveToWorkExp)
//        }
//        val detectBtn1 = findViewById<Button>(R.id.detectBtn)
//        detectBtn.setOnClickListener {
//            var moveToWorkExp = Intent(applicationContext, WorkExpActivity2::class.java)
//            startActivity(moveToWorkExp)
//        }

//        val imageView = findViewById<>(R.id.iv)
//        imageView.setOnClickListener{
//            var iv = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            //startActivityForResult(i, requestCode:123)
//        }