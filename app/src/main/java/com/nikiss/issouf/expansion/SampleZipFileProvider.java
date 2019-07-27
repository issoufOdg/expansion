package com.nikiss.issouf.expansion;

import android.net.Uri;
import com.android.vending.expansion.zipfile.APEZProvider;

public class SampleZipFileProvider extends APEZProvider {
    // main content provider URI
    private static final String CONTENT_PREFIX = "content://";

    // must match what is declared in the Zip content provider in
    // the AndroidManifest.xml file
    private static final String AUTHORITY = "com.nikiss.issouf.expansion.SampleZipFileProvider";

    public static final Uri ASSET_URI = Uri.parse(CONTENT_PREFIX + AUTHORITY);

    @Override
    public String getAuthority() {
        return AUTHORITY;
    }
}
