package com.example.resume.service;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.auth.Credentials;
import com.adobe.pdfservices.operation.exception.SdkException;
import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.exception.ServiceUsageException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.DocumentMergeOperation;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.DocumentMergeOptions;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.OutputFormat;
import com.example.resume.exception.UnauthorizedException;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.json.JSONObject;
public class AdobeGeneratePdfService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AdobeGeneratePdfService.class);
    public static void main(String json) {

        try {
            // Parse the JSON string
            JSONObject obj = new JSONObject(json);
            String template_id = obj.getString("template_id");

            // Reformat the JSON string
            json = ResumeService.reformatJson(json);

            String input_file = "src/main/resources/templates/Template" + template_id + ".docx";
            String output_file = "src/main/resources/output/resume.pdf";
            Files.deleteIfExists(Paths.get(output_file));

            JSONObject jsonDataForMerge = new JSONObject(json);



            System.out.println("About to generate a PDF based on " + input_file + "\n");
            String stringCredentials = new String(Files.readAllBytes(Paths.get("pdfservices-api-credentials.json")));
            JSONObject jsonCredentials = new JSONObject(stringCredentials);
            JSONObject clientCredentials = jsonCredentials.getJSONObject("client_credentials");
//

            Credentials credentials = Credentials.servicePrincipalCredentialsBuilder()
                    .withClientId(clientCredentials.getString("client_id"))
                    .withClientSecret(clientCredentials.getString("client_secret"))
                    .build();


            // Create an ExecutionContext using the credentials
            ExecutionContext executionContext = ExecutionContext.create(credentials);

            // Create DocumentMergeOptions with the JSON data and PDF as the output format
            DocumentMergeOptions documentMergeOptions = new DocumentMergeOptions(jsonDataForMerge, OutputFormat.PDF);

            // Create a DocumentMergeOperation with the merge options
            DocumentMergeOperation documentMergeOperation = DocumentMergeOperation.createNew(documentMergeOptions);

            // Provide the input file for the operation
            FileRef source = FileRef.createFromLocalFile(input_file);
            documentMergeOperation.setInput(source);

            // Execute the operation
            FileRef result = documentMergeOperation.execute(executionContext);

            // Save the result at the specified location
            result.saveAs(output_file);
            System.out.println("All Done");

        } catch (ServiceApiException | SdkException e) {
            throw new UnauthorizedException("unauthorized");
        } catch (IOException  | ServiceUsageException e) {
            LOGGER.error("Exception encountered while executing operation", e);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
