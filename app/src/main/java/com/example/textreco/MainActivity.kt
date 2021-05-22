package com.example.textreco

import android.app.Activity
import android.app.SearchManager
import android.widget.EditText
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
    lateinit var searchTextView: EditText
    lateinit var searchBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        snapBtn.setOnClickListener(View.OnClickListener {
            dispatchPictureIntent()
        })
        detectBtn.setOnClickListener(View.OnClickListener {
            detectText()
        })
        //searchTextView = findViewById(R.id.searchTextView)
        searchBtn = findViewById(R.id.searchBtn)
        searchBtn.setOnClickListener {
            val intent2 = Intent(Intent.ACTION_WEB_SEARCH)
            //val term = searchTextView.text.toString()
            val term = txtView.text.toString()
            intent2.putExtra(SearchManager.QUERY, term)
            startActivity(intent2)
        }
//        searchBtn.setOnClickListener{
//            searchText()
//        }


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
        //    val txt = block.text
            for (line in block.lines) {
                //val lineText = line.text
                for (element in line.elements) {
                    val elementText = element.text
                    txtView!!.textSize=16f
                    // txtView!!.setText(txt)
                    txtView!!.setText(elementText)
                }
            }
//            txtView!!.textSize=16f
//           // txtView!!.setText(txt)
//            txtView!!.setText(elementText)
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(
            baseContext,message,
            Toast.LENGTH_SHORT
        ).show()
    }

//    private fun searchText(){
//        val searchIntent = Intent(Intent.ACTION_WEB_SEARCH)
//        val term = editText.text.toString()
//        searchIntent.putExtra(SearchManager.QUERY,term)
//        startActivity(searchIntent)
//    }

}

