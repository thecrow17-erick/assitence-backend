package crow.uagrm.parcial.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

@Configuration
public class AzureConfig {

  @Value("${spring.cloud.azure.storage.blob.container-name}")
  private String containerName;

  @Value("${spring.cloud.azure.storage.connection-string}")
  private String connectionString;


  @Bean
  public BlobServiceClient blobServiceClient(){
    BlobServiceClient serviceClient = new BlobServiceClientBuilder()
      .connectionString(this.connectionString).buildClient();
    return serviceClient;
  }

  @Bean
  public BlobContainerClient blobContainerClient(){
    BlobContainerClient blobContainerClient = this.blobServiceClient()
      .getBlobContainerClient(this.containerName);
  
    return blobContainerClient;
  }
}
