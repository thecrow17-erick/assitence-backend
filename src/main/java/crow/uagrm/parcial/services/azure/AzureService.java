package crow.uagrm.parcial.services.azure;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;

@Service
public class AzureService {
  
  @Autowired
  private BlobServiceClient blobServiceClient;


  @Value("${spring.cloud.azure.storage.blob.container-name}")
  private String containerName;

  public String uploadFile(String containerName, String blobName, MultipartFile file) throws IOException {
    try {
      BlobContainerClient containerClient = getBlobContainerClient(containerName);
      containerClient.getBlobClient(blobName).upload(new ByteArrayInputStream(file.getBytes()), file.getSize(), true);
      return "sucess upload image";
    } catch (Exception e) {
      return "failed upload";
    }
  }

  private BlobContainerClient getBlobContainerClient(String containerName) {
    return blobServiceClient.getBlobContainerClient(containerName);
  }

}
