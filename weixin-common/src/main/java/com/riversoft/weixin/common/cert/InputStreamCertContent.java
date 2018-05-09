package com.riversoft.weixin.common.cert;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class InputStreamCertContent implements CertContent{
    private InputStream inputStream;

    public InputStreamCertContent(InputStream inputStream){

        this.inputStream = inputStream;
    }
    @Override
    public void load(KeyStore keyStore, String certPassword) throws CertificateException, NoSuchAlgorithmException, IOException {
        keyStore.load(inputStream, certPassword.toCharArray());
    }
}
