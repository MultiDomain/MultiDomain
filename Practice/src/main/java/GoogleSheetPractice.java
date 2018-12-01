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
import com.google.api.services.sheets.v4.model.*;
import jdk.internal.util.xml.impl.Input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class    GoogleSheetPractice {

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
    static String spreadSheetID = "1MKR8pXipURJGeb9ZXSWVVO0JIJPK-QGQbR73DnfpYbY";
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
                .update(spreadSheetID, "A1", range).setValueInputOption("RAW")
                .execute();
    }

//    --------------------------MiltiRange Writes------------------------------

    public static void BatchUpdate() throws IOException {
        List<ValueRange> data = new ArrayList<>();
        data.add(new ValueRange().setRange("D1")
                .setValues(Arrays.asList(
                        Arrays.asList("Jan Total","=B2+B3"))));
        data.add(new ValueRange().setRange("D4")
                .setValues(Arrays.asList(
                        Arrays.asList("Feb Total","=B5+B6"))));
        BatchUpdateValuesRequest batchBody = new BatchUpdateValuesRequest()
                .setValueInputOption("User_entered").setData(data);
        BatchUpdateValuesResponse batchResult = sheetService.spreadsheets().values()
                .batchUpdate(spreadSheetID,batchBody).execute();
    }

//    ---------------------------------Append Data -----------------------------

        public static void AppendingData () throws IOException {
            ValueRange appendBody = new ValueRange()
                .setValues(Arrays.asList(Arrays.asList("Total","=E1+E4")));
            AppendValuesResponse appendResult = sheetService.spreadsheets().values()
                    .append(spreadSheetID,"A1",appendBody)
                    .setValueInputOption("user_entered")
                    .setInsertDataOption("insert_rows")
                    .setIncludeValuesInResponse(true)
                    .execute();


//            String total = String.valueOf(appendResult.getUpdates().getUpdatedData().getValues().get(0));
//            System.out.println(total);
        }

//        -----------------------------------------Readingvalue from Sheet ---------
    public static void ReadValuesFormSheet() throws IOException, GeneralSecurityException {
        List<String> ranges = Arrays.asList("E1","E4");

        BatchGetValuesResponse readResult = sheetService.spreadsheets()
                .values().batchGet(spreadSheetID)
                .setRanges(ranges).execute();

        ValueRange JanTotal = readResult.getValueRanges().get(0);
        ValueRange FebTotal = readResult.getValueRanges().get(1);
//        ValueRange vr = sheetService.spreadsheets().values().get(spreadSheetID,"A1:F10").execute();
        List<List<Object>> value = new Sheets.Builder(httpTransport, jsonFactory, authorize(httpTransport)).setApplicationName(ApplicationName).build().spreadsheets().values().get(spreadSheetID, "A1:F8").execute().getValues();

        if (value == null || value.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("Name \t Major");
            for (List row : value) {
                // Print columns A and E, which correspond to indices 0 and 4.
                System.out.printf("%s \t %s\n", row.get(0), row.get(5));
            }
        }
//        System.out.println(JanTotal +"\n"+ FebTotal);
        System.out.println(JanTotal.getValues().get(0).get(0));
        System.out.println(FebTotal.getValues().get(0).get(0));

    }
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        setup();
        SingleRangeWrite();
        BatchUpdate();
        AppendingData();
        ReadValuesFormSheet();
    }
}
