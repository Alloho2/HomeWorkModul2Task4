package com.alloho.homeworkmodul2task4;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private String text = "У лукоморья дуб зелёный;\n" +
            "Златая цепь на дубе том:\n" +
            "И днём и ночью кот учёный\n" +
            "Всё ходит по цепи кругом;\n" +
            "Идёт направо — песнь заводит,\n" +
            "Налево — сказку говорит.\n" +
            "Там чудеса: там леший бродит,\n" +
            "Русалка на ветвях сидит;\n" +
            "Там на неведомых дорожках\n" +
            "Следы невиданных зверей;\n" +
            "Избушка там на курьих ножках\n" +
            "Стоит без окон, без дверей;\n" +
            "Там лес и дол видений полны;\n" +
            "Там о заре прихлынут волны\n" +
            "На брег песчаный и пустой,\n" +
            "И тридцать витязей прекрасных\n" +
            "Чредой из вод выходят ясных,\n" +
            "И с ними дядька их морской;";

    LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
    private TextView sumCharInLine;
    private TextView sumWordsInLine;
    private TextView sumWordsInText;
    private TextView sumCharInText;
    private TextView wordsFiveChars;
    private TextView wordsGlue;


    private void textInLine(String st) {

        try (BufferedReader reader = new BufferedReader(new StringReader(st))) {
            String line;
            int count = 0;

            while ((line = reader.readLine()) != null) {
                count++;
                hashMap.put("Строка " + count, line);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String sumCharInLine() {
        return hashMap.entrySet().stream().map(e -> e.getKey() + " содержит символов : " + e.getValue().length())
                .collect(Collectors.joining(", \n"));
    }

    private String sumWordsInLine() {
        return hashMap.entrySet().stream().map(e -> e.getKey() + " содержит слов : " + e.getValue().split("[\\W\\d\\s]+").length)
                .collect(Collectors.joining(", \n"));

    }

    private String sumWordsInText(String st) {
        return "Кол-во слов в тексте равно " + st.split("[\\W\\d\\s]+").length;

    }

    private String sumCharInText(String st) {
        int count = 0;
        char s = 'л';

        for (int i = 0; i < st.length(); i++) {
            if (st.charAt(i) == s) {
                count++;
            }
        }
        return "Кол-во букв л в тексте равно: " + count;
    }

    private String wordsFiveChars(String st) {
        return "Кол-во слов с 5 буквами равно: " + Arrays.stream(st.split("[\\W\\d\\s]+"))
                .filter(i -> i.matches("\\w{5}")).count();


    }

    private String wordsGlue(String st) {
        return "Строка из слов на т:\n " + Arrays.stream(st.toLowerCase(Locale.ROOT).split("[\\W\\d\\s]+"))
                .filter(i -> i.startsWith("т"))
                .collect(Collectors.joining(" "));


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        textInLine(text);
        setContentView(R.layout.main_activity);

        sumCharInLine = findViewById(R.id.sumCharInLine);
        sumCharInLine.setText(sumCharInLine());

        sumWordsInLine = findViewById(R.id.sumWordsInLine);
        sumWordsInLine.setText(sumWordsInLine());

        sumCharInText = findViewById(R.id.sumCharInText);
        sumCharInText.setText(sumCharInText(text));

        sumWordsInText = findViewById(R.id.sumWordsInText);
        sumWordsInText.setText(sumWordsInText(text));

        wordsFiveChars = findViewById(R.id.wordsFiveChars);
        wordsFiveChars.setText(wordsFiveChars(text));

        wordsGlue = findViewById(R.id.wordsGlue);
        wordsGlue.setText(wordsGlue(text));


        super.onCreate(savedInstanceState);
    }
}
