import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class googleSheetAPI {

    Credential credential() throws IOException {
      JacksonFactory jsonfactory = JacksonFactory.getDefaultInstance();

        List<String> scope = Arrays.asList(SheetsScopes.);
        try {
          HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

      } catch (GeneralSecurityException e) {
          e.printStackTrace();
      }
        InputStream in = GoogleSheetreader.class.getResourceAsStream("/home/sun/Downloads/client_secret.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonfactory, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpAuthentications, jsonfactory, clientSecrets)
    }
}

class GoogleSheetreader{

        }