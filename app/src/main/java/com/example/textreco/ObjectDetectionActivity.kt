package com.example.textreco

import android.app.SearchManager
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.textreco.ml.MobilenetV110224Quant
import kotlinx.android.synthetic.main.activity_main.*
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.net.URI

class ObjectDetectionActivity : AppCompatActivity() {
    lateinit var bitmap: Bitmap
    lateinit var imgView: ImageView
    lateinit var searchObjBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_detection)
        supportActionBar?.setTitle("ObjectReco")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_baseline_search_24)
        imgView = findViewById(R.id.imageView)
        val fileName = "label.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use{it.readText()}
        var townList = inputString.split("\n")

        var tv: TextView = findViewById(R.id.objDetectTextView)
        var select: Button =findViewById(R.id.objSelectBtn)
        select.setOnClickListener(View.OnClickListener {
            var intent:Intent =Intent(Intent.ACTION_GET_CONTENT)
            intent.type ="image/*"
            startActivityForResult(intent,100)

        })
// Search on google code
        searchObjBtn = findViewById(R.id.searchObjBtn)
        searchObjBtn.setOnClickListener {
            val intent3 = Intent(Intent.ACTION_WEB_SEARCH)
            //val term = searchTextView.text.toString()
            val term = tv.text.toString()
            intent3.putExtra(SearchManager.QUERY, term)
            startActivity(intent3)
        }

        var detect : Button =findViewById(R.id.objDetectImageBtn)
        detect.setOnClickListener(View.OnClickListener {
            var resized : Bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true)
            val model = MobilenetV110224Quant.newInstance(this)

// Creates inputs for reference.
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)
            var tbuffer = TensorImage.fromBitmap(resized)
            var byteBuffer =tbuffer.buffer
            inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer
            var max = getMax(outputFeature0.floatArray)
            tv.setText(townList[max])
// Releases model resources if no longer used.
            model.close()

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imgView.setImageURI(data?.data )
        var uri:Uri?= data?.data
        bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
    }
    fun getMax(arr:FloatArray): Int {
        var ind = 0
        var min = 0.0f
        for(i in 0..1000){
            if(arr[i]>min)
            {
                ind = i
                min = arr[i]
            }
        }
        return ind
    }
    // To navigate back to home page
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}