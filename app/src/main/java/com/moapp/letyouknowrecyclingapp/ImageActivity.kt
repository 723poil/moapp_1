package com.moapp.letyouknowrecyclingapp

import android.Manifest
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.moapp.letyouknowrecyclingapp.databinding.ActivityImageBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import java.text.SimpleDateFormat
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class ImageActivity : BaseActivity() {

    //권한용 코드
    val PERM_STORAGE = 9
    val PERM_CAMERA = 10

    //카메라 요청코드
    val REQ_CAMERA = 11

    //갤러리 요청코드
    val REQ_GALLERY = 12

    val binding by lazy { ActivityImageBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener(){
            finish()
        }

        // 1. 공용저장소 권한이 있는지 확인
        requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERM_STORAGE)

        binding.backBtn.setOnClickListener(){
            finish()
        }
    }


    fun initViews() {
        // 2. 카메라 요청시 권한을 먼저 체크하고 승인되었으면 카메라를 연다
        binding.buttonCamera.setOnClickListener() {
            requirePermissions(arrayOf(Manifest.permission.CAMERA), PERM_CAMERA)
        }
        // 5. 갤러리 버튼이 클릭 되면 갤러리를 연다
        binding.buttonGallery.setOnClickListener() {
            openGallery()
        }
    }

    //원본 이미지의 주소를 저장할 변수
    var realUri: Uri? = null
    var filenameInUri: String? = null

    // 3. 카메라에 찍은 사진을 저장하기 위한 Uri를 넘겨준다
    fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        createImageUri(newFileName(), "image/jpg")?.let { uri ->
            realUri = uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, realUri)
        }

        startActivityForResult(intent, REQ_CAMERA)
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, REQ_GALLERY)
    }

    // 원본 이미지를 저장할 Uri를 MediaStore(안드로이드의 데이터베이스)에 생성하는 메서드
    fun createImageUri(filename:String, mimeType:String) : Uri? {
        val values = ContentValues()
        //파일의 이름을 저장
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)

        //파일을 저장할 수 있는 uri를 넘겨줌
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    }

    // 파일 이름을 생성하는 메서드
    fun newFileName() : String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())
        filenameInUri = "${filename}.jpg"
        return "${filename}.jpg"
    }

    // 원본 이미지를 불러오는 메서드
    fun loadBitmap(photoUri: Uri) : Bitmap? {
        try{
            return if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) {
                val source = ImageDecoder.createSource(contentResolver, photoUri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(contentResolver, photoUri)
            }
        }catch (e:Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun permissionGranted(requestCode: Int) {
        when(requestCode) {
            //승인 됐을 때 처리
            PERM_STORAGE -> initViews()
            PERM_CAMERA -> openCamera()
        }
    }

    override fun permissionDenied(requestCode: Int) {
        when(requestCode) {
            PERM_STORAGE -> {
                Toast.makeText(this, "공용 저장소 권한을 승해야 앱을 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
            PERM_CAMERA -> {
                Toast.makeText(this, "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //4. 카메라촬영하고 V선택을 누르면 onactivityresult가 호출됨 //6. 갤러리에서 사진 선택후 호출된다.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val builder = AlertDialog.Builder(this)
            var intent = Intent(this, TabActivity::class.java)
            val context = this
            lateinit var resultText: String

            when(requestCode) {
                REQ_CAMERA -> {
                    /* val bitmap = data?.extras?.get("data") as Bitmap // 미리보기이미지
                     binding.imagePreview.setImageBitmap(bitmap)*/

                    realUri?.let { uri ->
                        val bitmap = loadBitmap(uri)
                        //이미지프리뷰에 사진결과 띄우기
                        binding.imagePreview.setImageBitmap(bitmap)
                        //서버로 전송
                        ClassificationRepository.postImg(bitmap, this)

                        val loading = LoadingDialog(this)
                        loading.startLoading()

                        val channel = Channel<Long>()
                        val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
                        backgroundScope.launch {
                            var time = measureTimeMillis {
                                while (!ClassificationRepository.isGet) {
                                    continue
                                }
                            }
                            resultText = ClassificationRepository.result.toString()
                            when (ClassificationRepository.result.toString()) {
                                "Aluminium" ->  {
                                    resultText = "알루미늄"
                                    intent.putExtra("data2", 1)
                                } // 1
                                "Carton" -> {
                                    resultText = "종이팩"
                                    intent.putExtra("data2", 2)
                                } // 2
                                "Glass" -> {
                                    resultText = "유리"
                                    intent.putExtra("data2", 3)
                                } // 3
                                "Organic Waste" -> {
                                    resultText = "음식물"
                                    intent.putExtra("data2", 8)
                                } // 8
                                "Other Plastics" -> {
                                    resultText = "플라스틱"
                                    intent.putExtra("data2", 4)
                                } // 4
                                "Paper and Cardboard" -> {
                                    resultText = "종이류"
                                    intent.putExtra("data2", 6)
                                } // 6
                                "Plastic" -> {
                                    resultText = "플라스틱"
                                    intent.putExtra("data2", 4)
                                } // 4
                                "Textiles" -> {
                                    resultText = "의류"
                                    intent.putExtra("data2", 5)
                                } // 5
                                "Wood" -> {
                                    resultText = "목재"
                                    intent.putExtra("data2", 7)
                                } // 7
                                else -> Log.d("lsh", "result is null")
                            }
                            ClassificationRepository.result = null
                            Log.d("lsh", "time : $time")
                            channel.send(time)
                        }

                        val mainScope = GlobalScope.launch(Dispatchers.Main) {
                            channel.consumeEach {
                                ClassificationRepository.isGet = false
                                loading.isDismiss()

                                builder
                                    .setTitle("$resultText")
                                    .setMessage("결과값을 확인하세요 맞으면 YES, 틀리면N NO")
                                    .setPositiveButton("YES",
                                        DialogInterface.OnClickListener { dialog, id ->
                                            // YES 버튼 선택 시 수행
                                            startActivity(intent)
                                        })
                                    .setNegativeButton("NO",
                                        DialogInterface.OnClickListener { dialog, id ->
                                            // NO 버튼 선택 시 수행
                                            // 결과값 서버로 전송
                                            ClassificationRepository.sendValidation(context)
                                            //No 클릭시, image 초기화
                                            binding.imagePreview.setImageBitmap(null)
                                        })
                                // Create the AlertDialog object and return it
                                builder.create()
                                builder.show()
                            }
                        }

                        realUri = null
                        filenameInUri = null
                    }
                }
                REQ_GALLERY -> {
                    data?.data.let { uri ->
//                        Log.d("lsh","${uri?.javaClass?.name}")
                        binding.imagePreview.setImageURI(uri)
                        try {
                            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                            ClassificationRepository.postImg(bitmap, this)

                            val loading = LoadingDialog(this)
                            loading.startLoading()

                            val channel = Channel<Long>()
                            val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
                            backgroundScope.launch {
                                var time = measureTimeMillis {
                                    while (!ClassificationRepository.isGet) {
                                        continue
                                    }
                                }
                                resultText = ClassificationRepository.result.toString()
                                when (ClassificationRepository.result.toString()) {
                                    "Aluminium" ->  {
                                        resultText = "알루미늄"
                                        intent.putExtra("data2", 1)
                                    } // 1
                                    "Carton" -> {
                                        resultText = "종이팩"
                                        intent.putExtra("data2", 2)
                                    } // 2
                                    "Glass" -> {
                                        resultText = "유리"
                                        intent.putExtra("data2", 3)
                                    } // 3
                                    "Organic Waste" -> {
                                        resultText = "음식물"
                                        intent.putExtra("data2", 8)
                                    } // 8
                                    "Other Plastics" -> {
                                        resultText = "플라스틱"
                                        intent.putExtra("data2", 4)
                                    } // 4
                                    "Paper and Cardboard" -> {
                                        resultText = "종이류"
                                        intent.putExtra("data2", 6)
                                    } // 6
                                    "Plastic" -> {
                                        resultText = "플라스틱"
                                        intent.putExtra("data2", 4)
                                    } // 4
                                    "Textiles" -> {
                                        resultText = "의류"
                                        intent.putExtra("data2", 5)
                                    } // 5
                                    "Wood" -> {
                                        resultText = "목재"
                                        intent.putExtra("data2", 7)
                                    } // 7
                                    else -> Log.d("lsh", "result is null")
                                }
                                ClassificationRepository.result = null
                                Log.d("lsh", "time : $time")
                                channel.send(time)
                            }

                            val mainScope = GlobalScope.launch(Dispatchers.Main) {
                                channel.consumeEach {
                                    ClassificationRepository.isGet = false
                                    loading.isDismiss()

                                    builder
                                        .setTitle("$resultText")
                                        .setMessage("결과값을 확인하세요 맞으면 YES, 틀리면N NO")
                                        .setPositiveButton("YES",
                                            DialogInterface.OnClickListener { dialog, id ->
                                                // YES 버튼 선택 시 수행
                                                startActivity(intent)
                                            })
                                        .setNegativeButton("NO",
                                            DialogInterface.OnClickListener { dialog, id ->
                                                // NO 버튼 선택 시 수행
                                                // 결과값 서버로 전송
                                                ClassificationRepository.sendValidation(context)
                                                //No 클릭시, image 초기화
                                                binding.imagePreview.setImageBitmap(null)
                                            })
                                    // Create the AlertDialog object and return it
                                    builder.create()
                                    builder.show()
                                }
                            }

                        } catch(err: Exception) {
                            Log.d("lsh", err.toString())
                        }
                    }
                }
            }
        }
    }
}