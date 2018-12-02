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
import org.codehaus.groovy.tools.javac.JavacCompilerFactory;
import sun.security.krb5.internal.rcache.AuthTimeWithHash;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class WorkingWithGoogleSheet {

    static JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
    static FileDataStoreFactory dataStoreFactory;
    static NetHttpTransport httpTransport;

    static{
        try {
            dataStoreFactory = new FileDataStoreFactory(new File(".credentials/workingWithGoogleSheetsAPI"));
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Credential credencial (NetHttpTransport httpTransport) throws IOException {
        InputStream inputStream = GoogleSheetPractice.class.getResourceAsStream("/credentials.json");
        InputStreamReader credencialLocation = new InputStreamReader(inputStream);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, credencialLocation);
        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow
                .Builder(httpTransport,jsonFactory,clientSecrets,scopes)
                .setDataStoreFactory(dataStoreFactory)
                .setAccessType("offline").build();
        LocalServerReceiver serverReceiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, serverReceiver).authorize("user");
        return credential;
    }

    public static Sheets getSheets(String appName) throws IOException {
        Credential getCredencial = credencial(httpTransport);
        Sheets sheet = new Sheets.Builder(httpTransport, jsonFactory, getCredencial)
                .setApplicationName(appName).build();
        return sheet;
    }


}
