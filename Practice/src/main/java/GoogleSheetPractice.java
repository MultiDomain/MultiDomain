import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import jdk.internal.util.xml.impl.Input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GoogleSheetPractice {

    static JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
    static FileDataStoreFactory dataStoreFactory;
    static NetHttpTransport httpTransport;

    static {
        try {
            dataStoreFactory = new FileDataStoreFactory(new File(".credentials/sheets-googleapis"));
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public static Credential authorize(NetHttpTransport httpTransport) throws IOException, GeneralSecurityException {
        //Google client secrets
        InputStream inputStream = GoogleSheetPractice.class.getResourceAsStream("/credentials.json");
        InputStreamReader credincialLocation = new InputStreamReader(inputStream);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, credincialLocation);
//        GoogleClientSecrets secrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(GoogleSheetPractice.class.getResourceAsStream("")))

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, scopes)
                .setDataStoreFactory(dataStoreFactory)
                .setAccessType("offline").build();
        LocalServerReceiver serverReceiver = new LocalServerReceiver.Builder().setPort(8888).build();
        LocalServerReceiver receiver = new LocalServerReceiver();
        // Credencial object
        Credential credincials = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        return credincials;
    }

    public static Sheets getSheetsService(String ApplicationName) throws IOException, GeneralSecurityException {
        Credential getCredential = authorize(httpTransport);
        Sheets sheets = new Sheets.Builder(httpTransport, jsonFactory, getCredential).setApplicationName(ApplicationName).build();
        return sheets;
    }
//----------------------------------Writing values to sheet ---------------------------------
//    https://docs.google.com/spreadsheets/d/1MKR8pXipURJGeb9ZXSWVVO0JIJPK-QGQbR73DnfpYbY/edit#gid=0
    static Sheets sheetService;
    static String spreadsheetID = "1MKR8pXipURJGeb9ZXSWVVO0JIJPK-QGQbR73DnfpYbY";
    static String ApplicationName = "Google Sheet Example";
    static void setup() throws IOException, GeneralSecurityException {
        sheetService = getSheetsService(ApplicationName);
    }
    public static void SingleRangeWrite() throws IOException, GeneralSecurityException {
        ValueRange range = new ValueRange().setValues(Arrays.asList(
                Arrays.asList("Expenses January"),
                Arrays.asList("books", "30"),
                Arrays.asList("pens", "10"),
                Arrays.asList("Expenses February"),
                Arrays.asList("cloths", "20"),
                Arrays.asList("shoes", "5")
        ));
        UpdateValuesResponse result = sheetService.spreadsheets().values()
                .update(spreadsheetID, "A1", range).setValueInputOption("RAW")
                .execute();
    }




    public static void main(String[] args) throws IOException, GeneralSecurityException {
        setup();
        SingleRangeWrite();
    }
}

