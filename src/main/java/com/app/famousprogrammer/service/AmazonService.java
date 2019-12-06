package com.app.famousprogrammer.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.app.famousprogrammer.exceptions.MyException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AmazonService {

    private AmazonS3 amazonS3;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @Value("${amazonProperties.folderName}")
    private String folderName;

    @Value("${amazonProperties.fileUrl}")
    private String fileUrl;

    @PostConstruct
    public void initialize(){

        AWSCredentials credentials = new BasicAWSCredentials( accessKey, secretKey );

        amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();

        log.info("BUCKETS: {}", getAllBuckets());
        log.info("FILES: {}", getAllFiles());

    }

    public String uploadFile(MultipartFile multipartFile, String filename) {

        try {

            if ( multipartFile.getBytes().length == 0 ) {
                return filename;
            }

            String newFilename = generateFilename(multipartFile);
            File file = convertMultipartFileToFile(multipartFile);
            amazonS3.putObject( bucketName, folderName + "/" + newFilename, file );
            if ( !deleteTempFile(file) ) {
                log.warn("temp file wasn't removed");
            }
            return fileUrl + newFilename;
        } catch ( Exception e ) {
            e.printStackTrace();
            throw new MyException("upload file exception: " + e.getMessage());
        }
    }

    public String downloadFile( String filename ) {

        if ( filename == null ) {
            throw new MyException("FILENAME STRING IS NULL");
        }

        try {

            S3Object s3Object = amazonS3.getObject(bucketName, folderName + "/" + filename);
            S3ObjectInputStream inputStream = s3Object.getObjectContent();
            FileUtils.copyInputStreamToFile(inputStream, new File(s3Object.getKey()));
            return s3Object.getKey();

        } catch ( Exception e ) {
            throw new MyException("download file exception: " + e.getMessage());
        }
    }

    public String deleteFile( String filename ) {

        System.out.println(filename);
        if ( filename == null ) {
            throw new MyException("FILENAME STRING IS NULL");
        }

        try {
            String[] elements = filename.split("/files/");
            amazonS3.deleteObject(bucketName, folderName + "/" + elements[elements.length - 1]);
            return filename;

        } catch ( Exception e ) {
            throw new MyException("DELETE FILE EXCEPTION: " + e.getMessage());
        }

    }

    private String getAllBuckets() {

        return amazonS3
                .listBuckets()
                .stream()
                .map(Bucket::getName)
                .collect(Collectors.joining(", "));

    }

    private String getAllFiles() {
        return amazonS3
                .listObjects(bucketName)
                .getObjectSummaries()
                .stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.joining(", "));

    }

    private boolean deleteTempFile( File file ) {
        if ( file == null ) {
            throw new MyException("FILE OBJECT IS NULL");
        }
        return file.delete();
    }

    private File convertMultipartFileToFile( MultipartFile multipartFile ) {
        try {
            File file = new File( multipartFile.getOriginalFilename() );
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
            return file;
        } catch ( Exception e ) {
            throw new MyException("CONVERT MULTIPART FILE TO FILE EXCEPTION");
        }
    }

    private String generateFilename( MultipartFile file ) {

        if ( !file.getOriginalFilename().contains(".") ) {
            throw new MyException("incorrect filename");
        }

        String extension = file.getOriginalFilename().split("\\.")[1];
        String filenamePart1 = UUID.randomUUID().toString().replaceAll("\\W", "");
        String filenamePart2 = String.valueOf(System.nanoTime());
        return filenamePart1 + "-" + filenamePart2 + "." + extension;
    }

}
