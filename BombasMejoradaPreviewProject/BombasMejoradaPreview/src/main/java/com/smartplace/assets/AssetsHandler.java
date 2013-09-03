package com.smartplace.assets;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Roberto on 10/07/13.
 *
 * DON´T FORGET TO ADD PERMISSION:
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 *
 * Example of usage:
 *
 * AssetsHandler.Operations.CopyAssetsToPhone(this,Environment.getExternalStorageState()+ "/BombasMejorada/","PDFs");
 *
 * In this example, the folder "PDFS" from assets is copied to "sdcard/BombasMejorada" path on the phone
 */
public class AssetsHandler {
    public static class Operations{
    public static void CopyAssetsToPhone(Context context,String whereToCopyPath,String FolderToCopy) {
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        File BombasDirectory = new File(whereToCopyPath);
        // have the object build the directory structure, if needed.
        BombasDirectory.mkdirs();
        try {
            files = assetManager.list(FolderToCopy);
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        for(String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(FolderToCopy+"/"+filename);   // if files resides inside the "Folder" directory itself
                out = new FileOutputStream(whereToCopyPath + filename);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch(Exception e) {
                Log.e("tag", e.getMessage());
            }
        }
    }
    private static void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }

        private static final String APP_SD_PATH = "/Android/data/com.smartplace.bombasmejorada";

        public static boolean fileExistsInSD(String sFileName){
            String sFolder = Environment.getExternalStorageDirectory().toString() +
                    APP_SD_PATH ;
            String sFile=sFolder+"/"+sFileName;
            java.io.File file = new java.io.File(sFile);
            return file.exists();
        }
}
}
