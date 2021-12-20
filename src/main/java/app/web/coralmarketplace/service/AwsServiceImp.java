package app.web.coralmarketplace.service;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class AwsServiceImp implements AwsService {

    @Value("${aws.accesskey}")
    private String accessKey;

    @Value("${aws.secretkey}")
    private String secretKey;

    @Value("${aws.bucket}")
    private String bucketName;

    private AmazonS3Client s3client;

    @PostConstruct
    private void init() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        s3client = (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_WEST_1).build();
    }

    @Override
    public String saveFile(String folder, String fileName, MultipartFile file, String oldFileUrl)
            throws AmazonServiceException, SdkClientException, IOException {
        fileName += getExtension(file.getOriginalFilename());

        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(file.getContentType());
        data.setContentLength(file.getSize());
        s3client.putObject(bucketName + "/" + folder, fileName, file.getInputStream(), data);

        String oldFileName = getFilenameFromUrl(oldFileUrl);
        if (!oldFileName.equals("") && !oldFileName.equals(fileName)) {
            s3client.deleteObject(new DeleteObjectRequest(bucketName + "/" + folder, oldFileName));
        }

        return s3client.getResourceUrl(bucketName + "/" + folder, fileName);
    }

    private String getExtension(String filename) {
        Optional<String> extension = Optional.ofNullable(filename).filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".")));

        return extension.isPresent() ? extension.get() : "";
    }

    private String getFilenameFromUrl(String url) {
        Optional<String> filename = Optional.ofNullable(url).filter(f -> f.contains("/"))
                .map(f -> f.substring(url.lastIndexOf("/") + 1));

        return filename.isPresent() ? filename.get() : "";
    }

}
