package com.riversoft.weixin.common.cert;

import java.io.IOException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public interface CertContent {
    void load(KeyStore keyStore, String certPassword) throws CertificateException, NoSuchAlgorithmException, IOException;
}
