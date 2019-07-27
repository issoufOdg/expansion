/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//package com.example.expansion.downloader;
package com.nikiss.issouf.expansion;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;

/**
 * This class demonstrates the minimal client implementation of the
 * DownloaderService from the Downloader library.
 */
public class SampleDownloaderService extends DownloaderService {
    // Votre clé publique fournie par Google Play
    private static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAufDPXz0aGRfKh9x+mfKuVdmhP6VQ4fpp3i/8/DkfVv6wvWQ0UHAfLZHcOwB38yc3why0OfCLiTTFQRJlt5Qr+8mb+N4sPkEAiMTiLhvIG43dY9u65mrUpDf3Ipr2De0WbCs7/0uiKX/lbTa1Plx2ygccSjzPoEttRgpXVOgYQOr1TzoFuZg2IIoFnWpaeQKlrGJjDucm9fNL1HIn9VvL3SFPJ5R4K48XjHvmR7kXofL9g5z5i0VQeBX3IicSbxj5JAsQGj03Yrl2dFvUN+8fUyvqlmV3QfpHTvG9NKbhQdktfKJ8b9BMLMGWP5r6snzutUo8f5kzii1rCMza4CV98wIDAQAB";
    // Vous devez également modifier ces chiffres, ils doivent être compris entre -128 et +127
    private static final byte[] SALT = new byte[] {
            1, 43, -12, -1, 54, 98,
            -100, -12, 43, 2, -8, -4, 9, 5, -106, -108, -33, 45, -1, 84
    };

    /**
     * This public key comes from your Android Market publisher account, and it
     * used by the LVL to validate responses from Market on your behalf.
     */
    @Override
    public String getPublicKey() {
        return BASE64_PUBLIC_KEY;
    }

    /**
     * This is used by the preference obfuscater to make sure that your
     * obfuscated preferences are different than the ones used by other
     * applications.
     */
    @Override
    public byte[] getSALT() {
        return SALT;
    }

    /**
     * Fill this in with the class name for your alarm receiver. We do this
     * because receivers must be unique across all of Android (it's a good idea
     * to make sure that your receiver is in your unique package)
     */
    @Override
    public String getAlarmReceiverClassName() {
        return SampleAlarmReceiver.class.getName();
    }

}
