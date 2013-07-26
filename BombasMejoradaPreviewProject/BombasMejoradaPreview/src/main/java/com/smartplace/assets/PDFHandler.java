package com.smartplace.assets;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Roberto on 11/07/13.
 */
public class PDFHandler {

    private String filePath;

    public PDFHandler(String filesPath)
    {
        this.filePath=filesPath;
    }
    public void OpenPDF(Context context,String filename)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(
                Uri.parse("file://" + filePath + filename),
                "application/pdf");

        context.startActivity(intent);
    }
    public void setFilesPath(String filesPath) {
        this.filePath = filesPath;
    }

    public String getFilesPath() {
        return this.filePath;
    }
}
