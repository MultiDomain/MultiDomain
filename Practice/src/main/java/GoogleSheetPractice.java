import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class    GoogleSheetPractice {

    static final String appName = "Practicing Google Sheet";
    static final JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
    static final String dirPathToken = "tokens";

    static final List<String> scopes = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    static final String userJsonCredential = "/credentials.json";

    static Credential getCredential(final NetHttpTransport httpTransport) throws IOException {
        InputStream in = GoogleSheetPractice.class.getResourceAsStream(userJsonCredential);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientSecrets, scopes).setDataStoreFactory(new FileDataStoreFactory(new File(dirPathToken))).setAccessType("offline").build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadsheetID = "";
        final String range = "Class Data!A2:E";
        Sheets service = new Sheets.Builder(httpTransport, jsonFactory,getCredential())
    }

}
