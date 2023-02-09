package com.deloitte.publish.sharepoint;

import com.deloitte.publish.Publisher;

import com.example.FunctionInput;

/*import com.azure.identity.UsernamePasswordCredential;
//import com.azure.identity.UsernamePasswordCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.*;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.tasks.IProgressCallback;
import com.microsoft.graph.tasks.LargeFileUploadTask;
import java.util.Arrays;
import java.util.List;
import com.deloitte.publish.PublisherException;
*/

import java.io.File;


public class SharePointPublisher implements Publisher {
    private String clientId; //provided by the creator of the developer app
    private String userName; //can be provided by the admin or a personal can be used
    private String userPassword;  //can be provided by the admin or a personal can be used
    private String sharepointFolder = "test/";

    public SharePointPublisher(FunctionInput functionInput) {
        clientId = functionInput.getGraphClientId();
        userName = functionInput.getGraphUserName();
        userPassword = functionInput.getGraphUserPassword();
        if(functionInput.getSharepointFolder() != null) {
            sharepointFolder = functionInput.getSharepointFolder();
        }
    }

    @Override
    public void send(File file) {
        if(clientId == null || userName == null || userPassword == null) {
            System.out.println("SharePointPublisher not configured...");
            return;
        }
        System.out.println("Start sending to sharepoint...");
        //scopes coming from the app created on portal.azure.com
        
        // error code
       
        /* 
        //scopes coming from the app created on portal.azure.com
        List<String> scopes = Arrays.asList("User.Read", "Files.ReadWrite");

        //use can use ClientSecretCredential/ClientSecretCredentialBuilder if client secret is the credential given
        final UsernamePasswordCredential usernamePasswordCredential = new UsernamePasswordCredentialBuilder()
                .clientId(clientId)
                .username(userName)
                .password(userPassword)
                .build();

        final TokenCredentialAuthProvider tokenCredentialAuthProvider = new TokenCredentialAuthProvider(scopes,
                usernamePasswordCredential);

        System.out.println("Initializing graph client...");
        final GraphServiceClient graphClient =
                GraphServiceClient
                        .builder()
                        .authenticationProvider(tokenCredentialAuthProvider)
                        .buildClient();

        DriveItemCreateUploadSessionParameterSet uploadParams =
                DriveItemCreateUploadSessionParameterSet.newBuilder()
                        .withItem(new DriveItemUploadableProperties()).build();

        String fileName = file.getName();

        System.out.println("Creating upload session...");
        //location on sharepoint#onedrive
        String sharepoint = sharepointFolder;
        if(!sharepoint.endsWith("/")) {
            sharepoint += "/";
        }
        sharepoint += fileName;
        // Create an upload session
        UploadSession uploadSession = graphClient
                .me()
                .drive()
                .root()
                // Set the path to sharepoint folder
                .itemWithPath(sharepoint)
                .createUploadSession(uploadParams)
                .buildRequest()
                .post();

        // Get an input stream for the file
        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new PublisherException("File not found", e);
        }
        // Do the upload
        try {
            long streamSize = file.length();

            // Create a callback used by the upload provider
            IProgressCallback callback = new IProgressCallback() {
                @Override
                // Called after each slice of the file is uploaded
                public void progress(final long current, final long max) {
                    System.out.println(String.format("Uploaded %d bytes of %d total bytes", current, max)
                    );
                }
            };
            LargeFileUploadTask<DriveItem> largeFileUploadTask =
                    new LargeFileUploadTask<>
                            (uploadSession, graphClient, fileStream, streamSize, DriveItem.class);

            System.out.println("Starting the upload...");
            //push the stream to sharepoint
            largeFileUploadTask.upload(0, null, callback);

            System.out.println("File " + file.getAbsolutePath() + " uploaded to " + sharepoint);

        } catch (IOException e) {
            e.printStackTrace();
            throw new PublisherException("Unable to upload file : " + file.getAbsolutePath(), e);
        }
    */
        
        
    }
}

