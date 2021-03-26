package com.rvg.idmsample;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.idmission.client.ImageProcessingResponseListener;
import com.idmission.client.ImageProcessingSDK;
import com.idmission.client.InitializationException;
import com.idmission.client.RequestResponseInterface;
import com.idmission.client.Response;

import java.util.Map;

public class MainActivity extends FragmentActivity implements ImageProcessingResponseListener, RequestResponseInterface {
    //SDK Instance
    private ImageProcessingSDK imageProcessingSDK = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            initializeListener();
        }    catch (InitializationException e) {
            StringUtil.printLogInDebugMode("INIT Exception", e.getMessage());
            Toast.makeText(MainActivity.this, "Not Initialized : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    public void initializeListener() throws InitializationException {

        String url  = "https://kyc.idmission.com/IDS/service/integ/idm/thirdparty/upsert";
        String loginId = "ev_integ_54567";
        String passwd = "Tec#291319245$";
        String merchantId = "34006";
        int productId = 920;
        String productName = "Identity_Validation_and_Face_Matching";
        String language = "en";
        boolean debug = false;
        try {
            imageProcessingSDK = ImageProcessingSDK.initialize(MainActivity.this, url, loginId, passwd, merchantId, productId, productName, language, debug);
            imageProcessingSDK.setImageProcessingResponseListener(MainActivity.this);
            Toast.makeText(MainActivity.this, "SDK Initialized  ", Toast.LENGTH_SHORT).show();
        }
     catch (InitializationException e) {
            StringUtil.printLogInDebugMode("INIT Exception", e.getMessage());
            Toast.makeText(MainActivity.this, "Not Initialized : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

       try {
           imageProcessingSDK.detectFace(MainActivity.this);
           Toast.makeText(MainActivity.this, "Detect face sdk", Toast.LENGTH_SHORT).show();
       }
       catch (Exception e){
        e.printStackTrace();
       }
    }
    @Override
    public void onImageProcessingResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onAutoImageCaptureResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onAutoFillResultAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onAutoFillFieldInformationAvailable(Map<String, String> map, Response response) {

    }

    @Override
    public void onFaceDetectionResultAvailable(Map<String, String> map, Response response) {

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
