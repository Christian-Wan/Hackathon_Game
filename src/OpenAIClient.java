// OpenAIClient.java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import io.github.cdimascio.dotenv.Dotenv;

public class OpenAIClient {

    // Add your actual OpenAI API key here

    Dotenv dotenv = Dotenv.load();
    private final String API_URL = dotenv.get("API_URL");
    private final String API_KEY = dotenv.get("API_KEY"); // <-- Replace with your actual API key

    public String generateQuestions(String prompt) {
        HttpURLConnection connection = null;
        try {
            // Set up connection to OpenAI API
            URL url = new URL(API_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setDoOutput(true);

            // Create the request body
            JSONObject requestBody = new JSONObject();
            requestBody.put("model", "gpt-4o-mini"); // Specify the model
            requestBody.put("messages", new org.json.simple.JSONArray() {{
                add(new JSONObject() {{
                    put("role", "system");
                    put("content", "You are a game show host and have incredible knowledge about fruits, vegetables, grains, and meat" +
                            "Keep the question and answers short and concise" +
                            "The question should be formatted like this, " +
                            "Question\n" +
                            "A) AnswerChoice\n" +
                            "B) AnswerChoice\n" +
                            "C) AnswerChoice\n" +
                            "D) AnswerChoice [Letter of the Correct Answer] | Hint: HintOfTheAnswer / Explaination: ExplainationOfTheAnswer");

                }});
                add(new JSONObject() {{
                    put("role", "user");
                    put("content", prompt);
                }});
            }});

            // Send the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Check for successful response
            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                // Parse the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response
                JSONParser parser = new JSONParser();
                JSONObject jsonResponse = (JSONObject) parser.parse(response.toString());
                JSONObject choice = (JSONObject) ((org.json.simple.JSONArray) jsonResponse.get("choices")).get(0);
                JSONObject message = (JSONObject) choice.get("message");
                return (String) message.get("content");

            } else {
                // If the response code is not OK, print the error stream
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                StringBuilder errorResponse = new StringBuilder();
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    errorResponse.append(errorLine);
                }
                errorReader.close();

                return "Error: Response Code " + statusCode + " - " + errorResponse.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}