package com.rvg.idmsample;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.idmission.client.BiometricType;
import com.idmission.client.CardImageType;
import com.idmission.client.ColorCode;
import com.idmission.client.FaceImageType;
import com.idmission.client.FingerType;
import com.idmission.client.FingerprintDeviceType;
import com.idmission.client.IDImageType;
import com.idmission.client.IdType;
import com.idmission.client.ImageProcessingResponseListener;
import com.idmission.client.ImageProcessingSDK;
import com.idmission.client.ImageType;
import com.idmission.client.InitializationException;
import com.idmission.client.RequestResponseFragment;
import com.idmission.client.RequestResponseInterface;
import com.idmission.client.Response;
import com.idmission.client.ResponseStatusCode;
import com.idmission.libtestproject.fragments.ResultData;
import com.idmission.libtestproject.fragments.ResultImageFragment;
import com.idmission.libtestproject.utils.StringUtil;
import com.konylabs.vm.Function;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


public class MainActivity extends FragmentActivity implements ImageProcessingResponseListener, RequestResponseInterface {

    //SDK Instance
    private ImageProcessingSDK imageProcessingSDK = null;
    //put captured image in this object to display in viewpager
    public static LinkedHashMap<String, ResultData> capturedImageData = new LinkedHashMap<>();
    public static Function mCallback = null;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            initializeListener();
        } catch (InitializationException e) {
            StringUtil.printLogInDebugMode("INIT Exception", e.getMessage());
            Toast.makeText(MainActivity.this, "Not Initialized : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void initializeListener() throws InitializationException {

        String url = "https://kyc.idmission.com/IDS/service/integ/idm/thirdparty/upsert";
        String loginId = "ev_integ_54567";
        String passwd = "Tec#291319245$";
        String merchantId = "34006";
        int productId = 920;
        String productName = "Identity_Validation_and_Face_Matching";
        String language = "en";
        boolean debug = false;

        // Loading  the library
        try {
            capturedImageData.clear();
            imageProcessingSDK = ImageProcessingSDK.initialize(MainActivity.this, url, loginId, passwd, merchantId, productId, productName, language, debug);
            imageProcessingSDK.setImageProcessingResponseListener(MainActivity.this);
            Toast.makeText(MainActivity.this, "SDK Initialized  ", Toast.LENGTH_SHORT).show();
        } catch (InitializationException e) {
            StringUtil.printLogInDebugMode("INIT Exception", e.getMessage());
            Toast.makeText(MainActivity.this, "Not Initialized : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
// detect face activity
        try {
            imageProcessingSDK.detectFace(MainActivity.this);

            Toast.makeText(MainActivity.this, "Detect face sdk", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onImageProcessingResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onAutoImageCaptureResultAvailable(Map<String, String> resultMap, Response response) {
        Log.d("SDK", "CALLBACK:::: onAutoImageCaptureResultAvailable");
        if (null != response) {
            Toast.makeText(MainActivity.this, response.getStatusMessage(), Toast.LENGTH_SHORT).show();

            String imageBase64 = null;
            if (null != resultMap) {
                if (resultMap.containsKey(ImageType.FRONT.toString())) {
                    imageBase64 = resultMap.get(ImageType.FRONT.toString());
                    if (!StringUtil.isEmpty(imageBase64)) {
                        capturedImageData.put(ResultData.FRONT_IMAGE_DATA, new ResultData(ResultData.FRONT_IMAGE_DATA, imageBase64));
                    }
                } else if (resultMap.containsKey(ImageType.BACK.toString())) {
                    imageBase64 = resultMap.get(ImageType.BACK.toString());
                    if (!StringUtil.isEmpty(imageBase64)) {
                        capturedImageData.put(ResultData.BACK_IMAGE_DATA, new ResultData(ResultData.BACK_IMAGE_DATA, imageBase64));
                    }
                }
            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            String base64Image = "";
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                base64Image = StringUtil.encodeBitmapTobase64(selectedImage);
                Log.d("", "");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
/*
            switch(requestCode) {
                case FRONT_IMG_REQ_CODE:
                    requestImageMap.put(ImageType.FRONT.toString(), base64Image);
                    capturedImageData.put(ResultData.FRONT_IMAGE_DATA, new ResultData(ResultData.FRONT_IMAGE_DATA, base64Image));
                    break;
                case BACK_IMG_REQ_CODE:
                    requestImageMap.put(ImageType.BACK.toString(), base64Image);
                    capturedImageData.put(ResultData.BACK_IMAGE_DATA, new ResultData(ResultData.BACK_IMAGE_DATA, base64Image));
                    break;
                case FACE_IMG_REQ_CODE:
                    requestImageMap.put(FaceImageType.FACE.toString(), base64Image);
                    capturedImageData.put(ResultData.FACE_IMAGE_DATA, new ResultData(ResultData.FACE_IMAGE_DATA, base64Image));
                    break;
            }*/
        }

    }
    @Override
    public void onAutoFillResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onAutoFillFieldInformationAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onFaceDetectionResultAvailable(Map<String, String> resultMap, Response response) {
        Log.d("SDK","CALLBACK:::: onFaceDetectionResultAvailable");
        if (null != response) {
            Toast.makeText(MainActivity.this, response.getStatusMessage(), Toast.LENGTH_SHORT).show();

            String faceImageBase64 = null, processedFaceImageBase64 = null,ovalFaceImageBase64 = null;
            if(null != resultMap){
                if (resultMap.containsKey(FaceImageType.FACE.toString())) {
                    faceImageBase64 = resultMap.get(FaceImageType.FACE.toString());
                }
                if (resultMap.containsKey(FaceImageType.PROCESSED_FACE.toString())) {
                    processedFaceImageBase64 = resultMap.get(FaceImageType.PROCESSED_FACE.toString());
                }
                if (resultMap.containsKey(FaceImageType.OVAL_FACE.toString())) {
                    ovalFaceImageBase64 = resultMap.get(FaceImageType.OVAL_FACE.toString());
                }
            }

            if (!StringUtil.isEmpty(faceImageBase64)) {
                capturedImageData.put(ResultData.FACE_IMAGE_DATA, new ResultData(ResultData.FACE_IMAGE_DATA, faceImageBase64));
            }

            if (!StringUtil.isEmpty(processedFaceImageBase64)) {
                capturedImageData.put(ResultData.PROCESSED_FACE_IMAGE_DATA, new ResultData(ResultData.PROCESSED_FACE_IMAGE_DATA, processedFaceImageBase64));
            }

            if (!StringUtil.isEmpty(ovalFaceImageBase64)) {
                capturedImageData.put(ResultData.OVAL_FACE_IMAGE_DATA, new ResultData(ResultData.OVAL_FACE_IMAGE_DATA, ovalFaceImageBase64));
            }
            Log.d("capturedata", "This is the capturedata" +capturedImageData.get(ovalFaceImageBase64));
           // resultImagePagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFaceMatchingResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onCardDetectionResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onCaptureProofOfAddressResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onCaptureBankStatementResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onCaptureGenericDocumentResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onCaptureBirthCertificateResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onImageProcessingAndFaceMatchingResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onOperationResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onCustomerVerificationResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onCustomizeUserInterfaceResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onVoiceRecordingFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onGPSCoordinateAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onFourFingerCaptureFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onFingerprintCaptureFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onFingerprintEnrolmentFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onFingerprintVerificationFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onVideoRecordingFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onScanBarcodeFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onCaptureSignatureFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onVerifyAddressFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onCreateEmployeeFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onVerifyEmployeeFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onGenerateTokenFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onVerifyTokenFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onSnippetImageCaptureResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onUpdateCustomerFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onGenerateOTPFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onVerifyOTPFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onInitializationResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onExecuteCustomProductCall(Map<String, String> map, Response response) {

    }

    @Override
    public void onUpdateEmployeeFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onIDValidationAndVideoMatchingFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void genericApiCallResponse(Map<String, String> map, Response response) {

    }

    @Override
    public void onVideoConferencingFinished(Map<String, String> map, Response response) {

    }

    @Override
    public void onDownloadXsltResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void cancelPogressBar() {

    }



}
