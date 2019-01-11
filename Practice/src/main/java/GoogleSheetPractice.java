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
//        LocalServerReceiver serverReceiver = new LocalServerReceiver.Builder().setPort(8888).build();
        LocalServerReceiver receiver = new LocalServerReceiver();
        // Credencial object
        Credential credincials = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        return credincials;
    }

    public static Sheets getSheetsService(String ApplicationName) throws IOException, GeneralSecurityException {
        Credential getCredential = authorize(httpTransport);
        Sheets sheets = new Sheets.Builder(httpTransport, jsonFactory, getCredential)
                .setApplicationName(ApplicationName)
                .build();
        return sheets;
    }

    //----------------------------------Writing values to sheet ---------------------------------
//    https://docs.google.com/spreadsheets/d/1MKR8pXipURJGeb9ZXSWVVO0JIJPK-QGQbR73DnfpYbY/edit#gid=0
    static Sheets sheetService;
    static String spreadSheetID = "150vm80GpL2msQS2HxdztlYQJnbCyNPrgEszh_lfKY7w";
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
                        Arrays.asList("Jan Total", "=B2+B3"))));
        data.add(new ValueRange().setRange("D4")
                .setValues(Arrays.asList(
                        Arrays.asList("Feb Total", "=B5+B6"))));
        BatchUpdateValuesRequest batchBody = new BatchUpdateValuesRequest()
                .setValueInputOption("User_entered").setData(data);
        BatchUpdateValuesResponse batchResult = sheetService.spreadsheets().values()
                .batchUpdate(spreadSheetID, batchBody).execute();
    }

//    ---------------------------------Append Data -----------------------------

    public static void AppendingData() throws IOException {
        ValueRange appendBody = new ValueRange()
                .setValues(Arrays.asList(Arrays.asList("Total", "=E1+E4")));
        AppendValuesResponse appendResult = sheetService.spreadsheets().values()
                .append(spreadSheetID, "A1", appendBody)
                .setValueInputOption("user_entered")
                .setInsertDataOption("insert_rows")
                .setIncludeValuesInResponse(true)
                .execute();


//            String total = String.valueOf(appendResult.getUpdates().getUpdatedData().getValues().get(0));
//            System.out.println(total);
    }

    //        -----------------------------------------Reading value from Sheet within multiple range---------
    public static void readingMultiRange() throws IOException {
        List<String> ranges = Arrays.asList("A1:E3", "A4:E6");
        BatchGetValuesResponse readResult = sheetService.spreadsheets().values()
                .batchGet(spreadSheetID)
                .setRanges(ranges)
                .execute();
        ValueRange JanTotal = readResult.getValueRanges().get(0);
        ValueRange FebTotal = readResult.getValueRanges().get(1);

//        System.out.println("Jan \n" + "Number of rows: " + JanTotal.size() + "\n" + "Number of columns: " + JanTotal.getValues().get(0).size());
//        System.out.println("Feb \n" + "Number of rows: " + FebTotal.size() + "\n" + "Number of columns: " + FebTotal.getValues().get(0).size());
        for (int i = 0; i < JanTotal.size(); i++) {
            for (int j = 0; j < JanTotal.getValues().get(i).size(); j++) {
                if (j < JanTotal.getValues().get(i).size() - 1) {
                    System.out.printf("%s \t", JanTotal.getValues().get(i).get(j));
                } else {
                    System.out.printf("%s \t \n", JanTotal.getValues().get(i).get(j));
                }
            }
        }

        for (int i = 0; i < FebTotal.size(); i++) {
            for (int j = 0; j < FebTotal.getValues().get(i).size(); j++) {
                if (j < FebTotal.getValues().get(i).size() - 1) {
                    System.out.printf("%s \t", FebTotal.getValues().get(i).get(j));
                } else {
                    System.out.printf("%s \t \n", FebTotal.getValues().get(i).get(j));
                }
            }
        }
    }

    //        -------------------------------------------Read data within single range ------------
    public static void readInRange() throws IOException, GeneralSecurityException {
        ValueRange valuesInSheet = sheetService.spreadsheets()
                .values().get(spreadSheetID, "A1:E10").execute();
        List<List<Object>> values = valuesInSheet.getValues();
//        System.out.println("number of rows: " + values.size() + " \n" + "number of columns: " + values.get(0).size());
        if (values == null || values.isEmpty()) {
            System.out.println("no data");
        } else {
            for (int i = 0; i < values.size(); i++) {
                for (int j = 0; j < values.get(i).size(); j++) {
                    if (j < values.get(i).size() - 1) {
                        System.out.printf("%s \t ", values.get(i).get(j));
                    } else {
                        System.out.printf("%s \t \n", values.get(i).get(j));
                    }
                }
            }
        }
    }

//    --------------------------------------------------New Spreadsheet------------------------

    public static void NewSheet() throws IOException, GeneralSecurityException {
        Spreadsheet spreadsheet = new Spreadsheet()
                .setProperties(new SpreadsheetProperties()
                        .setTitle("My SpreadSheet"));

        Spreadsheet result = sheetService.spreadsheets().create(spreadsheet).execute();
        System.out.println(result.getSpreadsheetId());
        System.out.println(result.getSpreadsheetUrl());

    }

//    -----------------------------------------update - change Sheet Name-------------------

    public static void UpdatingSheet() throws IOException {
        List<Request> requests = new ArrayList<>();

//        Rename SpreadSheet
        requests.add(new Request()
                .setUpdateSpreadsheetProperties(new UpdateSpreadsheetPropertiesRequest()
                        .setProperties(new SpreadsheetProperties()
                                .setTitle("Selenim Expenses"))
                        .setFields("title")));
//        Add new Sheet to spreadsheet
        requests.add(new Request()
                .setAddSheet(new AddSheetRequest()
                        .setProperties(new SheetProperties()
                                .setSheetId(1).setTitle("expense"))));

//        Rename sheet

        requests.add(new Request()
                .setUpdateSheetProperties(new UpdateSheetPropertiesRequest()
                        .setProperties(new SheetProperties().setSheetId(1)
                                .setTitle("Hello")).setFields("title")));

//        Rename Field
        requests.add(new Request().setFindReplace(new FindReplaceRequest()
                .setFind("Total").setReplacement("New Total")
                .setAllSheets(true)));

//        copy paste
        CopyPasteRequest copyPasteRequest = new CopyPasteRequest()
                .setSource(new GridRange().setSheetId(0)
                        .setStartColumnIndex(0).setEndColumnIndex(5)
                        .setStartRowIndex(0).setEndRowIndex(4))
                .setDestination(new GridRange().setSheetId(1)
                        .setStartColumnIndex(4).setEndColumnIndex(9)
                        .setStartRowIndex(4).setEndRowIndex(8))
                .setPasteType("paste_values");
        requests.add(new Request().setCopyPaste(copyPasteRequest));

        BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest()
                .setRequests(requests);
        BatchUpdateSpreadsheetResponse response = sheetService.spreadsheets()
                .batchUpdate(spreadSheetID, body).execute();
        FindReplaceResponse findReplaceResponse = response.getReplies()
                .get(1).getFindReplace();
    }

//    -----------------------------------------spreadsheet name --------------

    public static void getSpreadsheetName() throws IOException {

        Spreadsheet spreadsheet = sheetService.spreadsheets().get(spreadSheetID).setIncludeGridData(false).execute();

        System.out.println(spreadsheet.getProperties().getTitle());
        System.out.println(spreadsheet.getSheets().get(0).getProperties().getTitle());
        System.out.println(spreadsheet.getSheets().size());
    }

    //----------------------------------------implement-------------------------------
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        setup();

//        SingleRangeWrite();
//        BatchUpdate();
//        AppendingData();
//
//        readingMultiRange();
//        readInRange();

//        NewSheet();

//        getSpreadsheetName();

        UpdatingSheet();
    }
}

