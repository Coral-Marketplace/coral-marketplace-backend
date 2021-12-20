package app.web.coralmarketplace.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;

public interface AwsService {

    String saveFile(String folder, String fileName, MultipartFile file, String oldFileUrl)
            throws AmazonServiceException, SdkClientException, IOException;
}
