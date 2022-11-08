package testantony.resumemaker.cvmaker.profilecreate.Utils;

import android.app.ActivityManager;
import android.content.Context;
import android.net.Uri;
import android.util.Base64;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Utility {

    public static void Toast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

    public static String convertToBase64String(Context context, Uri uri) {
        try {
            return Base64.encodeToString(getBytes(context.getContentResolver().openInputStream(uri)), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }
}
