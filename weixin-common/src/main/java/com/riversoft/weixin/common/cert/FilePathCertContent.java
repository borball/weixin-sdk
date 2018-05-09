package com.riversoft.weixin.common.cert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class FilePathCertContent implements CertContent{
    private String filePath;

    public FilePathCertContent(String file){
        this.filePath = filePath;
    }
    @Override
    public void load(KeyStore keyStore, String certPassword) throws CertificateException, NoSuchAlgorithmException, IOException {
        FileInputStream inputStream = new FileInputStream(new File(filePath));
        keyStore.load(inputStream, certPassword.toCharArray());
    }
}
